package util;

public class RandomIdGeneration {

    private static final int MIN = 1;
    private static final int MAX = 4326523;

    public static int getRandomID(){
        return (int) (Math.random() * ((MAX - MIN) + 1)) + MIN;
    }

}
