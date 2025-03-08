package com.example.Odontoprev_Java.repository;

import com.example.Odontoprev_Java.Model.DentistasClinicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistasClinicasRepository extends JpaRepository<DentistasClinicas, Long> {
}
