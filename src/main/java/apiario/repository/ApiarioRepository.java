package apiario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import apiario.model.Apiario;

@EnableJpaRepositories
public interface ApiarioRepository extends JpaRepository<Apiario, Integer> {

    List<Apiario> findAllByOrderByIdApiarioDesc();

}
