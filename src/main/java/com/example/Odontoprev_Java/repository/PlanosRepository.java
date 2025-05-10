package com.example.Odontoprev_Java.repository;

import com.example.Odontoprev_Java.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanosRepository extends JpaRepository<Plano, Long> {

    List<Plano> findByStatus(String ativo);
}
