package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {
	
	private LinkedStack<String> operators = new LinkedStack<String>();
	private LinkedStack<Integer> operands  = new LinkedStack<Integer>();
	
	/** return stack object (for testing purpose) */
	public LinkedStack<String> getOperatorStack() { return operators; }
	public LinkedStack<Integer> getOperandStack()  { return operands;  }
	
	
	/** This method performs one step of evaluation of a infix expression.
	 *  The input is a token. Follow the infix evaluation algorithm
	 *  to implement this method. If the expression is invalid, throw an
	 *  exception with the corresponding exception message.
	 */	
	public void evaluate_step(String token) throws Exception {
		if(isOperand(token)) {
			operands.push(Integer.parseInt(token));
		} else if(token.equals("(")) {
			operators.push(token);
		} else if(token.equals(")")) {
			do {
				if(operators.top() == null)
					throw new Exception("missing (");
				processOperator();
			} while(!operators.top().equals("("));
		} else if(operators.isEmpty() || precedence(token)>precedence(operators.top())) {
			operators.push(token);
		} else {
			//repeatedly until precendence condition is met (loop) or any other condition is met (not loop)?
			processOperator();
			operators.push(token);
		}
	}
	/** This method evaluates an infix expression (defined by expr)
	 *  and returns the evaluation result. It throws an Exception object
	 *  if the infix expression is invalid.
	 */
	public Integer evaluate(String expr) throws Exception {

		for(String token : ArithParser.parse(expr)) {
			evaluate_step(token);
		}

		while(!operators.isEmpty())
			processOperator();
		/* TODO: what do we do after all tokens have been processed? */
		
		// The operand stack should have exactly one operand after the evaluation is done
		if(operands.size()>1)
			throw new Exception("too many operands");
		else if(operands.size()<1)
			throw new Exception("too few operands");
		
		return operands.pop();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new InfixEvaluator().evaluate("5+(5+2*(5+9))/!8"));
	}

	private void processOperator() throws Exception {
		String operator = operators.pop();

		if(operator.equals("(") || operator.equals(")"))
			return;

		Integer poppedOne = operands.pop();
		if(operator.equals("!")) {
			if(poppedOne == null)
				throw new Exception("too few operands");
			this.operands.push(-poppedOne);
			return;
		}

		Integer poppedTwo = operands.pop();
		if(poppedTwo == null)
			throw new Exception("too few operands");

		switch(operator) {
			case "+":
				operands.push(poppedTwo+poppedOne);
				break;
			case "-":
				operands.push(poppedTwo-poppedOne);
				break;
			case "*":
				operands.push(poppedTwo*poppedOne);
				break;
			case "/":
				if(poppedOne == 0)
					throw new Exception("division by zero");
				operands.push(poppedTwo/poppedOne);
				break;
			default:
				throw new Exception("invalid operator");
		}
	}
}
