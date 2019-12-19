(Expansions
    name:Patience
    expansions:(List
        (Expansion type:Patience expansion:(Game
                name:(member name:name)
                players:(List (Player name:1))
                setup:(List
                    (member name:setup)
                )
                preturn:(List )
                postturn:(List )
                turn:(List
                    (Move expr:(member name:canMove) rule:(Rule ))
                    (Move expr:(member name:canMovePile) rule:(Rule ))
                    (Move expr:(member name:canMovePile) rule:(Rule ))
                    (Move expr:True rule:(Rule (member name:deal)))
                    (Move expr:(...) rule:(Rule (member name:redeal)))
                )
                hasWon:(member name:hasWon)

                     attrs: (AttrList
                        (members name:pilegroups template:(Assign lhs:(Attr name:(member name:name) type:PileGroup) rhs:(New type:PileGroup args:(ArgList (Arg name:size value:(member name:size))))))
                     )

                    (Method name:getPileGroup params:(ParamList (Param name:name type:String)) returnType:PileGroup code:(Block
                         (members name:pilegroups template:(If cond:(Call function:(Member lhs:(String value:(member name:name)) rhs:equals) args:(ArgList (Arg name:str value:name))) code:(Block
                            (Return value:(member name:name))
                         )))
                         (Return value:null)
                    ))
                    (Method name:shouldTurn params:(ParamList (Param name:pile type:Pile) (Param name:card type:Card)  ) returnType:boolean code:(Block
                         (member name:shouldTurn)
                         (Return value:false)
                    ))
                    (Method name:hasLost params:(ParamList (Param name:deck type:Deck)) returnType:boolean code:(Block
                         (member name:hasLost)
                         (Return value:false)
                    ))
        ))
        (Expansion type:Move expansion:(If cond:(Call function:(Member lhs:(member name:pile) rhs:contains) args:(ArgList (Arg name:pile value:pile))) code:(Block
            (member name:rule)
        )))
        (Expansion type:Rule expansion:(If cond:(Eq lhs:(Call function:(Member lhs:pile rhs:size) args:(ArgList)) rhs:0) code:(Block
           (Return value:(member name:empty))
        ) otherwise:(Block
           (Return value:(member name:else))
        )))
        (Expansion type:Value expansion:(Call function:(Member lhs:card rhs:value) args:(ArgList)))
        (Expansion type:Suit expansion:(Call function:(Member lhs:card rhs:suit) args:(ArgList)))
        (Expansion type:Color expansion:(Call function:(Member lhs:card rhs:color) args:(ArgList)))
        (Expansion type:Less expansion:(Sub lhs:(member name:value) rhs:1))
        (Expansion type:More expansion:(Add lhs:(member name:value) rhs:1))
        (Expansion type:Top expansion:(Call function:(Member lhs:pile rhs:top) args:(ArgList)))
        (Expansion type:King expansion:13)
        (Expansion type:Queen expansion:12)
        (Expansion type:Jack expansion:11)
        (Expansion type:Ace expansion:1)
    )
)