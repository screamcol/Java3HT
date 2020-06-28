package Lesson_1.ArrayHT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyArray<T> {
    private T[] array;

    public MyArray(T[] array) {
        this.array = array;
    }

    public T[] getArray() {
        return array;
    }

    public void changeElementsInArray(int from, int to) {
        try {
            T temp = array[from];
            array[from] = array[to];
            array[to] = temp;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Elements should be in bound of array");
        }
    }

    public ArrayList<T> convertToArrayList() {
        List<T> aList = Arrays.asList(array);
        return new ArrayList<T>(aList);
    }

}
