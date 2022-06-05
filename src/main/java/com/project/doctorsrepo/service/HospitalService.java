package com.project.doctorsrepo.service;

import com.project.doctorsrepo.models.dtos.HospitalAddDto;
import com.project.doctorsrepo.models.entities.Hospital;
import com.project.doctorsrepo.repository.HospitalRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class HospitalService {
    private static final String FILE_PATH  = "src/main/resources/files/hospitals.json";
    private final HospitalRepository hospitalRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public HospitalService(HospitalRepository hospitalRepository, Gson gson, ModelMapper modelMapper) {
        this.hospitalRepository = hospitalRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    public String readFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(FILE_PATH)));
    }

    public void seedHospitals() throws IOException {
        if(hospitalRepository.count() == 0) {
            HospitalAddDto[] hospitalAddDtos = this.gson.fromJson(this.readFile(), HospitalAddDto[].class);
            for (HospitalAddDto hospitalAddDto : hospitalAddDtos) {
                Hospital hospital = this.modelMapper.map(hospitalAddDto, Hospital.class);
                this.hospitalRepository.saveAndFlush(hospital);
            }
        }
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public void getHospitalID(String name) {
        hospitalRepository.findHospitalByName(name);
    }
}
