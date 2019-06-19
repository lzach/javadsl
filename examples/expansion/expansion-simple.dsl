(Expansions
  name:ExpansionSimple
  expansions:(ExpansionList
      (Expansion type:Expansions expansion:(List
            (Import name:(Name java util Arrays))
            (Import name:(Name java util Map))
            (Import name:(Name java util HashMap))
            (Import name:(Name java util Deque))
            (Import name:(Name java util ArrayDeque))
            (Import name:(Name java util Collections))
            (Import name:(Name dsl ast AST))
            (Import name:(Name dsl ast ASTBuilder))
            (Class modifier:public name:(concat Expansion (Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:"name")))) attrs:(AttrList) base:(Name dsl expansion Expansion)
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
                             (Define type:Deque name:bQue)
                             (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList)))
                             (Assign lhs:bQue rhs:(New type:ArrayDeque args:(ArgList)))
                             (Select value:(Call function:(Member lhs:ast rhs:getTypeName) args:(ArgList)) block:(List
                                  (For  name:expansions template:(Case value:(String value:(member name:type)) block:(Return value:(Call function:(concat expand (member name:type)) args:(ArgList (Arg name:ast2 value:ast))))))
                                  (Default block:(Block
                                     (Call function:(Member lhs:builder rhs:setName) args:(ArgList
                                        (Arg name:name value:(Call function:(Member lhs:ast rhs:getTypeName) args:(ArgList)))
                                     ))
                                     (If cond:(isMember) code:(Block
                                        (Call function:(Member lhs:builder rhs:setName) args:(ArgList
                                                                            (Arg name:name value:"List")
                                                                        ))
                                        (Assign lhs:(Define type:List name:names) rhs:(New type:ArrayList args:(ArgList)))

                                        (For var:(Define name:memberName type:String) expr:(Call function:(Member lhs:ast rhs:getMembers) args:(ArgList)) code:(Block
                                           (Assign lhs:(Define type:AST name:child) rhs:(Call function:expand args:(ArgList (Arg name:ast value:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:memberName)))))))
                                           (If cond:(Neq lhs:child rhs:null) code:(Block
                                                (Call function:(Member lhs:names rhs:add) args:(ArgList (Arg name:value value:memberName)))
                                                (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:value value:child)))
                                           ))
                                        ))
                                        (Call function:(Member lhs:Collections rhs:reverse) args:(ArgList (Arg name:lst value:names)))

                                        (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:type value:(Call function:(Member lhs:ast rhs:getTypeName) args:(ArgList))))))
                                        (For var:(Define name:memberName type:String) expr:name code:(Block
                                             (Call function:(Member lhs:builder rhs:add) args:(ArgList
                                                 (Arg name:name value:memberName)
                                                 (Arg name:value value:(pop))
                                             ))
                                        ))
                                        (push value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                                     ) otherwise:(Block
                                         (If cond:(isList) code:(Block
                                            (Call function:(Member lhs:builder rhs:setName) args:(ArgList (Arg name:name value:List)))
                                            (Assign lhs:(Define type:List name:asts) rhs:(New type:ArrayList args:(ArgList)))

                                            (For var:(Define name:child type:AST) expr:(Call function:(Member lhs:ast rhs:getMemberList) args:(ArgList)) code:(Block
                                               (Assign lhs:child rhs:(Call function:expand args:(ArgList (Arg name:ast value:child))))
                                               (If cond:(Neq lhs:child rhs:null) code:(Block
                                                    (Call function:(Member lhs:asts rhs:add) args:(ArgList (Arg name:value value:child)))
                                               ))
                                            ))
                                            (Call function:(Member lhs:Collections rhs:reverse) args:(ArgList (Arg name:lst value:asts)))
                                            (For var:(Define name:child type:AST) expr:asts code:(Block
                                                (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:value value:child)))
                                            ))
                                            (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:type value:(Call function:(Member lhs:ast rhs:getTypeName) args:(ArgList))))))
                                            (For var:(Define name:memberName type:String) expr:names code:(Block
                                                 (Call function:(Member lhs:builder rhs:add) args:(ArgList
                                                     (Arg name:value value:(pop))
                                                 ))
                                                 (ast name:Call members:(List
                                                       (Arg name:function value:(ast name:Member members:(List
                                                               (Arg name:lhs value:builder)
                                                               (Arg name:rhs value:add)
                                                       )))
                                                       (Arg name:args value:(ast name:ArgList list:(List
                                                           (Arg name:arg value:(ast name:Arg members:(List
                                                                (Arg name:name value:value)
                                                                (Arg name:value value:name)
                                                           )))
                                                           (Arg name:arg value:(Convert type:AST value:
                                                              (Call function:(Member lhs:bQue rhs:pop) args:(ArgList))
                                                           ))
                                                       )))
                                                 ))
                                            ))
                                            (push value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                                         ) otherwise:(Block
                                            (Call function:(Member lhs:builder rhs:setName) args:(ArgList
                                                (Arg name:value value:(Call function:(Member lhs:ast rhs:getTypeName) args:(ArgList)))
                                            ))
                                            (Call function:(Member lhs:builder rhs:set) args:(ArgList
                                                (Arg name:value value:(Call function:(Member lhs:ast rhs:getValue) args:(ArgList)))
                                            ))
                                            (push value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                                     ))))
                                  ))

                             ))
                             (Return value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                         )
                     ))
                     (members name:expansions template:(Method name:(expFunName type:(member name:type) ) returnType:AST params:(ParamList (Param name:ast type:AST))
                        code:(Block
                             (Define type:ASTBuilder name:builder)
                             (Define type:Deque name:bQue)
                             (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList)))
                             (Assign lhs:bQue rhs:(New type:ArrayDeque args:(ArgList)))
                             (member name:expansion)
                             (Return value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                        )
                     ))
                 )
            )
      ))
      (Expansion type:Expansion expansion:(List))
      (Expansion type:concat expansion:(List))
  )
)
