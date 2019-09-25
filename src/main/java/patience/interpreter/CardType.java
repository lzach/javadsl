package patience.interpreter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import patience.*;
class CardType extends DSLType {
	private static final String name = "Card";
	public CardType() { super(name, createMembers()); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		members.put("value", new intType());
		members.put("suit", new CardSuitType());
		members.put("colour", new CardColourType());
		members.put("faceup", new booleanType());
		return members;
	}
}

class CardConstructorType extends MethodType {
	public CardConstructorType() { super(new CardType(), createMembers(), new String[]{"value","suit",}); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		members.put("value", new intType());
		members.put("suit", new CardSuitType());
		return members;
	}
}