grammar Pat;
@header {
package patience.parser;
import java.util.ArrayList;
import java.util.List;import java.util.Map;
import java.util.HashMap;
import patience.interpreter.Code;
import patience.interpreter.Leaf;
import patience.interpreter.VariableMethod;
import patience.*; }

parse returns [List<VariableMethod> methods] : { List<VariableMethod> methods = new ArrayList<VariableMethod>(); }( v1=variablerule ';' { methods.add($v1.method); } )+ {$methods = methods; }EOF;
variablerule returns [VariableMethod method] : st=stmt { $method = new VariableMethod($st.code.getName(), $st.code.getCodes()); } ;
stmts returns [List<Code> codes] : { $codes = new ArrayList<Code>(); } ((st1=stmt { $codes.add($st1.code); } | id=ID {$codes.add(new Leaf("id", $id.text, $id.getLine()));} | val=INTEGER { $codes.add(new Leaf("int", Integer.parseInt($val.text), $val.getLine()));} )  sts=stmts  { $codes.addAll($sts.codes); } | );
stmt  returns [Code code] : ('(' id=ID args=stmts ')'  { $code = new Code($id.text, $args.codes, $id.getLine()); } ) ;

fragment   INT  : [0-9] ;
COMMENT : '//' ~('\n') -> skip ;
INTEGER :  INT+ ;
NUM : '-'? INT+ ( '.'+   INT+ )? ;
ID : (  LETTER  | '_') ( INT  |  '_' | LETTER  )*  ;
STRING : '"' .*?[^\\] '"' ;
WS : [ \t\r\n]+ -> skip ;
fragment LETTER : [a-zA-Z] ;
