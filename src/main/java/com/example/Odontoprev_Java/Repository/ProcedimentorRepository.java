package com.example.Odontoprev_Java.Repository;

import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Model.Procedimento.Prodecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedimentorRepository extends JpaRepository<Prodecimento, Long> {
}
