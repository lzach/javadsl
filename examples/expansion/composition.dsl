(Expansions
    name:Composition
    expansions:(Expansions
        (Expansion type:Language expansion:(List
                                            (Import name:(Name java util Arrays))
                                            (Import name:(Name java util Map))
                                            (Import name:(Name java util HashMap))
                                            (Import name:(Name java util Deque))
                                            (Import name:(Name java util ArrayDeque))
                                            (Import name:(Name dsl ast AST))
                                            (Import name:(Name dsl ast ASTBuilder))
                                            (Class modifier:public name:Composition attrs:(AttrList) base:(Name dsl composition Composer)
                                                 attrs: (AttrList)
                                                 cons:(ConsList)
                                                 methods:(concat
                                                     (MethodList
                                                         (Method name:compose params:(ParamList) returnType:Language code:(Block
                                                            (members template:(expand))
                                                         ))
                                                     )
                                                 )
                                            )
                                      ))
        (Expansion type:Include expansion:())
        (Expansion type:Combine expansion:())
        (Expansion type:Restrict expansion:())
    )
)