package patience.interpreter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import patience.*;
class PileType extends ListType {
	private static final String name = "Pile";
	public PileType() { super(name, new CardType(), createMembers()); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		return members;
	}
}

class PileConstructorType extends MethodType {
	public PileConstructorType() { super(new PileType(), createMembers(), new String[]{}); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		return members;
	}
}