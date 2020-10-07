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
        synchronized (sharedQueue) {
            while (true) {
                if (sharedQueue.size() == SIZE) {
                    try {
                        sharedQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Produced: " + produce());
                }
            }
        }
    }

    private double produce() {
        synchronized (sharedQueue) {
            double newValue = Math.random();
            sharedQueue.add(newValue);
            sharedQueue.notifyAll();

            return newValue;
        }
    }
}