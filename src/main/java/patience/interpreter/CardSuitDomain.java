package patience.interpreter;
import java.util.List;
import patience.*;
import java.util.List;
import java.util.ArrayList;
class CardSuitDomain extends DSLDomain {
	public DSLType getType(String id) {
		if ( id.equals("CardSuit") ) return new CardSuitConstructorType();
		return null;
	}
	public Object eval(String id) {
		return null;
	}
	public Object eval(DSLContext context, String id, List<Code> codes) {
		return null;
	}
	public Object eval(DSLContext context, Object obj, String member, List<Code> codes) {
		return null;
	}
}