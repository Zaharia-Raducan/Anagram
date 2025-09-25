package io.dev.anagram;

import java.util.List;

public final class ImmPerson {

    private final String name;
    private final int age;
    private final List<String> attr;

    ImmPerson(String name, int age, List<String> attr) {
        this.name = name;
        this.age = age;
        this.attr = List.copyOf(attr);
    }

    public List<String> getAttr() {
        return this.attr;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private int age;
        private List<String> attr = List.of();

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder attr(List<String> attr) {
            this.attr = attr;
            return this;
        }

        public ImmPerson build() {
            return new ImmPerson(name, age, attr);
        }
    }
}
