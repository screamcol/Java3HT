package Lesson_6.classwork;

import org.apache.log4j.Logger;

public class A {

    B b;
    Logger LOG = Logger.getLogger(A.class);
    public A() {
        LOG.debug("Class A instance was created");
        b = new B();
    }

    public void foo1() {
        LOG.debug("text");
        b.foo2();
    }
}
