package de.cats.backend.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.cats.backend.model.Cat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatsRepositoryImpl extends CatsRepository {
    private final ObjectMapper mapper;

    List<Cat> loadCats() {
        List<Cat> catArray = new ArrayList<>();
         try {
            catArray = readCatsFromFile(catArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return catArray;
    }

    private List<Cat> readCatsFromFile(List<Cat> catArray) throws IOException {
        File datei = new File(System.getProperty("user.home") + File.separator + "CatGame" +
                File.separator + "Cats.json");
        catArray.addAll(Arrays.asList(this.mapper.readValue(datei, Cat[].class)));
        return catArray;
    }

    ArrayList<String> loadNames() {

        ArrayList<String> namesArray = new ArrayList<>();
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
