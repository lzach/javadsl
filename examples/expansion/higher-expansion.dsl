(Expansions
    name:Expansion
    default_expansion:(Call function:doStatic args:(ArgList(Arg name:ast value:ast)))
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
                                  (members name:expansions template:(Case value:(String value:(static_member name:type)) block:(Return value:(Call function:(concat  expand (static_member name:type)) args:(ArgList (Arg name:ast2 value:ast))))))
                                  (Default block:(If (Call function:(Member lhs:ast rhs:hasMember) args:(ArgList (Arg name:name value:"default_expansion"))) code:(Block
                                        (Return value:(member name:default_expansion))
                                      ) otherwise (Block
                                        (Return value:(Call function:doSimpleStatic args:(ArgList (Arg name:ast value:ast))))
                                      )
                                  ))
                             ))
                         ))
                     )
                     (members name:expansions template:(Method name:(concat expand (static_member name:type)) returnType:AST params:(ParamList (Param name:ast type:AST))
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
            ($Assign lhs:$builder rhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$name value:($Call function:$expand args:($ArgList ($Arg name:$ast value:($Call function:($Member lhs:$ast rhs:$get) args:($ArgList ($Arg name:$name value:$"name"))))))))))
            ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:($Call function:($Member lhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$ast value:($Convert type:$AST value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList)))))) rhs:$add) args:($ArgList ($Arg name:$ast value:$builder))) rhs:$create) args:($ArgList) ))))

            ($If cond:($Call function:($Member lhs:$ast rhs:$hasMember) args:($ArgList ($Arg name:$name value:$"template"))) code:($Block
                 (Call function:pushAST args:(ArgList (Arg name:ast value:ast)))
                 (Assign
                    lhs:ast
                    rhs:(Call
                          function:expand
                          args:(ArgList
                                (Arg name:ast value:(Call
                                      function:(Member lhs:ast rhs:get)
                                      args:(ArgList (Arg name:name value:(Call
                                          function:(Member
                                              lhs:(Call
                                                  function:(Member lhs:bQue rhs:pop)
                                                  args:(ArgList)
                                              )
                                              rhs:toString
                                          )
                                          args:(ArgList)
                                      )))
                                ))
                          )
                    )
                 )
                 ($Assign lhs:$builder rhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$name value:($Call function:$expand args:($ArgList ($Arg name:$ast value:($Call function:($Member lhs:$ast rhs:$get) args:($ArgList ($Arg name:$name value:$"name"))))))))))
                 ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:($Call function:($Member lhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$ast value:($Convert type:$AST value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList)))))) rhs:$add) args:($ArgList ($Arg name:$ast value:$builder))) rhs:$create) args:($ArgList) ))))
                 (Assign
                     lhs:ast
                     rhs:(Call function:popAST args:(ArgList ))
                 )
            ) otherwise:($Block
                (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:value value:(Call
                      function:expand
                      args:(ArgList
                            (Arg name:ast value:(Call
                                  function:(Member lhs:ast rhs:get)
                                  args:(ArgList (Arg name:name value:(Call
                                      function:(Member
                                          lhs:(Call
                                              function:(Member lhs:bQue rhs:pop)
                                              args:(ArgList)
                                          )
                                          rhs:toString
                                      )
                                      args:(ArgList)
                                  )))
                            ))
                      )
                ))))
            ))
            ($Assign lhs:$builder rhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$name value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList))))))
            ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:($Call function:($Member lhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$ast value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList))))) rhs:$add) args:($ArgList ($Arg name:$ast value:$builder))) rhs:$create) args:($ArgList)))))
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
                            (Arg name:name value:(String value:($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:$ast rhs:$get) args:($ArgList ($Arg name:$name value:$"name"))))))))
                        )
                    )
                )
             ) otherwise:($Block (Block)))
             ($Assign lhs:$builder rhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$name value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList))))))
             ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:($Call function:($Member lhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$ast value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList))))) rhs:$add) args:($ArgList ($Arg name:$ast value:$builder))) rhs:$create) args:($ArgList)))))


             (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:typeName value:"List"))))
             (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:value value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))))

             (If cond:(Call function:(Member lhs:ast rhs:isMembers) args:(ArgList)) code:(Block
                (For var:(Define name:member type:String) expr:(Call function:(Member lhs:ast rhs:getMembers) args:(ArgList)) code:(Block
                    (Assign lhs:ast rhs:(Call function:peekAST args:(ArgList )))
                    (Assign lhs:ast rhs:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:member))))
                    ($Assign lhs:$builder rhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$name value:($Call function:$expand args:($ArgList ($Arg name:$ast value:($Call function:($Member lhs:$ast rhs:$get) args:($ArgList ($Arg name:$name value:$"template"))))))))))
                    ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:($Call function:($Member lhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$ast value:($Convert type:$AST value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList)))))) rhs:$add) args:($ArgList ($Arg name:$ast value:$builder))) rhs:$create) args:($ArgList) ))))
                    (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))))
                    (Assign
                        lhs:builder
                        rhs:(Convert
                            type:ASTBuilder
                            value:(Call
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
             ) otherwise:(If cond:(Call function:(Member lhs:ast rhs:isList) args:(ArgList)) code:(Block
                    (For var:(Define name:member type:AST) expr:(Call function:(Member lhs:ast rhs:getMemberList) args:(ArgList)) code:(Block
                        (Assign lhs:ast rhs:member)
                        ($Assign lhs:$builder rhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$name value:($Call function:$expand args:($ArgList ($Arg name:$ast value:($Call function:($Member lhs:$ast rhs:$get) args:($ArgList ($Arg name:$name value:$"template"))))))))))
                        ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:($Call function:($Member lhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$ast value:($Convert type:$AST value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList)))))) rhs:$add) args:($ArgList ($Arg name:$ast value:$builder))) rhs:$create) args:($ArgList) ))))
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
                ) otherwise:(Block
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
        (Expansion type:concat expansion:($Block
            (Assign
                lhs:builder
                rhs:(New type:ASTBuilder args:(ArgList
                    (Arg name:name value:($Call
                         function:($Member lhs:$bQue rhs:$push)
                         args:($ArgList
                                ($Arg name:$value value:($Call
                                    function:($Member lhs:$AST rhs:$STRLit)
                                    args:($ArgList ($Arg name:$string value:($Call
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
                                    )))
                                ))
                         )
                    ))
                ))
            )
            (Call function:(Member lhs:bQue rhs:push) args:(ArgList
                (Arg name:value value:(Call
                    function:(Member lhs:builder rhs:create)
                    args:(ArgList)
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
                ($For var:($Define name:$member type:$AST) expr:($Call function:($Member lhs:$ast rhs:$getMemberList) args:($ArgList)) code:($Block
                    ($Assign lhs:$builder rhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$name value:($Call function:$expand args:($ArgList ($Arg name:$ast value:$member)))))))
                    ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:($Call function:($Member lhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$ast value:($Convert type:$AST value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList)))))) rhs:$add) args:($ArgList ($Arg name:$ast value:$builder))) rhs:$create) args:($ArgList) ))))

                    (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))))
                    (Call
                        function:(Member lhs:bQue rhs:push)
                        args:(ArgList
                            (Arg name:ast value:(Call
                                function:(Member
                                    lhs:(Call
                                        function:(Member
                                            lhs:(New type:ASTBuilder args:(ArgList
                                                (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)))
                                            ))
                                            rhs:addAll
                                        )
                                        args:(ArgList
                                            (Arg name:ast value:(Call
                                                function:(Member lhs:builder rhs:getList)
                                                args:(ArgList)
                                            ))
                                        )
                                    )
                                    rhs:create
                                )
                                args:(ArgList)
                            ))
                        )
                    )
                    ($Assign lhs:$builder rhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$name value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList))))))
                    ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:($Call function:($Member lhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$ast value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList))))) rhs:$add) args:($ArgList ($Arg name:$ast value:$builder))) rhs:$create) args:($ArgList)))))
                ))
            ) otherwise:($Block
                 (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(Call
                    function:(Member
                        lhs:(Call
                            function:(Member
                                lhs:(New
                                    type:ASTBuilder
                                    args:(ArgList
                                        (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)))
                                    )
                                )
                                rhs:set
                            )
                            args:(ArgList (Arg name:value value:""))
                        )
                        rhs:create
                    )
                    args:(ArgList)
                 ))))
                 ($For var:($Define name:$member type:$AST) expr:($Call function:($Member lhs:$ast rhs:$getMemberList) args:($ArgList)) code:($Block
                       ($Assign lhs:$builder rhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$name value:($Call function:$expand args:($ArgList ($Arg name:$ast value:$member)))))))
                       ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:($Call function:($Member lhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$ast value:($Convert type:$AST value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList)))))) rhs:$add) args:($ArgList ($Arg name:$ast value:$builder))) rhs:$create) args:($ArgList) ))))

                       (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))))
                       (Call
                             function:(Member lhs:bQue rhs:push)
                             args:(ArgList
                                 (Arg name:ast value:(Call
                                     function:(Member
                                         lhs:(Call
                                             function:(Member
                                                 lhs:(New type:ASTBuilder args:(ArgList
                                                     (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)))
                                                 ))
                                                 rhs:setAdd
                                             )
                                             args:(ArgList
                                                 (Arg name:ast value:(Call
                                                     function:(Member lhs:builder rhs:getValue)
                                                     args:(ArgList)
                                                 ))
                                             )
                                         )
                                         rhs:create
                                     )
                                     args:(ArgList)
                                 ))
                             )
                         )
                         ($Assign lhs:$builder rhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$name value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList))))))
                         ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:($Call function:($Member lhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$ast value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList))))) rhs:$add) args:($ArgList ($Arg name:$ast value:$builder))) rhs:$create) args:($ArgList)))))
                 ))
                 ($Assign lhs:$builder rhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$name value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList))))))
                 ($Call function:($Member lhs:$bQue rhs:$push) args:($ArgList ($Arg name:$value value:($Call function:($Member lhs:($Call function:($Member lhs:($New type:$ASTBuilder args:($ArgList ($Arg name:$ast value:($Call function:($Member lhs:$bQue rhs:$pop) args:($ArgList))))) rhs:$add) args:($ArgList ($Arg name:$ast value:$builder))) rhs:$create) args:($ArgList)))))
            ))
       ))
       (Expansion type:expFunName expansion:(concat expand (member name:type)))
       (Expansion type:hasMember expansion:(concat expand (member name:type)))
       (Expansion type:onASTType expansion:(concat expand (member name:type)))
       (Expansion type:expand expansion:(concat expand (member name:type)))
       (Expansion type:push expansion:(concat expand (member name:type)))
       (Expansion type:pop expansion:(concat expand (member name:type)))
    )
)