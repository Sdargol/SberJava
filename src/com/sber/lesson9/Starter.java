package com.sber.lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Starter {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        ArrayList<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Cat", 21),
                new Person("Human", 31),
                new Person("Programmer", 15),
                new Person("Tester", 100),
                new Person("Person", 1)
        ));

        //===============================

        System.out.println(XStream.of(list)
                .filter(e -> e > 1)
                .transform(e -> e + "b")
                .toMap(e->e, String::length, HashMap::new)
        );

        System.out.println(CStream.of(list)
                .filter(e -> e > 2)
                .transform(e -> e + "a")
                .toMap(e -> e, String::length, HashMap::new)
        );

        //===============================

        System.out.println(XStream.of(persons)
                .filter(p -> p.getAge() > 20)
                .transform(p -> new Person(p.getName(), p.getAge() + 5))
                .toMap(Person::getName, p -> p, HashMap::new)
        );

        System.out.println(CStream.of(persons)
                .filter(p -> p.getAge() > 1)
                .transform(p -> new Person(p.getName(), p.getAge() + 1000))
                .toMap(Person::getName, p -> p, HashMap::new)
        );


    }
}
