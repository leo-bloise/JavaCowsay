package leobloise.parser;

public enum OptionType {
    VALUE, // Contains the value in the next parameter
    BOOLEAN, // It's basically a flag.
    PARAMETER, // Parameter for the last option passed.
    COMPLETE // Contains the option and the value with =
}
