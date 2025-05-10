package leobloise.cowsay;

import leobloise.cowsay.utils.CowsayParser;
import leobloise.parser.Command;

public class App {
    public static void main(String[] args) {
        CowsayParser cowsayParser = new CowsayParser(args);
        Command command = cowsayParser.buildCommand();
        command.execute();
    }
}
