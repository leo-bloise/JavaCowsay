package leobloise.cowsay.utils;

import leobloise.cowsay.commands.CowsayCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {
    private final CowsayCommand cowsayCommand;
    public CommandLineParser(CowsayParser cowsayParser) {
        this.cowsayCommand = cowsayParser.buildCommand();
    }
    public String getAnimal() {
        return cowsayCommand.animal();
    }
    public String[] getMessage() {
        String message = cowsayCommand.message();
        int lineSize = 110;
        if(message.length() < lineSize) {
            return new String[] { message.trim() };
        }
        List<String> lines = new ArrayList<>(message.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            sb.append(message.charAt(i));
            if ((i % lineSize) != 0 || i == 0) continue;
            lines.add(sb.toString());
            sb.setLength(0);
        }
        if(!sb.isEmpty()) {
            lines.add(sb.toString());
        }
        return lines.toArray(new String[0]);
    }
}
