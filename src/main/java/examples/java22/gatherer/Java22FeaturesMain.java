package examples.java22.gatherer;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

// Java 23 was released a couple of months ago
// More features can be found here: https://www.happycoders.eu/java/java-22-features/
public class Java22FeaturesMain {

    // 1
    // 12
    // 123
    // 1234
    // 12345

    void main() {
        System.out.println("Java 22!");

        Stream.of(1, 2, 3, 4, 5)
            .gather(new SequentialScanGatherer())
                .forEach(System.out::println);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        List<List<Integer>> slidingWindows = numbers.stream()
                .gather(Gatherers.windowSliding(3))
                .toList();

        System.out.println(slidingWindows);
    }
}
