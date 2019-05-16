package dsl.definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Construct {
    private final Map<String, Construct> members = new HashMap<>();
    private final List<Construct> memberList = new ArrayList<>();
    private Class value;
}
