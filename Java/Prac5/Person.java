package Prac5;

public class Person {
    public String name;
    public int timeLeft;

    public Person(String name, int timeLeft) {
        this.name = name;
        this.timeLeft = timeLeft;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", timeLeft=" + timeLeft +
                '}';
    }
}

