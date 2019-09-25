// Generated from patience/parser/Pat.g4 by ANTLR 4.6

package patience.parser;
import java.util.ArrayList;
import java.util.List;import java.util.Map;
import java.util.HashMap;
import patience.interpreter.Code;
import patience.interpreter.Leaf;
import patience.interpreter.VariableMethod;
import patience.*; 
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PatParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.6", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, COMMENT=4, INTEGER=5, NUM=6, ID=7, STRING=8, WS=9;
	public static final int
		RULE_parse = 0, RULE_variablerule = 1, RULE_stmts = 2, RULE_stmt = 3;
	public static final String[] ruleNames = {
		"parse", "variablerule", "stmts", "stmt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "COMMENT", "INTEGER", "NUM", "ID", "STRING", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Pat.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PatParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public List<VariableMethod> methods;
		public VariableruleContext v1;
		public TerminalNode EOF() { return getToken(PatParser.EOF, 0); }
		public List<VariableruleContext> variablerule() {
			return getRuleContexts(VariableruleContext.class);
		}
		public VariableruleContext variablerule(int i) {
			return getRuleContext(VariableruleContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PatListener ) ((PatListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PatListener ) ((PatListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 List<VariableMethod> methods = new ArrayList<VariableMethod>(); 
			setState(13); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(9);
				((ParseContext)_localctx).v1 = variablerule();
				setState(10);
				match(T__0);
				 methods.add(((ParseContext)_localctx).v1.method); 
				}
				}
				setState(15); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__1 );
			((ParseContext)_localctx).methods =  methods; 
			setState(18);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableruleContext extends ParserRuleContext {
		public VariableMethod method;
		public StmtContext st;
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public VariableruleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variablerule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PatListener ) ((PatListener)listener).enterVariablerule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PatListener ) ((PatListener)listener).exitVariablerule(this);
		}
	}

	public final VariableruleContext variablerule() throws RecognitionException {
		VariableruleContext _localctx = new VariableruleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_variablerule);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			((VariableruleContext)_localctx).st = stmt();
			 ((VariableruleContext)_localctx).method =  new VariableMethod(((VariableruleContext)_localctx).st.code.getName(), ((VariableruleContext)_localctx).st.code.getCodes()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtsContext extends ParserRuleContext {
		public List<Code> codes;
		public StmtContext st1;
		public Token id;
		public Token val;
		public StmtsContext sts;
		public StmtsContext stmts() {
			return getRuleContext(StmtsContext.class,0);
		}
		public StmtContext stmt() {
			return getRuleContext(StmtContext.class,0);
		}
		public TerminalNode ID() { return getToken(PatParser.ID, 0); }
		public TerminalNode INTEGER() { return getToken(PatParser.INTEGER, 0); }
		public StmtsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmts; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PatListener ) ((PatListener)listener).enterStmts(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PatListener ) ((PatListener)listener).exitStmts(this);
		}
	}

	public final StmtsContext stmts() throws RecognitionException {
		StmtsContext _localctx = new StmtsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_stmts);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((StmtsContext)_localctx).codes =  new ArrayList<Code>(); 
			setState(37);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
			case INTEGER:
			case ID:
				{
				setState(31);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__1:
					{
					setState(24);
					((StmtsContext)_localctx).st1 = stmt();
					 _localctx.codes.add(((StmtsContext)_localctx).st1.code); 
					}
					break;
				case ID:
					{
					setState(27);
					((StmtsContext)_localctx).id = match(ID);
					_localctx.codes.add(new Leaf("id", (((StmtsContext)_localctx).id!=null?((StmtsContext)_localctx).id.getText():null), ((StmtsContext)_localctx).id.getLine()));
					}
					break;
				case INTEGER:
					{
					setState(29);
					((StmtsContext)_localctx).val = match(INTEGER);
					 _localctx.codes.add(new Leaf("int", Integer.parseInt((((StmtsContext)_localctx).val!=null?((StmtsContext)_localctx).val.getText():null)), ((StmtsContext)_localctx).val.getLine()));
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(33);
				((StmtsContext)_localctx).sts = stmts();
				 _localctx.codes.addAll(((StmtsContext)_localctx).sts.codes); 
				}
				break;
			case T__2:
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmtContext extends ParserRuleContext {
		public Code code;
		public Token id;
		public StmtsContext args;
		public TerminalNode ID() { return getToken(PatParser.ID, 0); }
		public StmtsContext stmts() {
			return getRuleContext(StmtsContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PatListener ) ((PatListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PatListener ) ((PatListener)listener).exitStmt(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(39);
			match(T__1);
			setState(40);
			((StmtContext)_localctx).id = match(ID);
			setState(41);
			((StmtContext)_localctx).args = stmts();
			setState(42);
			match(T__2);
			 ((StmtContext)_localctx).code =  new Code((((StmtContext)_localctx).id!=null?((StmtContext)_localctx).id.getText():null), ((StmtContext)_localctx).args.codes, ((StmtContext)_localctx).id.getLine()); 
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\13\60\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\6\2\20\n\2\r\2\16\2\21\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\"\n\4\3\4\3\4\3"+
		"\4\3\4\5\4(\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\2\2\6\2\4\6\b\2\2/\2\n\3\2"+
		"\2\2\4\26\3\2\2\2\6\31\3\2\2\2\b)\3\2\2\2\n\17\b\2\1\2\13\f\5\4\3\2\f"+
		"\r\7\3\2\2\r\16\b\2\1\2\16\20\3\2\2\2\17\13\3\2\2\2\20\21\3\2\2\2\21\17"+
		"\3\2\2\2\21\22\3\2\2\2\22\23\3\2\2\2\23\24\b\2\1\2\24\25\7\2\2\3\25\3"+
		"\3\2\2\2\26\27\5\b\5\2\27\30\b\3\1\2\30\5\3\2\2\2\31\'\b\4\1\2\32\33\5"+
		"\b\5\2\33\34\b\4\1\2\34\"\3\2\2\2\35\36\7\t\2\2\36\"\b\4\1\2\37 \7\7\2"+
		"\2 \"\b\4\1\2!\32\3\2\2\2!\35\3\2\2\2!\37\3\2\2\2\"#\3\2\2\2#$\5\6\4\2"+
		"$%\b\4\1\2%(\3\2\2\2&(\3\2\2\2\'!\3\2\2\2\'&\3\2\2\2(\7\3\2\2\2)*\7\4"+
		"\2\2*+\7\t\2\2+,\5\6\4\2,-\7\5\2\2-.\b\5\1\2.\t\3\2\2\2\5\21!\'";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}