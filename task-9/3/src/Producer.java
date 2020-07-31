import java.util.Queue;

class Producer implements Runnable {
    private final Queue<Double> sharedQueue;
    private final int SIZE;

    public Producer(Queue<Double> sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Produced: " + produce());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private double produce() throws InterruptedException {
        synchronized (sharedQueue) {
            if (sharedQueue.size() == SIZE) {
                sharedQueue.wait();
            }
            double newValue = Math.random();
            sharedQueue.add(newValue);
            sharedQueue.notifyAll();

            return newValue;
        }
    }
}