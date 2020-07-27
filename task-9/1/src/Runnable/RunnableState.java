package Runnable;

public class RunnableState {
    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        one.start();
        System.out.println(one.getState());
    }
}
