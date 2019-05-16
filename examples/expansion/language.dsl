(Expansions
   name:Language
   expansions:(ExpansionList
      (Expansion type:Language expansion:(List
                                      (Import name:(Name java util Arrays))
                                      (Import name:(Name java util Map))
                                      (Import name:(Name java util HashMap))
                                      (Import name:(Name java util Deque))
                                      (Import name:(Name java util ArrayDeque))
                                      (Import name:(Name dsl ast AST))
                                      (Import name:(Name dsl ast ASTBuilder))
                                      (Class modifier:public name:(concat (member member:name) Composition) attrs:(AttrList) base:(Name dsl definition Language)
                                           attrs: (AttrList)
                                           cons:(ConsList
                                                (members member:definitions)
                                           )
                                           methods:(MethodList)
                                      )
                                )

      )
      (Expansion type:Type expansion:(Call function:define args:(concat (ArgList (Arg name:name value:(member member:name)) (members member:attrs) ))))
      (Expansion type:Attr expansion:(Convert type:(Array type:Object) expr:(Block (member member:name template:(String value:member)) (member member:type template:(String value:member)))))
   )
)