package logic.app.proposition;

import java.util.*;

import aima.core.logic.propositional.algorithms.KnowledgeBase;

public class PLCodeProcessor {
	private String delim = ";"; //premises (conclusions) are separated by comma or line-break;
	private String rawPremises = "";
	private String rawConclusions = "";
	
	private LinkedList<PLStatement> premises = new LinkedList<PLStatement>();
	private LinkedList<PLStatement> conclusions = new LinkedList<PLStatement>();
	

	public PLCodeProcessor(String rawPremises, String rawConclusions) {
		breakToStatement(rawPremises, true);
		breakToStatement(rawConclusions, false);
	}
	private void breakToStatement(String str, boolean isPremise){
		String prevToken = "";
		StringTokenizer liner = new StringTokenizer(str, "\n"); 
		while(liner.hasMoreTokens()){
			String  line = liner.nextToken();
			line.trim();
			boolean isEmpty = line.equalsIgnoreCase("");
			if(!isEmpty){
				boolean isComment = line.substring(0,1).equalsIgnoreCase("#");
				if(!isComment){
					int idxStatementEnd = line.indexOf(";");
					if(idxStatementEnd < 0){
						//not found ;
						prevToken = prevToken + " " + line;
						continue; //exam next line
					}
						
					StringTokenizer senner = new StringTokenizer(line, ";"); 
					while(senner.hasMoreTokens()){
						String sen = senner.nextToken();
						sen.trim();
						boolean senEmpty = sen.equalsIgnoreCase("");
						if(!senEmpty){
							boolean endWithComma = line.endsWith(";");
							boolean isLastPart = !senner.hasMoreTokens();
							if(isLastPart && !endWithComma){
								prevToken = prevToken + " " + sen;
								break;
							}
							
							String statementText = prevToken + " " + sen;
							prevToken = "";
							statementText.trim();
							statementText = "( " + statementText + " )";
							if(isPremise)
								premises.add(new PLStatement(statementText));
							else
								conclusions.add(new PLStatement(statementText));
						}
					}//senner
				}//not a comment
			}//not empty
			
		}//linner
		
		if(!prevToken.equalsIgnoreCase("")){
			String statementText = "( " + prevToken + " )";
			if(isPremise)
				premises.add(new PLStatement(statementText));
			else
				conclusions.add(new PLStatement(statementText));
		}
		
	}
	
	public String getPremiseClauseText(){
		String ret = "";
		for(int i=0; i < this.premises.size(); i++){
			PLStatement statement = this.premises.get(i);
			ret += "\n Clauses of \"" + statement.getStatementText() + "\":" + "\n";
			ret += statement.getClauseText();	
		}
		return ret;
	}
	
	public String getProofResults(){
		KnowledgeBase base  = new KnowledgeBase();
		//add to knowledge base
		for(int i=0; i < this.premises.size(); i++){
			PLStatement statement = this.premises.get(i);
			base.tell(statement.getStatementText());
		}
		
		//for each conclusion
		String ret = "";
		for(int i=0; i < this.conclusions.size(); i++){
			PLStatement conclusion = this.conclusions.get(i);
			boolean proved = base.askWithDpll(conclusion.getStatementText());
			
			ret += "Conclusion \"" + conclusion.getStatementText() + "\":";
			ret += "\t" + (proved? "CAN BE PROVED" : "CANNOT BE PROVED") + "\n";
		}
		return ret;
	}
}
