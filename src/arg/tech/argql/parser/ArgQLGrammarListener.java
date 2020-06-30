package arg.tech.argql.parser;
// Generated from ArgQLGrammar.g4 by ANTLR 4.7.2

	import java.util.*;
	import arg.tech.argql.translator.sparql.*;
	import arg.tech.argql.results.*;
	import arg.tech.argql.patterns.*;
	import arg.tech.argql.filters.*;
	import arg.tech.utils.Enums.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ArgQLGrammarParser}.
 */
public interface ArgQLGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(ArgQLGrammarParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(ArgQLGrammarParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#returnValue}.
	 * @param ctx the parse tree
	 */
	void enterReturnValue(ArgQLGrammarParser.ReturnValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#returnValue}.
	 * @param ctx the parse tree
	 */
	void exitReturnValue(ArgQLGrammarParser.ReturnValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#dialogue_pattern}.
	 * @param ctx the parse tree
	 */
	void enterDialogue_pattern(ArgQLGrammarParser.Dialogue_patternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#dialogue_pattern}.
	 * @param ctx the parse tree
	 */
	void exitDialogue_pattern(ArgQLGrammarParser.Dialogue_patternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#argpattern}.
	 * @param ctx the parse tree
	 */
	void enterArgpattern(ArgQLGrammarParser.ArgpatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#argpattern}.
	 * @param ctx the parse tree
	 */
	void exitArgpattern(ArgQLGrammarParser.ArgpatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#premisePattern}.
	 * @param ctx the parse tree
	 */
	void enterPremisePattern(ArgQLGrammarParser.PremisePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#premisePattern}.
	 * @param ctx the parse tree
	 */
	void exitPremisePattern(ArgQLGrammarParser.PremisePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#conclusionPattern}.
	 * @param ctx the parse tree
	 */
	void enterConclusionPattern(ArgQLGrammarParser.ConclusionPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#conclusionPattern}.
	 * @param ctx the parse tree
	 */
	void exitConclusionPattern(ArgQLGrammarParser.ConclusionPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#argFilters}.
	 * @param ctx the parse tree
	 */
	void enterArgFilters(ArgQLGrammarParser.ArgFiltersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#argFilters}.
	 * @param ctx the parse tree
	 */
	void exitArgFilters(ArgQLGrammarParser.ArgFiltersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#af}.
	 * @param ctx the parse tree
	 */
	void enterAf(ArgQLGrammarParser.AfContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#af}.
	 * @param ctx the parse tree
	 */
	void exitAf(ArgQLGrammarParser.AfContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#metadata}.
	 * @param ctx the parse tree
	 */
	void enterMetadata(ArgQLGrammarParser.MetadataContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#metadata}.
	 * @param ctx the parse tree
	 */
	void exitMetadata(ArgQLGrammarParser.MetadataContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#premiseFilters}.
	 * @param ctx the parse tree
	 */
	void enterPremiseFilters(ArgQLGrammarParser.PremiseFiltersContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#premiseFilters}.
	 * @param ctx the parse tree
	 */
	void exitPremiseFilters(ArgQLGrammarParser.PremiseFiltersContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#inclusionFilter}.
	 * @param ctx the parse tree
	 */
	void enterInclusionFilter(ArgQLGrammarParser.InclusionFilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#inclusionFilter}.
	 * @param ctx the parse tree
	 */
	void exitInclusionFilter(ArgQLGrammarParser.InclusionFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#joinFilter}.
	 * @param ctx the parse tree
	 */
	void enterJoinFilter(ArgQLGrammarParser.JoinFilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#joinFilter}.
	 * @param ctx the parse tree
	 */
	void exitJoinFilter(ArgQLGrammarParser.JoinFilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#propositionSet}.
	 * @param ctx the parse tree
	 */
	void enterPropositionSet(ArgQLGrammarParser.PropositionSetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#propositionSet}.
	 * @param ctx the parse tree
	 */
	void exitPropositionSet(ArgQLGrammarParser.PropositionSetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#pathpattern}.
	 * @param ctx the parse tree
	 */
	void enterPathpattern(ArgQLGrammarParser.PathpatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#pathpattern}.
	 * @param ctx the parse tree
	 */
	void exitPathpattern(ArgQLGrammarParser.PathpatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#pp}.
	 * @param ctx the parse tree
	 */
	void enterPp(ArgQLGrammarParser.PpContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#pp}.
	 * @param ctx the parse tree
	 */
	void exitPp(ArgQLGrammarParser.PpContext ctx);
	/**
	 * Enter a parse tree produced by {@link ArgQLGrammarParser#relation}.
	 * @param ctx the parse tree
	 */
	void enterRelation(ArgQLGrammarParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ArgQLGrammarParser#relation}.
	 * @param ctx the parse tree
	 */
	void exitRelation(ArgQLGrammarParser.RelationContext ctx);
}