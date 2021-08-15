package de.cats.backend.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.cats.backend.model.Cat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@ExtendWith(MockitoExtension.class)
class CatsRepositoryImplTest {
    @Mock private ObjectMapper objectMapperMock;
    private CatsRepositoryImpl catsRepository;

    @Test
    public void itShouldThrowAnIOException() throws IOException {
        //given
        catsRepository = new CatsRepositoryImpl(objectMapperMock);

        //do-When
        doThrow(new IOException()).when(objectMapperMock).readValue(any(File.class), any(Class.class));

        //when-then
        Exception exception = assertThrows(RuntimeException.class, () -> catsRepository.loadCats());
        assertEquals(exception.getMessage(), "Die Cats.json-Datei war nicht lesbar");
    }

    @Test
    void listShouldContainCats() throws IOException {
        //given
        List<Cat> catlist = new ArrayList<Cat>();
        Cat[] cat = setupRepoAndMockCats();
        catlist.addAll(Arrays.asList(cat));

        //when
        List<Cat> listUnderTest = catsRepository.loadCats();

        //then
        assertThat(listUnderTest,is(catlist));
    }

    @Test
    public void getNamesShouldThrowAnIOException() throws IOException {
        doThrow(new IOException()).when(objectMapperMock).readValue(any(File.class), any(Class.class));
        catsRepository = new CatsRepositoryImpl(objectMapperMock);
        Exception exception = assertThrows(RuntimeException.class, () -> catsRepository.loadNames());
        assertEquals(exception.getMessage(), "Die Namensliste war nicht lesbar");
    }

    @Test
    void listShouldContainNameStrings() throws IOException {
        //given
        String[] nameslist = setupRepoAndMockNames();
        List<String> namesArray = new ArrayList<String>();
        namesArray.addAll(Arrays.asList(nameslist));
        List<String> listUnderTest;

        //when
        listUnderTest = catsRepository.loadNames();

        //then
        assertThat(listUnderTest, is(namesArray));
    }

    @Test
    void listShouldContainFiles() {
        //given
        List<File> filelist;
        catsRepository = new CatsRepositoryImpl(new ObjectMapper());

        //when
        filelist = catsRepository.collectImageFiles();
        System.out.println(filelist);
        //then
        Assertions.assertTrue(filelist.size()==32);
    }

    private Cat[] setupRepoAndMockCats() throws IOException {
        catsRepository = new CatsRepositoryImpl(objectMapperMock);
        Cat[] cat = getMockCats();
        when(objectMapperMock.readValue(any(File.class), eq(Cat[].class))).thenReturn(cat);
        return cat;
    }

    private Cat[] getMockCats() {
        Cat[] cat = {Cat.builder().name("Ernst").image(null).
                        size(20).weight(6.0).purrability(80).
                        maliciousness(60).element(null).
                        birthday(LocalDateTime.now()).build()};
        return cat;
    }

    private String[] setupRepoAndMockNames() throws IOException {
        catsRepository = new CatsRepositoryImpl(objectMapperMock);
        String[] names = {"Ernst, Otto"};
        when(objectMapperMock.readValue(any(File.class), eq(String[].class))).thenReturn(names);
        return names;
    }
}