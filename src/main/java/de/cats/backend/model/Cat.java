package de.cats.backend.model;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Random;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Cat {
    private String name;
    private File image;
    private int size;
    private double weight;
    private int purrability;
    private int maliciousness;
    private Element element;
    private LocalDateTime birthday;

    public Cat () {
    }
    public Cat(String name, File image, int size, double weight, int purrability, int maliciousness, Element element, LocalDateTime birthday) {
        this.name = name;
        this.image = image;
        this.size = size;
        this.weight = weight;
        this.purrability = purrability;
        this.maliciousness = maliciousness;
        this.element = element;
        this.birthday = birthday;
    }

    protected void setRandomElement() {
        element = Element.values()[new Random().nextInt(Element.values().length)];
    }
}
