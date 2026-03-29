package com.enset.hospital.service;

import com.enset.hospital.entities.Consultation;
import com.enset.hospital.entities.Medecin;
import com.enset.hospital.entities.Patient;
import com.enset.hospital.entities.RendezVous;

public interface IHospitalService {
    Patient  savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);
}
