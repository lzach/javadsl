// Generated from patience/parser/Pat.g4 by ANTLR 4.6

package patience.parser;
import java.util.ArrayList;
import java.util.List;import java.util.Map;
import java.util.HashMap;
import patience.interpreter.Code;
import patience.interpreter.Leaf;
import patience.interpreter.VariableMethod;
import patience.*; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PatParser}.
 */
public interface PatListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PatParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(PatParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PatParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(PatParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PatParser#variablerule}.
	 * @param ctx the parse tree
	 */
	void enterVariablerule(PatParser.VariableruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link PatParser#variablerule}.
	 * @param ctx the parse tree
	 */
	void exitVariablerule(PatParser.VariableruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link PatParser#stmts}.
	 * @param ctx the parse tree
	 */
	void enterStmts(PatParser.StmtsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PatParser#stmts}.
	 * @param ctx the parse tree
	 */
	void exitStmts(PatParser.StmtsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PatParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmt(PatParser.StmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link PatParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmt(PatParser.StmtContext ctx);
}