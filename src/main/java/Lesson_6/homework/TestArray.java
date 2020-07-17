package Lesson_6.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestArray {
//    public static void main(String[] args) {
//        int[] inputArray = new int[]{10, 1, 2, 3, 11, 12, 4};
//        System.out.println(Arrays.toString(getElementsAfterLast4Input(inputArray)));
//    }

    public Integer[] getElementsAfterLast4Input(int[] inputArray) {
        ArrayList<Integer> outputArrayList = new ArrayList<>();
        int count4Number = 0;
        for (int i = inputArray.length - 1; i >= 0 ; i--) {
            if (inputArray[i] == 4){
                count4Number++;
                break;
            }
            outputArrayList.add(inputArray[i]);
        }
        if(count4Number == 0) {
            throw new RuntimeException();
        }
        for (int i = 0; i < outputArrayList.size() / 2; i++) {
            int tmp = outputArrayList.get(i);
            outputArrayList.set(i, outputArrayList.get(outputArrayList.size() - i - 1));
            outputArrayList.set(outputArrayList.size() - i - 1, tmp);
        }
        return outputArrayList.toArray(new Integer[0]);
    }

    public boolean check1Or4InArray(int[] inputArray) {
        for (int i : inputArray) {
            if (i == 1 || i == 4) return true;
        }
        return false;
    }
}
