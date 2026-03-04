package LexParse;

import java.util.*;

public class Lexer {

    public static HashMap<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("END", TokenType.END);
        keywords.put("MOVE", TokenType.MOVE);
        keywords.put("REPEAT", TokenType.REPEAT);
        keywords.put("JUMP", TokenType.JUMP);
        keywords.put("TURN", TokenType.TURN);
        keywords.put("{", TokenType.L_BRACE);
        keywords.put("}", TokenType.R_BRACE);
        keywords.put("(", TokenType.L_PARENTHESES);
        keywords.put(")", TokenType.R_PARENTHESES);

    }

    public static List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        // Flag for end statement
        boolean endStatementSeen = false;

        int cursor = 0;
        while(cursor < input.length()){
            char c = input.charAt(cursor);

            if(Character.isWhitespace(c)){
                cursor++;
                continue;
            }
            else if (endStatementSeen){
                throw new RuntimeException("Statement seen after END!!");
            }
            else if(Character.isLetter(c)){
                // Word mode
                StringBuilder value = new StringBuilder();
                do{
                    value.append(input.charAt(cursor));
                    cursor++;
                }while (Character.isLetter(input.charAt(cursor)));

                if(keywords.containsKey(value.toString().toUpperCase())){
                    // If the token is end then make the flag true and continue the loop to check if there is a statement after end
                    if(value.toString().equalsIgnoreCase("end")){
                        endStatementSeen = true;
                    }
                    tokens.add(new Token(value.toString(), keywords.get(value.toString().toUpperCase())));
                }
                else
                    throw new RuntimeException("No matching token type!! --> " + value);

            }
            else if(Character.isDigit(c)){
                // Number mode
                StringBuilder value = new StringBuilder();
                do{
                    value.append(input.charAt(cursor));
                    cursor++;
                }while (Character.isDigit(input.charAt(cursor)));
                tokens.add(new Token(value.toString(), (TokenType.NUMBER)));
            }

            else if(isValidSymbol(c)){
                // Symbol mode
                StringBuilder value = new StringBuilder();
                value.append(c);
                tokens.add(new Token(value.toString(), keywords.get(value.toString())));
                cursor++;
            }
        }

        if(!endStatementSeen)
            throw new RuntimeException("Input must include END statement at the end");

        System.out.println(printTokens(tokens));
        return tokens;
    }

    public static boolean isValidSymbol(char c){
        return "{}()".indexOf(c) != -1;
    }

    public static String printTokens(List<Token> tokens){
        StringBuilder otp = new StringBuilder();
        for (Token t : tokens){
            otp.append(t.toString()).append("\n");
        }
        return otp.toString();
    }
}