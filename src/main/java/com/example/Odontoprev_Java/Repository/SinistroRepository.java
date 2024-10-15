package com.example.Odontoprev_Java.Repository;

import com.example.Odontoprev_Java.Model.Plano;
import com.example.Odontoprev_Java.Model.Sinistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinistroRepository extends JpaRepository<Sinistro, Long> {
}
