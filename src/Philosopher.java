public class Philosopher implements Runnable {
    private final Object leftFork;
    private final Object rightFork;
    private final int num;

    Philosopher(Object left, Object right, int num) {
        this.leftFork = left;
        this.rightFork = right;
        this.num = num;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " +
                action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    public int getNum(){
        return num;
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction(System.nanoTime() + ": Thinking"); // thinking
                // your code
                if (num % 2 == 0) {
                    synchronized (leftFork) {
                        pick_left();
                        synchronized (rightFork) {
                            pick_right();
                            eat();
                        }
                    }
                } else {
                    synchronized (rightFork) {
                        pick_right();
                        synchronized (leftFork) {
                            pick_left();
                            eat();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void pick_left() throws InterruptedException {
        doAction(System.nanoTime() + ": pick left fork.");
    }

    public void pick_right() throws InterruptedException {
        doAction(System.nanoTime() + ": pick right fork.");
    }

    public void eat() throws InterruptedException {
        doAction(System.nanoTime() + ": eating.");
    }
}