package leobloise.cowsay.utils;

import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {
    private final String[] args;
    public CommandLineParser(String[] args) {
        this.args = args;
    }
    public String getAnimal() {
        if(args.length <= 1) {
            return "default";
        }
        return args[1];
    }
    public String[] getMessage(String defaultMessage) {
        if (args.length == 0) return new String[] {defaultMessage};
        String message = args[0];
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
