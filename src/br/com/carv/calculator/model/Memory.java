package br.com.carv.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Memory 
{
	private static final Memory inst = new Memory();
	
	private enum CommandType {
			ZERAR, NUM, DIV, MULT, SUB, SUM, EQUAL, COMMA;  
	};
	
	private String currentText = "";
	private String textBuffer = "";
	private Boolean subs = false;
	private CommandType lastOperation = null;
	
	private final List<MemoryObserver> observers = new ArrayList<MemoryObserver>();
	
	private Memory()
	{
		
	}
	
	public static Memory getInst()
	{
		return inst;
	}
	
	public String getCurrentText()
	{
		return currentText.isEmpty() ? "0" : currentText;
	}
	
	public void addObserver(MemoryObserver value)
	{
		observers.add(value);
	}
	
	public void processComand(String value)
	{
		CommandType commandType = toDetectCommand(value);
		
		if(commandType == null)
		{
			return;
		}
		
		else if(commandType == CommandType.ZERAR)
		{
			currentText = ""; 
			textBuffer = ""; 
			subs = false; 
			lastOperation = null;
		}
		else if(commandType == CommandType.NUM || commandType == CommandType.COMMA)
		{
			currentText = subs ? value : currentText + value;
			subs = false;
			
		}
		
		else
		{
			subs = true;
			currentText = getResultOperation();
			textBuffer = currentText;
			lastOperation = commandType;
		}
		
		
		
		observers.forEach(o -> o.changedValue(getCurrentText()));
	}
	
	private String getResultOperation() 
	{
		if (lastOperation == null || lastOperation == CommandType.EQUAL)
		{
			return currentText;
		}
		
		double numBuffer  = Double.parseDouble(textBuffer.replace(",", "."));
		double currentNumb = Double.parseDouble(currentText.replace(",", "."));
		
		double result = 0; 
		
		if (lastOperation == CommandType.SUM)
		{
			result = numBuffer + currentNumb;
		}
		else if(lastOperation == CommandType.SUB)
		{
			result = numBuffer - currentNumb;
		}
		else if(lastOperation == CommandType.MULT)
		{
			result = numBuffer * currentNumb;
		}
		else if(lastOperation == CommandType.DIV)
		{
			result = numBuffer / currentNumb;
		}
		
		String resultString = Double.toString(result).replace(",", ".");
		
		boolean resultInteger = resultString.endsWith(",0"); 
		
		return resultInteger ? resultString.replace(",0", "") : resultString;
	}

	private CommandType toDetectCommand(String value)
	{
		if (currentText.isEmpty() && value == "0")
		{
			return null;
		}
		
		try 
		{
			Integer.parseInt(value);
			return CommandType.NUM;
			
		} catch (NumberFormatException e) 
		{
			if("AC".equals(value))
			{
				return CommandType.ZERAR;
			}
			else if("/".equals(value))
			{
				return CommandType.DIV;
			}
			else if("*".equals(value))
			{
				return CommandType.MULT;
			}
			else if("+".equals(value))
			{
				return CommandType.SUM;
			}
			else if("-".equals(value))
			{
				return CommandType.SUB;
			}
			else if("=".equals(value))
			{
				return CommandType.EQUAL;
			}
			else if(",".equals(value) && !currentText.contains(","))
			{
				return CommandType.COMMA;
			}
			
		}
		
		return null;
	}
}
