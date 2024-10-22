package examples.java21.virtualthreads;

import examples.java17.RandomNumberGeneration;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

// They are an alternate implementation of the java.lang.Thread type,
// which stores the stack frames in the heap (garbage-collected memory) instead of the stack.
public class VirtualThreads {

    public static class ThreadDefinition implements Runnable {
        private static final ThreadLocal<Integer> threadSum = ThreadLocal.withInitial(() -> 0);

        @Override
        // Q: what does this method do?
        // A: This method increments a thread-local variable by 1 and prints the thread id and the sum.
        public void run() {
            IntStream.rangeClosed(0, 100).forEach(i -> {
                threadSum.set(threadSum.get() + 1);
                var currentThread = Thread.currentThread();
                System.out.println(STR."Thread \{currentThread} id: \{currentThread.threadId()} - Sum: \{threadSum.get()}. \n");
                try {
                    Thread.sleep(RandomNumberGeneration.generateRandomNumber(0, 500));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.printf("Thread with id %d finished. \n", Thread.currentThread().threadId());
        }
    }

    public static void classicThreadExample() {
        for (int i = 0; i < 100_000; i++) {
            System.out.println("Creating thread " + (i + 1));
            new Thread(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(1L));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    public static void simpleThreadExample() {
        var thread = Thread.startVirtualThread(() -> {});

        try {
            thread.join();
            System.out.println("Thread finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void virtualThreadPoolExample() {
        try (var virtualThreadPool = Executors.newFixedThreadPool(3)) {
            IntStream.range(0, 5).forEach(i -> virtualThreadPool.submit(new ThreadDefinition()));
        }
    }

    public static void virtualThreadPerTaskExample() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 15).forEach(i -> executor.submit(new ThreadDefinition()));
        }
    }

    public static void main(String[] args) {

        // Classic thread example
        // classicThreadExample();

        // Create a new simple virtual thread executor
        // simpleThreadExample();

        // Create a virtual thread pool
        // virtualThreadPoolExample();

        // Create a new virtual thread per task
        virtualThreadPerTaskExample();
    }

}
