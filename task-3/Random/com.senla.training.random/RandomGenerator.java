package task1;

import java.util.Random;

public class RandomGenerator {
    private Random rnd = new Random();
    public int getRandomNumber(){
        int number = rnd.nextInt(900) + 100;
        return number;
    }

}
