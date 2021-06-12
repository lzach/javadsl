(Expansions
    name:Patience
    expansions:(List
        (Expansion type:Patience expansion:(List
                (Import name:(Name java util Objects))
                (Import name:(Name java util Arrays))
                (Import name:(Name java util Map))
                (Import name:(Name java util HashMap))
                (Import name:(Name java util Deque))
                (Import name:(Name java util ArrayDeque))
                (Import name:(Name java util Collections))
                (Import name:(Name java util List))
                (Import name:(Name patience Card))
                (Import name:(Name patience Pile))
                (Import name:(Name patience PileGroup))
                (Import name:(Name patience Deck))
                (Class modifier:public name:(concat Patience (static_member name:name)) attrs:(AttrList) base:(Name patience PatGame)
                     attrs: (AttrList
                        (members name:pilegroups template:(Assign lhs:(Attr name:(member name:name) type:PileGroup) rhs:(New type:PileGroup args:(ArgList (Arg name:size value:(member name:size))))))
                     )
                     cons:(ConsList)
                     methods:(MethodList
                        (Method name:canMove params:(ParamList (Param name:card type:Card)  (Param name:pile type:Pile)) returnType:boolean code:(Block
                             (member name:canMove)
                             (Return value:false)
                        ))
                        (Method name:canMovePile params:(ParamList (Param name:from type:Pile)  (Param name:to type:Pile)) returnType:boolean code:(Block
                             (member name:canMovePile)
                             (Return value:false)
                        ))
                        (Method name:getPileGroup params:(ParamList (Param name:name type:String)) returnType:PileGroup code:(Block
                             (members name:pilegroups template:(If cond:(Call function:(Member lhs:(String value:(member name:name)) rhs:equals) args:(ArgList (Arg name:str value:name))) code:(Block
                                (Return value:(member name:name))
                             )))
                             (Return value:null)
                        ))
                        (Method name:shouldTurn params:(ParamList (Param name:pile type:Pile) (Param name:card type:Card)  ) returnType:boolean code:(Block
                             (Return value:(member name:shouldTurn))
                        ))
                        (Method name:hasLost params:(ParamList (Param name:deck type:Deck)) returnType:boolean code:(Block
                             (member name:hasLost)
                             (Return value:false)
                        ))
                        (Method name:hasWon params:(ParamList) returnType:boolean code:(Block
                             (member name:hasWon)
                        ))
                        (Method name:redeal params:(ParamList (Param name:deck type:Deck)) returnType:void code:(Block
                             (member name:redeal)
                        ))
                        (Method name:setup params:(ParamList (Param name:deck type:Deck)) returnType:void code:(Block
                             (member name:setup)
                        ))
                        (Method name:deal params:(ParamList (Param name:deck type:Deck)) returnType:void code:(Block
                             (member name:deal)
                        ))
                     )
                 )
        ))
        (Expansion type:Move expansion:(If cond:(Call function:(Member lhs:(member name:pile) rhs:contains) args:(ArgList (Arg name:pile value:pile))) code:(Block
            (member name:rule)
        )))
        (Expansion type:PileMove expansion:(If cond:(Call function:(Member lhs:(member name:pile) rhs:contains) args:(ArgList (Arg name:pile value:to))) code:(Block
            (member name:rule)
        )))
        (Expansion type:Rule expansion:(If cond:(Eq lhs:(Call function:(Member lhs:pile rhs:size) args:(ArgList)) rhs:0) code:(Block
           (Return value:(member name:empty))
        ) otherwise:(Block
           (Return value:(member name:else))
        )))
        (Expansion type:PileRule expansion:(If cond:(Eq lhs:(Call function:(Member lhs:from rhs:size) args:(ArgList)) rhs:0) code:(Block
           (Return value:(member name:empty))
        ) otherwise:(Block
           (Return value:(member name:else))
        )))
        (Expansion type:InitPileList expansion:(members template:(List
                (For
                    init:(Assign lhs:(Define type:int name:i) rhs:(member name:first_pile))
                    cond:(Lte lhs:i rhs:(member name:last_pile))
                    post:(Inc value:i)
                    code:(Block
                        (Call
                            function:(Member
                                     lhs:(Call
                                          function:(Member
                                                lhs:(member name:pile)
                                                rhs:get
                                          )
                                          args:(ArgList (Arg name:index value:i))
                                     )
                                     rhs:add)
                            args:(ArgList (Arg name:card value:(Call function:(Member lhs:deck rhs:deal) args:(ArgList))))
                        )
                    )
                )

        )))
        (Expansion type:Value expansion:(Call function:(Member lhs:(member name:card) rhs:value) args:(ArgList)))
        (Expansion type:Suit expansion:(Call function:(Member lhs:(member name:card) rhs:suit) args:(ArgList)))
        (Expansion type:Color expansion:(Call function:(Member lhs:(member name:card) rhs:color) args:(ArgList)))
        (Expansion type:IsFaceUp expansion:(Call function:(Member lhs:(member name:card) rhs:faceUp) args:(ArgList)))
        (Expansion type:ShouldTurn expansion:(member name:rule))

        (Expansion type:Less expansion:(Sub lhs:(member name:value) rhs:1))
        (Expansion type:More expansion:(Add lhs:(member name:value) rhs:1))
        (Expansion type:Top expansion:(Call function:(Member lhs:(member name:pile) rhs:top) args:(ArgList)))
        (Expansion type:Bottom expansion:(Call function:(Member lhs:(member name:pile) rhs:bottom) args:(ArgList)))
        (Expansion type:King expansion:13)
        (Expansion type:Queen expansion:12)
        (Expansion type:Jack expansion:11)
        (Expansion type:Ace expansion:1)

        (Expansion type:Deal expansion:(List
            (For
                var:(Define type:Pile name:pile)
                expr:(member name:to)
                code:(Block
                    (Call
                        function:(Member
                                 lhs:pile
                                 rhs:add)
                        args:(ArgList (Arg name:card value:(Call function:(Member lhs:deck rhs:deal) args:(ArgList))))
                    )
                )
            )
        ))
        (Expansion type:ReDeal expansion:(List
              (For
                  var:(Define type:Pile name:pile)
                  expr:(member name:from)
                  code:(Block
                      (Call
                          function:(Member
                                   lhs:deck
                                   rhs:putBottom
                          )
                          args:(ArgList (Arg name:pile value:pile))
                      )
                  )
              )
        ))
        (Expansion type:WinCondition expansion:(List
              (For
                  var:(Define type:Pile name:pile)
                  expr:(member name:foundation)
                  code:(Block
                      (If cond:(Not value:(member name:cond)) code:(Block
                          (Return value:false)
                      ) otherwise:(Block))
                  )
              )
              (Return value:true)
        ))
        (Expansion type:Eq expansion:(Eq lhs:(member name:lhs) rhs:(member name:rhs)))
        (Expansion type:Not expansion:(Not value:(member name:value)))
        (Expansion type:Neq expansion:(Neq lhs:(member name:lhs) rhs:(member name:rhs)))
        (Expansion type:And expansion:(And lhs:(member name:lhs) rhs:(member name:rhs)))
        (Expansion type:Or expansion:(Or lhs:(member name:lhs) rhs:(member name:rhs)))
        (Expansion type:InP expansion:(Call function:(Member lhs:(member name:rhs) rhs:contains) args:(ArgList (Arg name:card value:(member name:lhs)))))
        (Expansion type:InPG expansion:(Call function:(Member lhs:(member name:rhs) rhs:contains) args:(ArgList (Arg name:pile value:(member name:lhs)))))
    )
)