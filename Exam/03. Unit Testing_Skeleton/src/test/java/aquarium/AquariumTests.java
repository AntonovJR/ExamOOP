package aquarium;

import org.junit.Assert;
import org.junit.Test;

public class AquariumTests {


    @Test
    public void testConstructor() {
        Aquarium aquarium = new Aquarium("Boxemia", 20);
        Assert.assertEquals(aquarium.getCapacity(), 20);
    }

    @Test
    public void testGetName() {
        Aquarium aquarium = new Aquarium("Boxemia", 20);
        Assert.assertEquals(aquarium.getName(), "Boxemia");
    }

    @Test(expected = NullPointerException.class)
    public void testSetterName() {
        Aquarium aquarium = new Aquarium("", 20);

    }

    @Test(expected = NullPointerException.class)
    public void testSetterNameNull() {
        Aquarium aquarium = new Aquarium(null, 20);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetterCapacityZero() {
        Aquarium aquarium = new Aquarium("Boxemia", -1);

    }

    @Test
    public void testFishSize() {
        Fish fish = new Fish("Gosho");
        Aquarium aquarium = new Aquarium("Boxemia", 10);
        aquarium.add(fish);
        Assert.assertEquals(aquarium.getCount(), 1);
    }

    @Test
    public void testFishRemove() {
        Fish fish = new Fish("Gosho");
        Aquarium aquarium = new Aquarium("Boxemia", 10);
        aquarium.add(fish);
        aquarium.remove("Gosho");
        Assert.assertEquals(aquarium.getCount(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFishRemoveFail() {
        Aquarium aquarium = new Aquarium("Boxemia", 10);
        aquarium.remove("Fafi");
    }

    @Test
    public void testSell() {
        Fish fish = new Fish("Gosho");
        Aquarium aquarium = new Aquarium("Boxemia", 10);
        aquarium.add(fish);
        aquarium.sellFish("Gosho");
        boolean available = fish.isAvailable();
        Assert.assertFalse(available);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellFail() {
        Aquarium aquarium = new Aquarium("Boxemia", 10);
        aquarium.sellFish("Fafi");
    }

    @Test
    public void testReport() {
        Aquarium aquarium = new Aquarium("Boxemia", 10);
        String text = aquarium.report();
        Assert.assertEquals(text, "Fish available at Boxemia: ");
    }

    @Test
    public void testAdd() {
        Fish fish = new Fish("Gosho");
        Aquarium aquarium = new Aquarium("Boxemia", 10);
        aquarium.add(fish);
        Assert.assertEquals(aquarium.getCount(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFail() {
        Fish fish = new Fish("Gosho");
        Fish fishTwo = new Fish("Gosho");
        Fish fishThree = new Fish("Gosho");
        Aquarium aquarium = new Aquarium("Boxemia", 2);
        aquarium.add(fish);
        aquarium.add(fishTwo);
        aquarium.add(fishThree);

    }


}

