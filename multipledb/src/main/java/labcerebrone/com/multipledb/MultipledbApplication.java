package labcerebrone.com.multipledb;


import labcerebrone.com.multipledb.admissions.Admissions;
import labcerebrone.com.multipledb.admissions.AdmissionsRepository;
import labcerebrone.com.multipledb.appointments.Appointment;
import labcerebrone.com.multipledb.appointments.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class MultipledbApplication {

	@Autowired
	private AdmissionsRepository admissionsRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	/** two Methods, Endpoints for Appointments Service */
	@PostConstruct
	public void addAppointmentsData() {
		appointmentRepository.saveAll(Stream.of(
				new Appointment(101,"First Appointment"),
				new Appointment(102,"Second Appointment"))
				.collect(Collectors.toList()));
	}
	@GetMapping("/getAppointments")
	public List<Appointment> getAppointments() {
		return appointmentRepository.findAll();
	}

	/** two Methods, Endpoints for Admissions Service */
	@PostConstruct
	public void addAdmissionsData() {
		admissionsRepository.saveAll(Stream.of(
				new Admissions(101,"First Addmission"),
				new Admissions(102,"Second Addmission"))
				.collect(Collectors.toList()));
	}
	@GetMapping("/getAdmissions")
	public List<Admissions> getAdmissions() {
		return admissionsRepository.findAll();
	}
	public static void main(String[] args) {
		SpringApplication.run(MultipledbApplication.class, args);
	}

}
