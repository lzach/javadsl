package patience.interpreter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import patience.*;
class DeckType extends ListType {
	private static final String name = "Deck";
	public DeckType() { super(name, new CardType(), createMembers()); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		members.put("deal", new CardType());
		members.put("putTopC", new voidType());
		members.put("putBottomC", new voidType());
		members.put("putTopP", new voidType());
		members.put("putBottomP", new voidType());
		return members;
	}
}

class DeckConstructorType extends MethodType {
	public DeckConstructorType() { super(new DeckType(), createMembers(), new String[]{}); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		return members;
	}
}