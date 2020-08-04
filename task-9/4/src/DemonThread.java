import java.util.Calendar;


public class DemonThread implements Runnable {
        public void run() {

            while (true) {
                System.out.println(Calendar.getInstance().getTime());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }


    public static void main(String[] args) throws InterruptedException {

        Thread timer = new Thread(new DemonThread());
        timer.setDaemon(true);
        timer.start();
        Thread.sleep(3000);

    }
}
