package leobloise.cowsay;

public class Animal {
    private String animal;
    public Animal(String animal) {
        this.animal = animal;
    }
    public String get() {
        if (animal == null) return "default";
        return animal;
    }
}
