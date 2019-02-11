(Language
  (CreateLang name:AnySymbol    returnType:Any          arguments:NoneArgs)
  (CreateLang name:AnyRecord    returnType:AnyRecord    arguments:NoneArgs)
  (CreateLang name:AnyClass     returnType:AnyClass     arguments:NoneArgs)
  (CreateLang name:AnyLanguage  returnType:AnyLanguage  arguments:NoneArgs)
  (CreateLang name:AnyLangArray returnType:AnyLangArray arguments:NoneArgs)
  (CreateLang name:AnyFunction  returnType:AnyFunction  arguments:NoneArgs)
  (CreateLang name:AnyMethod    returnType:AnyMethod    arguments:NoneArgs)

  (Restrict superset:AnySymbol subset:AnyRecord)
  (Restrict superset:AnySymbol subset:AnyClass)
  (Restrict superset:AnySymbol subset:AnyLanguage)
  (Restrict superset:AnySymbol subset:AnyLangArray)
  (Restrict superset:AnySymbol subset:AnyFunction)
  (Restrict superset:AnySymbol subset:AnyMethod)

  (CreateLang name:Restrict returnType:AnyLanguage arguments:AnyArgs)
  (CreateLang name:Relax    returnType:AnyLanguage arguments:AnyArgs)
)


