(Patience
    name:Klondike
    pilegroups:(List
        (PileGroup name:tableau size:7)
        (PileGroup name:foundation size:4)
        (PileGroup name:waste size:1)
    )
    canMove:(List
        (Move pile:tableau rule:(Rule
            empty:(Eq lhs:(Value card:card) rhs:(King))
            else:(And
                lhs:(Eq lhs:(Value card:card) rhs:(Less value:(Value card:(Top pile:pile))))
                rhs:(Neq lhs:(Color card:card) rhs:(Color card:(Top pile:pile)))
            )
        ))
        (Move pile:foundation rule:(Rule
            empty:(Eq lhs:(Value card:card) rhs:(Ace))
            else:(And
                lhs:(Eq lhs:(Value card:card) rhs:(More value:(Value card:(Top pile:pile))))
                rhs:(Eq lhs:(Suit card:card) rhs:(Suit card:(Top pile:pile)))
            )
        ))
        (Move pile:waste rule:(Rule empty:false else:false))
    )
    canMovePile:(List)
    hasLost:(List)
    hasWon:(List)
    shouldTurn:(List)
    redeal:(List)
    deal:(List)
    setup:(List)
)