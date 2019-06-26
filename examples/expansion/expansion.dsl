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
            (Class modifier:public name:(concat Expansion (member name:name)) attrs:(AttrList) base:(Name dsl expansion Expansion)
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
                                  (members name:expansions template:(Case value:(String value:(member name:type)) block:(Return value:(Call function:(expFunName type:(member name:type)) args:(ArgList (Arg name:ast2 value:ast))))))
                                  (Default block:(Block
                                     (Define type:(Generic type:List gens:(List String)) name:names)
                                     (Define type:(Generic type:List gens:(List AST)) name:members)
                                     (Assign lhs:names rhs:(New type:(Generic type:ArrayList gens:(List String)) args:(ArgList)))
                                     (Assign lhs:members rhs:(New type:(Generic type:ArrayList gens:(List AST)) args:(ArgList)))
                                     (Call function:(Member lhs:builder rhs:setName) args:(ArgList
                                        (Arg name:name value:(Call function:(Member lhs:ast rhs:getTypeName) args:(ArgList)))
                                     ))
                                     (If cond:(isMember) code:(Block
                                        (Call function:(Member lhs:builder rhs:setName) args:(ArgList
                                                                            (Arg name:name value:"List")
                                        ))
                                        (For var:(Define name:memberName type:String) expr:(Call function:(Member lhs:ast rhs:getMembers) args:(ArgList)) code:(Block
                                           (Assign lhs:(Define type:AST name:child) rhs:(Call function:expand args:(ArgList (Arg name:ast value:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:memberName)))))))
                                           (If cond:(Neq lhs:child rhs:null) code:(Block
                                                (Call function:(Member lhs:names rhs:add) args:(ArgList (Arg name:value value:memberName)))
                                                (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:value value:child)))
                                           ))
                                        ))
                                        (Call function:(Member lhs:Collections rhs:reverse) args:(ArgList (Arg name:lst value:names)))

                                        (Call function:(Member lhs:builder rhs:add) args:(ArgList
                                             (Arg name:ast value:(Call
                                                function:(Member
                                                    lhs:(Call
                                                        function:(Member
                                                            lhs:(New type:ASTBuilder args:(ArgList
                                                                      (Arg name:name value:"Assign")
                                                                ))
                                                            rhs:add
                                                        )
                                                        args:(ArgList
                                                            (Arg name:name value:"lhs")
                                                            (Arg name:value value:(Call
                                                                function:(Member lhs:AST rhs:IDLit)
                                                                args:(ArgList (Arg name:value value:"builder"))
                                                            ))
                                                        )
                                                    )
                                                    rhs:add
                                                )
                                                args:(ArgList
                                                   (Arg name:name value:"rhs")
                                                   (Arg name:value value:(Call
                                                      function:(Member
                                                         lhs:(Call
                                                             function:(Member
                                                                 lhs:(New type:ASTBuilder args:(ArgList
                                                                        (Arg name:name value:"New")
                                                                 ))
                                                                 rhs:add
                                                             )
                                                             args:(ArgList
                                                                (Arg name:name value:"type")
                                                                (Arg name:value value:(Call
                                                                    function:(Member lhs:AST rhs:IDLit)
                                                                    args:(ArgList (Arg name:value value:"ASTBuilder"))
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
                                                                        args:(ArgList (Arg name:value value:"ArgList"))
                                                                   )
                                                                   rhs:add
                                                                )
                                                                args:(ArgList
                                                                   (Arg name:ast value:(Call
                                                                         function:(Member
                                                                            lhs:(Call
                                                                                function:(Member
                                                                                    lhs:(New type:ASTBuilder args:(ArgList
                                                                                           (Arg name:name value:"New")
                                                                                    ))
                                                                                    rhs:add
                                                                                )
                                                                                args:(ArgList
                                                                                   (Arg name:name value:"type")
                                                                                   (Arg name:value value:(Call
                                                                                       function:(Member lhs:AST rhs:IDLit)
                                                                                       args:(ArgList (Arg name:value value:"ASTBuilder"))
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
                                                                                                       args:(ArgList (Arg name:value value:"Arg"))
                                                                                                  )
                                                                                                  rhs:add
                                                                                               )
                                                                                               args:(ArgList
                                                                                                  (Arg name:name value:"name")
                                                                                                  (Arg name:value value:(Call
                                                                                                       function:(Member
                                                                                                          lhs:AST
                                                                                                          rhs:IDLit
                                                                                                       )
                                                                                                       args:(ArgList (Arg name:name value:"name"))

                                                                                                  ))
                                                                                               )
                                                                                       )
                                                                                       rhs:add
                                                                                   )
                                                                                   args:(ArgList
                                                                                      (Arg name:name value:"value")
                                                                                      (Arg name:value value:(Call
                                                                                           function:(Member
                                                                                              lhs:AST
                                                                                              rhs:STRLit
                                                                                           )
                                                                                           args:(ArgList
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
                                                                   ))
                                                                )
                                                          ))
                                                      )
                                                   ))
                                                )
                                             ))
                                        ))
                                        (For var:(Define name:name type:String) expr:names code:(Block
                                             (Call function:(Member lhs:builder rhs:add) args:(ArgList
                                                  (Arg name:ast value:(Call
                                                      function:(Member
                                                          lhs:(Call
                                                              function:(Member
                                                                  lhs:(New type:ASTBuilder args:(ArgList
                                                                            (Arg name:name value:"Call")
                                                                      ))
                                                                  rhs:add
                                                              )
                                                              args:(ArgList
                                                                  (Arg name:name value:"function")
                                                                  (Arg name:value value:(Call
                                                                     function:(Member
                                                                         lhs:(Call
                                                                             function:(Member
                                                                                 lhs:(New type:ASTBuilder args:(ArgList
                                                                                           (Arg name:name value:"Member")
                                                                                     ))
                                                                                 rhs:add
                                                                             )
                                                                             args:(ArgList
                                                                                 (Arg name:name value:"lhs")
                                                                                 (Arg name:value value:(Call
                                                                                     function:(Member lhs:AST rhs:IDLit)
                                                                                     args:(ArgList (Arg name:value value:"builder"))
                                                                                 ))
                                                                             )
                                                                         )
                                                                         rhs:add
                                                                     )
                                                                     args:(ArgList
                                                                        (Arg name:name value:"rhs")
                                                                        (Arg name:value value:(Call
                                                                           function:(Member lhs:AST rhs:IDLit)
                                                                           args:(ArgList (Arg name:value value:"add"))
                                                                        ))
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
                                                                       args:(ArgList (Arg name:value value:"ArgList"))
                                                                  )
                                                                  rhs:add
                                                               )
                                                               args:(ArgList
                                                                  (Arg name:ast value:(Call
                                                                        function:(Member
                                                                           lhs:(Call
                                                                                function:(Member
                                                                                   lhs:(Call
                                                                                       function:(Member
                                                                                           lhs:(New type:ASTBuilder args:(ArgList
                                                                                                  (Arg name:name value:"New")
                                                                                           ))
                                                                                           rhs:add
                                                                                       )
                                                                                       args:(ArgList
                                                                                          (Arg name:name value:"type")
                                                                                          (Arg name:value value:(Call
                                                                                              function:(Member lhs:AST rhs:IDLit)
                                                                                              args:(ArgList (Arg name:value value:"ASTBuilder"))
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
                                                                                                              args:(ArgList (Arg name:value value:"Arg"))
                                                                                                         )
                                                                                                         rhs:add
                                                                                                      )
                                                                                                      args:(ArgList
                                                                                                         (Arg name:name value:"name")
                                                                                                         (Arg name:value value:(Call
                                                                                                              function:(Member
                                                                                                                 lhs:AST
                                                                                                                 rhs:IDLit
                                                                                                              )
                                                                                                              args:(ArgList (Arg name:name value:"name"))

                                                                                                         ))
                                                                                                      )
                                                                                              )
                                                                                              rhs:add
                                                                                          )
                                                                                          args:(ArgList
                                                                                             (Arg name:name value:"name")
                                                                                             (Arg name:value value:(Call
                                                                                                   function:(Member lhs:AST rhs:STRLit)
                                                                                                   args:(ArgList (Arg name:value value:name))
                                                                                             ))
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
                                                                                                        args:(ArgList (Arg name:value value:"Arg"))
                                                                                                   )
                                                                                                   rhs:add
                                                                                                )
                                                                                                args:(ArgList
                                                                                                   (Arg name:name value:"name")
                                                                                                   (Arg name:value value:(Call
                                                                                                        function:(Member
                                                                                                           lhs:AST
                                                                                                           rhs:IDLit
                                                                                                        )
                                                                                                        args:(ArgList (Arg name:name value:"name"))

                                                                                                   ))
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
                                                                                                           lhs:(New type:ASTBuilder args:(ArgList
                                                                                                                     (Arg name:name value:"Call")
                                                                                                               ))
                                                                                                           rhs:add
                                                                                                       )
                                                                                                       args:(ArgList
                                                                                                           (Arg name:name value:"function")
                                                                                                           (Arg name:value value:(Call
                                                                                                              function:(Member
                                                                                                                  lhs:(Call
                                                                                                                      function:(Member
                                                                                                                          lhs:(New type:ASTBuilder args:(ArgList
                                                                                                                                    (Arg name:name value:"Member")
                                                                                                                              ))
                                                                                                                          rhs:add
                                                                                                                      )
                                                                                                                      args:(ArgList
                                                                                                                          (Arg name:name value:"lhs")
                                                                                                                          (Arg name:value value:(Call
                                                                                                                              function:(Member lhs:AST rhs:IDLit)
                                                                                                                              args:(ArgList (Arg name:value value:"bQue"))
                                                                                                                          ))
                                                                                                                      )
                                                                                                                  )
                                                                                                                  rhs:add
                                                                                                              )
                                                                                                              args:(ArgList
                                                                                                                 (Arg name:name value:"rhs")
                                                                                                                 (Arg name:value value:(Call
                                                                                                                    function:(Member lhs:AST rhs:IDLit)
                                                                                                                    args:(ArgList (Arg name:value value:"pop"))
                                                                                                                 ))
                                                                                                              )
                                                                                                           ))
                                                                                                       )
                                                                                                   )
                                                                                                   rhs:add
                                                                                               )
                                                                                               args:(ArgList
                                                                                                  (Arg name:name value:"args")
                                                                                                  (Arg name:value value:(New
                                                                                                        type:ASTBuilder
                                                                                                        args:(ArgList (Arg name:value value:"ArgList"))

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
                                             ))
                                        ))
                                     ) otherwise:(Block
                                         (If cond:(isList) code:(Block
                                            (Call function:(Member lhs:builder rhs:setName) args:(ArgList (Arg name:name value:"List")))

                                            (For var:(Define name:child type:AST) expr:(Call function:(Member lhs:ast rhs:getMemberList) args:(ArgList)) code:(Block
                                               (Assign lhs:child rhs:(Call function:expand args:(ArgList (Arg name:ast value:child))))
                                               (If cond:(Neq lhs:child rhs:null) code:(Block
                                                    (Call function:(Member lhs:members rhs:add) args:(ArgList (Arg name:value value:child)))
                                               ))
                                            ))
                                            (Call function:(Member lhs:Collections rhs:reverse) args:(ArgList (Arg name:lst value:members)))
                                            (For var:(Define name:child type:AST) expr:members code:(Block
                                                (Call function:(Member lhs:builder rhs:add) args:(ArgList (Arg name:value value:child)))
                                            ))
                                            (Call function:(Member lhs:builder rhs:add) args:(ArgList
                                                 (Arg name:ast value:(Call
                                                    function:(Member
                                                        lhs:(Call
                                                            function:(Member
                                                                lhs:(New type:ASTBuilder args:(ArgList
                                                                          (Arg name:name value:"Assign")
                                                                    ))
                                                                rhs:add
                                                            )
                                                            args:(ArgList
                                                                (Arg name:name value:"lhs")
                                                                (Arg name:value value:(Call
                                                                    function:(Member lhs:AST rhs:IDLit)
                                                                    args:(ArgList (Arg name:value value:"builder"))
                                                                ))
                                                            )
                                                        )
                                                        rhs:add
                                                    )
                                                    args:(ArgList
                                                       (Arg name:name value:"rhs")
                                                       (Arg name:value value:(Call
                                                          function:(Member
                                                             lhs:(Call
                                                                 function:(Member
                                                                     lhs:(New type:ASTBuilder args:(ArgList
                                                                            (Arg name:name value:"New")
                                                                     ))
                                                                     rhs:add
                                                                 )
                                                                 args:(ArgList
                                                                    (Arg name:name value:"type")
                                                                    (Arg name:value value:(Call
                                                                        function:(Member lhs:AST rhs:IDLit)
                                                                        args:(ArgList (Arg name:value value:"ASTBuilder"))
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
                                                                            args:(ArgList (Arg name:value value:"ArgList"))
                                                                       )
                                                                       rhs:add
                                                                    )
                                                                    args:(ArgList
                                                                       (Arg name:ast value:(Call
                                                                             function:(Member
                                                                                lhs:(Call
                                                                                    function:(Member
                                                                                        lhs:(New type:ASTBuilder args:(ArgList
                                                                                               (Arg name:name value:"New")
                                                                                        ))
                                                                                        rhs:add
                                                                                    )
                                                                                    args:(ArgList
                                                                                       (Arg name:name value:"type")
                                                                                       (Arg name:value value:(Call
                                                                                           function:(Member lhs:AST rhs:IDLit)
                                                                                           args:(ArgList (Arg name:value value:"ASTBuilder"))
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
                                                                                                           args:(ArgList (Arg name:value value:"Arg"))
                                                                                                      )
                                                                                                      rhs:add
                                                                                                   )
                                                                                                   args:(ArgList
                                                                                                      (Arg name:name value:"name")
                                                                                                      (Arg name:value value:(Call
                                                                                                           function:(Member
                                                                                                              lhs:AST
                                                                                                              rhs:IDLit
                                                                                                           )
                                                                                                           args:(ArgList (Arg name:name value:"name"))

                                                                                                      ))
                                                                                                   )
                                                                                           )
                                                                                           rhs:add
                                                                                       )
                                                                                       args:(ArgList
                                                                                          (Arg name:name value:"value")
                                                                                          (Arg name:value value:(Call
                                                                                               function:(Member
                                                                                                  lhs:AST
                                                                                                  rhs:STRLit
                                                                                               )
                                                                                               args:(ArgList
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
                                                                       ))
                                                                    )
                                                              ))
                                                          )
                                                       ))
                                                    )
                                                 ))
                                            ))
                                            (For var:(Define name:ignored type:AST) expr:members code:(Block
                                                 (Call function:(Member lhs:builder rhs:add) args:(ArgList
                                                      (Arg name:ast value:(Call
                                                          function:(Member
                                                              lhs:(Call
                                                                  function:(Member
                                                                      lhs:(New type:ASTBuilder args:(ArgList
                                                                                (Arg name:name value:"Call")
                                                                          ))
                                                                      rhs:add
                                                                  )
                                                                  args:(ArgList
                                                                      (Arg name:name value:"function")
                                                                      (Arg name:value value:(Call
                                                                         function:(Member
                                                                             lhs:(Call
                                                                                 function:(Member
                                                                                     lhs:(New type:ASTBuilder args:(ArgList
                                                                                               (Arg name:name value:"Member")
                                                                                         ))
                                                                                     rhs:add
                                                                                 )
                                                                                 args:(ArgList
                                                                                     (Arg name:name value:"lhs")
                                                                                     (Arg name:value value:(Call
                                                                                         function:(Member lhs:AST rhs:IDLit)
                                                                                         args:(ArgList (Arg name:value value:"builder"))
                                                                                     ))
                                                                                 )
                                                                             )
                                                                             rhs:add
                                                                         )
                                                                         args:(ArgList
                                                                            (Arg name:name value:"rhs")
                                                                            (Arg name:value value:(Call
                                                                               function:(Member lhs:AST rhs:IDLit)
                                                                               args:(ArgList (Arg name:value value:"add"))
                                                                            ))
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
                                                                           args:(ArgList (Arg name:value value:"ArgList"))
                                                                      )
                                                                      rhs:add
                                                                   )
                                                                   args:(ArgList
                                                                      (Arg name:ast value:(Call
                                                                            function:(Member
                                                                               lhs:(Call
                                                                                   function:(Member
                                                                                       lhs:(New type:ASTBuilder args:(ArgList
                                                                                              (Arg name:name value:"New")
                                                                                       ))
                                                                                       rhs:add
                                                                                   )
                                                                                   args:(ArgList
                                                                                      (Arg name:name value:"type")
                                                                                      (Arg name:value value:(Call
                                                                                          function:(Member lhs:AST rhs:IDLit)
                                                                                          args:(ArgList (Arg name:value value:"ASTBuilder"))
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
                                                                                                          args:(ArgList (Arg name:value value:"Arg"))
                                                                                                     )
                                                                                                     rhs:add
                                                                                                  )
                                                                                                  args:(ArgList
                                                                                                     (Arg name:name value:"name")
                                                                                                     (Arg name:value value:(Call
                                                                                                          function:(Member
                                                                                                             lhs:AST
                                                                                                             rhs:IDLit
                                                                                                          )
                                                                                                          args:(ArgList (Arg name:name value:"name"))

                                                                                                     ))
                                                                                                  )
                                                                                          )
                                                                                          rhs:add
                                                                                      )
                                                                                      args:(ArgList
                                                                                         (Arg name:name value:"value")
                                                                                         (Arg name:value value:(ArgList
                                                                                             (Arg name:ast value:(Call
                                                                                                 function:(Member
                                                                                                     lhs:(Call
                                                                                                         function:(Member
                                                                                                             lhs:(New type:ASTBuilder args:(ArgList
                                                                                                                       (Arg name:name value:"Call")
                                                                                                                 ))
                                                                                                             rhs:add
                                                                                                         )
                                                                                                         args:(ArgList
                                                                                                             (Arg name:name value:"function")
                                                                                                             (Arg name:value value:(Call
                                                                                                                function:(Member
                                                                                                                    lhs:(Call
                                                                                                                        function:(Member
                                                                                                                            lhs:(New type:ASTBuilder args:(ArgList
                                                                                                                                      (Arg name:name value:"Member")
                                                                                                                                ))
                                                                                                                            rhs:add
                                                                                                                        )
                                                                                                                        args:(ArgList
                                                                                                                            (Arg name:name value:"lhs")
                                                                                                                            (Arg name:value value:(Call
                                                                                                                                function:(Member lhs:AST rhs:IDLit)
                                                                                                                                args:(ArgList (Arg name:value value:"bQue"))
                                                                                                                            ))
                                                                                                                        )
                                                                                                                    )
                                                                                                                    rhs:add
                                                                                                                )
                                                                                                                args:(ArgList
                                                                                                                   (Arg name:name value:"rhs")
                                                                                                                   (Arg name:value value:(Call
                                                                                                                      function:(Member lhs:AST rhs:IDLit)
                                                                                                                      args:(ArgList (Arg name:value value:"pop"))
                                                                                                                   ))
                                                                                                                )
                                                                                                             ))
                                                                                                         )
                                                                                                     )
                                                                                                     rhs:add
                                                                                                 )
                                                                                                 args:(ArgList
                                                                                                    (Arg name:name value:"args")
                                                                                                    (Arg name:value value:(New
                                                                                                          type:ASTBuilder
                                                                                                          args:(ArgList (Arg name:value value:"ArgList"))

                                                                                                    ))
                                                                                                 )
                                                                                             ))
                                                                                        ))
                                                                                      )
                                                                                ))
                                                                            )
                                                                      ))
                                                                   )
                                                             ))
                                                          )
                                                      ))
                                                 ))
                                            ))
                                         ) otherwise:(Block
                                            (Call function:(Member lhs:builder rhs:setName) args:(ArgList
                                                (Arg name:value value:(Call function:(Member lhs:ast rhs:getTypeName) args:(ArgList)))
                                            ))
                                            (Call function:(Member lhs:builder rhs:set) args:(ArgList
                                                (Arg name:value value:(Call function:(Member lhs:ast rhs:getValue) args:(ArgList)))
                                            ))
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
                             (Comment (member name:expansion))
                             (Return value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
                        )
                     ))
                 )
            )
      ))
      (Expansion type:Expansion expansion:(List))
      (Expansion type:member expansion:(List
                 (If cond:(hasMember name:template) code:(Block
                    (Comment (ast name:Assign members:(List
                         (Arg name:lhs value:ast)
                         (Arg name:rhs value:(ast name:Call members:(List
                             (Arg name:function value:(ast name:Member members:(List
                                  (Arg name:lhs value:ast)
                                  (Arg name:rhs value:get)
                             )))
                             (Arg name:args value:(ast name:ArgList list:(List
                                 (Arg name:first value:(ast name:Arg members:(List
                                    (Arg name:name value:name)
                                    (Arg name:value value:(ast name:Call members:(List
                                        (Arg name:function value:(ast name:Member members:(List
                                           (Arg name:lhs value:(pop))
                                           (Arg name:rhs value:toString)
                                        )))
                                        (Arg name:args value:(ast name:ArgList list:(List)))
                                    )))
                                 )))
                             )))
                         )))
                     )))
                 ) otherwise:(Block

                 ))
      ))
      (Expansion type:members expansion:(List
         (Define type:ASTBuilder name:builder)
         (Define type:Deque name:bQue)
         (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList)))
         (Assign lhs:bQue rhs:(New type:ArrayDeque args:(ArgList)))
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
                           (Arg name:args value:(ast name:ArgList list:(ArgList
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
                                                (Arg name:args value:(ast name:ArgList list:(ArgList)))
                                             )))
                                         )))
                                         (Arg name:rhs value:toString)
                                     )))
                                     (Arg name:args value:(ast name:ArgList list:(ArgList)))
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
      (Expansion type:ast expansion:(List
         (Comment "The below command needs to be reversed :/")
         (members reverse:true name:members template:(List
           (push value:(member name:value))
         ))
         (Assign lhs:builder rhs:(New type:ASTBuilder args:(ArgList (Arg name:type value:(typeName)))))
         (members name:members template:
              (Call function:(Member lhs:builder rhs:add) args:(ArgList
                      (Arg name:name value:(member name:name))
                      (Arg name:value value:(pop))
              ))
         )
         (push value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))
      ))

      (Expansion type:isMember expansion:(members template:(List)))
      (Expansion type:isList expansion:(members template:(List)))
      (Expansion type:isValue expansion:(members template:(List)))
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
