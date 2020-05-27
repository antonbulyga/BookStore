package task1;

import java.util.Random;

public class RandomGenerator {
    Random rnd = new Random();
   private int number = rnd.nextInt(899) + 100;
    public int getNumber() {
        return number;
    }

}
