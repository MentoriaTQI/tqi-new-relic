package br.com.tqi.dio.service;

import br.com.tqi.dio.domain.Address;
import br.com.tqi.dio.domain.City;
import br.com.tqi.dio.domain.District;
import br.com.tqi.dio.domain.Uf;
import br.com.tqi.dio.dto.ImportPostalCodeDTO;
import br.com.tqi.dio.repository.AddressRepository;
import br.com.tqi.dio.repository.CityRepository;
import br.com.tqi.dio.repository.DistrictRepository;
import br.com.tqi.dio.repository.UfRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class PostalCodeService {

    private final UfRepository ufRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final AddressRepository addressRepository;

    public void saveAll(ImportPostalCodeDTO message) {
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
                    log.warn("Address was exists={}", address.getPostalCode());
                }, () -> {
                    addressRepository.save(Address.builder()
                            .description(message.getAddress())
                            .postalCode(message.getPostalCode())
                            .district(district)
                            .city(city)
                            .uf(uf)
                            .build());
                    log.info("All data was insert={}", message.getPostalCode());
                });
    }

}
