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
                 attrs:(AttrList)
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
                                  (members name:expansions template:(Case value:(String value:(static_member name:type)) block:(Return value:(Call function:(concat expand (static_member name:type)) args:(ArgList (Arg name:ast value:ast))))))
                                  (Default block:(Block
                                      (Comment "This would constitute an error...")
                                  ))
                             ))
                             (Return value:null)
                         ))
                         (Method name:create_list_ast params:(ParamList (Param name:name type:String) (Param name:args type:(Array type:ASTBuilder))) returnType:ASTBuilder code:(Block
                              (Assign lhs:(Define type:ASTBuilder name:builder) rhs:(New
                                  type:ASTBuilder
                                  args:(ArgList (Arg name:name value:name))
                              ))
                              (For var:(Define type:ASTBuilder name:arg) expr:args code:(Block
                                 (Call
                                     function:(Member
                                         lhs:builder
                                         rhs:add
                                     )
                                     args:(ArgList
                                         (Arg name:value value:arg)
                                     )
                                 )
                              ))
                              (Return value:builder)
                         ))
                         (Method name:create_lit_ast params:(ParamList (Param name:name type:String) (Param name:value type:Object)) returnType:ASTBuilder code:(Block
                              (Return value:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:AST rhs:create) args:(ArgList (Arg name:name value:name) (Arg name:name value:value)))))))
                         ))
                         (Method name:create_labelled_ast params:(ParamList (Param name:name type:String) (Param name:args type:(Array type:Object))) returnType:ASTBuilder code:(Block
                             (Assign lhs:(Define type:ASTBuilder name:builder) rhs:(New
                                 type:ASTBuilder
                                 args:(ArgList (Arg name:name value:name))
                             ))
                             (Assign lhs:(Define name:i type:int) rhs:0)
                             (While cond:(Lt lhs:i rhs:(Sub lhs:(Member lhs:args rhs:length) rhs:1)) code:(Block
                                (Call
                                    function:(Member
                                        lhs:builder
                                        rhs:add
                                    )
                                    args:(ArgList
                                        (Arg name:name value:(Convert type:String value:(Index lhs:args rhs:i)))
                                        (Arg name:value value:(Convert type:ASTBuilder value:(Index lhs:args rhs:(Add lhs:i rhs:1))))
                                    )
                                )
                                (Assign lhs:i rhs:2)
                             ))
                             (Return value:builder)
                         ))
                         (Method name:create_call_ast params:(ParamList (Param name:name type:String) (Param name:function type:ASTBuilder) (Param name:args type:(Array type:ASTBuilder))) returnType:ASTBuilder code:(Block
                                (Return value:(Call
                                    function:create_labelled_ast
                                    args:(ArgList
                                        (Arg name:name value:"Call")
                                        (Arg name:args value:(NArray type:Object values:(List
                                            "function"
                                            function
                                            "args"
                                            (Call
                                                function:create_args_ast
                                                args:(ArgList
                                                    (Arg name:args value:args)
                                                )
                                            )
                                        )))
                                    )
                                ))
                        ))
                        (Method name:create_args_ast params:(ParamList (Param name:args type:(Array type:ASTBuilder))) returnType:ASTBuilder code:(Block
                              (Assign lhs:(Define type:ASTBuilder name:builder) rhs:(New
                                  type:ASTBuilder
                                  args:(ArgList (Arg name:name value:"ArgList"))
                              ))
                              (Assign lhs:(Define name:i type:int) rhs:0)
                              (For var:(Define type:ASTBuilder name:arg) expr:args code:(Block
                                 (Call
                                     function:(Member
                                         lhs:builder
                                         rhs:add
                                     )
                                     args:(ArgList
                                         (Arg name:value value:(Call
                                               function:create_labelled_ast
                                               args:(ArgList
                                                   (Arg name:name value:"Arg")
                                                   (Arg name:args value:(NArray type:Object values:(List
                                                       "name"
                                                       (Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"name")))
                                                       "value"
                                                       arg
                                                   )))
                                               )
                                         ))
                                     )
                                 )
                              ))
                              (Return value:builder)
                        ))
                        (Method name:create_member_ast params:(ParamList (Param name:lhs type:ASTBuilder) (Param name:rhs type:ASTBuilder)) returnType:ASTBuilder code:(Block
                            (Return value:(Call
                                function:create_labelled_ast
                                args:(ArgList
                                    (Arg name:name value:"Member")
                                    (Arg name:args value:(NArray type:Object values:(List
                                        "lhs"
                                        lhs
                                        "rhs"
                                        rhs
                                    )))
                                )
                            ))
                        ))
                        (Method name:create_new_ast params:(ParamList (Param name:type type:ASTBuilder) (Param name:args type:ASTBuilder)) returnType:ASTBuilder code:(Block
                            (Return value:(Call
                                function:create_labelled_ast
                                args:(ArgList
                                    (Arg name:name value:"New")
                                    (Arg name:args value:(NArray type:Object values:(List
                                        "type"
                                        type
                                        "args"
                                        args
                                    )))
                                )
                            ))
                        ))
                        (Method name:createe_ params:(ParamList (Param name:type type:ASTBuilder) (Param name:args type:ASTBuilder)) returnType:ASTBuilder code:(Block
                            (Return value:(Call
                                function:create_labelled_ast
                                args:(ArgList
                                    (Arg name:name value:"New")
                                    (Arg name:args value:(NArray type:Object values:(List
                                        "type"
                                        type
                                        "args"
                                        args
                                    )))
                                )
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
        (Expansion type:expand expansion:(List
            (if_member member:ast code:(block
                ($ ast)
                (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:name value:(Call function:expand args:(ArgList (Arg name:ast value:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:ast value:(Call function:(Member lhs:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)) rhs:toString) args:(ArgList))))))))))
            )) otherwise:(block
                (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:name value:(Call function:expand args:(ArgList (Arg name:ast value:ast)))))
            )))
        ))
        (Expansion type:insert expansion:(List
            ($ ast)
            (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:name value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)))))
        ))
        (Expansion type:member expansion:(List
            (if_member member:name code:(block
                (Call function:pushAST args:(ArgList (Arg name:ast value:ast)))
                ($ name)
                (Assign lhs:ast rhs:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:(Call function:(Member lhs:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)) rhs:toString) args:(ArgList))                ))))
            ))
            (if_member member:template code:(block
                (expand ast:template)
                (Call function:pushAST args:(ArgList (Arg name:ast value:ast)))
                ($ name)
                (Assign lhs:ast rhs:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:(Call function:(Member lhs:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)) rhs:toString) args:(ArgList))                ))))
            ) otherwise:(block
                (expand ast:name)
            ))
            (if_member member:name code:(block
                (Assign lhs:ast rhs:(Call function:popAST args:(ArgList )))
            ))
        ))
        (Expansion type:static_member expansion:(List
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
            (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(Call function:(Member lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"List"))) rhs:create) args:(ArgList)))))
            (if_member member:name code:(block
                (Call function:pushAST args:(ArgList (Arg name:ast value:ast)))
                ($ name)
                (Assign lhs:ast rhs:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:(Call function:(Member lhs:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)) rhs:toString) args:(ArgList))                ))))
            ))
            (if_member member:template code:(block
                 (Call function:pushAST args:(ArgList (Arg name:ast value:ast)))
                 (If cond:(Call function:(Member lhs:ast rhs:isMembers) args:(ArgList)) code:(Block (For var:(Define type:String name:child_name) expr:(Call function:(Member lhs:ast rhs:getMembers) args:(ArgList)) code:(Block
                        (Assign lhs:ast rhs:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:child_name))))
                        (expand ast:template)
                        (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))))

                        (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(Call
                               function:(Member
                                    lhs:(Call
                                       function:(Member
                                           lhs:(New type:ASTBuilder args:(ArgList
                                                (Arg name:ast value:(Call
                                                    function:(Member lhs:bQue rhs:pop)
                                                    args:(ArgList)
                                                ))
                                           ))
                                           rhs:add
                                       )
                                       args:(ArgList
                                            (Arg name:value value:child_name)
                                            (Arg name:value value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                                       )
                                    )
                                    rhs:create
                              )
                              args:(ArgList)
                        ))))
                 ))) otherwise:(block (If cond:(Call function:(Member lhs:ast rhs:isList) args:(ArgList)) code:(Block (For var:(Define type:AST name:child_ast) expr:(Call function:(Member lhs:ast rhs:getMemberList) args:(ArgList)) code:(Block
                    (Assign lhs:ast rhs:child_ast)
                    (expand ast:template)
                    (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))) )
                    (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(Call
                           function:(Member
                                lhs:(Call
                                       function:(Member
                                           lhs:(New type:ASTBuilder args:(ArgList
                                                (Arg name:ast value:(Call
                                                    function:(Member lhs:bQue rhs:pop)
                                                    args:(ArgList)
                                                ))
                                           ))
                                           rhs:add
                                       )
                                       args:(ArgList
                                            (Arg name:value value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                                       )
                                )
                                rhs:create
                           )
                           args:(ArgList)
                    ))))
                 ))) otherwise:(Block
                    (expand ast:template)
                    (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))) )
                    (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(Call
                           function:(Member
                                lhs:(Call
                                       function:(Member
                                           lhs:(New type:ASTBuilder args:(ArgList
                                                (Arg name:ast value:(Call
                                                    function:(Member lhs:bQue rhs:pop)
                                                    args:(ArgList)
                                                ))
                                           ))
                                           rhs:add
                                       )
                                       args:(ArgList
                                            (Arg name:value value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                                       )
                               )
                               rhs:create
                           )
                           args:(ArgList)
                    ))))
                 ))))
                 (Assign lhs:ast rhs:(Call function:popAST args:(ArgList )))
            ) otherwise:(block
                 (if_labelled code:(block (For var:(Define type:String name:child_name) expr:(Call function:(Member lhs:ast rhs:getMembers) args:(ArgList)) code:(Block
                        (Assign lhs:ast rhs:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:child_name))))
                        (Comment (expand ))
                        (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(Call
                               function:expand
                               args:(ArgList
                                    (Arg name:value value:ast)
                               )
                        ))))

                        (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))) )

                        (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(Call
                               function:(Member
                                    lhs:(Call
                                           function:(Member
                                               lhs:(New type:ASTBuilder args:(ArgList
                                                    (Arg name:ast value:(Call
                                                        function:(Member lhs:bQue rhs:pop)
                                                        args:(ArgList)
                                                    ))
                                               ))
                                               rhs:add
                                           )
                                           args:(ArgList
                                                (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:value value:child_name))))
                                                (Arg name:value value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                                           )
                                   )
                                   rhs:create
                               )
                               args:(ArgList)
                        ))))
                 ))) otherwise:(block (if_list code:(block (For var:(Define type:AST name:member) expr:(Call function:(Member lhs:ast rhs:getMemberList) args:(ArgList)) code:(Block
                    (Assign lhs:ast rhs:member)
                    (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(Call function:expand args:(ArgList (Arg name:ast value:ast))))))
                    (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))) )
                    (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(Call
                           function:(Member
                                lhs:(Call
                                       function:(Member
                                           lhs:(New type:ASTBuilder args:(ArgList
                                                (Arg name:ast value:(Call
                                                    function:(Member lhs:bQue rhs:pop)
                                                    args:(ArgList)
                                                ))
                                           ))
                                           rhs:add
                                       )
                                       args:(ArgList
                                            (Arg name:value value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                                       )
                               )
                               rhs:create
                           )
                           args:(ArgList)
                    ))))
                ))) otherwise:(block
                    (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(Call function:expand args:(ArgList (Arg name:ast value:ast))))))
                    (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))) )

                    (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(Call
                           function:(Member
                                lhs:(Call
                                       function:(Member
                                           lhs:(New type:ASTBuilder args:(ArgList
                                                (Arg name:ast value:(Call
                                                    function:(Member lhs:bQue rhs:pop)
                                                    args:(ArgList)
                                                ))
                                           ))
                                           rhs:add
                                       )
                                       args:(ArgList
                                            (Arg name:value value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                                       )
                                )
                                rhs:create
                          )
                          args:(ArgList)
                    ))))
                 ))))
            ))
            (if_member member:name code:(block
                (Assign lhs:ast rhs:(Call function:popAST args:(ArgList )))
            ))
       ))
       (Expansion type:concat expansion:(block
            (members )
            (If cond:(Call 
                    function:(Member 
                        lhs:(Index 
                            lhs:(Call 
                                function:(Member 
                                    lhs:(Call 
                                        function:(Member 
                                            lhs:bQue 
                                            rhs:peek
                                        ) 
                                        args:(ArgList)) 
                                    rhs:getMemberList) 
                                args:(ArgList)
                            )
                            rhs:0
                        ) 
                        rhs:isList
                    ) 
                    args:(ArgList)
            ) code:(Block
                (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))) )
                (Call function:(Member lhs:builder rhs:setName) args:(ArgList 
                    (Arg name:name value:(Call 
                        function:(Member 
                            lhs:(Call 
                                function:(Member
                                    lhs:(Call 
                                        function:(Member 
                                            lhs:builder 
                                            rhs:getList
                                        ) 
                                        args:(ArgList)
                                    )
                                    rhs:get
                                )
                                args:(ArgList (Arg name:index value:0))
                            ) 
                            rhs:getTypeName
                        ) 
                        args:(ArgList)))
                ))
                (Call function:(Member lhs:bQue rhs:push) args:(ArgList 
                        (Arg name:value value:(Call
                            function:(Member
                                lhs:builder
                                rhs:create
                            )
                            args:(ArgList)
                        ))
                ))
            ) otherwise:(Block
                (Call function:pushAST args:(ArgList (Arg name:ast value:ast)))
                (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:value value:"")))))))
                (For var:(Define type:AST name:member) expr:(Call
                                function:(Member 
                                    lhs:(Call 
                                        function:(Member 
                                            lhs:bQue 
                                            rhs:pop
                                        ) 
                                        args:(ArgList)) 
                                    rhs:getMemberList) 
                                args:(ArgList)
                            ) code:(Block
                    (Assign lhs:ast rhs:member)
                    (Call function:(Member lhs:builder rhs:setAdd) args:(ArgList (Arg name:value value:(Call function:(Member lhs:ast rhs:toString) args:(ArgList)))))
                ))
                (Call function:(Member lhs:bQue rhs:push) args:(ArgList 
                        (Arg name:value value:(Call
                            function:(Member
                                lhs:builder
                                rhs:create
                            )
                            args:(ArgList)
                        ))
                ))
                (Assign lhs:ast rhs:(Call function:popAST args:(ArgList)))
            ))
       ))
       (Expansion type:id expansion:(Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:value value:ast))))
       (Expansion type:list_type expansion:(if_member 
              member:ast 
              code:(block ($ ast) (Call function:getListType args:(ArgList (Arg name:ast value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))))
              otherwise:(block (Call function:getListType args:(ArgList (Arg name:ast value:ast))))
       ))
       (Expansion type:if_member expansion:(List
              (member name:member)
              (If
                  cond:(Call function:(Member lhs:ast rhs:hasMember) args:(ArgList (Arg name:name value:(Call function:(Member lhs:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)) rhs:toString) args:(ArgList))                )))
                  code:(block (member name:code))
                  otherwise:(block (if_member member:otherwise code:(block (member name:otherwise)) otherwise:(block (List))))
              )
       ))
       (Expansion type:if_labelled expansion:(List
            (If
                  cond:(Call function:(Member lhs:ast rhs:isMembers) args:(ArgList ))
                  code:(block (member name:code))
                  otherwise:(block (if_member member:otherwise code:(block (member name:otherwise)) otherwise:(block (List))))
            )
       ))
       (Expansion type:if_list expansion:(If
              cond:(Call function:(Member lhs:ast rhs:isList) args:(ArgList ))
              code:(block (member name:code))
              otherwise:(block (if_member member:otherwise code:(block (member name:otherwise)) otherwise:(block (List))))
       ))
       (Expansion type:if_atom expansion:(If
              cond:(Call function:(Member lhs:ast rhs:isValue) args:(ArgList ))
              code:(block (member name:code))
              otherwise:(block (if_member member:otherwise code:(block (member name:otherwise)) otherwise:(block (List))))
       ))
       (Expansion type:AST expansion:(List
            (Call
                function:(Member
                    lhs:bQue
                    rhs:push
                )
                args:(ArgList
                    (Arg name:name value:(Call
                        function:(Member
                            lhs:(New
                                type:ASTBuilder
                                args:(ArgList (Arg name:name value:"List"))
                            )
                            rhs:create
                        )
                        args:(ArgList)
                    ))
                )
            )
            ($ name)
            (Call
                function:(Member lhs:bQue rhs:push)
                args:(ArgList
                    (Arg name:value value:(Call
                       function:(Member
                          lhs:(Call
                              function:create_call_ast
                              args:(ArgList
                                  (Arg name:function value:(Call
                                     function:create_member_ast
                                     args:(ArgList
                                        (Arg name:lhs value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"bQue"))))
                                        (Arg name:rhs value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"push"))))
                                     )
                                  ))
                                  (Arg name:args value:(NArray type:ASTBuilder values:(List
                                     (Call
                                        function:create_new_ast
                                        args:(ArgList
                                            (Arg name:type value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"ASTBuilder"))))
                                            (Arg name:args value:(Call
                                                function:create_args_ast
                                                args:(ArgList
                                                )
                                            ))
                                        )
                                    )
                                  )))
                                  (Arg name:name value:(NArray type:Object values:(List
                                      "function"
                                      (Call
                                          function:create_labelled_ast
                                          args:(ArgList
                                              (Arg name:name value:"Member")
                                              (Arg name:name value:(NArray type:Object values:(List
                                                  "lhs"
                                                  (Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"bQue")))
                                                  "rhs"
                                                  (Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"push")))
                                              )))
                                          )
                                      )
                                      "args"
                                      (Call
                                            function:create_list_ast
                                            args:(ArgList
                                                (Arg name:name value:"ArgList")
                                                (Arg name:name value:(NArray type:ASTBuilder values:(List
                                                    (Call
                                                        function:create_labelled_ast
                                                        args:(ArgList
                                                            (Arg name:name value:"Arg")
                                                            (Arg name:name value:(NArray type:Object values:(List
                                                                "value"
                                                                (Call
                                                                    function:create_labelled_ast
                                                                    args:(ArgList
                                                                        (Arg name:name value:"New")
                                                                        (Arg name:name value:(NArray type:Object values:(List
                                                                            "type"
                                                                            (Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"ASTBuilder")))
                                                                            "args"
                                                                            (Call
                                                                                function:create_list_ast
                                                                                args:(ArgList
                                                                                    (Arg name:name value:"ArgList")
                                                                                    (Arg name:name value:(NArray type:ASTBuilder values:(List
                                                                                        (Call
                                                                                            function:create_labelled_ast
                                                                                            args:(ArgList
                                                                                                (Arg name:name value:"Arg")
                                                                                                (Arg name:name value:(NArray type:Object values:(List
                                                                                                    "value"
                                                                                                    (Call
                                                                                                          function:(Member
                                                                                                              lhs:AST
                                                                                                              rhs:STRLit
                                                                                                          )
                                                                                                          args:(ArgList
                                                                                                              (Arg name:name value:(Call
                                                                                                                   function:(Member
                                                                                                                      lhs:(Call
                                                                                                                          function:(Member
                                                                                                                              lhs:bQue
                                                                                                                              rhs:pop
                                                                                                                          )
                                                                                                                          args:(ArgList)
                                                                                                                      )
                                                                                                                      rhs:toString
                                                                                                                  )
                                                                                                                  args:(ArgList)
                                                                                                              ))
                                                                                                          )
                                                                                                    )
                                                                                                )))
                                                                                            )
                                                                                        )
                                                                                    )))
                                                                                )
                                                                          )
                                                                        )))
                                                                    )
                                                                )
                                                            )))
                                                        )
                                                    )
                                                )))
                                            )
                                      )
                                  )))
                              )
                          )
                          rhs:create
                       )
                       args:(ArgList)
                    ))
                )
            )
            (Assign
                 lhs:builder
                 rhs:(New type:ASTBuilder args:(ArgList
                     (Arg name:ast value:(Call
                         function:(Member lhs:bQue rhs:pop)
                         args:(ArgList)
                     ))
                 ))
            )
            (Call
                 function:(Member lhs:bQue rhs:push)
                 args:(ArgList
                     (Arg name:ast value:(Call
                         function:(Member
                             lhs:(Call
                                 function:(Member
                                     lhs:(New
                                         type:ASTBuilder
                                         args:(ArgList
                                             (Arg name:ast value:(Call
                                                 function:(Member
                                                     lhs:bQue
                                                     rhs:pop
                                                 )
                                                 args:(ArgList)
                                             ))
                                         )
                                     )
                                     rhs:add
                                 )
                                 args:(ArgList
                                     (Arg name:value value:builder)
                                 )
                             )
                             rhs:create
                         )
                         args:(ArgList)
                     ))
                 )
            )
            (if_member member:params code:(block
                (members name:params template:(list
                    (expand ast:ast)
                    ($ name)
                    (Assign
                        lhs:builder
                        rhs:(New type:ASTBuilder args:(ArgList
                            (Arg name:ast value:(Call
                                function:(Member lhs:bQue rhs:pop)
                                args:(ArgList)
                            ))
                        ))
                    )
                    (Call
                        function:(Member lhs:bQue rhs:push)
                        args:(ArgList
                            (Arg name:ast value:(Call
                                function:(Member
                                    lhs:(Call
                                        function:(Member
                                            lhs:(New
                                                type:ASTBuilder
                                                args:(ArgList
                                                    (Arg name:ast value:(Call
                                                        function:(Member
                                                            lhs:bQue
                                                            rhs:pop
                                                        )
                                                        args:(ArgList)
                                                    ))
                                                )
                                            )
                                            rhs:add
                                        )
                                        args:(ArgList
                                            (Arg name:name value:(String value:(Call function:(Member lhs:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)) rhs:toString) args:(ArgList))                ))
                                            (Arg name:value value:builder)
                                        )
                                    )
                                    rhs:create
                                )
                                args:(ArgList)
                            ))
                        )
                    )
                ))
            ) otherwise:(block (if_member member:list code:(block
                    (expand ast:list)
                    (Assign
                        lhs:builder
                        rhs:(New type:ASTBuilder args:(ArgList
                            (Arg name:ast value:(Call
                                function:(Member lhs:bQue rhs:pop)
                                args:(ArgList)
                            ))
                        ))
                    )
                    (Call
                        function:(Member lhs:bQue rhs:push)
                        args:(ArgList
                            (Arg name:ast value:(Call
                                function:(Member
                                    lhs:(Call
                                        function:(Member
                                            lhs:(New
                                                type:ASTBuilder
                                                args:(ArgList
                                                    (Arg name:ast value:(Call
                                                        function:(Member
                                                            lhs:bQue
                                                            rhs:pop
                                                        )
                                                        args:(ArgList)
                                                    ))
                                                )
                                            )
                                            rhs:add
                                        )
                                        args:(ArgList
                                            (Arg name:value value:builder)
                                        )
                                    )
                                    rhs:create
                                )
                                args:(ArgList)
                            ))
                        )
                    )
            ) otherwise:(block
                (expand ast:atom)
                (Call
                    function:(Member lhs:bQue rhs:push)
                    args:(ArgList
                        (Arg name:value value:(Call
                           function:(Member
                              lhs:(Call
                                  function:create_labelled_ast
                                  args:(ArgList
                                      (Arg name:name value:"Call")
                                      (Arg name:name value:(NArray type:Object values:(List
                                          "function"
                                          (Call
                                              function:create_labelled_ast
                                              args:(ArgList
                                                  (Arg name:name value:"Member")
                                                  (Arg name:name value:(NArray type:Object values:(List
                                                      "lhs"
                                                      (Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"bQue")))
                                                      "rhs"
                                                      (Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"push")))
                                                  )))
                                              )
                                          )
                                          "args"
                                          (Call
                                                function:create_list_ast
                                                args:(ArgList
                                                    (Arg name:name value:"ArgList")
                                                    (Arg name:name value:(NArray type:ASTBuilder values:(List
                                                        (Call
                                                            function:create_labelled_ast
                                                            args:(ArgList
                                                                (Arg name:name value:"Arg")
                                                                (Arg name:name value:(NArray type:Object values:(List
                                                                    "value"
                                                                    (Call
                                                                        function:create_labelled_ast
                                                                        args:(ArgList
                                                                            (Arg name:name value:"Call")
                                                                            (Arg name:values value:(NArray type:Object values:(List
                                                                                "function"
                                                                                (Call
                                                                                    function:create_labelled_ast
                                                                                    args:(ArgList
                                                                                        (Arg name:name value:"Member")
                                                                                        (Arg name:values value:(NArray type:Object values:(List
                                                                                            "lhs"
                                                                                            (Call
                                                                                                function:create_labelled_ast
                                                                                                args:(ArgList
                                                                                                    (Arg name:name value:"New")
                                                                                                    (Arg name:values value:(NArray type:Object values:(List
                                                                                                        "type"
                                                                                                        (Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"ASTBuilder")))
                                                                                                        "args"
                                                                                                        (Call
                                                                                                            function:create_list_ast
                                                                                                            args:(ArgList
                                                                                                                (Arg name:name value:"ArgList")
                                                                                                                (Arg name:name value:(NArray type:ASTBuilder values:(List
                                                                                                                    (Call
                                                                                                                        function:create_labelled_ast
                                                                                                                        args:(ArgList
                                                                                                                            (Arg name:name value:"Arg")
                                                                                                                            (Arg name:name value:(NArray type:Object values:(List
                                                                                                                                "value"
                                                                                                                                (Call
                                                                                                                                      function:create_labelled_ast
                                                                                                                                      args:(ArgList
                                                                                                                                          (Arg name:name value:"Call")
                                                                                                                                          (Arg name:name value:(NArray type:Object values:(List
                                                                                                                                              "function"
                                                                                                                                              (Call
                                                                                                                                                  function:create_labelled_ast
                                                                                                                                                  args:(ArgList
                                                                                                                                                      (Arg name:name value:"Member")
                                                                                                                                                      (Arg name:name value:(NArray type:Object values:(List
                                                                                                                                                          "lhs"
                                                                                                                                                          (Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"bQue")))
                                                                                                                                                          "rhs"
                                                                                                                                                          (Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"pop")))
                                                                                                                                                      )))
                                                                                                                                                  )
                                                                                                                                              )
                                                                                                                                              "args"
                                                                                                                                              (Call
                                                                                                                                                    function:create_list_ast
                                                                                                                                                    args:(ArgList
                                                                                                                                                        (Arg name:name value:"ArgList")
                                                                                                                                                        (Arg name:name value:(NArray type:ASTBuilder values:(List
                                                                                                                                                        )))
                                                                                                                                                    )
                                                                                                                                             )
                                                                                                                                         )))
                                                                                                                                     )
                                                                                                                                )
                                                                                                                            )))
                                                                                                                        )
                                                                                                                    )
                                                                                                                )))
                                                                                                            )
                                                                                                        )
                                                                                                    )))
                                                                                                )
                                                                                            )
                                                                                            "rhs"
                                                                                            (Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"add")))
                                                                                        )))
                                                                                    )
                                                                                )
                                                                                "args"
                                                                                (Call
                                                                                    function:create_list_ast
                                                                                    args:(ArgList
                                                                                        (Arg name:name value:"ArgList")
                                                                                        (Arg name:name value:(NArray type:ASTBuilder values:(List
                                                                                            (Call
                                                                                                function:create_labelled_ast
                                                                                                args:(ArgList
                                                                                                    (Arg name:name value:"Arg")
                                                                                                    (Arg name:name value:(NArray type:Object values:(List
                                                                                                        "value"
                                                                                                        (Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"builder")))
                                                                                                    )))
                                                                                                )
                                                                                            )
                                                                                        )))
                                                                                    )
                                                                                )
                                                                            )))
                                                                        )
                                                                    )

                                                                )))
                                                            )
                                                        )
                                                    )))
                                                )
                                          )
                                      )))
                                  )
                              )
                              rhs:create
                           )
                           args:(ArgList)
                        ))
                    )
                )
            ))))
       ))
       (Expansion type:typeName expansion:(Block
            (Call
                function:(Member lhs:bQue rhs:push)
                args:(ArgList
                    (Arg name:ast value:(Call
                        function:(Member lhs:AST rhs:create)
                        args:(ArgList
                            (Arg name:name value:"STRLit")
                            (Arg name:value value:(Call function:(Member lhs:ast rhs:getTypeName) args:(ArgList)))
                        )
                    ))
                )
            )
       ))
       (Expansion type:value expansion:(Block
            (Call
                function:(Member lhs:bQue rhs:push)
                args:(ArgList
                    (Arg name:ast value:(Call
                        function:(Member lhs:AST rhs:create)
                        args:(ArgList
                            (Arg name:value value:(Call
                                function:(Member
                                    lhs:ast
                                    rhs:getTypeName
                                )
                                args:(ArgList)
                            ))
                            (Arg name:value value:(Call
                                function:(Member
                                    lhs:ast
                                    rhs:getValue
                                )
                                args:(ArgList)
                            ))
                        )
                    ))
                )
            )
       ))
       (Expansion type:block expansion:(Block
            (members )
       ))
       (Expansion type:list expansion:(List
            (members )
       ))
       (Expansion type:$ expansion:(list
            (if_atom code:(block
                (Call
                    function:(Member lhs:bQue rhs:push)
                    args:(ArgList
                        (Arg name:value value:(Call
                            function:(Member
                                lhs:(Call
                                    function:(Member
                                        lhs:(Call
                                            function:(Member
                                                lhs:(New
                                                    type:ASTBuilder
                                                    args:(ArgList (Arg name:name value:"Call"))
                                                )
                                                rhs:add
                                            )
                                            args:(ArgList
                                                (Arg name:name value:"function")
                                                (Arg name:value value:(Call
                                                      function:(Member
                                                          lhs:(Call
                                                              function:(Member
                                                                  lhs:(New
                                                                      type:ASTBuilder
                                                                      args:(ArgList (Arg name:name value:"Member"))
                                                                  )
                                                                  rhs:add
                                                              )
                                                              args:(ArgList
                                                                  (Arg name:name value:"lhs")
                                                                  (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"bQue"))))
                                                              )
                                                          )
                                                          rhs:add
                                                      )
                                                      args:(ArgList
                                                          (Arg name:name value:"rhs")
                                                          (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"push"))))
                                                      )

                                                  ))
                                            )
                                        )
                                        rhs:add
                                    )
                                    args:(ArgList
                                        (Arg name:name value:"args")
                                        (Arg name:value value:(Call
                                                  function:(Member
                                                      lhs:(New
                                                          type:ASTBuilder
                                                          args:(ArgList (Arg name:name value:"ArgList"))
                                                      )
                                                      rhs:add
                                                  )
                                                  args:(ArgList
                                                      (Arg name:value value:(Call
                                                          function:(Member
                                                              lhs:(Call
                                                                  function:(Member
                                                                      lhs:(New
                                                                          type:ASTBuilder
                                                                          args:(ArgList (Arg name:name value:"Arg"))
                                                                      )
                                                                      rhs:add
                                                                  )
                                                                  args:(ArgList
                                                                      (Arg name:name value:"name")
                                                                      (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"name"))))
                                                                  )
                                                              )
                                                              rhs:add
                                                          )
                                                          args:(ArgList
                                                              (Arg name:name value:"value")
                                                              (Arg name:value value:(Call
                                                                  function:(Member
                                                                      lhs:(Call
                                                                          function:(Member
                                                                              lhs:(New
                                                                                  type:ASTBuilder
                                                                                  args:(ArgList (Arg name:name value:"Call"))
                                                                              )
                                                                              rhs:add
                                                                          )
                                                                          args:(ArgList
                                                                              (Arg name:name value:"function")
                                                                              (Arg name:value value:(Call
                                                                                  function:(Member
                                                                                      lhs:(Call
                                                                                          function:(Member
                                                                                              lhs:(New
                                                                                                  type:ASTBuilder
                                                                                                  args:(ArgList (Arg name:name value:"Member"))
                                                                                              )
                                                                                              rhs:add
                                                                                          )
                                                                                          args:(ArgList
                                                                                              (Arg name:name value:"lhs")
                                                                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"AST"))))
                                                                                          )
                                                                                      )
                                                                                      rhs:add
                                                                                  )
                                                                                  args:(ArgList
                                                                                      (Arg name:name value:"rhs")
                                                                                      (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"create"))))
                                                                                  )
                                                                              ))
                                                                          )
                                                                      )
                                                                      rhs:add
                                                                  )
                                                                  args:(ArgList
                                                                      (Arg name:name value:"args")
                                                                      (Arg name:value value:(Call
                                                                          function:(Member
                                                                              lhs:(Call
                                                                                  function:(Member
                                                                                      lhs:(New
                                                                                          type:ASTBuilder
                                                                                          args:(ArgList (Arg name:name value:"ArgList"))
                                                                                      )
                                                                                      rhs:add
                                                                                  )
                                                                                  args:(ArgList
                                                                                      (Arg name:value value:(Call
                                                                                          function:(Member
                                                                                              lhs:(Call
                                                                                                  function:(Member
                                                                                                      lhs:(New
                                                                                                          type:ASTBuilder
                                                                                                          args:(ArgList (Arg name:name value:"Arg"))
                                                                                                      )
                                                                                                      rhs:add
                                                                                                  )
                                                                                                  args:(ArgList
                                                                                                      (Arg name:name value:"name")
                                                                                                      (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"name"))))
                                                                                                  )
                                                                                              )
                                                                                              rhs:add
                                                                                          )
                                                                                          args:(ArgList
                                                                                              (Arg name:name value:"value")
                                                                                              (Arg name:value value:(Call
                                                                                                  function:(Member
                                                                                                    lhs:AST
                                                                                                    rhs:create
                                                                                                  )
                                                                                                  args:(ArgList
                                                                                                     (Arg name:name value:"STRLit")
                                                                                                     (Arg name:value value:(Call
                                                                                                        function:(Member
                                                                                                            lhs:ast
                                                                                                            rhs:getTypeName
                                                                                                        )
                                                                                                        args:(ArgList)
                                                                                                     ))
                                                                                                  )
                                                                                              ))
                                                                                          )
                                                                                      ))
                                                                                  )
                                                                              )
                                                                              rhs:add
                                                                          )
                                                                          args:(ArgList
                                                                              (Arg name:value value:(Call
                                                                                  function:(Member
                                                                                      lhs:(Call
                                                                                          function:(Member
                                                                                              lhs:(New
                                                                                                  type:ASTBuilder
                                                                                                  args:(ArgList (Arg name:name value:"Arg"))
                                                                                              )
                                                                                              rhs:add
                                                                                          )
                                                                                          args:(ArgList
                                                                                              (Arg name:name value:"name")
                                                                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:"value"))))
                                                                                          )
                                                                                      )
                                                                                      rhs:add
                                                                                  )
                                                                                  args:(ArgList
                                                                                      (Arg name:name value:"value")
                                                                                      (Arg name:value value:(Call
                                                                                          function:(Member
                                                                                            lhs:AST
                                                                                            rhs:create
                                                                                          )
                                                                                          args:(ArgList
                                                                                             (Arg name:name value:"STRLit")
                                                                                             (Arg name:value value:(Call
                                                                                                function:(Member
                                                                                                    lhs:(Call
                                                                                                        function:(Member
                                                                                                            lhs:ast
                                                                                                            rhs:getValue
                                                                                                        )
                                                                                                        args:(ArgList)
                                                                                                    )
                                                                                                    rhs:toString
                                                                                                )
                                                                                                args:(ArgList)
                                                                                             ))
                                                                                          )
                                                                                      ))
                                                                                  )
                                                                              ))
                                                                          )
                                                                      ))
                                                                  )

                                                              ))
                                                          )

                                                      ))
                                                  )
                                              ))
                                    )

                                )
                                rhs:create
                            )
                            args:(ArgList)
                        )
                    ))
                )
                (Assign
                    lhs:builder
                    rhs:(New
                        type:ASTBuilder
                        args:(ArgList
                            (Arg name:ast value:(Call
                                function:(Member
                                    lhs:bQue
                                    rhs:pop
                                )
                                args:(ArgList)
                            ))
                        )
                    )
                )
                (Call
                    function:(Member
                        lhs:bQue
                        rhs:push
                    )
                    args:(ArgList
                        (Arg name:value value:(Call
                            function:(Member
                                lhs:(Call
                                    function:(Member
                                        lhs:(New
                                            type:ASTBuilder
                                            args:(ArgList
                                                (Arg name:value value:(Call
                                                   function:(Member
                                                        lhs:bQue
                                                        rhs:pop
                                                   )
                                                   args:(ArgList)
                                                ))
                                            )
                                        )
                                        rhs:add
                                    )
                                    args:(ArgList
                                        (Arg name:value value:builder)
                                    )
                                )
                                rhs:create
                            )
                            args:(ArgList)
                        ))
                    )
                )
            ) otherwise:(block (if_list code:(block
                (members template:(Call
                        function:(Member lhs:bQue rhs:push)
                        args:(ArgList
                            (Arg name:ast value:(Call
                                function:expand
                                args:(ArgList
                                    (Arg name:ast value:(Call
                                        function:(Member lhs:ast rhs:get)
                                        args:(ArgList
                                            (Arg name:ast value:(value))
                                        )
                                    ))
                                )
                            ))
                        )
                    )
                )
            ) otherwise:(block (Block))))
       )))
       
       (Expansion type:Import expansion:(AST name:Import params:(list (param name:name ast:($ name)))))
       (Expansion type:Class expansion:(AST name:Class params:(list
                                    (param name:modifier ast:($ modifier))
                                    (param name:name ast:($ name)) 
                                    (param name:base ast:($ base)) 
                                    (param name:attrs ast:($ attrs)) 
                                    (param name:cons ast:($ cons)) 
                                    (param name:methods ast:($ methods)) 
       )))
       (Expansion type:Generic expansion:(AST name:Generic params:(list (param name:type ast:($ type)) (param name:gens ast:($ gens)))))
       (Expansion type:Array expansion:(AST name:Array params:(list (param name:type ast:($ type)))))
       (Expansion type:NArray expansion:(AST name:Array params:(list (param name:type ast:($ type)) (param name:values ast:($ values)))))
       (Expansion type:Method expansion:(AST name:Method params:(list
                                    (param name:returnType ast:($ returnType)) 
                                    (param name:name ast:($ name)) 
                                    (param name:params ast:($ params))
                                    (param name:code ast:($ code))
       )))
       (Expansion type:If expansion:(AST name:If params:(list (param name:cond ast:($ cond)) (param name:code ast:($ code)) (param name:otherwise ast:($ otherwise)))))
       (Expansion type:While expansion:(AST name:While params:(list (param name:cond ast:($ cond)) (param name:code ast:($ code)))))
       (Expansion type:For expansion:(AST name:For params:(list (param name:var ast:($ var)) (param name:expr ast:($ expr)) (param name:code ast:($ code)))))
       (Expansion type:Block expansion:(AST name:Block list:(members template:(expand ))))
       (Expansion type:Name expansion:(AST name:Name list:(members template:(expand ))))
       (Expansion type:List expansion:(AST name:List list:(members template:(expand ))))
       (Expansion type:AttrList expansion:(AST name:AttrList list:(members template:(expand ))))
       (Expansion type:MethodList expansion:(AST name:MethodList list:(members template:(expand ))))
       (Expansion type:ParamList expansion:(AST name:ParamList list:(members template:(expand ))))
       (Expansion type:Call expansion:(AST name:Call params:(list (param name:function ast:($ function)) (param name:args ast:($ args)))))
       (Expansion type:ArgList expansion:(AST name:ArgList list:(members template:(expand ))))
       (Expansion type:Param expansion:(AST name:Param params:(list (param name:name ast:($ name)) (param name:value ast:($ type)))))
       (Expansion type:Arg expansion:(AST name:Arg params:(list (param name:name ast:($ name)) (param name:value ast:($ value)))))
       (Expansion type:Member expansion:(AST name:Member params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Assign expansion:(AST name:Assign params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Define expansion:(AST name:Define params:(list (param name:type ast:($ type)) (param name:name ast:($ name)))))
       (Expansion type:New expansion:(AST name:New params:(list (param name:type ast:($ type)) (param name:args ast:($ args)))))
       (Expansion type:Convert expansion:(AST name:Convert params:(list (param name:type ast:($ type)) (param name:value ast:($ value)))))
       (Expansion type:Select expansion:(AST name:Select params:(list (param name:value ast:($ value)) (param name:block ast:($ block)))))
       (Expansion type:Case expansion:(AST name:Case params:(list (param name:value ast:($ value)) (param name:block ast:($ block)))))
       (Expansion type:Default expansion:(AST name:Default params:(list (param name:block ast:($ block)))))
       (Expansion type:Index expansion:(AST name:Index params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Return expansion:(AST name:Return params:(list (param name:value ast:($ value)) )))
       (Expansion type:Eq expansion:(AST name:Eq params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Neq expansion:(AST name:Neq params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Lt expansion:(AST name:Lt params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Gt expansion:(AST name:Gt params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Lte expansion:(AST name:Lte params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Gte expansion:(AST name:Gte params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Add expansion:(AST name:Add params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Sub expansion:(AST name:Sub params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Mul expansion:(AST name:Mul params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Div expansion:(AST name:Div params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:And expansion:(AST name:And params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Or expansion:(AST name:Or params:(list (param name:lhs ast:($ lhs)) (param name:rhs ast:($ rhs)))))
       (Expansion type:Inc expansion:(AST name:Inc params:(list (param name:value ast:($ value)))))
       (Expansion type:PostInc expansion:(AST name:PostInc params:(list (param name:value ast:($ value)))))
       (Expansion type:Dec expansion:(AST name:Dec params:(list (param name:value ast:($ value)))))
       (Expansion type:PostDec expansion:(AST name:PostDec params:(list (param name:value ast:($ value)))))
       (Expansion type:Comment expansion:(AST name:Comment list:(members template:(expand ))))
       (Expansion type:String expansion:(AST name:String params:(list (param name:value ast:($ value)))))
       (Expansion type:ID expansion:(AST name:IDLit atom:$))
       (Expansion type:STR expansion:(AST name:String atom:$))
       (Expansion type:IntLit expansion:(AST name:IntLit atom:$))
       (Expansion type:IDLit expansion:(AST name:IDLit atom:$))
       (Expansion type:NUM expansion:(AST name:NUMLit atom:$)
    )
)
