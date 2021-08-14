package de.cats.backend.repository;

import de.cats.backend.model.Player;

import java.util.ArrayList;

public interface RepositoryService {

    ArrayList<Player> getNewStacks(ArrayList<Player> players);
}
