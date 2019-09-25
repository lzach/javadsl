package dsl.definition;

import common.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Construct {
    private final Map<String, Construct> members = new HashMap<>();
    private final Construct listType;
    private final String type;

    public Construct(Map<String, Construct> members) {
        this.members.putAll(members);
        this.listType = null;
        this.type = null;
    }

    public Construct(Construct listType) {
        this.listType = listType;
        this.type = null;
    }

    public Construct(String type) {
        this.listType = null;
        this.type = type;
    }


    public Construct(Pair<String, Construct> ... pairs) {
        this.listType = null;
        this.type = null;
    }
}
