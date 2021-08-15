package de.cats.backend.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.cats.backend.model.Cat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
class CatsRepositoryImplTest {
    private ObjectMapper objectMapperMock;
    private CatsRepository catsRepository;

    @BeforeEach
    void setUp() {
        objectMapperMock = mock(ObjectMapper.class);
        catsRepository = new CatsRepositoryImpl(objectMapperMock);

    }

    @Test
    void listShouldContainCats() throws IOException {
        //given
        List<Cat> catlist = setupRepoAndMockCats();

        //when
        List<Cat> listUnderTest = catsRepository.loadCats();

        //then
        assertThat(listUnderTest,is(catlist));
        
    }

    private List<Cat> setupRepoAndMockCats() throws IOException {
        List<Cat> cats = getMockCats();
        when(objectMapperMock.readValue(any(File.class),  any (TypeReference.class))).thenReturn(cats);
        return cats;

    }

    private List<Cat> getMockCats() {
        List<Cat> cats = new ArrayList<Cat>();
        cats.add(
                Cat.builder().name("Ernst").image(null).
                        size(20).weight(6.0).purrability(80).
                        maliciousness(60).element(null).
                        birthday(LocalDateTime.now()).build()
        );
        cats.add(
                Cat.builder().name("Katzilla").image(null).
                size(25).weight(5.0).purrability(60).
                maliciousness(50).element(null).
                birthday(LocalDateTime.now()).build()
        );
        return cats;
    }


    @Test
    void listShouldContainNameStrings() {
        //given
        List<String> nameslist;

        //when
        nameslist = catsRepository.loadNames();

        //then
        assertThat(nameslist, contains (String.class));
    }


    @Test
    void listShouldContainFiles() {
        //given
        List<File> filelist;

        //when
        filelist = catsRepository.collectImageFiles();

        //then
        assertThat(filelist, contains(File.class));
    }

}