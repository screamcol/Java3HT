package Lesson_6.classwork;

import org.apache.log4j.Logger;

public class B {

    Logger LOG = Logger.getLogger(B.class);
    public B() {
        LOG.debug("class B instance was created!");
    }

    public void foo2() {
        LOG.debug("text");
    }
}
