package examples.java17;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.IntStream;

public class RandomNumberGeneration {

    private static final RandomGenerator randomGenerator = RandomGeneratorFactory.of("SecureRandom").create();

    public static int generateRandomNumber(int min, int max) {
        return randomGenerator.nextInt(min, max + 1);
    }

    public static IntStream getPseudoIntegers(String algorithm, int streamSize) {
        return RandomGeneratorFactory.of(algorithm)
                .create()
                .ints(streamSize);
    }

    public static void main(String[] args) {
        // Available random generators:
        //        "L32X64MixRandom"
        //        "SecureRandom"
        //        "SplittableRandom"
        //        "Xoroshiro128PlusPlus"
        //        "L128X128MixRandom"
        //        "L64X128MixRandom"
        //        "L64X1024MixRandom"
        //        "L128X256MixRandom"
        //        "L64X128StarStarRandom"
        //        "L64X256MixRandom"
        //        "Xoshiro256PlusPlus"
        //        "L128X1024MixRandom"
        //        "Random"
        IntStream randomNumbers = getPseudoIntegers("SecureRandom", 5);
        randomNumbers.forEach(System.out::println);
    }
}

