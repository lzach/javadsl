(Expansions
  (Expansion type:Language expansion:(List (Import name:(Name domain2 Context)) (Import name:(Name java util HashMap))
                                           (Class name:Language attrs:(AttrList) methods:(MethodList
                                                                         (Method name:getContext params:(ParamList) returnType:Context code:(Block
                                                                             (Define type:Context name:ctx)
                                                                             (Assign lhs:ctx rhs:(New type:Context args:(ArgList)))
                                                                             (members template:(Call function:(Member lhs:ctx rhs:define) args:(memberItem)))
                                                                             (Return value:ctx)
                                                                         )))))
  )
  (Expansion type:Type expansion:(ArgList
                                     (Arg name:name  value:(String value:(member name:name)))
                                     (hasArg name:members arg:(Arg name:members value:(New type:HashMap args:(ArgList)
                                                        body:(Block (Block (members name:members template:(Call function:put args:(memberItem))))))))
                                     (hasArg name:arrayType arg:(Arg name:arrayType value:(String value:(member name:arrayType)))) ))
  (Expansion type:MemberPair expansion:(ArgList (Arg name:name value:(String value:(member name:name))) (Arg name:value value:(String value:(member name:type)))))
  (Expansion type:Relation expansion:(l))
)