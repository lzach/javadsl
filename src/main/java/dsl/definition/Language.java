package dsl.definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Language {
    private Map<String,Construct> constructs = new HashMap<>();

    public Construct get(String name) {
        return constructs.get(name);
    }
}
