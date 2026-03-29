package com.enset.hospital;

import com.enset.hospital.entities.*;
import com.enset.hospital.repositories.ConsultationRepository;
import com.enset.hospital.repositories.MedecinRepository;
import com.enset.hospital.repositories.PatientRepository;
import com.enset.hospital.repositories.RendezVousRepository;
import com.enset.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll() // Autorise tout pour le développement
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
        return http.build();
    }
	@Bean
	CommandLineRunner start(IHospitalService hospitalService,
							PatientRepository patientRepository,
							RendezVousRepository rendezVousRepository,
							MedecinRepository medecinRepository){
		return args->{
			Stream.of("Mohamed","Hassan","Najat")
					.forEach(name->{
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						hospitalService.savePatient(patient);
						// la methode save retourne l'objet qui est enregistré si jamais on a besoin
					});
			Stream.of("Ayman","hanane","yassmine")
					.forEach(name->{
						Medecin medecin = new Medecin();
						medecin.setNom(name);
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dentiste");
						medecin.setEmail(name+"@gmail.com");
						hospitalService.saveMedecin(medecin);
					});

			Patient patient= patientRepository.findById(1L).orElse(null);  //un patient s'il existe il va le retourné sinon il va retourner null
		    Patient patient1=patientRepository.findByNom("Mohamed");
			Medecin medecin=medecinRepository.findByNom("yassmine");
			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			//rendezVousRepository.save(rendezVous);
			hospitalService.saveRDV(rendezVous);

			RendezVous rendezVous1= rendezVousRepository.findAll().get(0);
			Consultation consultation= new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de consultation...");
			hospitalService.saveConsultation(consultation);


		};

	}
}
