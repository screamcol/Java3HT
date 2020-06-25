package Lesson_1.FruitesHT;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    ArrayList<T> fruits = new ArrayList<>();

    public ArrayList<T> getFruits() {
        return fruits;
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        if (fruits.get(0) instanceof Apple) {
            return 1.0f * fruits.size();
        } else return 1.5f * fruits.size();
    }

    public boolean compare(Box anotherBox) {
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.00001;
    }

    public void transfer(Box<? super T> anotherBox) {
        anotherBox.fruits.addAll(this.fruits);
        this.fruits.clear();
    }

}
