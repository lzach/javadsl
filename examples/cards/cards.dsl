(Expansions
    name:Cards
    expansions:(List
        (Expansion type:Value expansion:(Call function:(Member lhs:card rhs:value) args:(ArgList)))
        (Expansion type:Suit expansion:(Call function:(Member lhs:card rhs:suit) args:(ArgList)))
        (Expansion type:Color expansion:(Call function:(Member lhs:card rhs:color) args:(ArgList)))
        (Expansion type:OneLess expansion:(Sub lhs:(member name:value) rhs:1))
        (Expansion type:OneMore expansion:(Add lhs:(member name:value) rhs:1))
        (Expansion type:Top expansion:(Call function:(Member lhs:pile rhs:top) args:(ArgList)))
        (Expansion type:King expansion:13)
        (Expansion type:Queen expansion:12)
        (Expansion type:Jack expansion:11)
        (Expansion type:Ace expansion:1)
    )
)