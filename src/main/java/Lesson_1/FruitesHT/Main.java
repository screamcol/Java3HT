package Lesson_1.FruitesHT;

public class Main {
    public static void main(String[] args) {
        Box<Apple> boxApple = new Box<Apple>();
        Box<Apple> boxApple2 = new Box<Apple>();
        Box<Orange> boxOrange = new Box<Orange>();
        Box<Orange> boxOrange2 = new Box<Orange>();

        boxApple.addFruit(new Apple("яблоко1"));
        boxApple.addFruit(new Apple("яблоко2"));
        boxApple2.addFruit(new Apple("яблоко3"));
        boxApple2.addFruit(new Apple("яблоко4"));
        boxApple2.addFruit(new Apple("яблоко5"));

        boxOrange.addFruit(new Orange("апельсин1"));
        boxOrange.addFruit(new Orange("апельсин2"));
        boxOrange2.addFruit(new Orange("апельсин3"));
        boxOrange2.addFruit(new Orange("апельсин4"));

        System.out.println(boxApple.getWeight());
        System.out.println(boxOrange.getWeight());
        System.out.println(boxApple.compare(boxOrange));
        System.out.println(boxApple.compare(boxApple));
        System.out.println(boxApple.compare(boxApple2));

        System.out.println(boxOrange.getFruits());
        System.out.println(boxOrange2.getFruits());
        boxOrange.transfer(boxOrange2);
        System.out.println(boxOrange.getFruits());
        System.out.println(boxOrange2.getFruits());
    }
}
