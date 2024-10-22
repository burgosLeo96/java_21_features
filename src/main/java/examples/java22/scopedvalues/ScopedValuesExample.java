package examples.java22.scopedvalues;

// A scoped value is value that may be safely and efficiently shared to methods without using method parameters
// They are preferred over thread-local variables,
public class ScopedValuesExample {

    // SUPPORT OF JAVA 22 FINISHED IN SEPTEMBER.
    // CHECK THE STATUS OF THESE FEATURES IN JAVA 23.
    // KEEP IN MIND THAT SOME OF THESE FEATURES MAYBE CHANGED / UPDATED IN JAVA 23.

    private static final ScopedValue<String> USER_ID = ScopedValue.newInstance();

    private record User(String name, int age) {}

    public User getUserDetails(String userId) {
        return new User(userId, 20);
    }

    public void getFarewellMessage() {
        String userId = USER_ID.get();
        String username = getUserDetails(userId).name();
        System.out.println(STR."Farewell, \{username}!");
    }

    public void getWelcomeMessage(String userId) {
        ScopedValue.where(USER_ID, userId).run(() -> {
            String username = getUserDetails(userId).name();
            System.out.println(STR."Welcome, \{username}!");
            getFarewellMessage();
        });
    }

    void main() {
        getWelcomeMessage("user1");
        getWelcomeMessage("user2");
    }
}
