package de.cats.backend.repository;

import static org.assertj.core.api.Assertions.assertThat;
import de.cats.backend.model.Cat;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


class CatsRepositoryImplTest {
    CatsRepositoryImpl catsRepository = new CatsRepositoryImpl();


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