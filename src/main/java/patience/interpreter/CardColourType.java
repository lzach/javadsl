package patience.interpreter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import patience.*;
class CardColourType extends DSLType {
	private static final String name = "CardColour";
	public CardColourType() { super(name, createMembers()); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		return members;
	}
}

class CardColourConstructorType extends MethodType {
	public CardColourConstructorType() { super(new CardColourType(), createMembers(), new String[]{}); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		return members;
	}
}