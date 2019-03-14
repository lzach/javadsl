(Expansions
  name:Expansion
  expansions:(ExpansionList
      (Expansion type:Expansions expansion:(List
            (Import name:(Name java util Deque))
            (Import name:(Name java util Arrays))
            (Import name:(Name java util Map))
            (Import name:(Name java util HashMap))
            (Import name:(Name java util ArrayDeque))
            (Import name:(Name dsl ast AST))
            (Import name:(Name dsl ast ASTBuilder))
            (Class modifier:public name:(concat Expansion (member name:name)) attrs:(AttrList) base:(Name dsl expansion Expansion)
                 attrs: (AttrList
                     (Attr name:funcMap type:Map)
                 )
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
                                  (members name:expansions template:(Case value:(String value:(member name:type)) block:(callExpansion )))
                                  (members name:functions template:(Case value:(String value:(member name:name)) block:(callFunction )))
                                  (members name:operations template:(Case value:(String value:(member name:name)) block:(callOperation )))
                                  (Default block:(Block
                                     (If cond:(isFunction(ast)) code:(Block
                                        (push value:(Call function:(getName) args:(getArgs)))
                                     ) otherwise:(If cond:(isOperation(ast)) code:(Block
                                        (pushLocal)

                                        (addMember member:(expand ast:(member name:expansions)))
                                        (popLocal)
                                     ) otherwise:(If cond:(isMember(ast)) code:(Block
                                        (setName name:(getName ))
                                        (For val:(Define name:memberName type:String) expr:(getMembers) code:(Block
                                           (setMember name:(ref name:memberName) member:(getMember member:(ref name:memberName)))
                                        ))
                                     ) otherwise:(If cond:(isList(ast)) code:(Block
                                        (setName name:(getName ))
                                        (For val:(Define name:memberAST type:AST) expr:(getMembers) code:(Block
                                           (addMember member:(ref name:memberAST)))
                                        )
                                     )) otherwise:(Block
                                        (setValue value:(expand ast:(getValue)))
                                     ))))
                                  )))
                             )
                             (Return value:(create))
                         ))
                     )
                     (members name:expansions template:(Method name:(expFunName ) returnType:AST params:(ParamList (Param name:ast type:AST))
                        code:ast
                     ))
                     (members name:functions template:(member name:expansion))
                 )
            )
      ))
      (Expansion type:Expansion expansion:(Block
            (Define type:ASTBuilder name:builder)
            (Define type:Deque name:bQue)
            (Assign lhs:bQue rhs:(New type:ArrayDeque args:(ArgList)))

            (member name:expansion template:(dispatch))

            (Return value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
      ))
      (Expansion type:Function expansion:(Method name:(member name:name) returnType:AST params:(concat (ParamList (Param name:ast type:AST)) (member name:params)) code:(Block
            (Define type:ASTBuilder name:builder)
            (Define type:Deque name:bQue)
            (Assign lhs:bQue rhs:(New type:ArrayDeque args:(ArgList)))
            (member name:expansion template:(dispatch))
            (Return value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
      )))
  )
  functions:(FunctionList
      (Function name:expFunName params:(ParamList ) expansion:(concat expand (member name:type)))
      (Function name:expExpansion params:(ParamList ) expansion:(List
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
      (Function name:dispatch params:(ParamList) expansion:(List
          (isFunction expansion:(List
                    (Assign lhs:builder rhs:(New type:ASTBuilder args:(Call function:(typeName) args:(getArgs ))))
             ) otherwise:(expExpansion )
          )
      ))
      (Function name:getFunArgs params:(ParamList ) expansion:(concat
          (ArgList (Arg name:ast value:ast))
          (member name:params template:(ArgList (members template:(Arg name:(itemKey) value:(literalValue)))))
      ))
  )
  operations:(OpList
     (Operation name:callExpansion params:(ParamList ) expansion:
            (Return value:(Call function:(expFunName ) args:(ArgList (Arg name:ast value:ast)))))
     (Operation name:callFunction params:(ParamList ) expansion:
            (Return value:(Call function:(member name:name) args:(getFunArgs ))))
     (Operation name:callOperation params:(ParamList ) expansion:
            (Call function:expand args:(ArgList (Arg name:ast value:(member name:expansion)))))
     (Operation name:isMember params:(ParamList (Param type:AST name:expansion) (Param type:AST name:otherwise)) expansion:(members template:(List)))
     (Operation name:isList params:(ParamList (Param type:AST name:expansion) (Param type:AST name:otherwise)) expansion:(members template:(List)))
     (Operation name:isValue params:(ParamList (Param type:AST name:expansion) (Param type:AST name:otherwise)) expansion:(members template:(List)))
     (Operation name:isFunction params:(ParamList (Param type:AST name:expansion) (Param type:AST name:otherwise)) expansion:(members template:(List)))
     (Operation name:isOperation params:(ParamList (Param type:AST name:expansion) (Param type:AST name:otherwise)) expansion:(members template:(List)))
     (Operation name:member  params:(List) expansion:(List))
     (Operation name:members params:(List) expansion:(List))
     (Operation name:concat  params:(List) expansion:(List))
     (Operation name:itemKey  params:(List) expansion:(List))
     (Operation name:typeName  params:(List) expansion:(List))
     (Operation name:literalItem  params:(List) expansion:ast)

     (Operation name:varDecls  params:(List) expansion:(List
         (Define type:ASTBuilder name:builder)
         (Define type:Deque name:bQue)
         (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList)))
         (Assign lhs:bQue rhs:(New type:ArrayDeque args:(ArgList)))
     ))
     (Operation name:setName params:(ParamList (Param name:name type:AST)) expansion:
        (Call function:(Member lhs:builder rhs:setName) args:(ArgList (Arg name:name value:(String value:(ref name:name)))))
     )
     (Operation name:create params:(ParamList) expansion:(Call function:(Member lhs:builder rhs:create) args:(ArgList))
     )

  )

)
