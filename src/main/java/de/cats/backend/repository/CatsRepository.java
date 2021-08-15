package de.cats.backend.repository;

import de.cats.backend.model.Cat;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistence. Only for IO-Operations
 */
abstract class CatsRepository {

    abstract List<Cat> loadCats();
    abstract List<String> loadNames();
    abstract List<File> collectImageFiles();

}
