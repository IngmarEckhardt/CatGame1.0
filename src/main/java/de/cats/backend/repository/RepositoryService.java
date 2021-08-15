package de.cats.backend.repository;

import de.cats.backend.model.Player;

import java.util.ArrayList;
import java.util.List;

public interface RepositoryService {

    List<Player> getNewStacks(List<Player> players);
}
