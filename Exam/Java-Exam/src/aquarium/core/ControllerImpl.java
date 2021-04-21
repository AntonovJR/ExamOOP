package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DecorationRepository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;
        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            default:
                throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }
        aquariums.add(aquarium);
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
            default:
                throw new NullPointerException(INVALID_DECORATION_TYPE);
        }
        decorations.add(decoration);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        if (decorations.findByType(decorationType) == null) {
            throw new IllegalArgumentException(String.format(NO_DECORATION_FOUND, decorationType));

        }

        Aquarium aquariumToAdd = null;
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                aquariumToAdd = aquarium;
            }
        }


        aquariumToAdd.addDecoration(decorations.findByType(decorationType));
        decorations.remove(decorations.findByType(decorationType));

        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish;
        Aquarium aquarium = null;
        String output;
        for (Aquarium aquarium1 : aquariums) {
            if (aquarium1.getName().equals(aquariumName)) {
                aquarium = aquarium1;
            }
        }
        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }
        int test = aquarium.getFish().size();
        aquarium.addFish(fish);
        if (aquarium.getFish().size()==test) {
            return WATER_NOT_SUITABLE;
        }
        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM,fishType,aquariumName);

    }


    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = null;
        for (Aquarium aquarium1 : aquariums) {
            if (aquarium1.getName().equals(aquariumName)) {
                aquarium = aquarium1;
            }
        }
        assert aquarium != null;
        aquarium.feed();
        return String.format(FISH_FED, aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = null;
        for (Aquarium aquarium1 : aquariums) {
            if (aquarium1.getName().equals(aquariumName)) {
                aquarium = aquarium1;
            }
        }
        double price = 0;
        assert aquarium != null;
        Collection<Fish> fish = aquarium.getFish();
        for (Fish fish1 : fish) {
            price += fish1.getPrice();

        }
        Collection<Decoration> decorations = aquarium.getDecorations();
        for (Decoration decoration : decorations) {
            price += decoration.getPrice();

        }
        return String.format(VALUE_AQUARIUM, aquariumName, price);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        for (Aquarium aquarium : aquariums) {
            sb.append(aquarium.getInfo());
            sb.append(System.lineSeparator());

        }
        return sb.toString().trim();
    }
}
