(Definitions
    name:Translation
    languages:(List Meta)
    constructs:(List
        (Construct name:Expansions listType:Expansion)
        (Construct name:Expansion members:(List (Member name:type type:TypeExpr) (Member name:exp type:ASTExpr)))
        (Construct name:member members:(List (Member name:type ast:ASTExpr) (Member name:member type:StringExpr)(Member name:exp type:ASTExpr)))
        (Construct name:members members:(List (Member name:type ast:ASTExpr) (Member name:exp type:ASTExpr)))
        (Construct name:concat members:(List (Member name:type ast:ASTExprList) ))
    )
)