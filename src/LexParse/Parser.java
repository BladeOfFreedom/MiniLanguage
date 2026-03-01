package LexParse;
import LexParse.Statements.MoveStatement;
import LexParse.Statements.RepeatStatement;
import LexParse.Statements.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {
    private static List<Token> tokens;
    private int pointer = 0;
    private static Stack<Integer> braceStack = new Stack<>();



    public Parser(List<Token> tokens){
        Parser.tokens = tokens;
    }

    public List<Statement> parse(){
        List<Statement> statements = new ArrayList<>();
        while (pointer < tokens.toArray().length){
            //Get the current token
            Token currentToken = peek();
            //Advance if there is a token
            if(currentToken != null){
                switch (currentToken.getTokenType()){
                    case TokenType.MOVE:
                        statements.add(addMoveStatement());
                        break;

                    case TokenType.REPEAT:
                        statements.add(addRepeatStatement());
                        break;

                    case TokenType.R_BRACE:
                        if(!braceStack.empty()){
                            braceStack.pop();
                            advance();
                            return statements;
                        }
                        //else
                          //  throw new RuntimeException("End Paranthesis Missing!");
                }

            }
        }
        return statements;
    }

    private Statement addMoveStatement(){
        //Skip the move token
        advance();
        //Get the number token
        Token currentToken = peek();
        //Validate the token
        if(currentToken == null || currentToken.getTokenType() != TokenType.NUMBER)
            throw new RuntimeException("Input must be NUMBER after MOVE statement! OR null Pointer!");
        //Skip the number token
        advance();
        return new MoveStatement(Integer.parseInt(currentToken.getValue()));
    }

    private Statement addRepeatStatement(){
        //Skip repeat token
        advance();
        //Get the new token (must be number)
        Token currentToken = peek();
        //Validate the new token
        if(currentToken == null || currentToken.getTokenType() != TokenType.NUMBER)
            throw new RuntimeException("Input must be NUMBER after REPEAT statement! OR null Pointer!");
        //Get the value of the number token
        int count = Integer.parseInt(currentToken.getValue());
        //Skip the number token
        advance();
        //Get the new token (must be braces)
        currentToken = peek();
        //Validate the token
        if(currentToken == null || currentToken.getTokenType() != TokenType.L_BRACE)
            throw new RuntimeException("BRACES missing! OR null Pointer!");
        //Push the brace into the stack to know the matching right left braces
        braceStack.push(1);
        //skip the brace token
        advance();
        //Create the correct statement
        return new RepeatStatement(count, parse());
    }

    private Token peek(){
        if(pointer >= tokens.toArray().length) return null;
        return tokens.get(pointer);
    }

    private void advance(){
        pointer++;
    }

}
