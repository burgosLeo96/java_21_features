package examples.java21.recordpattern;

import examples.java21.recordpattern.records.User;

import static java.lang.StringTemplate.STR;

public class RecordPattern {

    public static void processUser(Object user) {
        if(user instanceof User(var name, var lastName, var age)) {
            System.out.println(STR."\{name} \{lastName} is \{age} years old");
        }
    }

    public static void main(String[] args) {
        processUser(new User("John", "Smith", 18));
    }

}
