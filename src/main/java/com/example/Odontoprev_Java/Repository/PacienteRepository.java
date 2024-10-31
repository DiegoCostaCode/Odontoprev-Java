package com.example.Odontoprev_Java.Repository;

import com.example.Odontoprev_Java.Model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Procedure(name = "INSERT_ODONTO_PACIENTE")
    void inserirOdontoPaciente(Date p_data_nascimento, Long p_endereco_id, String p_cpf, String p_email, String p_nome, String p_telefone);



}
