package com.example.Odontoprev_Java.Repository;

import com.example.Odontoprev_Java.Model.Procedimento.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

}
