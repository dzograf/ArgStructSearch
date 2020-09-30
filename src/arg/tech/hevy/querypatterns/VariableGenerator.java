package arg.tech.hevy.querypatterns;

public class VariableGenerator {

	private static int eventCnt = 0;
	private static int textCnt = 0;
	private static int dateCnt = 0;
	private static int iNodeCnt = 0;
	private static int graphCnt = 0;
	private static int typeCnt = 0;
	private static int actorCnt = 0;
	private static int locationCnt = 0;
	private static int involvedCnt = 0;
	private static int illustratesCnt = 0;
	private static int timeCnt = 0;
	
	public static void initializeVariables(){
		eventCnt = 0;
		textCnt = 0;
		dateCnt = 0;
		iNodeCnt = 0;
		graphCnt = 0;
		typeCnt = 0;
		actorCnt = 0;
		locationCnt = 0;
		involvedCnt = 0;
		illustratesCnt = 0;
		timeCnt = 0;
	}
	public static String eventVariable() {
		
		eventCnt++;
		
		return "?ev" + eventCnt;
	}
	
	public static String textVariable() {
		textCnt++;
		
		return "?txt" + textCnt;
	}
	
	public static String dateVar() {
		dateCnt ++;
		
		return "?date" + dateCnt;
	}
	
	public static String iNode() {
		iNodeCnt ++;
		
		return "?i" + iNodeCnt;
	}
	
	public static String graphVar() {
		graphCnt++;
		
		return "?g" + graphCnt;
	}
	
	public static String typeVar() {
		typeCnt++;
		
		return "?type" + typeCnt;
	}
	
	public static String actorVar() {
		actorCnt ++;
		return "?act" + actorCnt;
	}
	
	public static String locationVar() {
		locationCnt ++ ;
		return "?location" + locationCnt;
	}
	
	public static String involvedVar() {
		involvedCnt ++;
		return "?inv" + involvedCnt;
	}
	
	public static String illustratesVar() {
		illustratesCnt++;
		return "?ill" + illustratesCnt;
	}
	
	public static String timeVar() {
		timeCnt ++;
		return "?time" + timeCnt;
	}
}
