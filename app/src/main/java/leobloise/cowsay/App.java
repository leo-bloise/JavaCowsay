package leobloise.cowsay;

import leobloise.cowsay.utils.CommandLineParser;

public class App {
    public static void main(String[] args) {
        CommandLineParser commandLineParser = new CommandLineParser(args);
        String[] message = commandLineParser.getMessage("Hello World");
        CowsayBuilder cowsayBuilder = new CowsayBuilder(new AnimalLoader());
        if (message.length == 1) {
            cowsayBuilder.buildBubble(message[0]);
        } else {
            cowsayBuilder.buildBubble(message);
        }
        System.out.println(cowsayBuilder
                .buildAnimal(commandLineParser.getAnimal())
                .build());
    }
}
