package leobloise.cowsay.utils;

import leobloise.cowsay.commands.CowsayCommand;
import leobloise.parser.DefaultParser;
import leobloise.parser.OptionType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CowsayParser extends DefaultParser<CowsayCommand> {
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
                case VALUE -> map.put("message", arg);
            }
        }
    }
    @Override
    public CowsayCommand buildCommand() {
        mapParams();
        return new CowsayCommand(
                map.getOrDefault("message", "Hello World"),
                map.getOrDefault("animal", "default")
        );
    }
}
;