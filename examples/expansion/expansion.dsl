(Expansions
  name:Expansion
  expansions:(ExpansionList
      (Expansion type:Expansions expansion:(List
            (Import name:(Name java util Arrays))
            (Import name:(Name java util Map))
            (Import name:(Name java util HashMap))
            (Import name:(Name java util Deque))
            (Import name:(Name java util ArrayDeque))
            (Import name:(Name dsl ast AST))
            (Import name:(Name dsl ast ASTBuilder))
            (Class modifier:public name:(concat Expansion (member name:name)) attrs:(AttrList) base:(Name dsl expansion Expansion)
                 attrs: (AttrList)
                 cons:(ConsList
                      (Constructor params:(ParamList (Param name:ast type:AST)) code:(Block
                          (Call function:super args:(ArgList (Arg name:ast value:ast)))
                          (Call function:update args:(ArgList))
                          (Call function:pushStack args:(ArgList))
                      ))
                 )
                 methods:(concat
                     (MethodList
                         (Method name:expand params:(ParamList) returnType:AST code:(Block
                             (Call function:expand args:(ArgList (Arg name:ast value:ast)))
                         ))
                         (Method name:expand params:(ParamList (Param name:ast type:AST)) returnType:AST code:(Block
                             (varDecls)
                             (Select value:(Call function:(Member lhs:ast rhs:getTypeName) args:(ArgList)) block:(List
                                  (members name:expansions template:(Case value:(String value:(member name:type)) block:(callExpansion type:(member name:type))))
                                  (Default block:(Block
                                     (If cond:(isMember) code:(Block
                                        (setName name:(getName))
                                        (For var:(Define name:memberName type:String) expr:(getMembers) code:(Block
                                           (setMember name:(ref name:memberName) member:(getMember member:(ref name:memberName)))
                                        ))
                                     ) otherwise:(If cond:(isList) code:(Block
                                        (setName name:(getName ))
                                        (For var:(Define name:memberAST type:AST) expr:(getMembers) code:(Block
                                           (addMember member:(ref name:memberAST)))
                                        )
                                     ) otherwise:(Block
                                        (setValue value:(expand ast:(getValue)))
                                     )))))
                                  ))
                             (Return value:(create)))
                     ))
                     (members name:expansions template:(Method name:(expFunName type:(member name:type) ) returnType:AST params:(ParamList (Param name:ast type:AST))
                        code:(Block )
                     ))
                 )
            )
      ))
      (Expansion type:Expansion expansion:(Block
            (Define type:ASTBuilder name:builder)
            (Define type:Deque name:bQue)
            (Assign lhs:bQue rhs:(New type:ArrayDeque args:(ArgList)))
            (member name:expansion template:(expand))
            (Return value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
      ))
      (Expansion type:member expansion:(List
                 (If cond:(hasMember name:template) code:(Block
                     (ast name:Assign members:(List
                         (Arg name:lhs value:ast)
                         (Arg name:rhs value:(ast name:(Call members:(List
                             (Arg name:function value:(ast name:Member members:(List
                                  (Arg name:lhs value:ast)
                                  (Arg name:rhs value:get)
                             )))
                             (Arg name:args value:(ast name:ArgList args:(List
                                 (comment "solve this...")
                                 (Arg name:first value:(ast name:Arg members:(List
                                    (Arg name:name value:name)
                                    (Arg name:value value:(ast name:Call members:(List
                                        (Arg name:function value:(ast name:Member members:(List
                                           (Arg name:lhs value:(pop))
                                           (Arg name:rhs value:toString)
                                        )))
                                        (Arg name:args value:(ast name:ArgList members:(List)))
                                    )))
                                 )))
                             )))
                         ))))
                     ))
                 ) otherwise:(Block

                 ))
      ))
      (Expansion type:members expansion:(List))
      (Expansion type:concat expansion:(List))
      (Expansion type:ast expansion:(List
            (comment "The below command needs to be reversed :/")
            (members name:members template:(List
               (push value:(expand expr:(member name:value)))
            ))
            (newBuilder)
            (members name:members template:
                  (Call function:(Member lhs:builder rhs:add) args:(ArgList
                          (Arg name:name value:(member name:name))
                          (Arg name:value value:(member name:(pop)))
                  ))
            )
            (push value:(create))
      ))

      (Expansion type:callExpansion expansion:
              (Return value:(Call function:(expFunName type:(member name:type)) args:(ArgList (Arg name:ast value:ast)))))
      (Expansion type:isMember expansion:(members template:(List)))
      (Expansion type:isList expansion:(members template:(List)))
      (Expansion type:isValue expansion:(members template:(List)))
      (Expansion type:itemKey expansion:(List))
      (Expansion type:typeName expansion:(List))
      (Expansion type:literalItem expansion:ast)
      (Expansion type:varDecls expansion:(List
           (Define type:ASTBuilder name:builder)
           (Define type:Deque name:bQue)
           (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList)))
           (Assign lhs:bQue rhs:(New type:ArrayDeque args:(ArgList)))
      ))
      (Expansion type:setName expansion:
          (Call function:(Member lhs:builder rhs:setName) args:(ArgList (Arg name:name value:(String value:(ref name:name)))))
      )
      (Expansion type:push expansion:ast)
      (Expansion type:pop expansion:(ast name:Convert members:(List
          (Arg name:type value:AST)
          (Arg name:value value:(ast name:Call members:(List
             (Arg name:function value:(ast name:Member members:(List
                (Arg name:lhs value:bQue)
                (Arg name:rhs value:pop)
             )))
             (Arg name:args value:(ast name:ArgList members:(List)))
          )))
      )))
      (Expansion type:newBuilder expansion:(Assign lhs:builder lhs:(New type:ASTBuilder args:(ArgList (Arg name:type value:(typeName))))))
      (Expansion type:create expansion:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
      (Expansion type:expFunName expansion:(concat expand (member name:type)))
      (Expansion type:expExpansion expansion:(List
             (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:(String value:(typeName))))))
             (Call function:(Member lhs:bQue rhs:push) args:builder)
             (isMember expansion:(members template:(List
                      (dispatch )
                      (Call function:(Member lhs:(Convert type:ASTBuilder value:(Call function:(Member lhs:bQue rhs:peek) args:(ArgList))) rhs:add) args:(ArgList (Arg name:name value:(String value:(itemKey))) (Arg name:value value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))))
             )))
             (isList expansion:(members template:(List
                      (dispatch )
                      (Call function:(Member lhs:(Convert type:ASTBuilder value:(Call function:(Member lhs:bQue rhs:peek) args:(ArgList))) rhs:add) args:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
             )))
             (isValue expansion:(Call function:(Member lhs:(Convert type:ASTBuilder value:(Call function:(Member lhs:bQue rhs:peek) args:(ArgList))) rhs:set) args:(String value:ast)))
             (Assign lhs:builder rhs:(Convert type:ASTBuilder value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))
      ))
      (Expansion type:expand expansion:(List
          (expExpansion)
      ))
  )
)
