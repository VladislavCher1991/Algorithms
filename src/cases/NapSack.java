package cases;

import java.util.Arrays;
import java.util.Comparator;

public class NapSack {
    public static void main(String[] args) {
        final int capacity = 7;

        final Item item1 = new Item(4, 20);
        final Item item2 = new Item(3, 18);
        final Item item3 = new Item(2, 14);

        final Item[] items = {item1, item2, item3};

        Arrays.sort(items, Comparator.comparingDouble(Item::valuePerWeight).reversed());

        System.out.println(Arrays.toString(items));

        int weightSoFar = 0;
        double valueSoFar = 0;
        int currentItem = 0;
        while (currentItem < items.length && weightSoFar != capacity) {
            if (weightSoFar + items[currentItem].getWeight() < capacity) {
                valueSoFar += items[currentItem].getValue();
                weightSoFar += items[currentItem].getWeight();
            } else {
                valueSoFar += ((capacity - weightSoFar) / (double) items[currentItem].getWeight())
                        * items[currentItem].getValue();
                weightSoFar = capacity;
            }
            currentItem++;
        }
        System.out.println(valueSoFar);
    }
}
class Item {
    int weight;
    int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public double valuePerWeight() {
        return value / (double) weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }
}

