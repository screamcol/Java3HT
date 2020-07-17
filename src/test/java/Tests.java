import Lesson_6.homework.TestArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Tests {

    private TestArray reference;

    private int[]array1 = new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7};
    private int[]array2 = new int[]{4, 1, 2, 3, 6, 0};
    private int[]array3 = new int[]{10, 1, 2, 3, 11, 12, 4};
    private int[]array4 = new int[]{10, 1, 2, 3};
    private int[]array5 = new int[]{10, 1, 2, 3};
    private int[]array6 = new int[]{8, 4, 25, 46};
    private int[]array7 = new int[]{111, 11, 2, 3};
    private int[]array8 = new int[]{4, 1, 2, 3};

    @Before
    public void initTest() {
        reference = new TestArray();
    }

    @Test
    public void testArrayMany4() {
        Assert.assertArrayEquals(new Integer[]{1, 7}, reference.getElementsAfterLast4Input(array1));
    }

    @Test
    public void testArray4AtStart() {
        Assert.assertArrayEquals(new Integer[]{1, 2, 3, 6, 0}, reference.getElementsAfterLast4Input(array2));
    }

    @Test
    public void testArray4AtEnd() {
        Assert.assertArrayEquals(new Integer[]{}, reference.getElementsAfterLast4Input(array3));
    }

    @Test(expected = RuntimeException.class)
    public void testArrayException() {
        reference.getElementsAfterLast4Input(array4);
    }

    @Test
    public void testArray1Or4ExistOnly1() {
        Assert.assertTrue("1 exist in array5", reference.check1Or4InArray(array5));
    }

    @Test
    public void testArray1Or4ExistOnly4() {
        Assert.assertTrue("4 exist in array6", reference.check1Or4InArray(array6));
    }

    @Test
    public void testArray1Or4ExistWithout1And4() {
        Assert.assertFalse("1 and 4 not exist in array7", reference.check1Or4InArray(array7));
    }

    @Test
    public void testArray1Or4ExistBoth1And4() {
        Assert.assertTrue("1 and 4 exist in array8", reference.check1Or4InArray(array8));
    }
}
