package com.project.doctorsrepo.service;

import com.project.doctorsrepo.models.dtos.ProfessionAddDto;
import com.project.doctorsrepo.models.entities.Profession;
import com.project.doctorsrepo.repository.ProfessionRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class ProfessionService {
    private static final String FILE_PATH  = "src/main/resources/files/professions.json";
    private final ProfessionRepository professionRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    public ProfessionService(ProfessionRepository professionRepository, Gson gson, ModelMapper modelMapper) {
        this.professionRepository = professionRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    public String readFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(FILE_PATH)));
    }

    public void seedProfessions() throws IOException {
        if(professionRepository.count() == 0) {
            ProfessionAddDto[] professionAddDtos = this.gson.fromJson(this.readFile(), ProfessionAddDto[].class);
            for (ProfessionAddDto professionAddDto : professionAddDtos) {
                Profession profession = this.modelMapper.map(professionAddDto, Profession.class);
                this.professionRepository.saveAndFlush(profession);
            }
        }
    }

    public List<Profession> getAllProfessions() {
        return professionRepository.findAll();
    }
}
