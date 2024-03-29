package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class PostfixEvaluator extends Evaluator {
	
	private LinkedStack<Integer> stack = new LinkedStack<Integer>();
	
	/** return stack object (for testing purpose) */
	public LinkedStack<Integer> getStack() { return stack; }
	
	/** This method performs one step of evaluation of a postfix expression.
	 *  The input is a token. Follow the postfix evaluation algorithm
	 *  to implement this method. If the expression is invalid, throw an
	 *  exception with the corresponding exception message.
	 */
	public void evaluate_step(String token) throws Exception {
		if(isOperand(token)) {
			stack.push(Integer.parseInt(token));
		} else {
			/* TODO: What do we do if the token is an operator?
			   If the expression is invalid, make sure you throw
			   an exception with the correct message
			 */
			// see if you need to check to pop
			if(token.equals("!")) {
				Integer popped = stack.pop();
				if(popped == null)
					throw new Exception("too few operands");
				this.stack.push(-popped);
			} else {
				Integer poppedOne = stack.pop(), poppedTwo = stack.pop();
				if(poppedOne == null || poppedTwo == null)
					throw new Exception("too few operands");
				switch (token) {
					case "+":
						stack.push(poppedOne+poppedTwo);
						break;
					case "-":
						stack.push(poppedTwo-poppedOne);
						break;
					case "*":
						stack.push(poppedOne*poppedTwo);
						break;
					case "/":
						if(poppedOne == 0)
							throw new Exception("division by zero");
						stack.push(poppedTwo/poppedOne);
						break;
					default:
						throw new Exception("invalid operator");
				}
			}
		}		
	}
	/** This method evaluates a postfix expression (defined by expr)
	 *  and returns the evaluation result. It throws an Exception
	 *  if the postfix expression is invalid.
	 */
	public Integer evaluate(String expr) throws Exception {

		String[] arr =  ArithParser.parse(expr);
		for(String token : arr) {
			evaluate_step(token);
		}
		// The stack should have exactly one operand after evaluation is done
		if(stack.size()>1) {
			throw new Exception("too many operands");
		} else if (stack.size()<1) {
			throw new Exception("too few operands");
		}
		return stack.pop(); 
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new PostfixEvaluator().evaluate("50 25 ! / 3 +"));
	}
}