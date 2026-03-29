package com.enset.hospital.repositories;

import com.enset.hospital.entities.Consultation;
import com.enset.hospital.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
}
