(Expansions
    name:Language
    expansions:(List
        (Expansion type:Language expansion:(List
                (Import name:(Name java util Objects))
                (Import name:(Name java util Arrays))
                (Import name:(Name java util Map))
                (Import name:(Name java util HashMap))
                (Import name:(Name java util Deque))
                (Import name:(Name java util ArrayDeque))
                (Import name:(Name java util Collections))
                (Import name:(Name java util List))
                (Import name:(Name dsl definition LookupService))
                (Import name:(Name dsl definition Language))
                (Import name:(Name common util Pair))

                (Class modifier:public name:(concat Language (static_member name:name)) attrs:(AttrList) base:(Name dsl definition Language)
                     attrs: (AttrList)
                     cons:(ConsList
                       (Constructor params:(ParamList (Param name:lookup type:LookupService)) code:(Block
                            (Call function:super args:(ArgList (Arg name:lookup value:lookup)))
                            (members name:includes)
                            (members name:composed)
                            (members name:definitions)
                        ))
                     )
                     methods:(MethodList)
                )
        ))

        (Expansion type:Define expansion:(Call function:define args:(concat
                (ArgList (Arg name:name value:(String value:(static_member name:type))))
                (members name:members)
        )))
        (Expansion type:Member expansion:(New type:Pair args:(ArgList
            (Arg name:first value:(String value:(static_member name:name)))
            (Arg name:second value:(String value:(static_member name:type)))
        )))

        (Expansion type:Restrict expansion:(Call function:define args:(ArgList
                (Arg name:name value:(String value:(static_member name:name)))
                (Arg name:name value:(Call function:restrict args:(ArgList
                         (Arg name:name value:(String value:(static_member name:super)))
                         (Arg name:name value:(member name:restriction))
                )))
        )))
        (Expansion type:ExecVal expansion:(New
            type:(Generic gens:(List String String) type:Pair)
            args:(ArgList (Arg name:"type" value:"ExecVal") (Arg name:value value:(String value:(member name:type))))
        ))
        (Expansion type:ListType expansion:(New
           type:(Generic gens:(List String String) type:Pair)
           args:(ArgList (Arg name:"type" value:"ListType") (Arg name:value value:(String value:(member name:type))))
        ))

        (Expansion type:Include expansion:(List
            (Assign lhs:(Define name:(static_member name:name) type:Language) rhs:(Call function:lookup args:(ArgList (Arg name:name value:(String value:(static_member name:name))))))
            (Assign lhs:(static_member name:name) rhs:(Call function:(Member lhs:(static_member name:name) rhs:view) args:(members name:constructs template:(String value:(id)))))
        ))
    )
)