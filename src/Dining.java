public class Dining{
    public static void main(String[] args) throws Exception {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];
        for (int i = 0; i < forks.length; i++) {
            // initialize fork object
            forks[i] = new Object();
        }
        for (int i = 0; i < philosophers.length; i++) {
            // initialize Philosopher object
            philosophers[i] = new Philosopher(forks[i],forks[(i+philosophers.length-1)%5],i);
        }
        for (Philosopher philosopher : philosophers) {
            new Thread(philosopher,"Philosopher "+philosopher.getNum()).start();
        }
    }
}