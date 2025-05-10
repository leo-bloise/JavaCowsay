package leobloise.cowsay;

import leobloise.cowsay.utils.AnimalLoader;
import leobloise.cowsay.utils.CommandLineParser;
import leobloise.cowsay.utils.CowsayBuilder;
import leobloise.cowsay.utils.CowsayParser;

public class App {
    private static CommandLineParser commandLineParser(String[] args) {
        CowsayParser cowsayParser = new CowsayParser(args);
        return new CommandLineParser(cowsayParser);
    }
    private static CowsayBuilder cowsayBuilder() {
        return new CowsayBuilder(new AnimalLoader());
    }
    public static void main(String[] args) {
        CommandLineParser parser = commandLineParser(args);
        CowsayBuilder builder = cowsayBuilder();
        String[] message = parser.getMessage();
        System.out.println(builder.buildBubble(message)
                .buildAnimal(parser.getAnimal())
                .build());
    }
}
