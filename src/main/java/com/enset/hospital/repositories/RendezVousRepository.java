package com.enset.hospital.repositories;

import com.enset.hospital.entities.Medecin;
import com.enset.hospital.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous,String> {
}
