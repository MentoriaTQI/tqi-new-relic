package br.com.tqi.dio.repository;

import br.com.tqi.dio.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
