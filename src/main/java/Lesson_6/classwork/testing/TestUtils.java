package Lesson_6.classwork.testing;

public class TestUtils {
    public int sum(int a, int b) {
        return a + b;
    }

    public int funcWithError(int x) {
        if (!(x >= 1 && x <= 5)) {
            throw  new RuntimeException("wrong x = " + x + " x should be interval [1; 5]");
        }
        return x;
    }

    public int[] getArray() {
        return new int[]{1,2,3};
    }
}
