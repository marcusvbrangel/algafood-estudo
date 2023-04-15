package com.mvbr.algafood.domain.repository;

import com.mvbr.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

}
