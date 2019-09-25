(Language
    name:Patience
    includes:(List
        (Include name:standard constructs:(List
            List ID Expr Int Bool
        ))
    )
    composed:(List
        (Restrict name:PileGroupID super:ID restriction:(ExecVal type:PileGroup))
    )
    definitions:(List
        (Define type:Patience members:(Members
                (Member name:name type:ID)
                (Member name:pilegroups type:List)
                (Member name:canMove type:List)
                (Member name:canMovePile type:List)
                (Member name:hasLost type:List)
                (Member name:hasWon type:List)
                (Member name:shouldTurn type:List)
                (Member name:redeal type:List)
                (Member name:deal type:List)
                (Member name:setup type:List)
        ))
        (Define type:Move members:(Members (Member name:pile type:PileGroupID) (Member name:rule type:Rule)))
        (Define type:Rule members:(Members (Member name:empty type:Expr) (Member name:else type:Expr)))
        (Define type:Value members:(Members))
        (Define type:Suit members:(Members))
        (Define type:Color members:(Members))
        (Define type:Less members:(Members (Member name:lhs type:Expr) (Member name:rhs type:Expr)))
        (Define type:More members:(Members (Member name:lhs type:Expr) (Member name:rhs type:Expr)))
        (Define type:Top members:(Members))
        (Define type:King type:Int)
        (Define type:Queen type:Int)
        (Define type:Jack type:Int)
        (Define type:Ace type:Int)
    )
)