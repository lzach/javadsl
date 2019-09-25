package dsl.definition.impl;

import common.util.Pair;
import dsl.definition.Language;
import dsl.definition.LookupService;

public class LanguageLanguage extends Language {
    public LanguageLanguage(LookupService lookupService) {
        super(lookupService);
        Language std = lookup("standard").view("");
        define("Members", std.restrict("List", new Pair<>("ListType", "Member")));
    }


}
