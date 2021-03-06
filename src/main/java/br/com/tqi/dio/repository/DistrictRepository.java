package br.com.tqi.dio.repository;

import br.com.tqi.dio.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {

}
