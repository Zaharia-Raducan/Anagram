package io.dev.anagram;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ImmPerson person = ImmPerson.builder()
                .name("Vasile")
                .age(28)
                .attr(List.of("smart", "misunderstood", "quiet"))
                .build();

        // we can't
//        person.getAttr().add("");

        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.getAttr());
    }
}