package sample.TestCode;

import sample.Entity.Actor;
import sample.Entity.Person;
import sample.Entity.Programmer;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TestContainer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Person> people = new LinkedList<>();
        Programmer programmer = new Programmer("AAA", "BBB", "33333333333");
        Actor actor = new Actor("CCC", "DDD", "44444444444");
        Person person = new Person("CCC", "DDD", "44444444444");
//        Person actor2 = new Actor("CCC", "DDD", "44444444444"); // is there a reason this should be used?

        people.add(programmer);
        people.add(actor);
        people.add(person);

        for (int i = 0; i < people.size(); i++) {
            people.get(i).printPersonalInformation();
        }


        //main ends here
    }
}
