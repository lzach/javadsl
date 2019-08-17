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
                         (Method name:expandMembers0 params:(ParamList (Param name:ast type:AST)) returnType:AST code:(Block
                            (Return value:(Call
                                function:(Member
                                    lhs:(Call
                                       function:(Member
                                           lhs:(Call
                                                   function:(Member
                                                       lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Assign")))
                                                       rhs:add
                                                   )
                                                   args:(ArgList
                                                       (Arg name:name value:"lhs")
                                                       (Arg name:ast value:(Call
                                                          function:(Member lhs:AST rhs:IDLit)
                                                          args:(ArgList (Arg name:lit value:"ast"))
                                                       ))
                                                   )
                                           )
                                           rhs:add
                                       )
                                       args:(ArgList
                                           (Arg name:name value:"rhs")
                                           (Arg name:ast value:(Call
                                                   function:(Member
                                                       lhs:(Call
                                                               function:(Member
                                                                   lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                   rhs:add
                                                               )
                                                               args:(ArgList
                                                                   (Arg name:name value:"function")
                                                                   (Arg name:ast value:(Call
                                                                        function:(Member
                                                                            lhs:(Call
                                                                                 function:(Member
                                                                                     lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                     rhs:add
                                                                                 )
                                                                                 args:(ArgList
                                                                                     (Arg name:name value:"lhs")
                                                                                     (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:lit value:"ast"))) )
                                                                                 )
                                                                            )
                                                                            rhs:add
                                                                        )
                                                                        args:(ArgList
                                                                            (Arg name:name value:"rhs")
                                                                            (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:lit value:"get"))) )
                                                                        )
                                                                   ))
                                                               )
                                                       )
                                                       rhs:add
                                                   )
                                                   args:(ArgList
                                                       (Arg name:name value:"args")
                                                       (Arg name:ast value:(Call
                                                           function:(Member
                                                               lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                                               rhs:add
                                                           )
                                                           args:(ArgList
                                                               (Arg name:name value:(Call
                                                                   function:(Member
                                                                       lhs:(Call
                                                                              function:(Member
                                                                                  lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                                                  rhs:add
                                                                              )
                                                                              args:(ArgList
                                                                                  (Arg name:name value:"name")
                                                                                  (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"name"))) )
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
                                                                                          lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                          rhs:add
                                                                                      )
                                                                                      args:(ArgList
                                                                                          (Arg name:name value:"function")
                                                                                          (Arg name:value value:(Call
                                                                                              function:(Member
                                                                                                  lhs:(Call
                                                                                                        function:(Member
                                                                                                            lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                            rhs:add
                                                                                                        )
                                                                                                        args:(ArgList
                                                                                                            (Arg name:name value:"lhs")
                                                                                                            (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"bQue"))) )
                                                                                                        )
                                                                                                  )
                                                                                                  rhs:add
                                                                                              )
                                                                                              args:(ArgList
                                                                                                  (Arg name:name value:"rhs")
                                                                                                  (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"pop"))) )
                                                                                              )
                                                                                          ))
                                                                                      )
                                                                                ) rhs:add
                                                                            )
                                                                            args:(ArgList
                                                                               (Arg name:name value:"args")
                                                                               (Arg name:value value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:lit value:"ArgList"))) )
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
                            ))
                         ))
                         (Method name:expandMembers1 params:(ParamList (Param name:ast type:AST)) returnType:AST code:(Block
                            (Return value:(Call
                                function:(Member
                                    lhs:(Call
                                       function:(Member
                                           lhs:(Call
                                               function:(Member
                                                   lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Assign")))
                                                   rhs:add
                                               )
                                               args:(ArgList
                                                   (Arg name:name value:"lhs")
                                                   (Arg name:ast value:(Call
                                                       function:(Member
                                                           lhs:AST
                                                           rhs:IDLit
                                                       )
                                                       args:(ArgList
                                                           (Arg name:value value:"builder")
                                                       )
                                                   ))
                                               )
                                           )
                                           rhs:add
                                       )
                                       args:(ArgList
                                           (Arg name:name value:"rhs")
                                           (Arg name:ast value:(Call
                                               function:(Member
                                                   lhs:(Call
                                                           function:(Member
                                                               lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"New")))
                                                               rhs:add
                                                           )
                                                           args:(ArgList
                                                               (Arg name:name value:"type")
                                                               (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"ASTBuilder"))))
                                                           )
                                                   )
                                                   rhs:add
                                               )
                                               args:(ArgList
                                                   (Arg name:name value:"args")
                                                   (Arg name:ast value:(Call
                                                           function:(Member
                                                               lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                                               rhs:add
                                                           )
                                                           args:(ArgList
                                                               (Arg name:value value:(Call
                                                                   function:(Member
                                                                       lhs:(Call
                                                                               function:(Member
                                                                                   lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                                                   rhs:add
                                                                               )
                                                                               args:(ArgList
                                                                                       (Arg name:name value:"name")
                                                                                       (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"ast"))))
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
                                                                                             lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                             rhs:add
                                                                                         )
                                                                                         args:(ArgList
                                                                                             (Arg name:name value:"function")
                                                                                             (Arg name:ast value:(Call
                                                                                                 function:(Member
                                                                                                     lhs:(Call
                                                                                                         function:(Member
                                                                                                             lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                             rhs:add
                                                                                                         )
                                                                                                         args:(ArgList
                                                                                                             (Arg name:name value:"lhs")
                                                                                                             (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"bQue"))) )
                                                                                                         )
                                                                                                     )
                                                                                                     rhs:add
                                                                                                 )
                                                                                                 args:(ArgList
                                                                                                     (Arg name:name value:"rhs")
                                                                                                     (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"pop"))) )
                                                                                                 )
                                                                                             ))
                                                                                         )
                                                                                 )
                                                                                 rhs:add
                                                                             )
                                                                             args:(ArgList
                                                                                 (Arg name:name value:"args")
                                                                                 (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
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
                            ))
                         ))
                         (Method name:expandMembers2 params:(ParamList (Param name:ast type:AST)  (Param name:tmpBuilder type:ASTBuilder)) returnType:AST code:(Block
                            (Return value:(Call
                                function:(Member
                                    lhs:(Call
                                          function:(Member
                                              lhs:(Call
                                                 function:(Member
                                                     lhs:(Call
                                                         function:(Member
                                                             lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"If")))
                                                             rhs:add
                                                         )
                                                         args:(ArgList
                                                             (Arg name:name value:"cond")
                                                             (Arg name:ast value:(Call
                                                                  function:(Member
                                                                      lhs:(Call
                                                                          function:(Member
                                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                              rhs:add
                                                                          )
                                                                          args:(ArgList
                                                                              (Arg name:name value:"function")
                                                                              (Arg name:ast value:(Call
                                                                                  function:(Member
                                                                                      lhs:(Call
                                                                                          function:(Member
                                                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                              rhs:add
                                                                                          )
                                                                                          args:(ArgList
                                                                                              (Arg name:name value:"lhs")
                                                                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"ast"))) )
                                                                                          )
                                                                                      )
                                                                                      rhs:add
                                                                                  )
                                                                                  args:(ArgList
                                                                                      (Arg name:name value:"rhs")
                                                                                      (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"isMembers"))) )
                                                                                  )
                                                                              ))
                                                                          )
                                                                      )
                                                                      rhs:add
                                                                  )
                                                                  args:(ArgList
                                                                      (Arg name:name value:"args")
                                                                      (Arg name:ast value:(Call
                                                                          function:(Member
                                                                              lhs:AST
                                                                              rhs:emptyList
                                                                          )
                                                                          args:(ArgList
                                                                              (Arg name:ast value:"ArgList")
                                                                          )
                                                                      ))
                                                                  )
                                                             ))
                                                         )
                                                     )
                                                     rhs:add
                                                 )
                                                 args:(ArgList
                                                     (Arg name:name value:"code")
                                                     (Arg name:ast value:(Call
                                                         function:(Member
                                                             lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Block")))
                                                             rhs:add
                                                         )
                                                         args:(ArgList
                                                             (Arg name:value value:(Call
                                                                  function:(Member
                                                                      lhs:(Call
                                                                          function:(Member
                                                                              lhs:(Call
                                                                                  function:(Member
                                                                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"For")))
                                                                                      rhs:add
                                                                                  )
                                                                                  args:(ArgList
                                                                                      (Arg name:name value:"var")
                                                                                      (Arg name:ast value:(Call
                                                                                           function:(Member
                                                                                               lhs:(Call
                                                                                                   function:(Member
                                                                                                       lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Define")))
                                                                                                       rhs:add
                                                                                                   )
                                                                                                   args:(ArgList
                                                                                                       (Arg name:name value:"name")
                                                                                                       (Arg name:ast value:(Call
                                                                                                           function:(Member
                                                                                                               lhs:AST
                                                                                                               rhs:IDLit
                                                                                                           )
                                                                                                           args:(ArgList
                                                                                                               (Arg name:value value:"member" )
                                                                                                           )
                                                                                                       ))
                                                                                                   )
                                                                                               )
                                                                                               rhs:add
                                                                                           )
                                                                                           args:(ArgList
                                                                                               (Arg name:name value:"type")
                                                                                               (Arg name:ast value:(Call
                                                                                                    function:(Member
                                                                                                        lhs:AST
                                                                                                        rhs:IDLit
                                                                                                    )
                                                                                                    args:(ArgList
                                                                                                        (Arg name:value value:"String" )
                                                                                                    )
                                                                                               ))
                                                                                           )
                                                                                      ))
                                                                                  )
                                                                              )
                                                                              rhs:add
                                                                          )
                                                                          args:(ArgList
                                                                              (Arg name:name value:"expr")
                                                                              (Arg name:ast value:(Call
                                                                                  function:(Member
                                                                                      lhs:(Call
                                                                                          function:(Member
                                                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                              rhs:add
                                                                                          )
                                                                                          args:(ArgList
                                                                                              (Arg name:name value:"function")
                                                                                              (Arg name:ast value:(Call
                                                                                                  function:(Member
                                                                                                      lhs:(Call
                                                                                                          function:(Member
                                                                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                              rhs:add
                                                                                                          )
                                                                                                          args:(ArgList
                                                                                                              (Arg name:name value:"lhs")
                                                                                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"ast"))) )
                                                                                                          )
                                                                                                      )
                                                                                                      rhs:add
                                                                                                  )
                                                                                                  args:(ArgList
                                                                                                      (Arg name:name value:"rhs")
                                                                                                      (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"getMembers"))) )
                                                                                                  )
                                                                                              ))
                                                                                          )
                                                                                      )
                                                                                      rhs:add
                                                                                  )
                                                                                  args:(ArgList
                                                                                      (Arg name:name value:"args")
                                                                                      (Arg name:ast value:(Call
                                                                                              function:(Member
                                                                                                  lhs:AST
                                                                                                  rhs:emptyList
                                                                                              )
                                                                                              args:(ArgList
                                                                                                  (Arg name:ast value:"ArgList")
                                                                                              )
                                                                                      ))
                                                                                  )
                                                                              ))
                                                                          )
                                                                      )
                                                                      rhs:add
                                                                  )
                                                                  args:(ArgList
                                                                      (Arg name:name value:"code")
                                                                      (Arg name:value value:(Call
                                                                            function:(Member
                                                                                lhs:(Call
                                                                                    function:(Member
                                                                                        lhs:(Call
                                                                                            function:(Member
                                                                                                lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Block")))
                                                                                                rhs:add
                                                                                            )
                                                                                            args:(ArgList
                                                                                                (Arg name:ast value:(Call
                                                                                                     function:(Member
                                                                                                         lhs:(Call
                                                                                                             function:(Member
                                                                                                                 lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Assign")))
                                                                                                                 rhs:add
                                                                                                             )
                                                                                                             args:(ArgList
                                                                                                                 (Arg name:name value:"lhs")
                                                                                                                 (Arg name:ast value:(Call
                                                                                                                     function:(Member
                                                                                                                         lhs:AST
                                                                                                                         rhs:IDLit
                                                                                                                     )
                                                                                                                     args:(ArgList
                                                                                                                         (Arg name:value value:"ast" )
                                                                                                                     )
                                                                                                                 ))
                                                                                                             )
                                                                                                         )
                                                                                                         rhs:add
                                                                                                     )
                                                                                                     args:(ArgList
                                                                                                         (Arg name:name value:"rhs")
                                                                                                         (Arg name:ast value:(Call
                                                                                                              function:(Member
                                                                                                                  lhs:(Call
                                                                                                                          function:(Member
                                                                                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                                                              rhs:add
                                                                                                                          )
                                                                                                                          args:(ArgList
                                                                                                                              (Arg name:name value:"function")
                                                                                                                              (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"peekAST"))))
                                                                                                                          )
                                                                                                                  )
                                                                                                                  rhs:add
                                                                                                              )
                                                                                                              args:(ArgList
                                                                                                                  (Arg name:name value:"args")
                                                                                                                  (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                                                                                              )
                                                                                                         ))
                                                                                                     )
                                                                                                ))
                                                                                            )
                                                                                        )
                                                                                        rhs:add
                                                                                    )
                                                                                    args:(ArgList
                                                                                        (Arg name:ast value:(Call
                                                                                            function:(Member
                                                                                                lhs:(Call
                                                                                                    function:(Member
                                                                                                        lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Assign")))
                                                                                                        rhs:add
                                                                                                    )
                                                                                                    args:(ArgList
                                                                                                        (Arg name:name value:"lhs")
                                                                                                        (Arg name:ast value:(Call
                                                                                                            function:(Member
                                                                                                                lhs:AST
                                                                                                                rhs:IDLit
                                                                                                            )
                                                                                                            args:(ArgList
                                                                                                                (Arg name:value value:"ast" )
                                                                                                            )
                                                                                                        ))
                                                                                                    )
                                                                                                )
                                                                                                rhs:add
                                                                                            )
                                                                                            args:(ArgList
                                                                                                (Arg name:name value:"rhs")
                                                                                                (Arg name:ast value:(Call
                                                                                                    function:(Member
                                                                                                        lhs:(Call
                                                                                                            function:(Member
                                                                                                                lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                                                rhs:add
                                                                                                            )
                                                                                                            args:(ArgList
                                                                                                                (Arg name:name value:"function")
                                                                                                                (Arg name:ast value:(Call
                                                                                                                    function:(Member
                                                                                                                        lhs:(Call
                                                                                                                            function:(Member
                                                                                                                                lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                                                rhs:add
                                                                                                                            )
                                                                                                                            args:(ArgList
                                                                                                                                (Arg name:name value:"lhs")
                                                                                                                                (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"ast"))))
                                                                                                                            )
                                                                                                                        )
                                                                                                                        rhs:add
                                                                                                                    )
                                                                                                                    args:(ArgList
                                                                                                                        (Arg name:name value:"rhs")
                                                                                                                        (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"get"))))
                                                                                                                    )
                                                                                                                ))
                                                                                                            )
                                                                                                        )
                                                                                                        rhs:add
                                                                                                    )
                                                                                                    args:(ArgList
                                                                                                        (Arg name:name value:"args")
                                                                                                        (Arg name:ast value:(Call
                                                                                                            function:(Member
                                                                                                                lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                                                                                                rhs:add
                                                                                                            )
                                                                                                            args:(ArgList
                                                                                                                (Arg name:value value:(Call
                                                                                                                    function:(Member
                                                                                                                        lhs:(Call
                                                                                                                                function:(Member
                                                                                                                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                                                                                                    rhs:add
                                                                                                                                )
                                                                                                                                args:(ArgList
                                                                                                                                        (Arg name:name value:"name")
                                                                                                                                        (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"name"))))
                                                                                                                                )
                                                                                                                        )
                                                                                                                        rhs:add
                                                                                                                    )
                                                                                                                    args:(ArgList
                                                                                                                            (Arg name:name value:"value")
                                                                                                                            (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"member"))))
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
                                                                                rhs:add
                                                                            )
                                                                            args:(ArgList
                                                                                (Arg name:value value:(Call
                                                                                      function:(Member
                                                                                          lhs:tmpBuilder
                                                                                          rhs:create
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
                                              )
                                              rhs:add
                                          )
                                          args:(ArgList
                                              (Arg name:name value:"otherwise")
                                              (Arg name:value value:(Call
                                                    function:(Member
                                                        lhs:(Call
                                                           function:(Member
                                                               lhs:(Call
                                                                   function:(Member
                                                                       lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"If")))
                                                                       rhs:add
                                                                   )
                                                                   args:(ArgList
                                                                       (Arg name:name value:"cond")
                                                                       (Arg name:ast value:(Call
                                                                            function:(Member
                                                                                lhs:(Call
                                                                                    function:(Member
                                                                                        lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                        rhs:add
                                                                                    )
                                                                                    args:(ArgList
                                                                                        (Arg name:name value:"function")
                                                                                        (Arg name:ast value:(Call
                                                                                            function:(Member
                                                                                                lhs:(Call
                                                                                                    function:(Member
                                                                                                        lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                        rhs:add
                                                                                                    )
                                                                                                    args:(ArgList
                                                                                                        (Arg name:name value:"lhs")
                                                                                                        (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"ast"))) )
                                                                                                    )
                                                                                                )
                                                                                                rhs:add
                                                                                            )
                                                                                            args:(ArgList
                                                                                                (Arg name:name value:"rhs")
                                                                                                (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"isList"))) )
                                                                                            )
                                                                                        ))
                                                                                    )
                                                                                )
                                                                                rhs:add
                                                                            )
                                                                            args:(ArgList
                                                                                (Arg name:name value:"args")
                                                                                (Arg name:ast value:(Call
                                                                                    function:(Member
                                                                                        lhs:AST
                                                                                        rhs:emptyList
                                                                                    )
                                                                                    args:(ArgList
                                                                                        (Arg name:ast value:"ArgList")
                                                                                    )
                                                                                ))
                                                                            )
                                                                       ))
                                                                   )
                                                               )
                                                               rhs:add
                                                           )
                                                           args:(ArgList
                                                               (Arg name:name value:"code")
                                                               (Arg name:ast value:(Call
                                                                   function:(Member
                                                                       lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Block")))
                                                                       rhs:add
                                                                   )
                                                                   args:(ArgList
                                                                       (Arg name:value value:(Call
                                                                            function:(Member
                                                                                lhs:(Call
                                                                                    function:(Member
                                                                                        lhs:(Call
                                                                                            function:(Member
                                                                                                lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"For")))
                                                                                                rhs:add
                                                                                            )
                                                                                            args:(ArgList
                                                                                                (Arg name:name value:"var")
                                                                                                (Arg name:ast value:(Call
                                                                                                     function:(Member
                                                                                                         lhs:(Call
                                                                                                             function:(Member
                                                                                                                 lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Define")))
                                                                                                                 rhs:add
                                                                                                             )
                                                                                                             args:(ArgList
                                                                                                                 (Arg name:name value:"name")
                                                                                                                 (Arg name:ast value:(Call
                                                                                                                     function:(Member
                                                                                                                         lhs:AST
                                                                                                                         rhs:IDLit
                                                                                                                     )
                                                                                                                     args:(ArgList
                                                                                                                         (Arg name:value value:"member" )
                                                                                                                     )
                                                                                                                 ))
                                                                                                             )
                                                                                                         )
                                                                                                         rhs:add
                                                                                                     )
                                                                                                     args:(ArgList
                                                                                                         (Arg name:name value:"type")
                                                                                                         (Arg name:ast value:(Call
                                                                                                              function:(Member
                                                                                                                  lhs:AST
                                                                                                                  rhs:IDLit
                                                                                                              )
                                                                                                              args:(ArgList
                                                                                                                  (Arg name:value value:"AST" )
                                                                                                              )
                                                                                                         ))
                                                                                                     )
                                                                                                ))
                                                                                            )
                                                                                        )
                                                                                        rhs:add
                                                                                    )
                                                                                    args:(ArgList
                                                                                        (Arg name:name value:"expr")
                                                                                        (Arg name:ast value:(Call
                                                                                            function:(Member
                                                                                                lhs:(Call
                                                                                                    function:(Member
                                                                                                        lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                                        rhs:add
                                                                                                    )
                                                                                                    args:(ArgList
                                                                                                        (Arg name:name value:"function")
                                                                                                        (Arg name:ast value:(Call
                                                                                                            function:(Member
                                                                                                                lhs:(Call
                                                                                                                    function:(Member
                                                                                                                        lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                                        rhs:add
                                                                                                                    )
                                                                                                                    args:(ArgList
                                                                                                                        (Arg name:name value:"lhs")
                                                                                                                        (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"ast"))) )
                                                                                                                    )
                                                                                                                )
                                                                                                                rhs:add
                                                                                                            )
                                                                                                            args:(ArgList
                                                                                                                (Arg name:name value:"rhs")
                                                                                                                (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"getMemberList"))) )
                                                                                                            )
                                                                                                        ))
                                                                                                    )
                                                                                                )
                                                                                                rhs:add
                                                                                            )
                                                                                            args:(ArgList
                                                                                                (Arg name:name value:"args")
                                                                                                (Arg name:ast value:(Call
                                                                                                        function:(Member
                                                                                                            lhs:AST
                                                                                                            rhs:emptyList
                                                                                                        )
                                                                                                        args:(ArgList
                                                                                                            (Arg name:ast value:"ArgList")
                                                                                                        )
                                                                                                ))
                                                                                            )
                                                                                        ))
                                                                                    )
                                                                                )
                                                                                rhs:add
                                                                            )
                                                                            args:(ArgList
                                                                                (Arg name:name value:"code")
                                                                                (Arg name:value value:(Call
                                                                                      function:(Member
                                                                                          lhs:(Call
                                                                                              function:(Member
                                                                                                  lhs:(Call
                                                                                                      function:(Member
                                                                                                          lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Block")))
                                                                                                          rhs:add
                                                                                                      )
                                                                                                      args:(ArgList
                                                                                                          (Arg name:ast value:(Call
                                                                                                               function:(Member
                                                                                                                   lhs:(Call
                                                                                                                       function:(Member
                                                                                                                           lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Assign")))
                                                                                                                           rhs:add
                                                                                                                       )
                                                                                                                       args:(ArgList
                                                                                                                           (Arg name:name value:"lhs")
                                                                                                                           (Arg name:ast value:(Call
                                                                                                                               function:(Member
                                                                                                                                   lhs:AST
                                                                                                                                   rhs:IDLit
                                                                                                                               )
                                                                                                                               args:(ArgList
                                                                                                                                   (Arg name:value value:"ast" )
                                                                                                                               )
                                                                                                                           ))
                                                                                                                       )
                                                                                                                   )
                                                                                                                   rhs:add
                                                                                                               )
                                                                                                               args:(ArgList
                                                                                                                   (Arg name:name value:"rhs")
                                                                                                                   (Arg name:ast value:(Call
                                                                                                                        function:(Member
                                                                                                                            lhs:(Call
                                                                                                                                    function:(Member
                                                                                                                                        lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                                                                        rhs:add
                                                                                                                                    )
                                                                                                                                    args:(ArgList
                                                                                                                                        (Arg name:name value:"function")
                                                                                                                                        (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"peekAST"))))
                                                                                                                                    )
                                                                                                                            )
                                                                                                                            rhs:add
                                                                                                                        )
                                                                                                                        args:(ArgList
                                                                                                                            (Arg name:name value:"args")
                                                                                                                            (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                                                                                                        )
                                                                                                                   ))
                                                                                                               )
                                                                                                          ))
                                                                                                      )
                                                                                                  )
                                                                                                  rhs:add
                                                                                              )
                                                                                              args:(ArgList
                                                                                                  (Arg name:ast value:(Call
                                                                                                      function:(Member
                                                                                                          lhs:(Call
                                                                                                              function:(Member
                                                                                                                  lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Assign")))
                                                                                                                  rhs:add
                                                                                                              )
                                                                                                              args:(ArgList
                                                                                                                  (Arg name:name value:"lhs")
                                                                                                                  (Arg name:ast value:(Call
                                                                                                                      function:(Member
                                                                                                                          lhs:AST
                                                                                                                          rhs:IDLit
                                                                                                                      )
                                                                                                                      args:(ArgList
                                                                                                                          (Arg name:value value:"ast" )
                                                                                                                      )
                                                                                                                  ))
                                                                                                              )
                                                                                                          )
                                                                                                          rhs:add
                                                                                                      )
                                                                                                      args:(ArgList
                                                                                                          (Arg name:name value:"rhs")
                                                                                                          (Arg name:ast value:(Call
                                                                                                              function:(Member
                                                                                                                  lhs:AST
                                                                                                                  rhs:IDLit
                                                                                                              )
                                                                                                              args:(ArgList
                                                                                                                  (Arg name:value value:"member" )
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
                                                                                                    lhs:tmpBuilder
                                                                                                    rhs:create
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
                                                        )
                                                        rhs:add
                                                    )
                                                    args:(ArgList
                                                        (Arg name:name value:"otherwise")
                                                        (Arg name:value value:(Call
                                                              function:(Member
                                                                  lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Block")))
                                                                  rhs:add
                                                              )
                                                              args:(ArgList
                                                                  (Arg name:ast value:(Call
                                                                        function:(Member
                                                                            lhs:tmpBuilder
                                                                            rhs:create
                                                                        )
                                                                        args:(ArgList)
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
                            ))
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
                     args:(ArgList (Arg name:ast value:(Call
                        function:(Member
                            lhs:(Call
                                    function:(Member
                                        lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                        rhs:add
                                    )
                                    args:(ArgList
                                        (Arg name:name value:"function")
                                        (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"pushAST"))))
                                    )
                            )
                            rhs:add
                        )
                        args:(ArgList
                            (Arg name:name value:"args")
                            (Arg name:ast value:(Call
                                    function:(Member
                                        lhs:(Call
                                                function:(Member
                                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                                    rhs:add
                                                )
                                                args:(ArgList
                                                    (Arg name:ast value:(Call
                                                        function:(Member
                                                            lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                            rhs:add
                                                        )
                                                        args:(ArgList
                                                            (Arg name:name value:"name")
                                                            (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:lit value:"ast"))) )
                                                        )
                                                    ))
                                                )
                                        )
                                        rhs:add
                                    )
                                    args:(ArgList
                                        (Arg name:ast value:(Call
                                            function:(Member
                                                lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                rhs:add
                                            )
                                            args:(ArgList
                                                (Arg name:name value:"ast")
                                                (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"ast"))) )
                                            )
                                        ))
                                    )
                            ))
                        )
                     )))
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
             (Call
                  function:(Member lhs:builder rhs:add)
                  args:(ArgList (Arg name:ast value:(Call
                     function:(Member
                         lhs:(Call
                                 function:(Member
                                     lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                     rhs:add
                                 )
                                 args:(ArgList
                                     (Arg name:name value:"function")
                                     (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"pushAST"))))
                                 )
                         )
                         rhs:add
                     )
                     args:(ArgList
                         (Arg name:name value:"args")
                         (Arg name:ast value:(Call
                                 function:(Member
                                     lhs:(Call
                                             function:(Member
                                                 lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                                 rhs:add
                                             )
                                             args:(ArgList
                                                 (Arg name:ast value:(Call
                                                     function:(Member
                                                         lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                         rhs:add
                                                     )
                                                     args:(ArgList
                                                         (Arg name:name value:"name")
                                                         (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:lit value:"ast"))) )
                                                     )
                                                 ))
                                             )
                                     )
                                     rhs:add
                                 )
                                 args:(ArgList
                                     (Arg name:ast value:(Call
                                         function:(Member
                                             lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                             rhs:add
                                         )
                                         args:(ArgList
                                             (Arg name:name value:"value")
                                             (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"ast"))) )
                                         )
                                     ))
                                 )
                         ))
                     )
                  )))
             )
             (If cond:(Call function:(Member lhs:ast rhs:hasMember) args:(ArgList (Arg name:name value:"name"))) code:(Block
                (Call function:(Member lhs:builder rhs:add) args:(ArgList (Arg name:value value:(Call function:expand args:(ArgList (Arg name:ast value:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:name value:"name")))))))))
                (Call
                      function:(Member lhs:builder rhs:add)
                      args:(ArgList (Arg name:ast value:(Call function:expandMembers0 args:(ArgList (Arg name:ast value:ast)))))
                )
             ) otherwise:(Block))
             (Call
                   function:(Member lhs:builder rhs:add)
                   args:(ArgList (Arg name:ast value:(Call
                      function:(Member
                          lhs:(Call
                                  function:(Member
                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Assign")))
                                      rhs:add
                                  )
                                  args:(ArgList
                                      (Arg name:name value:"lhs")
                                      (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"builder"))))
                                  )
                          )
                          rhs:add
                      )
                      args:(ArgList
                          (Arg name:name value:"args")
                          (Arg name:ast value:(Call
                                  function:(Member
                                      lhs:(Call
                                              function:(Member
                                                  lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"New")))
                                                  rhs:add
                                              )
                                              args:(ArgList
                                                  (Arg name:name value:"type")
                                                  (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"ASTBuilder"))) )
                                              )
                                      )
                                      rhs:add
                                  )
                                  args:(ArgList
                                      (Arg name:name value:"args")
                                      (Arg name:value value:(Call
                                          function:(Member
                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                              rhs:add
                                          )
                                          args:(ArgList
                                              (Arg name:value value:(Call
                                                    function:(Member
                                                        lhs:(Call
                                                                function:(Member
                                                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                                    rhs:add
                                                                )
                                                                args:(ArgList
                                                                     (Arg name:name value:"name")
                                                                     (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"typeName"))) )
                                                                )
                                                        )
                                                        rhs:add
                                                    )
                                                    args:(ArgList
                                                         (Arg name:name value:"value")
                                                         (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:lit value:"List"))) )
                                                    )
                                              ))
                                          )
                                      ))
                                  )
                          ))
                      )
                   )))
             )
             (Assign
                lhs:(Define name:tmpBuilder type:ASTBuilder)
                rhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"List")))
             )
             (Call
                function:(Member lhs:tmpBuilder rhs:add)
                args:(ArgList
                    (Arg name:ast value:(Call
                        function:expand
                        args:(ArgList
                            (Arg name:ast value:(Call
                                function:(Member lhs:ast rhs:get)
                                args:(ArgList (Arg name:name value:"template"))
                            ))
                        )
                    ))
                )
             )
             (Call
                function:(Member lhs:tmpBuilder rhs:add)
                args:(ArgList
                    (Arg name:ast value:(Call function:expandMembers0 args:(ArgList (Arg name:ast value:ast))))
                )
             )
             (Call
                function:(Member lhs:tmpBuilder rhs:add)
                args:(ArgList
                    (Arg name:ast value:(Call
                         function:(Member
                             lhs:(Call
                                 function:(Member
                                     lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Assign")))
                                     rhs:add
                                 )
                                 args:(ArgList
                                     (Arg name:name value:"lhs")
                                     (Arg name:ast value:(Call
                                         function:(Member
                                             lhs:AST
                                             rhs:IDLit
                                         )
                                         args:(ArgList
                                             (Arg name:value value:"builder")
                                         )
                                     ))
                                 )
                             )
                             rhs:add
                         )
                         args:(ArgList
                             (Arg name:name value:"rhs")
                             (Arg name:ast value:(Call
                                    function:(Member
                                        lhs:(Call
                                             function:(Member
                                                 lhs:(Call
                                                         function:(Member
                                                             lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"New")))
                                                             rhs:add
                                                         )
                                                         args:(ArgList
                                                             (Arg name:name value:"type")
                                                             (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"ASTBuilder"))))
                                                         )
                                                 )
                                                 rhs:add
                                             )
                                             args:(ArgList
                                                 (Arg name:name value:"args")
                                                 (Arg name:ast value:(Call
                                                         function:(Member
                                                             lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                                             rhs:add
                                                         )
                                                         args:(ArgList
                                                             (Arg name:value value:(Call
                                                                 function:(Member
                                                                     lhs:(Call
                                                                             function:(Member
                                                                                 lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                                                 rhs:add
                                                                             )
                                                                             args:(ArgList
                                                                                     (Arg name:name value:"name")
                                                                                     (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"ast"))))
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
                                                                                           lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                           rhs:add
                                                                                       )
                                                                                       args:(ArgList
                                                                                           (Arg name:name value:"function")
                                                                                           (Arg name:ast value:(Call
                                                                                               function:(Member
                                                                                                   lhs:(Call
                                                                                                       function:(Member
                                                                                                           lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                           rhs:add
                                                                                                       )
                                                                                                       args:(ArgList
                                                                                                           (Arg name:name value:"lhs")
                                                                                                           (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"bQue"))) )
                                                                                                       )
                                                                                                   )
                                                                                                   rhs:add
                                                                                               )
                                                                                               args:(ArgList
                                                                                                   (Arg name:name value:"rhs")
                                                                                                   (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"pop"))) )
                                                                                               )
                                                                                           ))
                                                                                       )
                                                                               )
                                                                               rhs:add
                                                                           )
                                                                           args:(ArgList
                                                                               (Arg name:name value:"args")
                                                                               (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                                                           )
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
                                        (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"builder"))))
                                    )
                             ))
                         )
                    ))
                )
             )
             (Call
                function:(Member lhs:tmpBuilder rhs:add)
                args:(ArgList
                    (Arg name:ast value:(Call
                        function:(Member
                            lhs:(Call
                                    function:(Member
                                        lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                        rhs:add
                                    )
                                    args:(ArgList
                                        (Arg name:name value:"function")
                                        (Arg name:ast value:(Call
                                                function:(Member
                                                    lhs:(Call
                                                            function:(Member
                                                                lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                rhs:add
                                                            )
                                                            args:(ArgList
                                                                (Arg name:name value:"lhs")
                                                                (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"bQue"))) )
                                                            )
                                                    )
                                                    rhs:add
                                                )
                                                args:(ArgList
                                                    (Arg name:name value:"rhs")
                                                    (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"push"))) )
                                                )
                                        ))
                                    )
                            )
                            rhs:add
                        )
                        args:(ArgList
                            (Arg name:name value:"args")
                            (Arg name:ast value:(Call
                                    function:(Member
                                        lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                        rhs:add
                                    )
                                    args:(ArgList
                                        (Arg name:value value:(Call
                                            function:(Member
                                                lhs:(Call
                                                        function:(Member
                                                            lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                            rhs:add
                                                        )
                                                        args:(ArgList
                                                                (Arg name:name value:"name")
                                                                (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"name"))))
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
                                                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                      rhs:add
                                                                  )
                                                                  args:(ArgList
                                                                      (Arg name:name value:"function")
                                                                      (Arg name:ast value:(Call
                                                                              function:(Member
                                                                                  lhs:(Call
                                                                                          function:(Member
                                                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                              rhs:add
                                                                                          )
                                                                                          args:(ArgList
                                                                                              (Arg name:name value:"lhs")
                                                                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"builder"))) )
                                                                                          )
                                                                                  )
                                                                                  rhs:add
                                                                              )
                                                                              args:(ArgList
                                                                                  (Arg name:name value:"rhs")
                                                                                  (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"create"))) )
                                                                              )
                                                                      ))
                                                                  )
                                                          )
                                                          rhs:add
                                                      )
                                                      args:(ArgList
                                                          (Arg name:name value:"args")
                                                          (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
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
             (Call
                function:(Member lhs:builder rhs:add)
                args:(ArgList (Arg name:ast value:(Call function:expandMembers2 args:(ArgList (Arg name:ast value:ast) (Arg name:tmpBuilder value:tmpBuilder)))))
             )
             (Call
                 function:(Member
                     lhs:builder
                     rhs:add
                 )
                 args:(ArgList
                     (Arg name:ast value:(Call
                         function:(Member
                             lhs:(Call
                                 function:(Member
                                     lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Assign")))
                                     rhs:add
                                 )
                                 args:(ArgList
                                     (Arg name:name value:"lhs")
                                     (Arg name:ast value:(Call
                                         function:(Member
                                             lhs:AST
                                             rhs:IDLit
                                         )
                                         args:(ArgList
                                             (Arg name:value value:"ast" )
                                         )
                                     ))
                                 )
                             )
                             rhs:add
                         )
                         args:(ArgList
                             (Arg name:name value:"rhs")
                             (Arg name:ast value:(Call
                                 function:(Member
                                     lhs:(Call
                                             function:(Member
                                                 lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                 rhs:add
                                             )
                                             args:(ArgList
                                                 (Arg name:name value:"function")
                                                 (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"popAST"))))
                                             )
                                     )
                                     rhs:add
                                 )
                                 args:(ArgList
                                     (Arg name:name value:"args")
                                     (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                 )
                             ))
                         )
                 ))
                 )
             )

        ))
        (Expansion type:concat expansion:(Block
            (Call
                  function:(Member lhs:builder rhs:setName)
                  args:(ArgList (Arg name:name value:"List"))
            )
            (Assign
                lhs:(Define type:(Generic type:List gens:(List AST)) name:parts)
                rhs:(New type:(Generic type:ArrayList gens:(List AST)) args:(ArgList))
            )
            (For var:(Define type:AST name:member) expr:(Call function:(Member lhs:ast rhs:getMemberList) args:(ArgList)) code:(Block
                (Call
                    function:(Member
                        lhs:parts
                        rhs:add
                    )
                    args:(ArgList
                        (Arg name:value value:(Call
                            function:expand
                            args:(ArgList
                                (Arg name:ast value:member)
                            )
                        ))
                    )
                )
            ))
            (Call
                function:(Member
                    lhs:Collections
                    rhs:reverse
                )
                args:(ArgList
                    (Arg name:value value:parts)
                )
            )
            (Call
                function:(Member
                    lhs:builder
                    rhs:addAll
                )
                args:(ArgList
                    (Arg name:value value:parts)
                )
            )

            (Call
                function:(Member lhs:builder rhs:add)
                args:(ArgList
                    (Arg name:value value:(Call
                          function:(Member
                              lhs:(Call
                                  function:(Member
                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Assign")))
                                      rhs:add
                                  )
                                  args:(ArgList
                                      (Arg name:name value:"lhs")
                                      (Arg name:ast value:(Call
                                          function:(Member
                                              lhs:AST
                                              rhs:IDLit
                                          )
                                          args:(ArgList
                                              (Arg name:value value:"builder" )
                                          )
                                      ))
                                  )
                              )
                              rhs:add
                          )
                          args:(ArgList
                              (Arg name:name value:"rhs")
                              (Arg name:ast value:(Call
                                              function:(Member
                                                  lhs:(Call
                                                          function:(Member
                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"New")))
                                                              rhs:add
                                                          )
                                                          args:(ArgList
                                                              (Arg name:name value:"type")
                                                              (Arg name:ast value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"ASTBuilder"))))
                                                          )
                                                  )
                                                  rhs:add
                                              )
                                              args:(ArgList
                                                  (Arg name:name value:"args")
                                                  (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                              )
                              ))
                          )
                  ))
                )
            )
            (Call
                function:(Member lhs:builder rhs:add)
                args:(ArgList (Arg name:ast value:(Call
                      function:(Member
                          lhs:(Call
                                  function:(Member
                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                      rhs:add
                                  )
                                  args:(ArgList
                                      (Arg name:name value:"function")
                                      (Arg name:ast value:(Call
                                              function:(Member
                                                  lhs:(Call
                                                          function:(Member
                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                              rhs:add
                                                          )
                                                          args:(ArgList
                                                              (Arg name:name value:"lhs")
                                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"builder"))) )
                                                          )
                                                  )
                                                  rhs:add
                                              )
                                              args:(ArgList
                                                  (Arg name:name value:"rhs")
                                                  (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"setName"))) )
                                              )
                                      ))
                                  )
                          )
                          rhs:add
                      )
                      args:(ArgList
                          (Arg name:name value:"args")
                          (Arg name:ast value:(Call
                                  function:(Member
                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                      rhs:add
                                  )
                                  args:(ArgList
                                      (Arg name:value value:(Call
                                          function:(Member
                                              lhs:(Call
                                                      function:(Member
                                                          lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                          rhs:add
                                                      )
                                                      args:(ArgList
                                                              (Arg name:name value:"name")
                                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"name"))))
                                                      )
                                              )
                                              rhs:add
                                          )
                                          args:(ArgList
                                                  (Arg name:name value:"value")
                                                  (Arg name:value value:(Call function:(Member lhs:AST rhs:STRLit) args:(ArgList (Arg name:name value:
                                                        (Call
                                                            function:(Member
                                                                lhs:(Index
                                                                    lhs:(Call
                                                                        function:(Member
                                                                            lhs:ast
                                                                            rhs:getMemberList
                                                                        )
                                                                        args:(ArgList)
                                                                    )
                                                                    rhs:0
                                                                )
                                                                rhs:getTypeName
                                                            )
                                                            args:(ArgList)
                                                        )
                                                  ))))
                                          )
                                      ))
                                  )
                          ))
                      )
                )))
            )
            (If cond:(Call
                function:(Member
                    lhs:(Index
                        lhs:(Call
                            function:(Member
                                lhs:ast
                                rhs:getMemberList
                            )
                            args:(ArgList)
                        )
                        rhs:0
                    )
                    rhs:isList
                )
                args:(ArgList)
            ) code:(Block
                (Call
                    function:(Member lhs:builder rhs:add)
                    args:(ArgList (Arg name:ast value:(Call
                          function:(Member
                              lhs:(Call
                                      function:(Member
                                          lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                          rhs:add
                                      )
                                      args:(ArgList
                                          (Arg name:name value:"function")
                                          (Arg name:ast value:(Call
                                                  function:(Member
                                                      lhs:(Call
                                                              function:(Member
                                                                  lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                  rhs:add
                                                              )
                                                              args:(ArgList
                                                                  (Arg name:name value:"lhs")
                                                                  (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"builder"))) )
                                                              )
                                                      )
                                                      rhs:add
                                                  )
                                                  args:(ArgList
                                                      (Arg name:name value:"rhs")
                                                      (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"addAll"))) )
                                                  )
                                          ))
                                      )
                              )
                              rhs:add
                          )
                          args:(ArgList
                              (Arg name:name value:"args")
                              (Arg name:ast value:(Call
                                      function:(Member
                                          lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                          rhs:add
                                      )
                                      args:(ArgList
                                          (Arg name:value value:(Call
                                              function:(Member
                                                  lhs:(Call
                                                      function:(Member
                                                          lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                          rhs:add
                                                      )
                                                      args:(ArgList
                                                              (Arg name:name value:"name")
                                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"ast"))))
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
                                                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                    rhs:add
                                                                )
                                                                args:(ArgList
                                                                    (Arg name:name value:"function")
                                                                    (Arg name:ast value:(Call
                                                                        function:(Member
                                                                            lhs:(Call
                                                                                function:(Member
                                                                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                    rhs:add
                                                                                )
                                                                                args:(ArgList
                                                                                    (Arg name:name value:"lhs")
                                                                                    (Arg name:value value:(Call
                                                                                              function:(Member
                                                                                                  lhs:(Call
                                                                                                          function:(Member
                                                                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                                              rhs:add
                                                                                                          )
                                                                                                          args:(ArgList
                                                                                                              (Arg name:name value:"function")
                                                                                                              (Arg name:ast value:(Call
                                                                                                                    function:(Member
                                                                                                                        lhs:(Call
                                                                                                                                function:(Member
                                                                                                                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                                                    rhs:add
                                                                                                                                )
                                                                                                                                args:(ArgList
                                                                                                                                    (Arg name:name value:"lhs")
                                                                                                                                    (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"bQue"))) )
                                                                                                                                )
                                                                                                                        )
                                                                                                                        rhs:add
                                                                                                                    )
                                                                                                                    args:(ArgList
                                                                                                                        (Arg name:name value:"rhs")
                                                                                                                        (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"pop"))) )
                                                                                                                    )
                                                                                                              ))
                                                                                                          )
                                                                                                  )
                                                                                                  rhs:add
                                                                                              )
                                                                                              args:(ArgList
                                                                                                  (Arg name:name value:"args")
                                                                                                  (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                                                                              )
                                                                                    ))
                                                                                )
                                                                            )
                                                                            rhs:add
                                                                        )
                                                                        args:(ArgList
                                                                            (Arg name:name value:"rhs")
                                                                            (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"getMemberList"))) )
                                                                        )
                                                                    ))
                                                                )
                                                            )
                                                            rhs:add
                                                        )
                                                        args:(ArgList
                                                            (Arg name:name value:"args")
                                                            (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                                        )
                                                  ))
                                              )
                                          ))
                                      )
                              ))
                          )
                    )))
                )
            ) otherwise:(Block
                (Call
                    function:(Member lhs:builder rhs:add)
                    args:(ArgList (Arg name:ast value:(Call
                          function:(Member
                              lhs:(Call
                                  function:(Member
                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                      rhs:add
                                  )
                                  args:(ArgList
                                      (Arg name:name value:"function")
                                      (Arg name:ast value:(Call
                                          function:(Member
                                              lhs:(Call
                                                  function:(Member
                                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                      rhs:add
                                                  )
                                                  args:(ArgList
                                                      (Arg name:name value:"lhs")
                                                      (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"builder"))) )
                                                  )
                                              )
                                              rhs:add
                                          )
                                          args:(ArgList
                                              (Arg name:name value:"rhs")
                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"set"))) )
                                          )
                                      ))
                                  )
                              )
                              rhs:add
                          )
                          args:(ArgList
                              (Arg name:name value:"args")
                              (Arg name:ast value:(Call
                                  function:(Member
                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                      rhs:add
                                  )
                                  args:(ArgList
                                      (Arg name:value value:(Call
                                          function:(Member
                                              lhs:(Call
                                                      function:(Member
                                                          lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                          rhs:add
                                                      )
                                                      args:(ArgList
                                                              (Arg name:name value:"name")
                                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"value"))))
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
                                                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                    rhs:add
                                                                )
                                                                args:(ArgList
                                                                    (Arg name:name value:"function")
                                                                    (Arg name:ast value:(Call
                                                                        function:(Member
                                                                            lhs:(Call
                                                                                function:(Member
                                                                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                    rhs:add
                                                                                )
                                                                                args:(ArgList
                                                                                    (Arg name:name value:"lhs")
                                                                                    (Arg name:value value:(Call
                                                                                              function:(Member
                                                                                                  lhs:(Call
                                                                                                          function:(Member
                                                                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                                              rhs:add
                                                                                                          )
                                                                                                          args:(ArgList
                                                                                                              (Arg name:name value:"function")
                                                                                                              (Arg name:ast value:(Call
                                                                                                                    function:(Member
                                                                                                                        lhs:(Call
                                                                                                                                function:(Member
                                                                                                                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                                                    rhs:add
                                                                                                                                )
                                                                                                                                args:(ArgList
                                                                                                                                    (Arg name:name value:"lhs")
                                                                                                                                    (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"bQue"))) )
                                                                                                                                )
                                                                                                                        )
                                                                                                                        rhs:add
                                                                                                                    )
                                                                                                                    args:(ArgList
                                                                                                                        (Arg name:name value:"rhs")
                                                                                                                        (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"pop"))) )
                                                                                                                    )
                                                                                                              ))
                                                                                                          )
                                                                                                  )
                                                                                                  rhs:add
                                                                                              )
                                                                                              args:(ArgList
                                                                                                  (Arg name:name value:"args")
                                                                                                  (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                                                                              )
                                                                                    ))
                                                                                )
                                                                            )
                                                                            rhs:add
                                                                        )
                                                                        args:(ArgList
                                                                            (Arg name:name value:"rhs")
                                                                            (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"toString"))))
                                                                        )
                                                                    ))
                                                                )
                                                            )
                                                            rhs:add
                                                        )
                                                        args:(ArgList
                                                            (Arg name:name value:"args")
                                                            (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                                        )
                                                  ))
                                          )
                                      ))
                                  )
                          ))
                          )
                    )))
                )
                (For init:(Assign lhs:(Define name:i type:int) rhs:1) cond:(Lt lhs:i rhs:(Call function:(Member lhs:parts rhs:size) args:(ArgList))) post:(Inc value:i) code:(Block
                    (Call
                        function:(Member lhs:builder rhs:add)
                        args:(ArgList (Arg name:ast value:(Call
                              function:(Member
                                  lhs:(Call
                                          function:(Member
                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                              rhs:add
                                          )
                                          args:(ArgList
                                              (Arg name:name value:"function")
                                              (Arg name:ast value:(Call
                                                      function:(Member
                                                          lhs:(Call
                                                                  function:(Member
                                                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                      rhs:add
                                                                  )
                                                                  args:(ArgList
                                                                      (Arg name:name value:"lhs")
                                                                      (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"builder"))) )
                                                                  )
                                                          )
                                                          rhs:add
                                                      )
                                                      args:(ArgList
                                                          (Arg name:name value:"rhs")
                                                          (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"set"))) )
                                                      )
                                              ))
                                          )
                                  )
                                  rhs:add
                              )
                              args:(ArgList
                                  (Arg name:name value:"args")
                                  (Arg name:ast value:(Call
                                      function:(Member
                                          lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                          rhs:add
                                      )
                                      args:(ArgList
                                          (Arg name:value value:(Call
                                              function:(Member
                                                  lhs:(Call
                                                          function:(Member
                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                              rhs:add
                                                          )
                                                          args:(ArgList
                                                                  (Arg name:name value:"name")
                                                                  (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"value"))))
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
                                                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Add")))
                                                                    rhs:add
                                                                )
                                                                args:(ArgList
                                                                    (Arg name:name value:"lhs")
                                                                    (Arg name:value value:(Call
                                                                          function:(Member
                                                                              lhs:(Call
                                                                                  function:(Member
                                                                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                      rhs:add
                                                                                  )
                                                                                  args:(ArgList
                                                                                      (Arg name:name value:"function")
                                                                                      (Arg name:ast value:(Call
                                                                                          function:(Member
                                                                                              lhs:(Call
                                                                                                  function:(Member
                                                                                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                      rhs:add
                                                                                                  )
                                                                                                  args:(ArgList
                                                                                                      (Arg name:name value:"lhs")
                                                                                                      (Arg name:value value:(Call
                                                                                                            function:(Member
                                                                                                                lhs:(Call
                                                                                                                    function:(Member
                                                                                                                        lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                                                        rhs:add
                                                                                                                    )
                                                                                                                    args:(ArgList
                                                                                                                        (Arg name:name value:"function")
                                                                                                                        (Arg name:ast value:(Call
                                                                                                                            function:(Member
                                                                                                                                lhs:(Call
                                                                                                                                    function:(Member
                                                                                                                                        lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                                                        rhs:add
                                                                                                                                    )
                                                                                                                                    args:(ArgList
                                                                                                                                        (Arg name:name value:"lhs")
                                                                                                                                        (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"builder"))) )
                                                                                                                                    )
                                                                                                                                )
                                                                                                                                rhs:add
                                                                                                                            )
                                                                                                                            args:(ArgList
                                                                                                                                (Arg name:name value:"rhs")
                                                                                                                                (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"getValue"))) )
                                                                                                                            )
                                                                                                                        ))
                                                                                                                    )
                                                                                                                )
                                                                                                                rhs:add
                                                                                                            )
                                                                                                            args:(ArgList
                                                                                                                (Arg name:name value:"args")
                                                                                                                (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                                                                                            )
                                                                                                      ))
                                                                                                  )
                                                                                              )
                                                                                              rhs:add
                                                                                          )
                                                                                          args:(ArgList
                                                                                              (Arg name:name value:"rhs")
                                                                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"toString"))) )
                                                                                          )
                                                                                      ))
                                                                                  )
                                                                              )
                                                                              rhs:add
                                                                          )
                                                                          args:(ArgList
                                                                              (Arg name:name value:"args")
                                                                              (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                                                          )
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
                                                                            lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                            rhs:add
                                                                        )
                                                                        args:(ArgList
                                                                            (Arg name:name value:"function")
                                                                            (Arg name:ast value:(Call
                                                                                function:(Member
                                                                                    lhs:(Call
                                                                                            function:(Member
                                                                                                lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                rhs:add
                                                                                            )
                                                                                            args:(ArgList
                                                                                                (Arg name:name value:"lhs")
                                                                                                (Arg name:value value:(Call
                                                                                                      function:(Member
                                                                                                          lhs:(Call
                                                                                                                function:(Member
                                                                                                                    lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                                                                                    rhs:add
                                                                                                                )
                                                                                                                args:(ArgList
                                                                                                                    (Arg name:name value:"function")
                                                                                                                    (Arg name:value value:(Call
                                                                                                                        function:(Member
                                                                                                                            lhs:(Call
                                                                                                                                  function:(Member
                                                                                                                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                                                                                      rhs:add
                                                                                                                                  )
                                                                                                                                  args:(ArgList
                                                                                                                                      (Arg name:name value:"lhs")
                                                                                                                                      (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"bQue"))) )
                                                                                                                                  )
                                                                                                                            )
                                                                                                                            rhs:add
                                                                                                                        )
                                                                                                                        args:(ArgList
                                                                                                                            (Arg name:name value:"rhs")
                                                                                                                            (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"pop"))) )
                                                                                                                        )
                                                                                                                    ))
                                                                                                                )
                                                                                                          ) rhs:add
                                                                                                      )
                                                                                                      args:(ArgList
                                                                                                         (Arg name:name value:"args")
                                                                                                         (Arg name:value value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:lit value:"ArgList"))) )
                                                                                                      )
                                                                                                ))
                                                                                            )
                                                                                    )
                                                                                    rhs:add
                                                                                )
                                                                                args:(ArgList
                                                                                    (Arg name:name value:"rhs")
                                                                                    (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"toString"))) )
                                                                                )
                                                                            ))
                                                                        )
                                                                    )
                                                                    rhs:add
                                                                )
                                                                args:(ArgList
                                                                    (Arg name:name value:"args")
                                                                    (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                                                )
                                                            ))
                                                        )
                                                  ))
                                              )
                                          ))
                                      )
                                  ))
                              )
                        )))
                    )
                ))
            ))
            (Call
                function:(Member lhs:builder rhs:add)
                args:(ArgList (Arg name:ast value:(Call
                      function:(Member
                          lhs:(Call
                                  function:(Member
                                      lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                      rhs:add
                                  )
                                  args:(ArgList
                                      (Arg name:name value:"function")
                                      (Arg name:ast value:(Call
                                              function:(Member
                                                  lhs:(Call
                                                          function:(Member
                                                              lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                              rhs:add
                                                          )
                                                          args:(ArgList
                                                              (Arg name:name value:"lhs")
                                                              (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"bQue"))) )
                                                          )
                                                  )
                                                  rhs:add
                                              )
                                              args:(ArgList
                                                  (Arg name:name value:"rhs")
                                                  (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"push"))) )
                                              )
                                      ))
                                  )
                          )
                          rhs:add
                      )
                      args:(ArgList
                          (Arg name:name value:"args")
                          (Arg name:ast value:(Call
                              function:(Member
                                  lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"ArgList")))
                                  rhs:add
                              )
                              args:(ArgList
                                  (Arg name:value value:(Call
                                      function:(Member
                                          lhs:(Call
                                              function:(Member
                                                  lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Arg")))
                                                  rhs:add
                                              )
                                              args:(ArgList
                                                      (Arg name:name value:"name")
                                                      (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:name value:"name"))))
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
                                                            lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Call")))
                                                            rhs:add
                                                        )
                                                        args:(ArgList
                                                            (Arg name:name value:"function")
                                                            (Arg name:ast value:(Call
                                                                function:(Member
                                                                    lhs:(Call
                                                                        function:(Member
                                                                            lhs:(New type:ASTBuilder args:(ArgList (Arg name:name value:"Member")))
                                                                            rhs:add
                                                                        )
                                                                        args:(ArgList
                                                                            (Arg name:name value:"lhs")
                                                                            (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"builder"))) )
                                                                        )
                                                                    )
                                                                    rhs:add
                                                                )
                                                                args:(ArgList
                                                                    (Arg name:name value:"rhs")
                                                                    (Arg name:value value:(Call function:(Member lhs:AST rhs:IDLit) args:(ArgList (Arg name:lit value:"create"))) )
                                                                )
                                                            ))
                                                        )
                                                    )
                                                    rhs:add
                                                )
                                                args:(ArgList
                                                    (Arg name:name value:"args")
                                                    (Arg name:ast value:(Call function:(Member lhs:AST rhs:emptyList) args:(ArgList (Arg name:name value:"ArgList"))))
                                                )
                                          ))
                                      )
                                  ))
                              )
                          ))
                      )
                )))
            )
        ))
        (Expansion type:expFunName expansion:(concat expand (member name:type)))
    )
)
