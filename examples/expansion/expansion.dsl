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
                         (Method name:expand params:(ParamList) returnType:AST code:(Block
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
                         )
                     ))
                     (members name:expansions template:(Method name:(expFunName type:(static_member name:type) ) returnType:AST params:(ParamList (Param name:ast type:AST))
                        code:(Block
                             (Define type:ASTBuilder name:builder)
                             (Define type:(Generic type:Deque gens:(List AST)) name:bQue)
                             (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList)))
                             (Assign lhs:bQue rhs:(New type:(Generic type:ArrayDeque gens:(List AST)) args:(ArgList)))

                             (member name:expansion)

                             (Return value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
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
             (Return value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
      ))
      (Expansion type:member expansion:(List
          (Call
                function:(Member lhs:builder rhs:setName)
                args:(ArgList (Arg name:name value:"List"))
          )
          (Call
                function:(Member lhs:builder rhs:add)
                args:(ArgList (Arg name:ast value:(Call
                    function:expand
                    args:(ArgList (Arg name:ast value:(Call
                        function:(Member lhs:ast rhs:get)
                        args:(ArgList (Arg name:name value:"name"))
                    )))
                )))
          )
          (If cond:(Call function:(Member lhs:ast rhs:hasMember) args:(ArgList (Arg name:name value:"template"))) code:(Block
             (Call
                 function:(Member lhs:builder rhs:add)
                 args:(ArgList (Arg name:ast value:(ast name:"Call" members:(ArgList
                    (Arg name:"function" value:"pushAST")
                    (Arg name:"args"  value:(ast name:"ArgList" list:(ArgList
                        (Arg name:"" value:(ast name:"Arg" members:(ArgList
                            (Arg name:"name"  value:"ast")
                            (Arg name:"value" value:ast)
                        )))
                    )))
                 ))))
             )
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
             (Call
                 function:(Member lhs:builder rhs:add)
                 args:(ArgList (Arg name:ast value:(Call
                     function:expand
                     args:(ArgList (Arg name:ast value:(Call
                         function:(Member lhs:ast rhs:get)
                         args:(ArgList (Arg name:name value:"template"))
                     )))
                 )))
             )
             (Assign
                 lhs:ast
                 rhs:(Call function:popAST args:(ArgList ))
             )
          ) otherwise:(Block
              (Call
                    function:(Member lhs:builder rhs:add)
                    args:(ArgList (Arg name:ast value:(Call
                        function:(Member lhs:bQue rhs:push)
                        args:(ArgList (Arg name:ast value:(Call
                            function:expand
                            args:(ArgList (Arg name:ast value:(Call
                                function:(Member lhs:ast rhs:get)
                                args:(ArgList (Arg name:name value:(Call
                                    function:(Member
                                        lhs:(Call
                                            function:(Member lhs:bQue rhs:pop)
                                            args:(ArgList)
                                        )
                                        rhs:toString)
                                    args:(ArgList)
                                ))))
                            )))
                        )))
                    )))
              )
          )
      ))
      (Expansion type:static_member expansion:(List
           (Call function:(Member lhs:bQue rhs:push) args:(ArgList
                (Arg name:value value:(Call
                       function:expand
                       args:(ArgList (Arg name:ast value:(Call
                          function:(Member lhs:ast rhs:get)
                          args:(ArgList (Arg name:name value:"name"))
                       )))
                ))
           ))
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
         (Call function:(Member lhs:builder rhs:setName) args:(ArgList (Arg name:name value:"List")))
         (If cond:(Call function:(Member lhs:ast rhs:hasMember) args:(ArgList (Arg name:name value:"name"))) code:(Block
            (Call function:(Member lhs:builder rhs:add) args:(ArgList (Arg name:value value:(Call function:expand args:(ArgList (Arg name:ast value:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:"name")))))))))
            (Comment (Call function:(Member lhs:builder rhs:add) args:(ArgList (Arg name:value value:
                  (ast name:Assign members:(ArgList
                        (Arg name:lhs value:ast)
                        (Arg name:rhs value:(ast name:Call members:(ArgList
                           (Arg name:function value:(ast name:Member members:(ArgList
                               (Arg name:lhs value:ast)
                               (Arg name:rhs value:get)
                           )))
                           (Arg name:args value:(ast name:ArgList members:(ArgList
                              (Arg name:arg1 value:(ast name:Arg members:(ArgList
                                 (Arg name:name value:name)
                                 (Arg name:value value:(ast name:Call members:(ArgList
                                     (Arg name:function value:(ast name:Member members:(ArgList
                                         (Arg name:lhs value:(ast name:Call members:(ArgList
                                             (Arg name:function value:(ast name:Member members:(ArgList
                                                    (Arg name:lhs value:bQue)
                                                    (Arg name:rhs value:push)
                                             )))
                                             (Arg name:args value:(ast name:Call members:(ArgList
                                                (Arg name:function value:(ast name:Member members:(ArgList
                                                   (Arg name:lhs value:builder)
                                                   (Arg name:rhs value:create)
                                                )))
                                                (Arg name:args value:(ast name:ArgList members:(ArgList)))
                                             )))
                                         )))
                                         (Arg name:rhs value:toString)
                                     )))
                                     (Arg name:args value:(ast name:ArgList members:(ArgList)))
                                 )))
                              )))
                           )))
                        )))
                    ))
            ))))
         ) otherwise:(Block)

         )
         (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:value value:(Call
            function:expand
            args:(ArgList (Arg name:ast value:(Call
               function:(Member lhs:ast rhs:get)
               args:(ArgList (Arg name:name value:"template"))
            )))
         ))))
      ))
      (Expansion type:concat expansion:(List))
      (Expansion type:ast expansion:(Block
         (Call function:(Member lhs:builder rhs:setName) args:(ArgList (Arg name:name value:"List")))
         (Assign lhs:(Define type:(Generic type:List gens:(List AST)) name:children) rhs:(New type:(Generic type:ArrayList gens:(List AST)) args:(ArgList)))
         (Assign lhs:(Define type:(Generic type:List gens:(List String)) name:members) rhs:(New type:(Generic type:ArrayList gens:(List String)) args:(ArgList)))
         (If cond:(Call function:(Member lhs:ast rhs:hasMember) args:(ArgList (Arg name:name value:"members"))) code:(Block
             (For var:(Define type:AST name:child) expr:(Call
                  function:(Member
                      lhs:(Call
                          function:(Member lhs:ast rhs:get)
                          args:(ArgList
                              (Arg name:name value:"members")
                          )
                      )
                      rhs:getMemberList
                  )
                  args:(ArgList)
                ) code:(Block
                    (Call
                        function:(Member lhs:children rhs:add)
                        args:(ArgList
                           (Arg name:name value:(Call
                              function:expand
                              args:(ArgList
                                 (Arg name:name value:(Call
                                    function:(Member lhs:child rhs:get)
                                    args:(ArgList
                                        (Arg name:name value:"value")
                                    )
                                 ))
                              )
                           ))
                        )
                    )
                    (Call
                        function:(Member lhs:members rhs:add)
                        args:(ArgList
                           (Arg name:name value:(Call
                              function:(Member
                                 lhs:(Call
                                    function:(Member lhs:child rhs:get)
                                    args:(ArgList
                                        (Arg name:name value:"name")
                                    )
                                 )
                                 rhs:toString
                              )
                              args:(ArgList)
                           ))
                        )
                    )
                )
             )
         ) otherwise:(Block
            (For var:(Define type:AST name:child) expr:(Call
                  function:(Member
                      lhs:(Call
                          function:(Member lhs:ast rhs:get)
                          args:(ArgList
                              (Arg name:name value:"list")
                          )
                      )
                      rhs:getMemberList
                  )
                  args:(ArgList)
                ) code:(Block
                    (Call
                        function:(Member lhs:children rhs:add)
                        args:(ArgList
                           (Arg name:name value:(Call
                              function:expand
                              args:(ArgList
                                 (Arg name:name value:(Call
                                    function:(Member lhs:child rhs:get)
                                    args:(ArgList
                                        (Arg name:name value:"value")
                                    )
                                 ))
                              )
                           ))
                        )
                    )
                )
            )
         ))
         (Call
            function:(Member
                lhs:Collections
                rhs:reverse
            )
            args:(ArgList
                (Arg name:name value:children)
            )
         )
         (Call
            function:(Member
                lhs:builder
                rhs:addAll
            )
            args:(ArgList
                (Arg name:name value:children)
            )
         )
         (Call
               function:(Member
                  lhs:builder
                  rhs:add
               )
               args:(ArgList
                  (Arg name:ast value:(Call
                      function:expand
                      args:(ArgList
                         (Arg name:ast value:(Call
                             function:(Member
                                 lhs:ast
                                 rhs:get
                             )
                             args:(ArgList
                                 (Arg name:name value:"name")
                             )
                         ))
                      )
                  ))
               )
         )
         (ast name:Call members:(ArgList
                      (Arg name:function value:(ast name:Member members:(ArgList
                            (Arg name:lhs value:bQue)
                            (Arg name:rhs value:pop)
                      )))
                      (Arg name:args value:(ast name:ArgList list:(ArgList
                            (Arg name:a1 value:(ast name:Assign members:(ArgList
                                (Arg name:lhs value:(Call
                                    function:(Member lhs:AST rhs:create)
                                    args:(ArgList
                                        (Arg name:name value:"IDLit")
                                        (Arg name:value value:"builder")
                                    )
                                ))
                                (Arg name:rhs value:(ast name:New members:(ArgList
                                    (Arg name:type value:(Call
                                         function:(Member lhs:AST rhs:create)
                                         args:(ArgList
                                             (Arg name:name value:"IDLit")
                                             (Arg name:value value:"ASTBuilder")
                                         )
                                    ))
                                    (Arg name:args value:(ast name:ArgList list:(ArgList
                                        (Arg name:a1 value:(ast name:Arg members:(ArgList
                                            (Arg name:name value:(Call
                                                function:(Member lhs:AST rhs:create)
                                                args:(ArgList
                                                    (Arg name:name value:"STRLit")
                                                    (Arg name:value value:"name")
                                                )
                                            ))
                                        )))
                                        (Arg name:a2 value:(ast name:Arg members:(ArgList
                                            (Arg name:name value:(Call
                                                function:(Member lhs:AST rhs:STRLit)
                                                args:(ArgList
                                                    (Arg name:string value:(Call
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
                                            ))
                                        )))
                                    )))
                                )))
                            )))
                      )))
                   ))
         (If cond:(Call function:(Member lhs:ast rhs:hasMember) args:(ArgList (Arg name:name value:"members"))) code:(Block
              (For var:(Define type:String name:member) expr:members code:(Block
                     (ast name:Call members:(ArgList
                        (Arg name:function value:(ast name:Member members:(ArgList
                            (Arg name:lhs value:(ast name:Call members:(ArgList
                                (Arg name:function value:(ast name:Members members:(ArgList
                                    (Arg name:lhs value:(Call
                                        function:(Member lhs:AST rhs:create)
                                        args:(ArgList
                                            (Arg name:name value:"IDLit")
                                            (Arg name:value value:"bQue")
                                        )
                                    ))
                                    (Arg name:lhs value:(Call
                                        function:(Member lhs:AST rhs:create)
                                        args:(ArgList
                                            (Arg name:name value:"IDLit")
                                            (Arg name:value value:"push")
                                        )
                                    ))
                                )))
                                (Arg name:args value:(ast name:ArgList list:(ArgList
                                    (Arg name:a1 value:(ast name:Arg members:(ArgList
                                        (Arg name:name value:(Call
                                             function:(Member lhs:AST rhs:create)
                                             args:(ArgList
                                                 (Arg name:name value:"STRLit")
                                                 (Arg name:value value:"ast")
                                             )
                                        ))
                                        (Arg name:value value:(ast name:Call members:(ArgList
                                              (Arg name:function value:(ast name:Member members:(ArgList
                                                  (Arg name:lhs value:(Call
                                                      function:(Member lhs:AST rhs:create)
                                                      args:(ArgList
                                                          (Arg name:name value:"IDLit")
                                                          (Arg name:value value:"builder")
                                                      )
                                                  ))
                                                  (Arg name:rhs value:(Call
                                                      function:(Member lhs:AST rhs:create)
                                                      args:(ArgList
                                                          (Arg name:name value:"IDLit")
                                                          (Arg name:value value:"add")
                                                      )
                                                  ))
                                              )))
                                              (Arg name:args value:(ast name:ArgList list:(ArgList
                                                  (Arg name:a1 value:(ast name:Arg members:(ArgList
                                                      (Arg name:name value:(Call
                                                         function:(Member lhs:AST rhs:create)
                                                         args:(ArgList
                                                             (Arg name:name value:"STRLit")
                                                             (Arg name:value value:"name")
                                                         )
                                                      ))
                                                      (Arg name:value value:(Call
                                                           function:(Member lhs:AST rhs:create)
                                                           args:(ArgList
                                                               (Arg name:name value:"STRLit")
                                                               (Arg name:value value:member)
                                                           )
                                                      ))
                                                  )))
                                                  (Arg name:a2 value:(ast name:Arg members:(ArgList
                                                      (Arg name:name value:(Call
                                                          function:(Member lhs:AST rhs:create)
                                                          args:(ArgList
                                                              (Arg name:name value:"STRLit")
                                                              (Arg name:value value:"name")
                                                          )
                                                      ))
                                                      (Arg name:value value:(ast name:Call members:(ArgList
                                                          (Arg name:function value:(ast name:Member members:(ArgList
                                                              (Arg name:lhs value:(Call
                                                                  function:(Member lhs:AST rhs:create)
                                                                  args:(ArgList
                                                                      (Arg name:name value:"IDLit")
                                                                      (Arg name:value value:"bQue")
                                                                  )
                                                              ))
                                                              (Arg name:lhs value:(Call
                                                                  function:(Member lhs:AST rhs:create)
                                                                  args:(ArgList
                                                                      (Arg name:name value:"IDLit")
                                                                      (Arg name:value value:"pop")
                                                                  )
                                                              ))
                                                          )))
                                                          (Arg name:args value:(ast name:ArgList list:(ArgList)))
                                                      )))
                                                  )))
                                              )))
                                          )))
                                    )))
                                )))
                            )))
                        )))
                     ))

              ))
              (Call function:(Member lhs:builder rhs:add) args:(ArgList
                   (ast name:Assign members:(ArgList
                        (Arg name:lhs value:builder)
                        (Arg name:rhs value:(ast name:New members:(ArgList
                            (Arg name:type value:ASTBuilder)
                            (Arg name:args value:(ast name:ArgList list:(ArgList
                                (Arg name:a1 value:(ast name:Arg members:(ArgList
                                    (Arg name:name value:name)
                                    (Arg name:value value:"List")
                                )))
                            )))
                        )))
                   ))
              ))
              (For init:(Assign lhs:(Define name:ignored type:int) rhs:0) cond:(Lte lhs:ignored rhs:(Call
                        function:(Member lhs:members rhs:size)
                        args:(ArgList)
                  )) post:(Inc value:ignored) code:(Block
                         (Call function:(Member lhs:builder rhs:add) args:(ArgList
                               (Arg name:ast value:(ast name:Call members:(ArgList
                                    (Arg name:function value:(ast name:Member members:(ArgList
                                        (Arg name:lhs value:bQue)
                                        (Arg name:rhs value:pop)
                                    )))
                                    (Arg name:args value:(ast name:ArgList list:(ArgList)))
                               )))
                         ))
                  )
              )
              (Call function:(Member lhs:builder rhs:add) args:(ArgList
                   (Arg name:ast value:(ast name:Call members:(ArgList
                        (Arg name:function value:(ast name:Member members:(ArgList
                            (Arg name:lhs value:bQue)
                            (Arg name:rhs value:push)
                        )))
                        (Arg name:args value:(ast name:ArgList list:(ArgList
                            (Arg name:a1 value:(ast name:Arg members:(ArgList
                                (Arg name:value value:(ast name:Call members:(ArgList
                                    (Arg name:function value:(ast name:Member members:(ArgList
                                        (Arg name:lhs value:builder)
                                        (Arg name:rhs value:create)
                                    )))
                                    (Arg name:args value:(ast name:ArgList list:(ArgList)))
                                )))
                            )))
                        )))
                   )))
              ))
         ) otherwise:(Block
              (For init:(Assign lhs:(Define name:ignored type:int) rhs:0) cond:(Lt lhs:ignored rhs:(Call
                       function:(Member lhs:children rhs:size)
                       args:(ArgList)
                 )) post:(Inc value:ignored) code:(Block
                     (Call
                         function:(Member lhs:builder rhs:add)
                         args:(ArgList
                            (Arg name:name value:(Call
                               function:(Member lhs:bQue rhs:push)
                               args:(ArgList
                                  (Arg name:name value:(Call
                                     function:(Member
                                        lhs:(ast name:Call members:(ArgList
                                            (Arg name:function value:(ast name:Member members:(ArgList
                                                (Arg name:lhs value:(Call
                                                    function:(Member lhs:AST rhs:create)
                                                    args:(ArgList
                                                        (Arg name:name value:"IDLit")
                                                        (Arg name:value value:"builder")
                                                    )
                                                ))
                                                (Arg name:rhs value:(Call
                                                    function:(Member lhs:AST rhs:create)
                                                    args:(ArgList
                                                        (Arg name:name value:"IDLit")
                                                        (Arg name:value value:"add")
                                                    )
                                                ))
                                            )))
                                            (Arg name:args value:(ast name:ArgList list:(ArgList
                                                (Arg name:a1 value:(ast name:Arg members:(ArgList
                                                    (Arg name:name value:(Call
                                                        function:(Member lhs:AST rhs:create)
                                                        args:(ArgList
                                                            (Arg name:name value:"STRLit")
                                                            (Arg name:value value:"name")
                                                        )
                                                    ))
                                                    (Arg name:value value:(ast name:Call members:(ArgList
                                                        (Arg name:function value:(ast name:Member members:(ArgList
                                                            (Arg name:lhs value:(Call
                                                                function:(Member lhs:AST rhs:create)
                                                                args:(ArgList
                                                                    (Arg name:name value:"IDLit")
                                                                    (Arg name:value value:"bQue")
                                                                )
                                                            ))
                                                            (Arg name:lhs value:(Call
                                                                function:(Member lhs:AST rhs:create)
                                                                args:(ArgList
                                                                    (Arg name:name value:"IDLit")
                                                                    (Arg name:value value:"pop")
                                                                )
                                                            ))
                                                        )))
                                                        (Arg name:args value:(ast name:ArgList list:(ArgList)))
                                                    )))
                                                )))
                                            )))
                                        ))
                                        rhs:create
                                     )
                                     args:(ArgList)
                                  ))
                               )
                            ))
                         )
                     )
              ))
              (Call function:(Member lhs:builder rhs:add) args:(ArgList
                   (ast name:Assign members:(ArgList
                        (Arg name:lhs value:builder)
                        (Arg name:rhs value:(ast name:New members:(ArgList
                            (Arg name:type value:ASTBuilder)
                            (Arg name:args value:(ast name:ArgList list:(ArgList
                                (Arg name:a1 value:(ast name:Arg members:(ArgList
                                    (Arg name:name value:name)
                                    (Arg name:value value:"List")
                                )))
                            )))
                        )))
                   ))
              ))
              (For init:(Assign lhs:(Define name:ignored type:int) rhs:0) cond:(Lte lhs:ignored rhs:(Call
                        function:(Member lhs:children rhs:size)
                        args:(ArgList)
                  )) post:(Inc value:ignored) code:(Block
                         (Call function:(Member lhs:builder rhs:add) args:(ArgList
                               (Arg name:ast value:(ast name:Call members:(ArgList
                                    (Arg name:function value:(ast name:Member members:(ArgList
                                        (Arg name:lhs value:bQue)
                                        (Arg name:rhs value:pop)
                                    )))
                                    (Arg name:args value:(ast name:ArgList list:(ArgList)))
                               )))
                         ))
                  )
              )
              (Call function:(Member lhs:builder rhs:add) args:(ArgList
                   (Arg name:ast value:(ast name:Call members:(ArgList
                        (Arg name:function value:(ast name:Member members:(ArgList
                            (Arg name:lhs value:bQue)
                            (Arg name:rhs value:push)
                        )))
                        (Arg name:args value:(ast name:ArgList list:(ArgList
                            (Arg name:a1 value:(ast name:Arg members:(ArgList
                                (Arg name:value value:(ast name:Call members:(ArgList
                                    (Arg name:function value:(ast name:Member members:(ArgList
                                        (Arg name:lhs value:builder)
                                        (Arg name:rhs value:create)
                                    )))
                                    (Arg name:args value:(ast name:ArgList list:(ArgList)))
                                )))
                            )))
                        )))
                   )))
              ))
         ))
      ))
      (Expansion type:hasMember expansion:(Call function:(Member lhs:ast rhs:hasMember) args:(ArgList (Arg name:name value:(static_member name:name)))))
      (Expansion type:isMember expansion:(Call function:(Member lhs:ast rhs:isMembers) args:(ArgList)))
      (Expansion type:isList expansion:(Call function:(Member lhs:ast rhs:isList) args:(ArgList)))
      (Expansion type:isValue expansion:(Call function:(Member lhs:ast rhs:isValue) args:(ArgList)))
      (Expansion type:push expansion:(List (Comment (ast name:Convert members:(List
           (Arg name:type value:AST)
           (Arg name:value value:(ast name:Call members:(List
              (Arg name:function value:(ast name:Member members:(List
                 (Arg name:lhs value:bQue)
                 (Arg name:rhs value:push)
              )))
              (Arg name:args value:(ast name:ArgList members:(List
                  (Arg name:type value:(member name:value))
              )))
           )))
      )))))
      (Expansion type:pop expansion:(List (Comment (ast name:Convert members:(List
          (Arg name:type value:AST)
          (Arg name:value value:(ast name:Call members:(List
             (Arg name:function value:(ast name:Member members:(List
                (Arg name:lhs value:bQue)
                (Arg name:rhs value:pop)
             )))
             (Arg name:args value:(ast name:ArgList members:(List)))
          )))
      )))))
      (Expansion type:expFunName expansion:(concat expand (member name:type)))
  )
)
