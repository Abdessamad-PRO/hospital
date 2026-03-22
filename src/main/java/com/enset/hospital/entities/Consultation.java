package com.enset.hospital.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
@Entity
@NoArgsConstructor
@AllArgsConstructor @Data
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dateConsultation;
    private String rapport;
    @OneToOne
    private RendezVous rendezVous;

}
