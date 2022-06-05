package com.project.doctorsrepo.service;

import com.project.doctorsrepo.models.dtos.DoctorDto;
import com.project.doctorsrepo.models.dtos.DoctorMakeNewDto;
import com.project.doctorsrepo.models.entities.Doctor;
import com.project.doctorsrepo.models.entities.Hospital;
import com.project.doctorsrepo.models.entities.Profession;
import com.project.doctorsrepo.repository.DoctorRepository;
import com.project.doctorsrepo.repository.HospitalRepository;
import com.project.doctorsrepo.repository.ProfessionRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DoctorService {
    private static final String FILE_PATH  = "src/main/resources/files/doctors.json";
    private final DoctorRepository doctorRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final HospitalRepository hospitalRepository;
    private final ProfessionRepository professionRepository;
    private Random random;

    public DoctorService(DoctorRepository doctorRepository, Gson gson, ModelMapper modelMapper,
                         HospitalRepository hospitalRepository, ProfessionRepository professionRepository) {
        this.doctorRepository = doctorRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.hospitalRepository = hospitalRepository;
        this.professionRepository = professionRepository;
    }

    @Transactional
    public void seedDoctors() throws Exception {
        if(doctorRepository.count() == 0) {
            DoctorDto[] doctorDtos = this.gson.fromJson(this.readFile(), DoctorDto[].class);
            for (DoctorDto doctorDto : doctorDtos) {
                Doctor doctor = this.modelMapper.map(doctorDto, Doctor.class);
                doctor.setHospital(getRandomHospital());
                doctor.setProfession(getRandomProfession());
                this.doctorRepository.saveAndFlush(doctor);
            }
        }
    }

    private Hospital getRandomHospital() throws Exception {
        random = new Random();
        int index = random.nextInt((int) this.hospitalRepository.count()) + 1;
        Optional<Hospital> hospital = this.hospitalRepository.findById(index);
        if(hospital.isPresent()) {
            return hospital.get();
        } else {
            throw new Exception();
        }
    }

    private Profession getRandomProfession() throws Exception {
        random = new Random();
        int index = random.nextInt((int) this.professionRepository.count()) + 1;
        Optional<Profession> profession = this.professionRepository.findById(index);
        if(profession.isPresent()) {
            return profession.get();
        } else {
            throw new Exception();
        }
    }


    public String readFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(FILE_PATH)));
    }


    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAllOrdered();
    }

    public List<Doctor> getHospital(int id) {
        return doctorRepository.getAllByHospital_IdOrderByName(id);
    }

    public List<Doctor> getProfession(int id) {
        return doctorRepository.getAllByProfession_IdOrderByName(id);
    }

    @Transactional
    public void createDoctor(DoctorMakeNewDto doctorMakeNewDto) {
        Doctor doctor = new Doctor(doctorMakeNewDto.getName(),
                hospitalRepository.findHospitalByName(doctorMakeNewDto.getHospital()),
                professionRepository.findProfessionByName(doctorMakeNewDto.getProfession()));
        this.doctorRepository.saveAndFlush(doctor);
    }

    public void deleteDoctor(DoctorDto doctorDto) {
        this.doctorRepository.delete(this.doctorRepository.getDoctorByName(doctorDto.getName()));
    }
}
