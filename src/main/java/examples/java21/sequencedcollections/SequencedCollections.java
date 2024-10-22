package examples.java21.sequencedcollections;

import java.util.*;
import java.util.stream.Collectors;

// Necessity: lack of uniform methods for accessing first and last elements and iterating in reverse order
// More info: https://www.baeldung.com/java-21-sequenced-collections
public class SequencedCollections {

    public static void sequencedList() {
        System.out.println("\n\nSequenced list");
        var sequencedList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        System.out.println("Before insertion:" + sequencedList);

        sequencedList.addFirst(0);
        sequencedList.addLast(11);

        System.out.println("After insertion: " + sequencedList);

        sequencedList.removeFirst();
        sequencedList.removeLast();

        System.out.println("After removing: " + sequencedList);

        // Before SequencedList interface
        System.out.println("Before SequencedList interface");
        System.out.println(sequencedList.stream().sorted(Comparator.reverseOrder()).map(String::valueOf).collect(Collectors.joining("-")));

        System.out.println("After SequencedList interface");
        System.out.println(sequencedList.stream().map(String::valueOf).collect(Collectors.joining("-")));
        System.out.println(sequencedList.reversed().stream().map(String::valueOf).collect(Collectors.joining("-")));
    }

    public static void sequencedSet() {
        System.out.println("\n\nSequenced set");
        var sequencedSet = new LinkedHashSet<>(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));

        System.out.println("Before insertion: " + sequencedSet);

        sequencedSet.addFirst(0);
        sequencedSet.addLast(1);

        System.out.println("After insertion: " + sequencedSet);

        sequencedSet.removeFirst();
        sequencedSet.removeLast();

        System.out.println("After removing: " + sequencedSet);
        System.out.println("Reversed set: " + sequencedSet.reversed());
    }

    public static void sequencedMap() {
        // Note: sequenced map has the following order:
        // The least recently inserted entry (the eldest) is first, and the youngest entry is last.

        System.out.println("\n\nSequenced map");
        // Initialization make not preserve the insertion order, so map at the first println would not look how we can see in the code
        var sequencedMap = new LinkedHashMap<>(Map.of('a', 1, 'b', 2, 'c', 3, 'd', 4, 'e', 5, 'f', 6));

        System.out.println(sequencedMap);

        sequencedMap.putFirst('q', 0);
        sequencedMap.putLast(null, 7);
        sequencedMap.put('g', -1);

        System.out.println("After insertion");
        System.out.println(sequencedMap);

        System.out.println("Sequenced map keys: " + sequencedMap.sequencedKeySet());
        System.out.println("Sequenced map values: " + sequencedMap.sequencedValues());

        sequencedMap.pollFirstEntry();
        sequencedMap.pollLastEntry();

        System.out.println("After removing");
        System.out.println(sequencedMap);

        var reversedMap = sequencedMap.reversed();

        System.out.println(reversedMap);

        // NOTE: important considerations: maybe custom implementations were made and can clash with the new methods.
    }

    public static void main(String[] args) {
        SequencedCollections.sequencedList();
        SequencedCollections.sequencedSet();
        SequencedCollections.sequencedMap();
    }

}
