package examples.java22.gatherer;

import java.util.function.Supplier;
import java.util.stream.Gatherer;


// <T -> tipo de datos que el Gatherer consume,
//  A -> tipo del estado intermedio,
//  R -> tipo de los datos de salida del Gatherer >

// Para este ejemplo:
// T -> Integer
// A -> State
// R -> String
public class SequentialScanGatherer<T> implements Gatherer<Integer, SequentialScanGatherer.State, String> {

    public static class State {
        String current = "";
    }

    @Override
    public Supplier<State> initializer() {
        return State::new;
    }

    @Override
     // <A, T, R>
    public Integrator<State, Integer, String> integrator() {
        return Integrator.ofGreedy(((state, element, downstream) -> {
            state.current = state.current.concat(String.valueOf(element));
            return downstream.push(state.current);
        }));
    }
}
