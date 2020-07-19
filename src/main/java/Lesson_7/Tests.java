package Lesson_7;

public class Tests {

    @BeforeSuite
    public void testBeforeSuite() {
        System.out.println("Before Suite");
    }

    @AfterSuite
    public void testAfterSuite() {
        System.out.println("After Suite");
    }

    @Test(level = 1)
    public void test1() {
        System.out.println("Test 1 with level 1");
    }

    @Test(level = 5)
    public void test2() {
        System.out.println("Test 2 with level 5");
    }

    @Test(level = 4)
    public void test3() {
        System.out.println("Test 3 with level 4");
    }

    @Test(level = 2)
    public void test4() {
        System.out.println("Test 4 with level 2");
    }
}
