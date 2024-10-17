package com.example.Odontoprev_Java.Repository;


import com.example.Odontoprev_Java.Model.Endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
