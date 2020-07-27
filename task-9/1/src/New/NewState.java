package New;

public class NewState {
        public static void main(String[] args){
            Thread one = new Thread(new Runnable(){
                @Override
                public void run(){

                }
            });
            System.out.println(one.getState());

        }

}
