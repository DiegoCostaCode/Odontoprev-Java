package com.example.Odontoprev_Java.repository;

import com.example.Odontoprev_Java.Model.Auditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditorRepository extends JpaRepository<Auditor, Long> {
}
