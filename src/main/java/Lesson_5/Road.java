package Lesson_5;

import java.util.concurrent.atomic.AtomicBoolean;

public class Road extends Stage {
    private AtomicBoolean isWin = new AtomicBoolean(true);

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            if (length == 40 && isWin.get()) {
                isWin.set(false);
                System.out.println(c.getName() + " WIN");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
