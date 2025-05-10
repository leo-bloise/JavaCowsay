package leobloise.cowsay.utils;

import leobloise.parser.Command;
import leobloise.cowsay.commands.CommandFactory;
import leobloise.parser.DefaultParser;
import leobloise.parser.OptionType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CowsayParser extends DefaultParser<Command> {
    private Map<String, String> map = new HashMap<>();
    public CowsayParser(String[] args) {
        super(args);
    }
    private void handleCompleteArg(String arg) {
        String[] data = arg.split("=");
        map.put(data[0], data[1]);
    }
    private void handleBooleanArg(String arg) {
        map.put(arg, null);
    }
    private void handleParameterArg(String arg, int i) {
        map.put(arg, getArg(i + 1));
    }
    private void mapParams() {
        List<OptionType> optionTypes = getOptionTypes();
        if (optionTypes.isEmpty()) return;
        for (int i = 0; i < optionTypes.size(); i++) {
            OptionType optionType = optionTypes.get(i);
            String arg = getArg(i);
            arg = arg.replaceAll("-", "");
            switch (optionType) {
                case COMPLETE -> handleCompleteArg(arg);
                case BOOLEAN -> handleBooleanArg(arg);
                case PARAMETER -> handleParameterArg(arg, i++);
                case VALUE -> {
                    if (map.containsKey("message")) continue;
                    map.put("message", arg);
                }
            }
        }
    }
    @Override
    public Command buildCommand() {
        mapParams();
        CommandFactory commandFactory = new CommandFactory();
        return commandFactory.build(map);
    }
}
;