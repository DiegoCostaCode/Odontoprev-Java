package com.example.Odontoprev_Java.repository;

import com.example.Odontoprev_Java.Model.Planos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanosRepository extends JpaRepository<Planos, Long> {
}
