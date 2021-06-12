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
    canMovePile:(List
        (PileMove pile:tableau rule:(PileRule
            empty:(Eq lhs:(Value card:(Bottom pile:from)) rhs:(King))
            else:(And
                lhs:(And
                    lhs:(Eq lhs:(Value card:(Bottom pile:from)) rhs:(Less value:(Value card:(Top pile:to))))
                    rhs:(Neq lhs:(Color card:(Bottom pile:from)) rhs:(Color card:(Top pile:to)))
                )
                rhs:(IsFaceUp card:(Bottom pile:from))
        )))
    )
    hasLost:(List)
    hasWon:(WinCondition foundation:foundation cond:(Eq lhs:(Value card:(Top pile:pile)) rhs:(King)))
    shouldTurn:(Or lhs:(InPG lhs:pile rhs:waste) rhs:(Eq lhs:(Top pile:pile) rhs:card))
    redeal:(ReDeal from:waste)
    deal:(Deal to:waste)
    setup:(InitPileList
              (InitPileExpr pile:tableau first_pile:6 last_pile:6)
              (InitPileExpr pile:tableau first_pile:5 last_pile:6)
              (InitPileExpr pile:tableau first_pile:4 last_pile:6)
              (InitPileExpr pile:tableau first_pile:3 last_pile:6)
              (InitPileExpr pile:tableau first_pile:2 last_pile:6)
              (InitPileExpr pile:tableau first_pile:1 last_pile:6)
              (InitPileExpr pile:tableau first_pile:0 last_pile:6)
    )
)