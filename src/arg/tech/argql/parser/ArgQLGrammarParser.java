package arg.tech.argql.parser;
// Generated from ArgQLGrammar.g4 by ANTLR 4.7.2

	import java.util.*;
	import arg.tech.argql.translator.sparql.*;
	import arg.tech.argql.results.*;
	import arg.tech.argql.patterns.*;
	import arg.tech.argql.filters.*;
	import arg.tech.utils.Enums.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArgQLGrammarParser extends Parser {
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
	public static final int
		RULE_query = 0, RULE_returnValue = 1, RULE_dialogue_pattern = 2, RULE_argpattern = 3, 
		RULE_premisePattern = 4, RULE_conclusionPattern = 5, RULE_argFilters = 6, 
		RULE_af = 7, RULE_metadata = 8, RULE_premiseFilters = 9, RULE_inclusionFilter = 10, 
		RULE_joinFilter = 11, RULE_propositionSet = 12, RULE_pathpattern = 13, 
		RULE_pp = 14, RULE_relation = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"query", "returnValue", "dialogue_pattern", "argpattern", "premisePattern", 
			"conclusionPattern", "argFilters", "af", "metadata", "premiseFilters", 
			"inclusionFilter", "joinFilter", "propositionSet", "pathpattern", "pp", 
			"relation"
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

	@Override
	public String getGrammarFileName() { return "ArgQLGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		/*StringBuilder sparqlBuilder = new StringBuilder();
		String rdfPrefix = "rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";
		String aifPrefix = "aif:<http://www.arg.dundee.ac.uk/aif#>";*/

	public ArgQLGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class QueryContext extends ParserRuleContext {
		public boolean optimized;
		public String sparqlQuery;
		public Dialogue_patternContext dialogue_pattern;
		public TerminalNode MATCH() { return getToken(ArgQLGrammarParser.MATCH, 0); }
		public List<Dialogue_patternContext> dialogue_pattern() {
			return getRuleContexts(Dialogue_patternContext.class);
		}
		public Dialogue_patternContext dialogue_pattern(int i) {
			return getRuleContext(Dialogue_patternContext.class,i);
		}
		public TerminalNode RETURN() { return getToken(ArgQLGrammarParser.RETURN, 0); }
		public List<ReturnValueContext> returnValue() {
			return getRuleContexts(ReturnValueContext.class);
		}
		public ReturnValueContext returnValue(int i) {
			return getRuleContext(ReturnValueContext.class,i);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public QueryContext(ParserRuleContext parent, int invokingState, boolean optimized) {
			super(parent, invokingState);
			this.optimized = optimized;
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query(boolean optimized) throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState(), optimized);
		enterRule(_localctx, 0, RULE_query);
		((QueryContext)_localctx).sparqlQuery =  "";
				SPARQLTranslator.initialize(optimized);
			
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(MATCH);
			{
			setState(33);
			((QueryContext)_localctx).dialogue_pattern = dialogue_pattern();

			 			DialoguePattern dp = ((QueryContext)_localctx).dialogue_pattern.value;
			 			SPARQLTranslator.addDialoguePattern(dp);
			 		
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(35);
				match(T__0);
				setState(36);
				((QueryContext)_localctx).dialogue_pattern = dialogue_pattern();

				 				DialoguePattern dp1 = ((QueryContext)_localctx).dialogue_pattern.value;
				 				SPARQLTranslator.addDialoguePattern(dp1);
				 			
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURN) {
				{
				setState(44);
				match(RETURN);
				setState(45);
				returnValue();
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(46);
					match(T__0);
					setState(47);
					returnValue();
					}
					}
					setState(52);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
			_ctx.stop = _input.LT(-1);

				//	SPARQLTranslator.terminate();
					((QueryContext)_localctx).sparqlQuery =  SPARQLTranslator.generateSPARQLQuery(optimized);
					
				
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

	public static class ReturnValueContext extends ParserRuleContext {
		public Token VARIABLE;
		public List<TerminalNode> VARIABLE() { return getTokens(ArgQLGrammarParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(ArgQLGrammarParser.VARIABLE, i);
		}
		public TerminalNode PATH() { return getToken(ArgQLGrammarParser.PATH, 0); }
		public ReturnValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterReturnValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitReturnValue(this);
		}
	}

	public final ReturnValueContext returnValue() throws RecognitionException {
		ReturnValueContext _localctx = new ReturnValueContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_returnValue);
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				((ReturnValueContext)_localctx).VARIABLE = match(VARIABLE);

							SPARQLTranslator.addVarReturnvalue((((ReturnValueContext)_localctx).VARIABLE!=null?((ReturnValueContext)_localctx).VARIABLE.getText():null));
						
				}
				break;
			case PATH:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(PATH);
				String var1, var2; 
				setState(59);
				match(T__1);
				setState(60);
				((ReturnValueContext)_localctx).VARIABLE = match(VARIABLE);
				 var1 = (((ReturnValueContext)_localctx).VARIABLE!=null?((ReturnValueContext)_localctx).VARIABLE.getText():null);
				setState(62);
				match(T__0);
				setState(63);
				((ReturnValueContext)_localctx).VARIABLE = match(VARIABLE);
				 var2 = (((ReturnValueContext)_localctx).VARIABLE!=null?((ReturnValueContext)_localctx).VARIABLE.getText():null); 
				setState(65);
				match(T__2);

							SPARQLTranslator.addPathReturnValue(var1, var2); 
						  
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Dialogue_patternContext extends ParserRuleContext {
		public DialoguePattern value;
		public ArgpatternContext argpattern;
		public PathpatternContext pathpattern;
		public List<ArgpatternContext> argpattern() {
			return getRuleContexts(ArgpatternContext.class);
		}
		public ArgpatternContext argpattern(int i) {
			return getRuleContext(ArgpatternContext.class,i);
		}
		public List<PathpatternContext> pathpattern() {
			return getRuleContexts(PathpatternContext.class);
		}
		public PathpatternContext pathpattern(int i) {
			return getRuleContext(PathpatternContext.class,i);
		}
		public Dialogue_patternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dialogue_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterDialogue_pattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitDialogue_pattern(this);
		}
	}

	public final Dialogue_patternContext dialogue_pattern() throws RecognitionException {
		Dialogue_patternContext _localctx = new Dialogue_patternContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_dialogue_pattern);

			ArgPattern ap1;
			PathPatternCollection pathcollection = new PathPatternCollection();
		 	PathPatternCollection newPathCollection = new PathPatternCollection();
		 	

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			((Dialogue_patternContext)_localctx).argpattern = argpattern();

						ap1 = ((Dialogue_patternContext)_localctx).argpattern.value;
					
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << ATTACK) | (1L << REBUT) | (1L << UNDERCUT) | (1L << UNDERMINE) | (1L << SUPPORT) | (1L << ENDORSE) | (1L << BACK))) != 0)) {
				{
				{
				setState(71);
				((Dialogue_patternContext)_localctx).pathpattern = pathpattern();

								//System.out.println("Paths: ");
								//System.out.println(((Dialogue_patternContext)_localctx).pathpattern.value.toString());
								newPathCollection = ((Dialogue_patternContext)_localctx).pathpattern.value;
							
				setState(73);
				((Dialogue_patternContext)_localctx).argpattern = argpattern();

								ArgPattern ap2 = ((Dialogue_patternContext)_localctx).argpattern.value;
								newPathCollection.updateFirst(ap1);
								newPathCollection.updateLast(ap2);
								
												
							    pathcollection = PathPatternCollection.merge(pathcollection, newPathCollection);
							//	sparqlBuilder.append(ap2.getSparqlRepresentation());
										
								
							/* 				
								for(int j=0; j < pathcollection.getPathSet().size(); j++){
									PathPattern path = (PathPattern) pathcollection.getPathSet().get(j);
									
									if((j + 1) < pathcollection.getPathSet().size())
										sparqlBuilder.append("{\n");
								
									
									for(int i=0; i < path.getRelSequence().size(); i++) {
										RelationPattern relPattern = path.getRelSequence().get(i);
										
										if(i!=path.getRelSequence().size()-1)
										{
											sparqlBuilder.append(relPattern.getAp2().getSparqlRepresentation());
										}
											
										sparqlBuilder.append(relPattern.getSparqlRepresentation());
									}
									 
									
									if((j + 1) < pathcollection.getPathSet().size()){
										sparqlBuilder.append("}\n");	
										sparqlBuilder.append("UNION\n");
									}
								}
								 
								if(pathcollection != null){
						 			System.out.println(pathcollection.toString());  
						 		}*/
								ap1 = ap2;
								
							
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			 		DialoguePattern dp = null;
			 		if(pathcollection.getPathSet().isEmpty()) {
			 			dp = new DialoguePattern(ap1);
			 		} else{
			 			dp = new DialoguePattern(pathcollection); 
			 		}
			 		  
			 		 ((Dialogue_patternContext)_localctx).value =  dp;
			 	
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

	public static class ArgpatternContext extends ParserRuleContext {
		public ArgPattern value;
		public Token VARIABLE;
		public PremisePatternContext premisePattern;
		public ConclusionPatternContext conclusionPattern;
		public ArgFiltersContext argFilters;
		public TerminalNode VARIABLE() { return getToken(ArgQLGrammarParser.VARIABLE, 0); }
		public PremisePatternContext premisePattern() {
			return getRuleContext(PremisePatternContext.class,0);
		}
		public ConclusionPatternContext conclusionPattern() {
			return getRuleContext(ConclusionPatternContext.class,0);
		}
		public ArgFiltersContext argFilters() {
			return getRuleContext(ArgFiltersContext.class,0);
		}
		public TerminalNode DOTS() { return getToken(ArgQLGrammarParser.DOTS, 0); }
		public ArgpatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argpattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterArgpattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitArgpattern(this);
		}
	}

	public final ArgpatternContext argpattern() throws RecognitionException {
		ArgpatternContext _localctx = new ArgpatternContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_argpattern);
		 
			String raVar = SPARQLTranslator.generateRAVariable();
			ArgPattern arg_pattern = new ArgPattern();
			arg_pattern.setRaVariable(raVar);

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(83);
				((ArgpatternContext)_localctx).VARIABLE = match(VARIABLE);

							if(SPARQLTranslator.addVar((((ArgpatternContext)_localctx).VARIABLE!=null?((ArgpatternContext)_localctx).VARIABLE.getText():null), VariableType.ARGUMENT)){
								arg_pattern = SPARQLTranslator.argPatternVariable((((ArgpatternContext)_localctx).VARIABLE!=null?((ArgpatternContext)_localctx).VARIABLE.getText():null), raVar);
								//sparqlBuilder.append(arg_pattern.getSparqlRepresentation());
							}
							
						
				}
				break;
			case 2:
				{
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VARIABLE) {
					{
					setState(85);
					((ArgpatternContext)_localctx).VARIABLE = match(VARIABLE);
					setState(86);
					match(DOTS);

								if(SPARQLTranslator.addVar((((ArgpatternContext)_localctx).VARIABLE!=null?((ArgpatternContext)_localctx).VARIABLE.getText():null), VariableType.ARGUMENT))
									arg_pattern.setArgVar((((ArgpatternContext)_localctx).VARIABLE!=null?((ArgpatternContext)_localctx).VARIABLE.getText():null));
								
							
					}
				}

				setState(90);
				match(T__3);
				setState(91);
				((ArgpatternContext)_localctx).premisePattern = premisePattern(arg_pattern);

									PremisePattern prempattern = ((ArgpatternContext)_localctx).premisePattern.value;
									
									arg_pattern.setPremisePattern(prempattern);
											
				setState(93);
				match(T__0);
				setState(94);
				((ArgpatternContext)_localctx).conclusionPattern = conclusionPattern(arg_pattern);

									ConclusionPattern conclPattern = ((ArgpatternContext)_localctx).conclusionPattern.value;
									arg_pattern.setConclusionPattern(conclPattern);
									
				setState(96);
				match(T__4);

												
								
				}
				break;
			}
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(101);
				((ArgpatternContext)_localctx).argFilters = argFilters();

						Metadata meta = ((ArgpatternContext)_localctx).argFilters.value;
						arg_pattern.setMetadata(meta);
					
				}
			}

			}
			_ctx.stop = _input.LT(-1);

				//SPARQLTranslator.addArgPattern(arg_pattern);
				((ArgpatternContext)_localctx).value =  arg_pattern;
				

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

	public static class PremisePatternContext extends ParserRuleContext {
		public ArgPattern arg_pattern;
		public PremisePattern value;
		public PropositionSetContext propositionSet;
		public Token VARIABLE;
		public PremiseFiltersContext premiseFilters;
		public PropositionSetContext propositionSet() {
			return getRuleContext(PropositionSetContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(ArgQLGrammarParser.VARIABLE, 0); }
		public PremiseFiltersContext premiseFilters() {
			return getRuleContext(PremiseFiltersContext.class,0);
		}
		public TerminalNode EMPTYSET() { return getToken(ArgQLGrammarParser.EMPTYSET, 0); }
		public PremisePatternContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public PremisePatternContext(ParserRuleContext parent, int invokingState, ArgPattern arg_pattern) {
			super(parent, invokingState);
			this.arg_pattern = arg_pattern;
		}
		@Override public int getRuleIndex() { return RULE_premisePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterPremisePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitPremisePattern(this);
		}
	}

	public final PremisePatternContext premisePattern(ArgPattern arg_pattern) throws RecognitionException {
		PremisePatternContext _localctx = new PremisePatternContext(_ctx, getState(), arg_pattern);
		enterRule(_localctx, 8, RULE_premisePattern);

			PremisePattern premPattern = null;

		int _la;
		try {
			setState(119);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				((PremisePatternContext)_localctx).propositionSet = propositionSet();
				 
							ArrayList<PropositionPattern> propositions = ((PremisePatternContext)_localctx).propositionSet.value;
							premPattern = SPARQLTranslator.premisePropSet(propositions, arg_pattern.getRaVariable());
							((PremisePatternContext)_localctx).value =  premPattern;
						
				}
				break;
			case VARIABLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				((PremisePatternContext)_localctx).VARIABLE = match(VARIABLE);

						if(SPARQLTranslator.addVar((((PremisePatternContext)_localctx).VARIABLE!=null?((PremisePatternContext)_localctx).VARIABLE.getText():null), VariableType.PREMISE)){
								premPattern = SPARQLTranslator.premiseVar((((PremisePatternContext)_localctx).VARIABLE!=null?((PremisePatternContext)_localctx).VARIABLE.getText():null), arg_pattern.getRaVariable());				
							}
						
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(111);
					((PremisePatternContext)_localctx).premiseFilters = premiseFilters(premPattern, arg_pattern.getRaVariable());

					 			premPattern.setFilter(((PremisePatternContext)_localctx).premiseFilters.value);
					 		
					}
				}


				 		((PremisePatternContext)_localctx).value =  premPattern;
				 	
				}
				break;
			case EMPTYSET:
				enterOuterAlt(_localctx, 3);
				{
				setState(117);
				match(EMPTYSET);
				 
				 		premPattern = SPARQLTranslator.premiseEmpty();
				 		((PremisePatternContext)_localctx).value =  premPattern; 
				 	
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ConclusionPatternContext extends ParserRuleContext {
		public ArgPattern ap;
		public ConclusionPattern value;
		public Token VARIABLE;
		public Token TEXT;
		public TerminalNode VARIABLE() { return getToken(ArgQLGrammarParser.VARIABLE, 0); }
		public TerminalNode TEXT() { return getToken(ArgQLGrammarParser.TEXT, 0); }
		public ConclusionPatternContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ConclusionPatternContext(ParserRuleContext parent, int invokingState, ArgPattern ap) {
			super(parent, invokingState);
			this.ap = ap;
		}
		@Override public int getRuleIndex() { return RULE_conclusionPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterConclusionPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitConclusionPattern(this);
		}
	}

	public final ConclusionPatternContext conclusionPattern(ArgPattern ap) throws RecognitionException {
		ConclusionPatternContext _localctx = new ConclusionPatternContext(_ctx, getState(), ap);
		enterRule(_localctx, 10, RULE_conclusionPattern);
		try {
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				((ConclusionPatternContext)_localctx).VARIABLE = match(VARIABLE);

						if(SPARQLTranslator.addVar((((ConclusionPatternContext)_localctx).VARIABLE!=null?((ConclusionPatternContext)_localctx).VARIABLE.getText():null), VariableType.CONCLUSION)){
							ConclusionPattern conclPattern = SPARQLTranslator.conclVarPattern((((ConclusionPatternContext)_localctx).VARIABLE!=null?((ConclusionPatternContext)_localctx).VARIABLE.getText():null));
							conclPattern.setArgPattern(ap);
							((ConclusionPatternContext)_localctx).value =  conclPattern;	
						}
					
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				((ConclusionPatternContext)_localctx).TEXT = match(TEXT);

						ConclusionPattern conclPattern = SPARQLTranslator.conclPropositionPattern((((ConclusionPatternContext)_localctx).TEXT!=null?((ConclusionPatternContext)_localctx).TEXT.getText():null));
						conclPattern.setArgPattern(ap);
						//sparqlBuilder.append(conclPattern.getSparqlRepresentation());
						((ConclusionPatternContext)_localctx).value =  conclPattern;
					
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ArgFiltersContext extends ParserRuleContext {
		public Metadata value;
		public AfContext af;
		public List<AfContext> af() {
			return getRuleContexts(AfContext.class);
		}
		public AfContext af(int i) {
			return getRuleContext(AfContext.class,i);
		}
		public ArgFiltersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argFilters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterArgFilters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitArgFilters(this);
		}
	}

	public final ArgFiltersContext argFilters() throws RecognitionException {
		ArgFiltersContext _localctx = new ArgFiltersContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_argFilters);

			Metadata meta = new Metadata();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(T__5);
			setState(128);
			((ArgFiltersContext)_localctx).af = af();

						meta.addSimpleEntry(((ArgFiltersContext)_localctx).af.value);
					
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(130);
				match(T__0);
				setState(131);
				((ArgFiltersContext)_localctx).af = af();

							meta.addSimpleEntry(((ArgFiltersContext)_localctx).af.value);
						
				}
				}
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(139);
			match(T__6);
			}
			_ctx.stop = _input.LT(-1);

				((ArgFiltersContext)_localctx).value =  meta;

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

	public static class AfContext extends ParserRuleContext {
		public AbstractMap.SimpleEntry<String, String> value;
		public Token TEXT;
		public MetadataContext metadata;
		public TerminalNode SCHEME() { return getToken(ArgQLGrammarParser.SCHEME, 0); }
		public TerminalNode DOTS() { return getToken(ArgQLGrammarParser.DOTS, 0); }
		public TerminalNode TEXT() { return getToken(ArgQLGrammarParser.TEXT, 0); }
		public MetadataContext metadata() {
			return getRuleContext(MetadataContext.class,0);
		}
		public AfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_af; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterAf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitAf(this);
		}
	}

	public final AfContext af() throws RecognitionException {
		AfContext _localctx = new AfContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_af);
		try {
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SCHEME:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				match(SCHEME);
				setState(142);
				match(DOTS);
				setState(143);
				((AfContext)_localctx).TEXT = match(TEXT);

				 		String scheme = (((AfContext)_localctx).TEXT!=null?((AfContext)_localctx).TEXT.getText():null);
				 		((AfContext)_localctx).value =  new AbstractMap.SimpleEntry<String, String>("sch", scheme);
					
				}
				break;
			case TIMESTAMP:
			case AUTHOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				((AfContext)_localctx).metadata = metadata();

					  	((AfContext)_localctx).value =  ((AfContext)_localctx).metadata.value;
					  
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class MetadataContext extends ParserRuleContext {
		public AbstractMap.SimpleEntry<String, String> value;
		public Token TEXT;
		public TerminalNode TIMESTAMP() { return getToken(ArgQLGrammarParser.TIMESTAMP, 0); }
		public TerminalNode DOTS() { return getToken(ArgQLGrammarParser.DOTS, 0); }
		public TerminalNode TEXT() { return getToken(ArgQLGrammarParser.TEXT, 0); }
		public TerminalNode AUTHOR() { return getToken(ArgQLGrammarParser.AUTHOR, 0); }
		public MetadataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metadata; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterMetadata(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitMetadata(this);
		}
	}

	public final MetadataContext metadata() throws RecognitionException {
		MetadataContext _localctx = new MetadataContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_metadata);
		try {
			setState(157);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TIMESTAMP:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				match(TIMESTAMP);
				setState(151);
				match(DOTS);
				setState(152);
				((MetadataContext)_localctx).TEXT = match(TEXT);

				 	String timestamp = (((MetadataContext)_localctx).TEXT!=null?((MetadataContext)_localctx).TEXT.getText():null);
				 	((MetadataContext)_localctx).value =  new AbstractMap.SimpleEntry<String, String>("tm", timestamp);
				 
				}
				break;
			case AUTHOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(AUTHOR);
				setState(155);
				match(DOTS);
				setState(156);
				match(TEXT);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class PremiseFiltersContext extends ParserRuleContext {
		public PremisePattern premPattern;
		public String raVar;
		public Filter value;
		public InclusionFilterContext inclusionFilter;
		public JoinFilterContext joinFilter;
		public InclusionFilterContext inclusionFilter() {
			return getRuleContext(InclusionFilterContext.class,0);
		}
		public JoinFilterContext joinFilter() {
			return getRuleContext(JoinFilterContext.class,0);
		}
		public PremiseFiltersContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public PremiseFiltersContext(ParserRuleContext parent, int invokingState, PremisePattern premPattern, String raVar) {
			super(parent, invokingState);
			this.premPattern = premPattern;
			this.raVar = raVar;
		}
		@Override public int getRuleIndex() { return RULE_premiseFilters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterPremiseFilters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitPremiseFilters(this);
		}
	}

	public final PremiseFiltersContext premiseFilters(PremisePattern premPattern,String raVar) throws RecognitionException {
		PremiseFiltersContext _localctx = new PremiseFiltersContext(_ctx, getState(), premPattern, raVar);
		enterRule(_localctx, 18, RULE_premiseFilters);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(T__5);
			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				{
				setState(160);
				((PremiseFiltersContext)_localctx).inclusionFilter = inclusionFilter(premPattern, raVar);

				 			((PremiseFiltersContext)_localctx).value =  ((PremiseFiltersContext)_localctx).inclusionFilter.value;
				 		
				}
				break;
			case T__8:
				{
				setState(163);
				((PremiseFiltersContext)_localctx).joinFilter = joinFilter(premPattern, raVar);

				 			((PremiseFiltersContext)_localctx).value =  ((PremiseFiltersContext)_localctx).joinFilter.value;
				 		
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(168);
			match(T__6);
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

	public static class InclusionFilterContext extends ParserRuleContext {
		public PremisePattern premPattern;
		public String raVar;
		public InclusionFilter value;
		public PropositionSetContext propositionSet;
		public Token VARIABLE;
		public PropositionSetContext propositionSet() {
			return getRuleContext(PropositionSetContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(ArgQLGrammarParser.VARIABLE, 0); }
		public InclusionFilterContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public InclusionFilterContext(ParserRuleContext parent, int invokingState, PremisePattern premPattern, String raVar) {
			super(parent, invokingState);
			this.premPattern = premPattern;
			this.raVar = raVar;
		}
		@Override public int getRuleIndex() { return RULE_inclusionFilter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterInclusionFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitInclusionFilter(this);
		}
	}

	public final InclusionFilterContext inclusionFilter(PremisePattern premPattern,String raVar) throws RecognitionException {
		InclusionFilterContext _localctx = new InclusionFilterContext(_ctx, getState(), premPattern, raVar);
		enterRule(_localctx, 20, RULE_inclusionFilter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(T__7);
			setState(176);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				{
				setState(171);
				((InclusionFilterContext)_localctx).propositionSet = propositionSet();

							((InclusionFilterContext)_localctx).value =  new InclusionFilter(premPattern, ((InclusionFilterContext)_localctx).propositionSet.value, raVar);
						
				}
				break;
			case VARIABLE:
				{
				setState(174);
				((InclusionFilterContext)_localctx).VARIABLE = match(VARIABLE);
				 
							ArgPattern ap2 = SPARQLTranslator.premVarExists((((InclusionFilterContext)_localctx).VARIABLE!=null?((InclusionFilterContext)_localctx).VARIABLE.getText():null));
							if(ap2 != null){
								((InclusionFilterContext)_localctx).value =  new InclusionFilter(premPattern, ap2, raVar);
							}						
						
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

	public static class JoinFilterContext extends ParserRuleContext {
		public PremisePattern premPattern;
		public String raVar;
		public JoinFilter value;
		public PropositionSetContext propositionSet;
		public Token VARIABLE;
		public PropositionSetContext propositionSet() {
			return getRuleContext(PropositionSetContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(ArgQLGrammarParser.VARIABLE, 0); }
		public JoinFilterContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public JoinFilterContext(ParserRuleContext parent, int invokingState, PremisePattern premPattern, String raVar) {
			super(parent, invokingState);
			this.premPattern = premPattern;
			this.raVar = raVar;
		}
		@Override public int getRuleIndex() { return RULE_joinFilter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterJoinFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitJoinFilter(this);
		}
	}

	public final JoinFilterContext joinFilter(PremisePattern premPattern,String raVar) throws RecognitionException {
		JoinFilterContext _localctx = new JoinFilterContext(_ctx, getState(), premPattern, raVar);
		enterRule(_localctx, 22, RULE_joinFilter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			match(T__8);
			setState(184);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				{
				setState(179);
				((JoinFilterContext)_localctx).propositionSet = propositionSet();

							((JoinFilterContext)_localctx).value =   new JoinFilter(premPattern, ((JoinFilterContext)_localctx).propositionSet.value, raVar); 	
						
				}
				break;
			case VARIABLE:
				{
				setState(182);
				((JoinFilterContext)_localctx).VARIABLE = match(VARIABLE);

							ArgPattern ap2 = SPARQLTranslator.premVarExists((((JoinFilterContext)_localctx).VARIABLE!=null?((JoinFilterContext)_localctx).VARIABLE.getText():null));
							if(ap2 != null){
								((JoinFilterContext)_localctx).value =  new JoinFilter(premPattern, ap2, raVar);
							} 
						
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

	public static class PropositionSetContext extends ParserRuleContext {
		public ArrayList<PropositionPattern> value;
		public Token TEXT;
		public List<TerminalNode> TEXT() { return getTokens(ArgQLGrammarParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(ArgQLGrammarParser.TEXT, i);
		}
		public PropositionSetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propositionSet; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterPropositionSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitPropositionSet(this);
		}
	}

	public final PropositionSetContext propositionSet() throws RecognitionException {
		PropositionSetContext _localctx = new PropositionSetContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_propositionSet);
		ArrayList<PropositionPattern> propSet = new ArrayList<PropositionPattern>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(T__9);
			setState(187);
			((PropositionSetContext)_localctx).TEXT = match(TEXT);

						PropositionPattern prop = SPARQLTranslator.newPropositionPattern((((PropositionSetContext)_localctx).TEXT!=null?((PropositionSetContext)_localctx).TEXT.getText():null));
						propSet.add(prop);
						//sparqlBuilder.append(prop.getSparqlRepresentation());
					
			setState(194);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(189);
				match(T__0);
				setState(190);
				((PropositionSetContext)_localctx).TEXT = match(TEXT);

								PropositionPattern prop2 = SPARQLTranslator.newPropositionPattern((((PropositionSetContext)_localctx).TEXT!=null?((PropositionSetContext)_localctx).TEXT.getText():null));
								propSet.add(prop2);
							//	sparqlBuilder.append(prop2.getSparqlRepresentation());
								
				}
				}
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(197);
			match(T__10);
			}
			_ctx.stop = _input.LT(-1);
			 ((PropositionSetContext)_localctx).value =  propSet; 
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

	public static class PathpatternContext extends ParserRuleContext {
		public PathPatternCollection value;
		public PpContext pp;
		public List<PpContext> pp() {
			return getRuleContexts(PpContext.class);
		}
		public PpContext pp(int i) {
			return getRuleContext(PpContext.class,i);
		}
		public PathpatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathpattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterPathpattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitPathpattern(this);
		}
	}

	public final PathpatternContext pathpattern() throws RecognitionException {
		PathpatternContext _localctx = new PathpatternContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_pathpattern);

		 	PathPatternCollection leftCollection;
		 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			((PathpatternContext)_localctx).pp = pp();

			//	pathcollection = ((PathpatternContext)_localctx).pp.value;
					leftCollection = ((PathpatternContext)_localctx).pp.value;
				
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(201);
				match(T__7);
				setState(202);
				((PathpatternContext)_localctx).pp = pp();

				 			
							PathPatternCollection rightCollection= ((PathpatternContext)_localctx).pp.value;
				 			
				 			String raVar = SPARQLTranslator.generateRAVariable();
							ArgPattern newAP = SPARQLTranslator.argPatternVariable("_", raVar);
							
							leftCollection.updateLast(newAP);
							rightCollection.updateFirst(newAP);		
												
							leftCollection = PathPatternCollection.merge(leftCollection, rightCollection);
						
				}
				}
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			_ctx.stop = _input.LT(-1);

			 	((PathpatternContext)_localctx).value =  leftCollection;
			 
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

	public static class PpContext extends ParserRuleContext {
		public PathPatternCollection value;
		public RelationContext relation;
		public PathpatternContext pathpattern;
		public Token INT;
		public RelationContext relation() {
			return getRuleContext(RelationContext.class,0);
		}
		public PathpatternContext pathpattern() {
			return getRuleContext(PathpatternContext.class,0);
		}
		public TerminalNode INT() { return getToken(ArgQLGrammarParser.INT, 0); }
		public PpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterPp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitPp(this);
		}
	}

	public final PpContext pp() throws RecognitionException {
		PpContext _localctx = new PpContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_pp);
		try {
			setState(224);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATTACK:
			case REBUT:
			case UNDERCUT:
			case UNDERMINE:
			case SUPPORT:
			case ENDORSE:
			case BACK:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				((PpContext)_localctx).relation = relation();

							PathPatternCollection pathcollection = new PathPatternCollection();
							PathPattern path = new PathPattern();
							path.addToPath(((PpContext)_localctx).relation.value);
							pathcollection.addToCollection(path);
							((PpContext)_localctx).value =  pathcollection;
							/* 
							 	if(pathcollection.getPathSet().isEmpty()) {
									PathPattern path = new PathPattern();
									path.addToPath(((PpContext)_localctx).relation.value);
									pathcollection.addToCollection(path);
								} else {
									for(PathPattern path : pathcollection.getPathSet()) {
										path.addToPath(((PpContext)_localctx).relation.value);
									//	System.out.println("Path Collection: "+pathcollection.toString());
									
									}
								}
								((PpContext)_localctx).value =  pathcollection;*/
						
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
				match(T__1);
				setState(214);
				((PpContext)_localctx).pathpattern = pathpattern();
				setState(215);
				match(T__2);
				setState(222);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__11:
					{
					setState(216);
					match(T__11);
					setState(217);
					((PpContext)_localctx).INT = match(INT);

					   		 				PathPatternCollection pathcollection = ((PpContext)_localctx).pathpattern.value;
					   		 			 	PathPatternCollection newCollection = new PathPatternCollection();
					   		 			 	
					   		 				int num = Integer.parseInt((((PpContext)_localctx).INT!=null?((PpContext)_localctx).INT.getText():null));
					   		 				for(PathPattern pathpattern : pathcollection.getPathSet()){
					   		 					newCollection.addToCollection(PathPattern.multiplyPathWith(pathpattern, num));
					   		 				}	
					   		 				((PpContext)_localctx).value =  newCollection;
					   					 
					}
					break;
				case T__12:
					{
					setState(219);
					match(T__12);
					setState(220);
					((PpContext)_localctx).INT = match(INT);

					   						PathPatternCollection pathcollection = ((PpContext)_localctx).pathpattern.value;
					   						PathPatternCollection newCollection = new PathPatternCollection();
					   						int num = Integer.parseInt((((PpContext)_localctx).INT!=null?((PpContext)_localctx).INT.getText():null));
					   						
					   						for(int i=0; i < pathcollection.getPathSet().size(); i++){
					   							PathPattern base = PathPattern.deepCopy(pathcollection.getPathSet().get(i));
					   						//	PathPattern newPath = new PathPattern();
					   							for(int j=0; j<num; j++){	
					   								if(j==0) {
					   									newCollection.addToCollection(base);
					   					//				newPath.relSequence.addAll(base.relSequence);
					   								} else{
					   									int prevPathPosition = newCollection.getPathSet().size()-1;
					   									PathPattern prevPath = newCollection.getPathSet().get(prevPathPosition);
					   									
					   								//	ArrayList<RelationPattern> prevRelSequence = new ArrayList<RelationPattern>(newCollection.getPathSet().get(prevPathPosition).getRelSequence());
					   							//		PathPattern prevPath = new PathPattern();
					   								//	prevPath.setRelSequence(prevRelSequence);
					   									PathPattern newPath = PathPattern.appendPathPattern(prevPath, base);
					   									
					   									newCollection.addToCollection(newPath);
					   								}
					   							}
					   						}
					   						
					   						
					   						/*for(int i=1; i<=num; i++){
					   							for(int j=0; j < pathcollection.getPathSet().size(); j++){
					   							 	PathPattern pathpattern = pathcollection.getPathSet().get(j);
					   							
					   								PathPattern newPath = PathPattern.multiplyPathWith(pathpattern, i); 
					   		 						newCollection.addToCollection(newPath);
					   		 					}
					   						}*/
					   						((PpContext)_localctx).value =  newCollection;
					   					 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class RelationContext extends ParserRuleContext {
		public RelationPattern value;
		public TerminalNode ATTACK() { return getToken(ArgQLGrammarParser.ATTACK, 0); }
		public TerminalNode REBUT() { return getToken(ArgQLGrammarParser.REBUT, 0); }
		public TerminalNode UNDERCUT() { return getToken(ArgQLGrammarParser.UNDERCUT, 0); }
		public TerminalNode UNDERMINE() { return getToken(ArgQLGrammarParser.UNDERMINE, 0); }
		public TerminalNode SUPPORT() { return getToken(ArgQLGrammarParser.SUPPORT, 0); }
		public TerminalNode ENDORSE() { return getToken(ArgQLGrammarParser.ENDORSE, 0); }
		public TerminalNode BACK() { return getToken(ArgQLGrammarParser.BACK, 0); }
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArgQLGrammarListener ) ((ArgQLGrammarListener)listener).exitRelation(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_relation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ATTACK:
				{
				setState(226);
				match(ATTACK);

					 	//((RelationContext)_localctx).value =  "attack";
					 		((RelationContext)_localctx).value =  (AttackPattern)SPARQLTranslator.attack();
					 	
				}
				break;
			case REBUT:
				{
				setState(228);
				match(REBUT);

					    	((RelationContext)_localctx).value =  (RebutPattern)SPARQLTranslator.rebut();
					    	//((RelationContext)_localctx).value =  "rebut";
					 	
				}
				break;
			case UNDERCUT:
				{
				setState(230);
				match(UNDERCUT);

						 ((RelationContext)_localctx).value =  (UndercutPattern)SPARQLTranslator.undercut();
						// ((RelationContext)_localctx).value =  "undercut";
					 	
				}
				break;
			case UNDERMINE:
				{
				setState(232);
				match(UNDERMINE);

						 ((RelationContext)_localctx).value =  (UnderminePattern)SPARQLTranslator.undermine();
						// ((RelationContext)_localctx).value =  "undercut";
					 	
				}
				break;
			case SUPPORT:
				{
				setState(234);
				match(SUPPORT);

					 	((RelationContext)_localctx).value =  (SupportPattern)SPARQLTranslator.support();
					 	// ((RelationContext)_localctx).value =  "support";
					 	
				}
				break;
			case ENDORSE:
				{
				setState(236);
				match(ENDORSE);

					 	((RelationContext)_localctx).value =  (EndorsePattern)SPARQLTranslator.endorse();
					 	//((RelationContext)_localctx).value =  "endorse";
					 	
				}
				break;
			case BACK:
				{
				setState(238);
				match(BACK);

						((RelationContext)_localctx).value =  (BackPattern)SPARQLTranslator.backing();
					 	//((RelationContext)_localctx).value =  "back";
					 	
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\"\u00f5\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\7\2*\n\2\f\2\16\2-\13\2\3\2\3\2\3\2\3\2\7\2\63\n\2"+
		"\f\2\16\2\66\13\2\5\28\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\3F\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4O\n\4\f\4\16\4R\13\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\5\5[\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\5\5f\n\5\3\5\3\5\3\5\5\5k\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6u\n"+
		"\6\3\6\3\6\3\6\5\6z\n\6\3\7\3\7\3\7\3\7\5\7\u0080\n\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\7\b\u0089\n\b\f\b\16\b\u008c\13\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\t\u0097\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00a0\n\n\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a9\n\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\f\u00b3\n\f\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00bb\n\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\7\16\u00c3\n\16\f\16\16\16\u00c6\13\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u00d0\n\17\f\17\16\17\u00d3\13"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00e1"+
		"\n\20\5\20\u00e3\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\5\21\u00f3\n\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \2\2\2\u0100\2\"\3\2\2\2\4E\3\2\2\2\6G\3\2\2\2\be\3"+
		"\2\2\2\ny\3\2\2\2\f\177\3\2\2\2\16\u0081\3\2\2\2\20\u0096\3\2\2\2\22\u009f"+
		"\3\2\2\2\24\u00a1\3\2\2\2\26\u00ac\3\2\2\2\30\u00b4\3\2\2\2\32\u00bc\3"+
		"\2\2\2\34\u00c9\3\2\2\2\36\u00e2\3\2\2\2 \u00f2\3\2\2\2\"#\7\20\2\2#$"+
		"\5\6\4\2$+\b\2\1\2%&\7\3\2\2&\'\5\6\4\2\'(\b\2\1\2(*\3\2\2\2)%\3\2\2\2"+
		"*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,\67\3\2\2\2-+\3\2\2\2./\7\22\2\2/\64\5"+
		"\4\3\2\60\61\7\3\2\2\61\63\5\4\3\2\62\60\3\2\2\2\63\66\3\2\2\2\64\62\3"+
		"\2\2\2\64\65\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\67.\3\2\2\2\678\3\2\2\2"+
		"8\3\3\2\2\29:\7\32\2\2:F\b\3\1\2;<\7\21\2\2<=\b\3\1\2=>\7\4\2\2>?\7\32"+
		"\2\2?@\b\3\1\2@A\7\3\2\2AB\7\32\2\2BC\b\3\1\2CD\7\5\2\2DF\b\3\1\2E9\3"+
		"\2\2\2E;\3\2\2\2F\5\3\2\2\2GH\5\b\5\2HP\b\4\1\2IJ\5\34\17\2JK\b\4\1\2"+
		"KL\5\b\5\2LM\b\4\1\2MO\3\2\2\2NI\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2"+
		"QS\3\2\2\2RP\3\2\2\2ST\b\4\1\2T\7\3\2\2\2UV\7\32\2\2Vf\b\5\1\2WX\7\32"+
		"\2\2XY\7\37\2\2Y[\b\5\1\2ZW\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\]\7\6\2\2]^"+
		"\5\n\6\2^_\b\5\1\2_`\7\3\2\2`a\5\f\7\2ab\b\5\1\2bc\7\7\2\2cd\b\5\1\2d"+
		"f\3\2\2\2eU\3\2\2\2eZ\3\2\2\2fj\3\2\2\2gh\5\16\b\2hi\b\5\1\2ik\3\2\2\2"+
		"jg\3\2\2\2jk\3\2\2\2k\t\3\2\2\2lm\5\32\16\2mn\b\6\1\2nz\3\2\2\2op\7\32"+
		"\2\2pt\b\6\1\2qr\5\24\13\2rs\b\6\1\2su\3\2\2\2tq\3\2\2\2tu\3\2\2\2uv\3"+
		"\2\2\2vz\b\6\1\2wx\7\33\2\2xz\b\6\1\2yl\3\2\2\2yo\3\2\2\2yw\3\2\2\2z\13"+
		"\3\2\2\2{|\7\32\2\2|\u0080\b\7\1\2}~\7 \2\2~\u0080\b\7\1\2\177{\3\2\2"+
		"\2\177}\3\2\2\2\u0080\r\3\2\2\2\u0081\u0082\7\b\2\2\u0082\u0083\5\20\t"+
		"\2\u0083\u008a\b\b\1\2\u0084\u0085\7\3\2\2\u0085\u0086\5\20\t\2\u0086"+
		"\u0087\b\b\1\2\u0087\u0089\3\2\2\2\u0088\u0084\3\2\2\2\u0089\u008c\3\2"+
		"\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c"+
		"\u008a\3\2\2\2\u008d\u008e\7\t\2\2\u008e\17\3\2\2\2\u008f\u0090\7\36\2"+
		"\2\u0090\u0091\7\37\2\2\u0091\u0092\7 \2\2\u0092\u0097\b\t\1\2\u0093\u0094"+
		"\5\22\n\2\u0094\u0095\b\t\1\2\u0095\u0097\3\2\2\2\u0096\u008f\3\2\2\2"+
		"\u0096\u0093\3\2\2\2\u0097\21\3\2\2\2\u0098\u0099\7\34\2\2\u0099\u009a"+
		"\7\37\2\2\u009a\u009b\7 \2\2\u009b\u00a0\b\n\1\2\u009c\u009d\7\35\2\2"+
		"\u009d\u009e\7\37\2\2\u009e\u00a0\7 \2\2\u009f\u0098\3\2\2\2\u009f\u009c"+
		"\3\2\2\2\u00a0\23\3\2\2\2\u00a1\u00a8\7\b\2\2\u00a2\u00a3\5\26\f\2\u00a3"+
		"\u00a4\b\13\1\2\u00a4\u00a9\3\2\2\2\u00a5\u00a6\5\30\r\2\u00a6\u00a7\b"+
		"\13\1\2\u00a7\u00a9\3\2\2\2\u00a8\u00a2\3\2\2\2\u00a8\u00a5\3\2\2\2\u00a9"+
		"\u00aa\3\2\2\2\u00aa\u00ab\7\t\2\2\u00ab\25\3\2\2\2\u00ac\u00b2\7\n\2"+
		"\2\u00ad\u00ae\5\32\16\2\u00ae\u00af\b\f\1\2\u00af\u00b3\3\2\2\2\u00b0"+
		"\u00b1\7\32\2\2\u00b1\u00b3\b\f\1\2\u00b2\u00ad\3\2\2\2\u00b2\u00b0\3"+
		"\2\2\2\u00b3\27\3\2\2\2\u00b4\u00ba\7\13\2\2\u00b5\u00b6\5\32\16\2\u00b6"+
		"\u00b7\b\r\1\2\u00b7\u00bb\3\2\2\2\u00b8\u00b9\7\32\2\2\u00b9\u00bb\b"+
		"\r\1\2\u00ba\u00b5\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb\31\3\2\2\2\u00bc"+
		"\u00bd\7\f\2\2\u00bd\u00be\7 \2\2\u00be\u00c4\b\16\1\2\u00bf\u00c0\7\3"+
		"\2\2\u00c0\u00c1\7 \2\2\u00c1\u00c3\b\16\1\2\u00c2\u00bf\3\2\2\2\u00c3"+
		"\u00c6\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2"+
		"\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c8\7\r\2\2\u00c8\33\3\2\2\2\u00c9\u00ca"+
		"\5\36\20\2\u00ca\u00d1\b\17\1\2\u00cb\u00cc\7\n\2\2\u00cc\u00cd\5\36\20"+
		"\2\u00cd\u00ce\b\17\1\2\u00ce\u00d0\3\2\2\2\u00cf\u00cb\3\2\2\2\u00d0"+
		"\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\35\3\2\2"+
		"\2\u00d3\u00d1\3\2\2\2\u00d4\u00d5\5 \21\2\u00d5\u00d6\b\20\1\2\u00d6"+
		"\u00e3\3\2\2\2\u00d7\u00d8\7\4\2\2\u00d8\u00d9\5\34\17\2\u00d9\u00e0\7"+
		"\5\2\2\u00da\u00db\7\16\2\2\u00db\u00dc\7!\2\2\u00dc\u00e1\b\20\1\2\u00dd"+
		"\u00de\7\17\2\2\u00de\u00df\7!\2\2\u00df\u00e1\b\20\1\2\u00e0\u00da\3"+
		"\2\2\2\u00e0\u00dd\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00d4\3\2\2\2\u00e2"+
		"\u00d7\3\2\2\2\u00e3\37\3\2\2\2\u00e4\u00e5\7\23\2\2\u00e5\u00f3\b\21"+
		"\1\2\u00e6\u00e7\7\24\2\2\u00e7\u00f3\b\21\1\2\u00e8\u00e9\7\25\2\2\u00e9"+
		"\u00f3\b\21\1\2\u00ea\u00eb\7\26\2\2\u00eb\u00f3\b\21\1\2\u00ec\u00ed"+
		"\7\27\2\2\u00ed\u00f3\b\21\1\2\u00ee\u00ef\7\30\2\2\u00ef\u00f3\b\21\1"+
		"\2\u00f0\u00f1\7\31\2\2\u00f1\u00f3\b\21\1\2\u00f2\u00e4\3\2\2\2\u00f2"+
		"\u00e6\3\2\2\2\u00f2\u00e8\3\2\2\2\u00f2\u00ea\3\2\2\2\u00f2\u00ec\3\2"+
		"\2\2\u00f2\u00ee\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3!\3\2\2\2\30+\64\67"+
		"EPZejty\177\u008a\u0096\u009f\u00a8\u00b2\u00ba\u00c4\u00d1\u00e0\u00e2"+
		"\u00f2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}