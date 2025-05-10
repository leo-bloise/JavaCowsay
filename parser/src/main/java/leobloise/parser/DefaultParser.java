package leobloise.parser;

import java.util.*;

public abstract class DefaultParser<C> implements Parser<Command> {
    private final String[] args;
    private int lastPositionEvaluated = 0;
    private final Map<Integer, OptionType> optionTypes = new LinkedHashMap<>();
    public DefaultParser(String[] args) {
        this.args = args;
    }
    private OptionType evaluateOptionType(String arg) {
        if (arg.startsWith("-") && arg.contains("=")) {
            optionTypes.put(lastPositionEvaluated, OptionType.COMPLETE);
            return OptionType.COMPLETE;
        }
        if (lastPositionEvaluated == (args.length - 1)) {
            if (!arg.startsWith("-")) {
                optionTypes.put(lastPositionEvaluated, OptionType.VALUE);
                return OptionType.VALUE;
            }
            optionTypes.put(lastPositionEvaluated, OptionType.BOOLEAN);
            return OptionType.BOOLEAN;
        }
        if (arg.startsWith("-") && !arg.contains("=")) {
            int oldPosition = lastPositionEvaluated;
            String nextArg = args[++lastPositionEvaluated];
            OptionType optionType = evaluateOptionType(nextArg);
            if (optionType == OptionType.VALUE) {
                optionTypes.put(oldPosition, OptionType.PARAMETER);
                return OptionType.PARAMETER;
            }
            optionTypes.put(oldPosition, OptionType.BOOLEAN);
            return OptionType.BOOLEAN;
        }
        optionTypes.put(lastPositionEvaluated, OptionType.VALUE);
        return OptionType.VALUE;
    }
    private OptionType[] convertToArray() {
        OptionType[] types = new OptionType[optionTypes.size()];
        optionTypes.forEach((index, optionTypes) -> {
            types[index] = optionTypes;
        });
        return types;
    }
    @Override
    public List<OptionType> getOptionTypes() {
        if (args.length == 0) {
            return Arrays.stream(convertToArray()).toList();
        }
        if (!optionTypes.isEmpty()) return Arrays.stream(convertToArray()).toList();
        while (lastPositionEvaluated < args.length) {
            String arg = args[lastPositionEvaluated];
            evaluateOptionType(arg);
            lastPositionEvaluated++;
        }
        return Arrays.stream(convertToArray()).toList();
    }
    protected String getArg(int index) {
        if (index >= args.length || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        return args[index];
    }
}
