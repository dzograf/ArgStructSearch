package arg.tech.argql.parser;
// Generated from ArgQLGrammar.g4 by ANTLR 4.7.2

	import java.util.*;
	import arg.tech.argql.translator.sparql.*;
	import arg.tech.argql.results.*;
	import arg.tech.argql.patterns.*;
	import arg.tech.argql.filters.*;
	import arg.tech.utils.Enums.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArgQLGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, MATCH=14, PATH=15, RETURN=16, ATTACK=17, 
		REBUT=18, UNDERCUT=19, UNDERMINE=20, SUPPORT=21, ENDORSE=22, BACK=23, 
		VARIABLE=24, EMPTYSET=25, TIMESTAMP=26, AUTHOR=27, SCHEME=28, DOTS=29, 
		TEXT=30, INT=31, WS=32;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "MATCH", "PATH", "RETURN", "ATTACK", 
			"REBUT", "UNDERCUT", "UNDERMINE", "SUPPORT", "ENDORSE", "BACK", "VARIABLE", 
			"EMPTYSET", "TIMESTAMP", "AUTHOR", "SCHEME", "DOTS", "TEXT", "INT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'('", "')'", "'<'", "'>'", "'['", "']'", "'/'", "'.'", 
			"'{'", "'}'", "'*'", "'+'", null, null, null, null, null, null, null, 
			null, null, null, null, "'_'", null, null, null, "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "MATCH", "PATH", "RETURN", "ATTACK", "REBUT", "UNDERCUT", 
			"UNDERMINE", "SUPPORT", "ENDORSE", "BACK", "VARIABLE", "EMPTYSET", "TIMESTAMP", 
			"AUTHOR", "SCHEME", "DOTS", "TEXT", "INT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


		/*StringBuilder sparqlBuilder = new StringBuilder();
		String rdfPrefix = "rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";
		String aifPrefix = "aif:<http://www.arg.dundee.ac.uk/aif#>";*/


	public ArgQLGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ArgQLGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 31:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			skip();
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\"\u00d1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\7\31\u00a8"+
		"\n\31\f\31\16\31\u00ab\13\31\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37\7\37\u00bf\n\37\f\37"+
		"\16\37\u00c2\13\37\3\37\3\37\3 \6 \u00c7\n \r \16 \u00c8\3!\6!\u00cc\n"+
		"!\r!\16!\u00cd\3!\3!\3\u00c0\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"\3\2\25\4\2OOoo\4\2CCcc\4\2VVv"+
		"v\4\2EEee\4\2JJjj\4\2RRrr\4\2TTtt\4\2GGgg\4\2WWww\4\2PPpp\4\2MMmm\4\2"+
		"DDdd\4\2FFff\4\2KKkk\4\2UUuu\4\2QQqq\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f"+
		"\17\17\"\"\2\u00d4\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3C\3\2\2\2\5E"+
		"\3\2\2\2\7G\3\2\2\2\tI\3\2\2\2\13K\3\2\2\2\rM\3\2\2\2\17O\3\2\2\2\21Q"+
		"\3\2\2\2\23S\3\2\2\2\25U\3\2\2\2\27W\3\2\2\2\31Y\3\2\2\2\33[\3\2\2\2\35"+
		"]\3\2\2\2\37c\3\2\2\2!h\3\2\2\2#o\3\2\2\2%v\3\2\2\2\'|\3\2\2\2)\u0085"+
		"\3\2\2\2+\u008f\3\2\2\2-\u0097\3\2\2\2/\u009f\3\2\2\2\61\u00a4\3\2\2\2"+
		"\63\u00ac\3\2\2\2\65\u00ae\3\2\2\2\67\u00b1\3\2\2\29\u00b6\3\2\2\2;\u00ba"+
		"\3\2\2\2=\u00bc\3\2\2\2?\u00c6\3\2\2\2A\u00cb\3\2\2\2CD\7.\2\2D\4\3\2"+
		"\2\2EF\7*\2\2F\6\3\2\2\2GH\7+\2\2H\b\3\2\2\2IJ\7>\2\2J\n\3\2\2\2KL\7@"+
		"\2\2L\f\3\2\2\2MN\7]\2\2N\16\3\2\2\2OP\7_\2\2P\20\3\2\2\2QR\7\61\2\2R"+
		"\22\3\2\2\2ST\7\60\2\2T\24\3\2\2\2UV\7}\2\2V\26\3\2\2\2WX\7\177\2\2X\30"+
		"\3\2\2\2YZ\7,\2\2Z\32\3\2\2\2[\\\7-\2\2\\\34\3\2\2\2]^\t\2\2\2^_\t\3\2"+
		"\2_`\t\4\2\2`a\t\5\2\2ab\t\6\2\2b\36\3\2\2\2cd\t\7\2\2de\t\3\2\2ef\t\4"+
		"\2\2fg\t\6\2\2g \3\2\2\2hi\t\b\2\2ij\t\t\2\2jk\t\4\2\2kl\t\n\2\2lm\t\b"+
		"\2\2mn\t\13\2\2n\"\3\2\2\2op\t\3\2\2pq\t\4\2\2qr\t\4\2\2rs\t\3\2\2st\t"+
		"\5\2\2tu\t\f\2\2u$\3\2\2\2vw\t\b\2\2wx\t\t\2\2xy\t\r\2\2yz\t\n\2\2z{\t"+
		"\4\2\2{&\3\2\2\2|}\t\n\2\2}~\t\13\2\2~\177\t\16\2\2\177\u0080\t\t\2\2"+
		"\u0080\u0081\t\b\2\2\u0081\u0082\t\5\2\2\u0082\u0083\t\n\2\2\u0083\u0084"+
		"\t\4\2\2\u0084(\3\2\2\2\u0085\u0086\t\n\2\2\u0086\u0087\t\13\2\2\u0087"+
		"\u0088\t\16\2\2\u0088\u0089\t\t\2\2\u0089\u008a\t\b\2\2\u008a\u008b\t"+
		"\2\2\2\u008b\u008c\t\17\2\2\u008c\u008d\t\13\2\2\u008d\u008e\t\t\2\2\u008e"+
		"*\3\2\2\2\u008f\u0090\t\20\2\2\u0090\u0091\t\n\2\2\u0091\u0092\t\7\2\2"+
		"\u0092\u0093\t\7\2\2\u0093\u0094\t\21\2\2\u0094\u0095\t\b\2\2\u0095\u0096"+
		"\t\4\2\2\u0096,\3\2\2\2\u0097\u0098\t\t\2\2\u0098\u0099\t\13\2\2\u0099"+
		"\u009a\t\16\2\2\u009a\u009b\t\21\2\2\u009b\u009c\t\b\2\2\u009c\u009d\t"+
		"\20\2\2\u009d\u009e\t\t\2\2\u009e.\3\2\2\2\u009f\u00a0\t\r\2\2\u00a0\u00a1"+
		"\t\3\2\2\u00a1\u00a2\t\5\2\2\u00a2\u00a3\t\f\2\2\u00a3\60\3\2\2\2\u00a4"+
		"\u00a5\7A\2\2\u00a5\u00a9\t\22\2\2\u00a6\u00a8\t\23\2\2\u00a7\u00a6\3"+
		"\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\62\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00ad\7a\2\2\u00ad\64\3\2\2\2\u00ae"+
		"\u00af\t\4\2\2\u00af\u00b0\t\2\2\2\u00b0\66\3\2\2\2\u00b1\u00b2\t\3\2"+
		"\2\u00b2\u00b3\t\n\2\2\u00b3\u00b4\t\4\2\2\u00b4\u00b5\t\6\2\2\u00b58"+
		"\3\2\2\2\u00b6\u00b7\t\20\2\2\u00b7\u00b8\t\5\2\2\u00b8\u00b9\t\6\2\2"+
		"\u00b9:\3\2\2\2\u00ba\u00bb\7<\2\2\u00bb<\3\2\2\2\u00bc\u00c0\7$\2\2\u00bd"+
		"\u00bf\13\2\2\2\u00be\u00bd\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00c1\3"+
		"\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c3\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3"+
		"\u00c4\7$\2\2\u00c4>\3\2\2\2\u00c5\u00c7\4\62;\2\u00c6\u00c5\3\2\2\2\u00c7"+
		"\u00c8\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9@\3\2\2\2"+
		"\u00ca\u00cc\t\24\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cb"+
		"\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\b!\2\2\u00d0"+
		"B\3\2\2\2\7\2\u00a9\u00c0\u00c8\u00cd\3\3!\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}