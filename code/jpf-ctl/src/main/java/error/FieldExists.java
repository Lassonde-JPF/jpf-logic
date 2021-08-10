package error;

import org.ctl.CTLBaseListener;
import org.ctl.CTLParser;


public class FieldExists extends CTLBaseListener {
	
	/**
	 * This method verifies if the class/field name does exist in the package
	 * If the fields does not exists, it prints the error messages on the console.
	 * 
	 * @param ctx - CTL Parser Atomic Proposition 
	 */
	@Override
	public void enterAtomicProposition(CTLParser.AtomicPropositionContext ctx) {
		
		//Determine the class and field name from the input atomic Proposition
		String atomicProposition = ctx.getText();
		int indexOfLastDot = atomicProposition.lastIndexOf(".");
		String className = atomicProposition.substring(0, indexOfLastDot);
		String fieldName = atomicProposition.substring(indexOfLastDot + 1);

		String inputFormula = ctx.getStart().getTokenSource().getInputStream().toString();
		String[] lines = inputFormula.split("\r\n|\r|\n");
		int lineNum = ctx.getStart().getLine();
		int charPositionInLine = ctx.getStart().getCharPositionInLine();
		
		try 
		{
			Class.forName(className).getDeclaredField(fieldName);
		} 
		catch (ClassNotFoundException e) 
		{			  
			underLineError(lines[lineNum - 1],charPositionInLine,lineNum, " Class '" + className + " ' cannot be found" );
		} 
		catch (NoSuchFieldException | SecurityException e) 
		{
			underLineError(lines[lineNum - 1],charPositionInLine + className.length() + 1, lineNum," Field '" + fieldName + " ' cannot be found" );
		}
	}
	
	/**
	 * This method prints the error messages on the console.
	 * It also underlines the error location in the input.
	 * 
	 * @param errorLine	- input formula that contains error.
	 * @param charPositionInLine - error location in the input.
	 * @param errorMsg - error message.
	 */
	private void underLineError(String inString, int charPositionInLine, int lineNum, String errorMsg )
	{
		StringBuilder errMsg = new StringBuilder();
		
		errMsg.append("\nline "+ lineNum +":"+ (charPositionInLine + 1) + errorMsg + "\n");
		errMsg.append(inString+ "\n");
		//To underlines the error location
		for(int i=0; i<charPositionInLine; i++)
			errMsg.append(" ");
		
		errMsg.append("^"+ "\n");
		
		System.err.print(errMsg.toString() );
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
