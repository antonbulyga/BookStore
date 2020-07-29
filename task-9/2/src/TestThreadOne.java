public class TestThreadOne extends Thread {
    public static void main(String[]args) throws InterruptedException {
        while (true) {
            Thread t1 = new Thread(new TestThreadOne());
            t1.start();
            Thread t2 = new Thread(new TestThreadTwo());
            t1.join();
            t2.start();
        }
    }

    public void run() {
        System.out.println("Thread one");
    }
}


