(Language
    name:Game
    includes:(List
        (Include name:standard constructs:(List
            List ID Expr Int Bool
        ))
    )
    composed:(List
        (Restrict name:PileGroupID super:ID restriction:(ExecVal type:PileGroup))
    )
    definitions:(List
        (Define type:Game members:(Members
            (Member name:name type:ID)
            (Member name:players type:List)
            (Member name:setup type:List)
            (Member name:preturn type:List)
            (Member name:postturn type:List)
            (Member name:turn type:List)
            (Member name:hasWon type:List)
        ))
        (Define type:Move members:(Members (Member name:expr type:Expr) (Member name:rule type:Rule)))
        (Define type:Rule members:(Members ))
    )
)