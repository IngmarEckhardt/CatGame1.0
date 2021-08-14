package de.cats.backend.model;

import java.util.*;

import lombok.Data;

@Data
public class Player {
    private final String name;
    private Result result;
    private List<Cat> stack;
    private Map<Cat, Result> resultsOfRounds = new HashMap<>();


    public Player (String name) {
        this.stack = new ArrayList<>();
        this.name = name;
    }

    public void addToStack (Cat cat) {
        this.stack.add(cat);
    }

    Cat removeFirstFromStack(){
        return this.stack.remove(0);
    }

    void returnToStack(Cat catReturn) {
        this.stack.add(catReturn);
    }

    void addWinningsToStack(Cat cat1, Cat cat2){
        Random random = new Random();
        boolean adCats = random.nextBoolean();
        if (adCats) {
            this.stack.add(cat1);
            this.stack.add(cat2);
        } else {
            this.stack.add(cat2);
            this.stack.add(cat1);
        }
    }

    public boolean hasCardsLeft(){

        return !stack.isEmpty();
        }

    public Map<Cat, Result> getResultsOfRounds() {
        return resultsOfRounds;
    }

    protected void addResultToList(Cat cat, Result result) {
        this.resultsOfRounds.put(cat, result);
    }

    protected Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
