package leobloise.cowsay.commands;

import leobloise.parser.Command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HelpCommand implements Command {
    private String message = """
    cowsay - Generates a cow saying something provided by the user
    Usage: cowsay <message> [options]
           cowsay [options]
    
    Cowsay can generates an animal saying something provided by the user. If no input is provided, then "Hello World" is displayed.
    If no animal is provided, then the default one is displayed (cow). 
    """;
    private boolean full;
    private String[] options = {
            "--message: Provide a message to the animal",
            "--animal: Provide an animal to say the message",
            "--help: Display this message"
    };
    public HelpCommand(boolean full) {
        this.full = full;
    }
    @Override
    public void execute() {
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        if (!full) {
            sb.append("\n\n")
                    .append("For a listing of options, use cowsay --help");
            System.out.println(sb);
            return;
        };
        sb.append("\n")
                .append("Options:")
                .append("\n\n")
                .append(String.join("\n", options));
        System.out.println(sb);
    }
}
