package com.example.Odontoprev_Java.Repository;

import com.example.Odontoprev_Java.Model.Carteirinha;
import com.example.Odontoprev_Java.Model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteirinhaRepository extends JpaRepository<Carteirinha, Long> {
}
