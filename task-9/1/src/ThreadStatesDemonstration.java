public class ThreadStatesDemonstration implements Runnable {
    public void run() {
        try{
            Thread.sleep(1500);
        }
        catch (InterruptedException e){

            e.printStackTrace();
        }

        System.out.println(Test.thread1.getState());

        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

