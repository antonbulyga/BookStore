public class Test implements Runnable {

    public static Thread thread1;
    public static Test obj;

    public static void main(String[] args) {
        obj = new Test();
        thread1 = new Thread(obj);
        thread1.start();
    }

    public void run() {
        ThreadStatesDemonstration myThread = new ThreadStatesDemonstration();
        Thread thread2 = new Thread(myThread);

        System.out.println(thread2.getState());
        thread2.start();

        System.out.println(thread2.getState());

        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(thread2.getState() );


        try {
            thread2.join();
        }
        catch (InterruptedException e) {

            e.printStackTrace();
        }
        System.out.println(thread2.getState());

    }

}
