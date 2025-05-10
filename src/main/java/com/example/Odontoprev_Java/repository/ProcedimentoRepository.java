package com.example.Odontoprev_Java.repository;

import com.example.Odontoprev_Java.model.procedimento.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

    public List<Procedimento> findByStatus(String status);

}
