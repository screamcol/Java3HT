package Lesson_4.task_1;

public class Main {
    private static final Printer printer = new Printer();
    static int num = 1;
    static int positionA = 0;
    static int positionB = 0;
    static int positionC = 0;

    public static void main(String[] args) {

        new Thread(() -> {
            int count = 0;
            while (true) {
                try {
                    synchronized (printer) {
                        if (num == 1 || num == positionA + 3) {
                            positionA = num;
                            num++;
                            printer.printA();
                            printer.notifyAll();
                            if (++count == 5) break;
                            printer.wait();
                        }
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            int count = 0;
            while (true) {
                try {
                    synchronized (printer) {
                        if (num == 2 || num == positionB + 3) {
                            positionB = num;
                            num++;
                            printer.printB();
                            printer.notifyAll();
                            if (++count == 5) break;
                            printer.wait();
                        }
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            int count = 0;
            while (true) {
                try {
                    synchronized (printer) {
                        if (num == 3 || num == positionC + 3) {
                            positionC = num;
                            num++;
                            printer.printC();
                            printer.notifyAll();
                            if (++count == 5) break;
                            printer.wait();
                        }
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

}
