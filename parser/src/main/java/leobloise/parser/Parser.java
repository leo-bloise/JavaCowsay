package leobloise.parser;

import java.util.List;
import java.util.Map;

interface Parser<T> {
    List<OptionType> getOptionTypes();
    T buildCommand();
}