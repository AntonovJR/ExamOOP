package aquarium.entities.aquariums;

import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        this.setName(name);
        this.setCapacity(capacity);
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(AQUARIUM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int calculateComfort() {
        int comfort = 0;
        for (Decoration decoration : decorations) {
            comfort += decoration.getComfort();

        }
        return comfort;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() == this.capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }
        if (getClass().getSimpleName().contains("Fresh") && fish.getClass().getSimpleName().contains("Fresh")) {
            this.fish.add(fish);
        } else if (getClass().getSimpleName().contains("Salt") && fish.getClass().getSimpleName().contains("Salt")) {
            this.fish.add(fish);
        }
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        for (Fish fish : fish) {
            fish.eat();

        }

    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s (%s):", getName(), getClass().getSimpleName()));
        sb.append(System.lineSeparator());
        if (fish.isEmpty()) {
            sb.append("Fish: none");
        } else {
            String names="";
            for (Fish fish1 : fish) {
                names += fish1.getName() + " ";

            }
            sb.append("Fish: ");
            sb.append(names.trim());
        }
        sb.append(System.lineSeparator());
        sb.append(String.format("Decorations: %d", decorations.size()));
        sb.append(System.lineSeparator());
        sb.append(String.format("Comfort: %d", calculateComfort()));

        return sb.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }
}
