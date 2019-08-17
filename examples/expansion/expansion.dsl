(Expansions
    name:Expansion
    expansions:(ExpansionList
        (Expansion type:Expansions expansion:(List
            (Import name:(Name java util Arrays))
            (Import name:(Name java util Map))
            (Import name:(Name java util HashMap))
            (Import name:(Name java util Deque))
            (Import name:(Name java util ArrayDeque))
            (Import name:(Name java util Collections))
            (Import name:(Name java util List))
            (Import name:(Name java util ArrayList))
            (Import name:(Name dsl ast AST))
            (Import name:(Name dsl ast ASTBuilder))
            (Class modifier:public name:(concat Expansion (static_member name:name)) attrs:(AttrList) base:(Name dsl expansion Expansion)
                 attrs: (AttrList)
                 cons:(ConsList
                      (Constructor params:(ParamList (Param name:ast type:AST)) code:(Block
                          (Call function:super args:(ArgList (Arg name:ast value:ast)))
                          (Call function:update args:(ArgList))
                      ))
                 )
                 methods:(concat
                     (MethodList
                         (Method name:expand params:(ParamList ) returnType:AST code:(Block
                            (Return value:(Call function:expand args:(ArgList (Arg name:ast value:expansion))))
                         ))
                         (Method name:expand params:(ParamList (Param name:ast type:AST)) returnType:AST code:(Block
                             (Define type:ASTBuilder name:builder)
                             (Define type:(Generic type:Deque gens:(List AST)) name:bQue)
                             (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList)))
                             (Assign lhs:bQue rhs:(New type:(Generic type:ArrayDeque gens:(List AST)) args:(ArgList)))
                             (Select value:(Call function:(Member lhs:ast rhs:getTypeName) args:(ArgList)) block:(List
                                  (members name:expansions template:(Case value:(String value:(static_member name:type)) block:(Return value:(Call function:(expFunName type:(static_member name:type)) args:(ArgList (Arg name:ast2 value:ast))))))
                                  (Default block:(Return value:(Call function:doStatic args:(ArgList
                                        (Arg name:ast value:ast))
                                  )))

                             ))
                         ))
                     )
                     (members name:expansions template:(Method name:(expFunName type:(static_member name:type) ) returnType:AST params:(ParamList (Param name:ast type:AST))
                        code:(Block
                             (Define type:ASTBuilder name:builder)
                             (Define type:(Generic type:Deque gens:(List AST)) name:bQue)
                             (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList)))
                             (Assign lhs:bQue rhs:(New type:(Generic type:ArrayDeque gens:(List AST)) args:(ArgList)))

                             (member name:expansion)

                             (Return value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)))
                        )
                     ))
                 )
            )
        ))
        (Expansion type:Expansion expansion:(Block
             (Define type:ASTBuilder name:builder)
             (Define type:Deque name:bQue)
             (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList)))
             (Assign lhs:bQue rhs:(New type:ArrayDeque args:(ArgList)))
             (Return value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)))
        ))
        (Expansion type:member expansion:(List
            ($Call
                function:($Member lhs:$bQue rhs:$push)
                args:($ArgList ($Arg name:$ast value:($Call
                    function:$expand
                    args:($ArgList ($Arg name:$ast value:($Call
                        function:($Member lhs:$ast rhs:$get)
                        args:($ArgList ($Arg name:$name value:$"name"))
                    )))
                )))
            )
            ($If cond:($Call function:($Member lhs:$ast rhs:$hasMember) args:($ArgList ($Arg name:$name value:$"template"))) code:($Block
                 (Block
                     (Call function:pushAST args:(ArgList (Arg name:ast value:ast)))
                     (Assign
                        lhs:ast
                        rhs:(Call
                            function:(Member lhs:ast rhs:get)
                            args:(ArgList (Arg name:name value:(Call
                                function:(Member
                                    lhs:(Call
                                        function:(Member lhs:bQue rhs:pop)
                                        args:(ArgList)
                                    )
                                    rhs:toString)
                                args:(ArgList)
                            )))
                        )
                     )
                     ($Call
                         function:($Member lhs:$builder rhs:$add)
                         args:($ArgList ($Arg name:$ast value:($Call
                             function:$expand
                             args:($ArgList ($Arg name:$ast value:($Call
                                 function:($Member lhs:$ast rhs:$get)
                                 args:($ArgList ($Arg name:$name value:$"template"))
                             )))
                         )))
                     )
                     (Assign
                         lhs:ast
                         rhs:(Call function:popAST args:(ArgList ))
                     )
                 )
            ) otherwise:($Block
                ($Call
                    function:($Member lhs:$bQue rhs:$push)
                    args:($ArgList ($Arg name:$ast value:($Call
                        function:$expand
                        args:($ArgList ($Arg name:$ast value:($Call
                            function:($Member lhs:$ast rhs:$get)
                            args:($ArgList ($Arg name:$name value:($Call
                                function:($Member
                                    lhs:($Call
                                        function:($Member lhs:$bQue rhs:$pop)
                                        args:($ArgList)
                                    )
                                    rhs:$toString)
                                args:($ArgList)
                            ))))
                        )))
                )))
            ))
        ))
        (Expansion type:static_member expansion:(List
           ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList
                ($Arg name:$value value:($Call
                       function:$expand
                       args:($ArgList ($Arg name:$ast value:($Call
                          function:($Member lhs:$ast rhs:$get)
                          args:($ArgList ($Arg name:$name value:$"name"))
                       )))
                ))
           ))
           ($Assign
               lhs:$builder
               rhs:($New
                   type:$ASTBuilder
                   args:($ArgList
                       ($Arg
                           name:$ast
                           value:($Convert
                               type:$AST
                               value:($Call
                                   function:($Member
                                       lhs:$bQue
                                       rhs:$pop
                                   )
                                   args:($ArgList)
                               )
                           )
                       )
                   )
               )
           )
           ($Call
               function:($Member
                   lhs:$bQue
                   rhs:$push
               )
               args:($ArgList
                   ($Arg
                       name:$ast
                       value:($Call
                           function:($Member
                               lhs:($Call
                                   function:($Member
                                       lhs:($New
                                           type:$ASTBuilder
                                           args:($ArgList
                                               ($Arg
                                                   name:$ast
                                                   value:($Convert
                                                       type:$AST
                                                       value:($Call
                                                           function:($Member lhs:$bQue rhs:$pop)
                                                           args:($ArgList)
                                                       )
                                                   )
                                               )
                                           )
                                       )
                                       rhs:$add
                                   )
                                   args:($ArgList ($Arg name:$ast value:$builder))
                               )
                               rhs:$create
                           )
                           args:($ArgList)
                       )
                   )
               )
           )
           (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:value value:(Call
               function:(Member lhs:ast rhs:get)
               args:(ArgList (Arg name:ast value:(Call
                    function:(Member
                        lhs:(Call
                            function:(Member lhs:bQue rhs:pop)
                            args:(ArgList )
                        )
                        rhs:toString
                    )
                    args:(ArgList)
               )))
           ))))
        ))
        (Expansion type:members expansion:(List
             (Call function:pushAST args:(ArgList (Arg name:ast value:ast)))
             ($If cond:($Call function:($Member lhs:$ast rhs:$hasMember) args:($ArgList ($Arg name:$name value:$"name"))) code:($Block
                (Assign
                    lhs:ast
                    rhs:(Call
                        function:(Member
                            lhs:ast
                            rhs:get
                        )
                        args:(ArgList
                            (Arg name:name value:($List
                                ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:$expand args:($ArgList ($Arg name:$ast value:($Call function:($Member lhs:$ast rhs:$get) args:($ArgList ($Arg name:$name value:$"name")))))))))
                            ))
                        )
                    )
                )

             ) otherwise:($Block (Block)))

             (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:typeName value:"List"))))
             (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:value value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))))

             ($If cond:($Call function:($Member lhs:$ast rhs:$isMembers) args:($ArgList)) code:($Block
                ($For var:($Define name:$member type:$String) expr:($Call function:($Member lhs:$ast rhs:$getMembers) args:($ArgList)) code:($Block
                    (Block
                        (Assign lhs:ast rhs:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:"member"))))
                        (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))))
                                     (Assign
                                        lhs:builder
                                        rhs:(Call
                                            function:(Member
                                                lhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)))))
                                                rhs:add
                                            )
                                            args:(ArgList
                                                (Arg name:ast value:(Call
                                                    function:(Member
                                                        lhs:builder
                                                        rhs:create
                                                    )
                                                    args:(ArgList)
                                                ))
                                            )
                                        )
                                     )
                                     (Call
                                        function:(Member lhs:bQue rhs:push)
                                        args:(ArgList
                                            (Arg name:value value:(Call
                                                function:(Member lhs:builder rhs:create)
                                                args:(ArgList)
                                            ))
                                        )
                                     )
                    )
                ))
             ) otherwise:($If cond:($Call function:($Member lhs:$ast rhs:$isList) args:($ArgList)) code:($Block
                    ($For var:($Define name:$member type:$AST) expr:($Call function:($Member lhs:$ast rhs:$getMemberList) args:($ArgList)) code:($Block
                        (Block
                            (Assign lhs:ast rhs:member)
                            (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))))
                                (Assign
                                   lhs:builder
                                   rhs:(Call
                                       function:(Member
                                           lhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)))))
                                           rhs:add
                                       )
                                       args:(ArgList
                                           (Arg name:ast value:(Call
                                               function:(Member
                                                   lhs:builder
                                                   rhs:create
                                               )
                                               args:(ArgList)
                                           ))
                                       )
                                   )
                                )
                                (Call
                                   function:(Member lhs:bQue rhs:push)
                                   args:(ArgList
                                       (Arg name:value value:(Call
                                           function:(Member lhs:builder rhs:create)
                                           args:(ArgList)
                                       ))
                                   )
                                )
                        )
                    ))
                ) otherwise:($Block

                         (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))))
                         (Assign
                            lhs:builder
                            rhs:(Call
                                function:(Member
                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)))))
                                    rhs:add
                                )
                                args:(ArgList
                                    (Arg name:ast value:(Call
                                        function:(Member
                                            lhs:builder
                                            rhs:create
                                        )
                                        args:(ArgList)
                                    ))
                                )
                            )
                         )
                         (Call
                            function:(Member lhs:bQue rhs:push)
                            args:(ArgList
                                (Arg name:value value:(Call
                                    function:(Member lhs:builder rhs:create)
                                    args:(ArgList)
                                ))
                            )
                         )

                ))
             )
             (Assign lhs:ast rhs:(Call function:popAST args:(ArgList)))
        ))
        (Expansion type:concat expansion:(Block
            ($Assign
                lhs:($Define type:($Generic type:$List gens:($List $AST)) name:$parts)
                rhs:($New type:($Generic type:$ArrayList gens:($List $AST)) args:($ArgList))
            )
            ($For var:($Define type:$AST name:$member) expr:($Call function:($Member lhs:$ast rhs:$getMemberList) args:($ArgList)) code:($Block
                ($Call
                    function:($Member
                        lhs:$parts
                        rhs:$add
                    )
                    args:($ArgList
                        ($Arg name:$value value:($Call
                            function:$expand
                            args:($ArgList
                                ($Arg name:$ast value:$member)
                            )
                        ))
                    )
                )
            ))
            ($Assign
                lhs:$builder
                rhs:($New type:$ASTBuilder args:($ArgList
                    ($Arg name:$value value:$"List")
                ))
            )
            ($Call
                function:($Member
                    lhs:$builder
                    rhs:$addAll
                )
                args:($ArgList
                    ($Arg name:$value value:$parts)
                )
            )
            ($Call
                function:($Member
                    lhs:$bQue
                    rhs:$push
                )
                args:($ArgList
                    ($Arg name:$value value:($Call
                        function:($Member lhs:$builder rhs:$create)
                        args:($ArgList)
                    ))
                )
            )
            (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList)))
            (Call function:(Member lhs:builder rhs:setName) args:(ArgList
                (Arg name:name value:($List
                     ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList
                         ($Arg name:$ast value:($Call
                             function:($Member
                                 lhs:$AST
                                 rhs:$STRLit
                             )
                             args:($ArgList
                                 ($Arg name:$str value:($Call
                                         function:($Member
                                             lhs:($Index
                                                 lhs:($Call
                                                     function:($Member
                                                         lhs:$ast
                                                         rhs:$getMemberList
                                                     )
                                                     args:($ArgList)
                                                 )
                                                 rhs:$0
                                             )
                                             rhs:$getTypeName
                                         )
                                         args:($ArgList)
                                 ))
                             )
                         ))
                     ))

                ))
            ))
            ($If cond:($Call
                function:($Member
                    lhs:($Index
                        lhs:($Call
                            function:($Member
                                lhs:$ast
                                rhs:$getMemberList
                            )
                            args:($ArgList)
                        )
                        rhs:$0
                    )
                    rhs:$isList
                )
                args:($ArgList)
            ) code:($Block
                ($For init:($Assign lhs:($Define type:$int name:$i) rhs:$0) cond:($Lt lhs:$i rhs:($Call
                    function:($Member lhs:$parts rhs:$size) args:($ArgList)
                )) post:($Inc value:$i) code:($Block
                    (Call function:(Member lhs:builder rhs:addAll) args:(ArgList
                        (Arg name:ast value:(Call
                            function:(Member
                                lhs:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))
                                rhs:getMemberList
                            )
                            args:(ArgList)
                        ))
                    ))
                ))
            ) otherwise:($Block
                (Block
                    (Call
                        function:(Member lhs:builder rhs:set)
                        args:(ArgList
                            (Arg name:value value:(Call
                                function:(Member
                                    lhs:(Call
                                        function:(Member lhs:bQue rhs:pop)
                                        args:(ArgList)
                                    )
                                    rhs:toString
                                )
                                args:(ArgList)
                            ))
                        )
                    )
                    ($For init:($Assign lhs:($Define type:$int name:$i) rhs:$1) cond:($Lt lhs:$i rhs:($Call
                        function:($Member lhs:$parts rhs:$size) args:($ArgList)
                    )) post:($Inc value:$i) code:($Block
                        (Call function:(Member lhs:builder rhs:set) args:(ArgList
                            (Arg name:ast value:(Add
                                lhs:(Call function:(Member lhs:builder rhs:getValue) args:(ArgList))
                                rhs:(Call
                                    function:(Member
                                        lhs:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))
                                        rhs:toString
                                    )
                                    args:(ArgList)
                                )
                            ))
                        ))
                    ))
                    (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:value value:(Call
                        function:(Member lhs:builder rhs:create)
                        args:(ArgList)
                    ))))
                )
            ))
       ))
       (Expansion type:expFunName expansion:(concat expand (member name:type)))
    )
)