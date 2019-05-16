package dsl.definition;

import java.util.HashMap;
import java.util.Map;

public class Languages {
    private static Map<String, Language> languageMap = new HashMap<>();
    public static Language load(String language) {
        return languageMap.get(language);
    }
    public static Language register(String languageName, Language language) {
        if ( languageMap.containsKey(languageName)) {
            throw new IndexOutOfBoundsException("Already exist");
        }
        languageMap.put(languageName, language);
        return language;
    }
}
