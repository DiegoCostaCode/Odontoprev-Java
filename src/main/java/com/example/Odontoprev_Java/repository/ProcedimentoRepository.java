package com.example.Odontoprev_Java.repository;

import com.example.Odontoprev_Java.Model.procedimento.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

}
