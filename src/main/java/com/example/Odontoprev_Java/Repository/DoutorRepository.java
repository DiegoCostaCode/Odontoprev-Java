package com.example.Odontoprev_Java.Repository;

import com.example.Odontoprev_Java.Model.Clinica;
import com.example.Odontoprev_Java.Model.Doutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoutorRepository extends JpaRepository<Doutor, Long> {
}
