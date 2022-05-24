package br.com.tqi.dio.repository;

import br.com.tqi.dio.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
