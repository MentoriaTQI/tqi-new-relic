package br.com.tqi.dio.repository;

import br.com.tqi.dio.domain.Address;
import br.com.tqi.dio.domain.City;
import br.com.tqi.dio.domain.District;
import br.com.tqi.dio.domain.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByPostalCodeAndCityAndDistrictAndUf(String postalCode, City city, District district, Uf uf);

}
