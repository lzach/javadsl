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
            (Class modifier:public name:(concat Expansion (member name:name)) attrs:(AttrList) base:(Name domain2 expansion Expansion)
                 attrs: (AttrList
                     (Attr name:funcMap type:Map)
                 )
                 cons:(ConsList
                      (Constructor params:(ParamList) code:(Block
                          (Assign lhs:funcMap rhs:(New type:HashMap args:(ArgList)))
                          (members name:functions template:(List
                             (Call function:(Member lhs:funcMap rhs:put) args:(ArgList
                                    (Arg name:key value:(String value:(member name:name)))
                                    (Arg name:value  value:(Call function:(Member lhs:Arrays rhs:asList) args:(members name:params template:(String value:(member name:name)))))
                             ))
                          ))
                       ))
                 )
                 methods:(concat
                     (MethodList
                         (Method name:expand params:(ParamList (Param name:ast type:AST)) returnType:AST code:(Block
                             (Select value:(Call function:(Member lhs:ast rhs:getTypeName) args:(ArgList)) block:
                                  (members name:expansions template:(Case value:(String value:(member name:type)) block:(callExpansion ast:(literalItem)))))
                             (Return value:(Call function:(Member lhs:AST rhs:create) args:(ArgList (Arg name:typeName value:(String value:"")))))
                         ))
                     )
                     (members name:expansions template:(Method name:(expFunName ast:(literalItem)) returnType:AST params:(ParamList (Param name:ast type:AST))
                        code:(expandItem)
                     ))
                     (members name:functions template:(expandItem))
                 )
            ))
      )
      (Expansion type:Expansion expansion:(Block
            (Define type:ASTBuilder name:builder)
            (Define type:Deque name:bQue)
            (Assign lhs:bQue rhs:(New type:ArrayDeque args:(ArgList)))
            (member name:expansion template:(dispatch ast:(literalItem)))
            (Return value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
      ))
      (Expansion type:Function expansion:(Method name:(member name:name) returnType:AST params:(concat (ParamList (Param name:ast type:AST)) (member name:params)) code:(Block
            (Define type:ASTBuilder name:builder)
            (Define type:Deque name:bQue)
            (Assign lhs:bQue rhs:(New type:ArrayDeque args:(ArgList)))
            (member name:expansion template:(dispatch ast:(literalItem)))
            (Return value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
      )))
  )
  functions:(FunctionList
      (Function name:callExpansion params:(ParamList ) expansion:
            (Return value:(Call function:(expFunName ast:(literalItem)) args:(ArgList (Arg name:ast value:ast)))))
      (Function name:expFunName params:(ParamList ) expansion:(concat expand (member name:type)))
      (Function name:expExpansion params:(ParamList ) expansion:(List
             (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:(String value:(typeName))))))
             (Call function:(Member lhs:bQue rhs:push) args:builder)
             (isMember expansion:(members template:(List
                      (dispatch ast:(literalItem))
                      (Call function:(Member lhs:(Convert type:ASTBuilder value:(Call function:(Member lhs:bQue rhs:peek) args:(ArgList))) rhs:add) args:(ArgList (Arg name:name value:(String value:(itemKey))) (Arg name:value value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))))
             )))
             (isList expansion:(members template:(List
                      (dispatch ast:(literalItem))
                      (Call function:(Member lhs:(Convert type:ASTBuilder value:(Call function:(Member lhs:bQue rhs:peek) args:(ArgList))) rhs:add) args:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
             )))
             (isValue expansion:(Call function:(Member lhs:(Convert type:ASTBuilder value:(Call function:(Member lhs:bQue rhs:peek) args:(ArgList))) rhs:set) args:(String value:(literalItem))))
             (Assign lhs:builder rhs:(Convert type:ASTBuilder value:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList))))
      ))
      (Function name:dispatch params:(ParamList) expansion:(List
          (isFunction expansion:(List
                    (Assign lhs:builder rhs:(New type:ASTBuilder args:(Call function:(typeName) args:(getArgs ast:(literalItem)))))
             ) otherwise:(expExpansion ast:(literalItem))
          )
      ))
      (Function name:getArgs params:(ParamList ) expansion:(concat (ArgList (Arg name:ast value:ast) (members template:(Arg name:(itemKey) value:(literalValue))))))
      (Function name:isFunction params:(ParamList (Param type:AST name:expansion) (Param type:AST name:otherwise)) expansion:(members template:(List)))
  )
)
