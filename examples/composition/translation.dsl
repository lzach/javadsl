(Translation
   languages:(List Translation S T)
   operations:(
      (Base language:Translation)
      (Restrict on:(List (Restriction type:OutputType restriction:TStatement)))
      (Restrict language:S on:(List (Restriction type:TypeExpr restriction:SType))
      (Restrict language:(Combine this T) on:(List (Restriction type:ExpExpr))
   )
)