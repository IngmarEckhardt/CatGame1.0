package de.cats.backend.repository;

import de.cats.backend.model.Cat;
import java.io.File;
import java.util.ArrayList;

/**
 * Persistence. Only for IO-Operations
 */
abstract class CatsRepository {

    abstract ArrayList<Cat> loadCats();
    abstract ArrayList<String> loadNames();
    abstract ArrayList<File> collectImageFiles();

}
