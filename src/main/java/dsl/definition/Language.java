package dsl.definition;

import common.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Language {
    private final LookupService lookup;
    private Map<String,Construct> constructs = new HashMap<>();

    public Language(LookupService lookup) {
        this.lookup = lookup;
    }

    protected Language lookup(String name) {
        return lookup.lookup(name);
    }

    public Construct get(String name) {
        return constructs.get(name);
    }

    public Construct define(String name, Pair<String,String> ... members) {
        return constructs.put(name, new Construct());
    }

    public Language view(String... constructs) {
        Language nLang = new Language(lookup);
        for ( String name : constructs) {
            nLang.constructs.put(name, get(name));
        }
        return nLang;
    }

    public Pair<String, String> restrict(String type, Pair<String, String>... restrictions) {
        Construct construct = get(type);

        return null;
    }
}
