package br.com.tqi.dio.repository;

import br.com.tqi.dio.domain.Uf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UfRepository extends JpaRepository<Uf, String> {

//    Optional<Uf> findByDescription(String description);

}
