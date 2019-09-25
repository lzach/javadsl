(Language
    name:Language
    includes:(List
        (Include name:standard constructs:(List
            List ID Expr Int Bool
        ))
    )
    composed:(List
        (Restrict name:Members super:List restriction:(List (ListType type:Member)))
    )
    definitions:(List
        (Define type:Language members:(Members
                (Member name:name type:ID)
                (Member name:includes type:List)
                (Member name:composed type:List)
                (Member name:definitions type:List)
        ))
        (Define type:Define members:(Members
                (Member name:type type:ID)
                (Member name:members type:Members)
                (Member name:list type:ID)
                (Member name:value type:ID)
        ))
        (Define type:Restrict members:(Members
                (Member name:name type:ID)
                (Member name:super type:ID)
                (Member name:restriction type:List)
        ))
        (Define type:ExecVal members:(Members (Member name:type type:ID)))
        (Define type:ListType members:(Members (Member name:type type:ID)))
        (Define type:Include members:(Members
                (Member name:name type:ID)
                (Member name:constructs type:List)
        ))
        (Define type:Member members:(Members
                (Member name:name type:ID)
                (Member name:type type:ID)
        ))
    )
)