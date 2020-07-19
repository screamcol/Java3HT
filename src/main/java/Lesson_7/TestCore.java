package Lesson_7;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestCore {
    private static class Node {
        Method method;
        int level;

        public Node(Method method, int level) {
            this.method = method;
            this.level = level;
        }
    }

    public static void runTests(Class<?> inputClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        try {
            Constructor classConstructor = inputClass.getConstructor();
            Object newObject = classConstructor.newInstance();
            int countBeforeSuites = 0;
            int countAfterSuites = 0;
            ArrayList<Node> methods = new ArrayList<>();
            for (Method method : inputClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    methods.add(new Node(method, method.getDeclaredAnnotation(Test.class).level()));
                }
                if (method.isAnnotationPresent(BeforeSuite.class)) {
                    methods.add(new Node(method, method.getDeclaredAnnotation(BeforeSuite.class).level()));;
                    countBeforeSuites++;
                }
                if (method.isAnnotationPresent(AfterSuite.class)) {
                    methods.add(new Node(method, method.getDeclaredAnnotation(AfterSuite.class).level()));
                    countAfterSuites++;
                }
            }

            if (countBeforeSuites >  1 || countAfterSuites > 1) throw new RuntimeException("You have more than one BeforeSuite or AfterSuite annotations in class: " + inputClass.getName());
            methods.sort((o1, o2) -> o2.level - o1.level);
            for (int i = 0; i < methods.size(); i++) {
                methods.get(i).method.invoke(newObject);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
