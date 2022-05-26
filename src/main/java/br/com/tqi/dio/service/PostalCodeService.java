package br.com.tqi.dio.service;

import br.com.tqi.dio.domain.Address;
import br.com.tqi.dio.domain.City;
import br.com.tqi.dio.domain.District;
import br.com.tqi.dio.domain.Uf;
import br.com.tqi.dio.dto.PostalCodeDTO;
import br.com.tqi.dio.exception.PostalCodeNotFoundException;
import br.com.tqi.dio.repository.AddressRepository;
import br.com.tqi.dio.repository.CityRepository;
import br.com.tqi.dio.repository.DistrictRepository;
import br.com.tqi.dio.repository.UfRepository;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
@RequiredArgsConstructor
public class PostalCodeService {

    private final UfRepository ufRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final AddressRepository addressRepository;

    @Trace(dispatcher = true)
    public void saveAddressDataFromQueue(PostalCodeDTO message) {
        message.setOrigin("QUEUE");
        saveAddressData(message);
    }

    public void saveAddressDataFromRest(PostalCodeDTO message) {
        message.setOrigin("REST");
        saveAddressData(message);
    }

    private void saveAddressData(PostalCodeDTO message) {
        addCustomerParameters(message);
        District district = districtRepository.findById(message.getDistrict())
                .orElse(District.builder()
                        .id(message.getDistrict())
                        .build());
        City city = cityRepository.findById(message.getCity())
                .orElse(City.builder()
                        .id(message.getCity())
                        .build());
        Uf uf = ufRepository.findById(message.getState())
                .orElse(Uf.builder()
                        .id(message.getState())
                        .build());
        districtRepository.save(district);
        cityRepository.save(city);
        ufRepository.save(uf);
        addressRepository.findByPostalCodeAndCityAndDistrictAndUf(message.getPostalCode(), city, district, uf)
                .ifPresentOrElse(address -> {
                    addressRepository.save(address);
                    NewRelic.incrementCounter("record-updated");
                    log.info("Record was updated={}", address.getPostalCode());
                }, () -> {
                    addressRepository.save(Address.builder()
                            .description(message.getAddress())
                            .postalCode(message.getPostalCode())
                            .district(district)
                            .city(city)
                            .uf(uf)
                            .build());
                    log.info("Address was insert={}", message.getPostalCode());
                    NewRelic.incrementCounter("record-created");
                });
    }

    public Address findByPostalCode(String postalCode) {
        return addressRepository.findByPostalCode(postalCode)
                .orElseThrow(() -> new PostalCodeNotFoundException(postalCode));
    }

    private void addCustomerParameters(PostalCodeDTO message) {
        Map<String, Object> params = new HashMap<>();
        params.put("postalCode", message.getPostalCode());
        params.put("origin", message.getOrigin());
        NewRelic.addCustomParameters(params);
    }

}
