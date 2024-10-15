package com.example.Odontoprev_Java.Repository;

import com.example.Odontoprev_Java.Model.Carteirinha;
import com.example.Odontoprev_Java.Model.ClinicaDoutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicaDoutorRepository extends JpaRepository<ClinicaDoutor, Long> {
}
