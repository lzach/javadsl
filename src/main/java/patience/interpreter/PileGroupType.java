package patience.interpreter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import patience.*;
class PileGroupType extends ListType {
	private static final String name = "PileGroup";
	public PileGroupType() { super(name, new PileType(), createMembers()); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		return members;
	}
}

class PileGroupConstructorType extends MethodType {
	public PileGroupConstructorType() { super(new PileGroupType(), createMembers(), new String[]{"pilecount",}); }
	private static Map<String, DSLType> createMembers() {
		Map<String, DSLType> members = new HashMap<String, DSLType>();
		members.put("pilecount", new intType());
		return members;
	}
}