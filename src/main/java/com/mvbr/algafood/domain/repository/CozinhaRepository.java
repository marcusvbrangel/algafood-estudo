package com.mvbr.algafood.domain.repository;

import com.mvbr.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    List<Cozinha> nome(String nome);  // Note: this works fine...

//    List<Cozinha> findByNome(String nome);  // Note: this works fine...

    List<Cozinha> findQualquerCoisaByNome(String nome);  // Note: this works fine...

    List<Cozinha> findTodasByNome(String nome);  // Note: this works fine...

    Optional<Cozinha> findByNome(String nome);  // Note: this works fine...

    List<Cozinha> findByNomeContaining(String nome);

    List<Cozinha> findTodasByNomeContaining(String nome);

    boolean existsByNome(String nome);

}
