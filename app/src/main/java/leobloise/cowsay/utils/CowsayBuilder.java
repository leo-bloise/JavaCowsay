package leobloise.cowsay.utils;

import java.util.List;

public class CowsayBuilder {
    private String bubble;
    private String message;
    private List<String> animal;
    private final AnimalLoader animalLoader;
    public CowsayBuilder(
            AnimalLoader animalLoader
    ) {
        this.animalLoader = animalLoader;
    }
    public CowsayBuilder buildBubble(String[] message) {
        StringBuilder stringBuilder = new StringBuilder();
        int maxSize = message[0].length();
        stringBuilder.append("  ")
                .append("-".repeat(maxSize))
                .append("\n");
        for(String line: message)
            stringBuilder
                    .append("< ")
                    .append(line)
                    .append(" ".repeat(maxSize - line.length()))
                    .append(" >")
                .append("\n");
        stringBuilder
                .append("  ")
                .append("-".repeat(maxSize));
        this.bubble = stringBuilder.toString();
        this.message = message[0];
        return this;
    }
    public CowsayBuilder buildAnimal(String animal) {
        this.animal = this.animalLoader.loadAnimal(animal);
        return this;
    }
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(bubble).append("\n");
        animal.forEach(line -> {
            sb.append(" ".repeat(message.length() + 2)).append(line).append("\n");
        });
        return sb.toString();
    }
}
