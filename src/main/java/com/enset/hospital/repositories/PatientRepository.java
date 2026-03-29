package com.enset.hospital.repositories;

import com.enset.hospital.entities.Medecin;
import com.enset.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByNom(String name);
}
