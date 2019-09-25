package patience.interpreter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import patience.*;
class CardSuitType extends DSLType {
	private static final String name = "CardSuit";
	public CardSuitType() { super(name, createMembers()); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		return members;
	}
}

class CardSuitConstructorType extends MethodType {
	public CardSuitConstructorType() { super(new CardSuitType(), createMembers(), new String[]{}); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		return members;
	}
}