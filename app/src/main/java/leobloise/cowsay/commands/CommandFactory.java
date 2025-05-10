package leobloise.cowsay.commands;

import leobloise.cowsay.Animal;
import leobloise.cowsay.Message;
import leobloise.parser.Command;

import java.util.Map;

public class CommandFactory {
    public Command build(Map<String, String> params) {
        if (params.isEmpty()) return new HelpCommand(false);
        if (params.containsKey("help")) return new HelpCommand(true);
        return new CowsayCommand(
                new Message(params.get("message")),
                new Animal(params.get("animal"))
        );
    }
}
