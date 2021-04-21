package aquarium.entities.fish;

import aquarium.entities.aquariums.Aquarium;

public class FreshwaterFish extends BaseFish {
    private int size = 3;

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(size);
    }

    @Override
    public void eat() {
        setSize(getSize() + 3);

    }

}
