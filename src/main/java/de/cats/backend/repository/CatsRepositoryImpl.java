package de.cats.backend.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.cats.backend.model.Cat;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CatsRepositoryImpl extends CatsRepository {

     ArrayList<Cat> loadCats() {
        ArrayList<Cat> catArray = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        File datei = new File(System.getProperty("user.home") + File.separator + "CatGame" +
                File.separator + "Cats.json");
        try {
            catArray.addAll(Arrays.asList(mapper.readValue(datei, Cat[].class)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return catArray;
    }

    ArrayList<String> loadNames() {
        ArrayList<String> namesArray = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            File datei = new File(System.getProperty("user.home") + File.separator + "CatGame" +
                    File.separator + "Names.json");
            try {
                namesArray.addAll(Arrays.asList(mapper.readValue(datei, String[].class)));

            } catch (IOException e) {
                e.printStackTrace();
            }
            return namesArray;
        }

    ArrayList<File> collectImageFiles() {
        ArrayList<File> images = new ArrayList<>();
        for (int i = 1; i < 33; i++) {
            File catImage = new File(System.getProperty("user.home") + File.separator + "CatGame" +
                    File.separator + "CatImage" + i + ".jpg");
            images.add(catImage);
        }
        return images;
    }
}