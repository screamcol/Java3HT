package Lesson_1.ArrayHT;


import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[] array1 = {"one", "two","three", "four", "five"};
        Integer[] array2 = {1, 2, 3, 4, 5};
        Object[] array3 = {"one", 2, "three", new File("path"), 4};

        MyArray<String> myArray = new MyArray<>(array1);
        MyArray<Integer> myArray1 = new MyArray<>(array2);
        MyArray<Object> myArray2 = new MyArray<>(array3);

        myArray.changeElementsInArray(1, 2);
        myArray1.changeElementsInArray(2, 3);
        myArray2.changeElementsInArray(3, 4);

        for (String s : myArray.getArray()) {
            System.out.print(s + " ");
        }
        System.out.println();

        for (Integer i: myArray1.getArray()) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (Object ob : myArray2.getArray()) {
            System.out.print(ob + " ");
        }
        System.out.println();

        ArrayList<String> stringArrayList = myArray.convertToArrayList();
        System.out.println(stringArrayList.getClass());
    }


}
