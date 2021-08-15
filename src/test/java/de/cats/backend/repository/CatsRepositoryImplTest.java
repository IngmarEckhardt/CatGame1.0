package de.cats.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import de.cats.backend.model.Cat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Service
class CatsRepositoryImplTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    CatsRepositoryImpl catsRepository;

    @BeforeEach
    void setUp() {
        catsRepository = new CatsRepositoryImpl(objectMapper);
        objectMapper.registerModule (new JavaTimeModule());
        objectMapper.disable (SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void listShouldContainCats() {
        //given
        ArrayList<Cat> catlist;

        //when
        catlist = catsRepository.loadCats();

        //then
        assertThat(catlist).extracting("class").contains(Cat.class);
    }


    @Test
    void listShouldContainNameStrings() {
        //given
        ArrayList<String> nameslist;

        //when
        nameslist = catsRepository.loadNames();

        //then
        assertThat(nameslist).extracting("class").contains(String.class);
    }


    @Test
    void listShouldContainFiles() {
        //given
        ArrayList<File> filelist;

        //when
        filelist = catsRepository.collectImageFiles();

        //then
        assertThat(filelist).extracting("class").contains(File.class);
    }

    @Test
    void itShouldThrowException () {


    }

}