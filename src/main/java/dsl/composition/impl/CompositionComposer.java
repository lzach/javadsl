package dsl.composition.impl;

import dsl.composition.Composer;
import dsl.definition.Language;
import dsl.definition.Languages;

public class CompositionComposer extends Composer  {
    @Override
    public Language compose() {
        // Do we want a Language collection from where languages can be read in?
        // We need a way to reference other languages.
        // preferably this would be able to read in from DSL files.
        Languages.load("");

       return null;
    }
}
