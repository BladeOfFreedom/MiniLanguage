package LexParse;

import java.util.*;

public class Lexer {

    public static HashMap<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("MOVE", TokenType.MOVE);
        keywords.put("REPEAT", TokenType.REPEAT);
        keywords.put("START", TokenType.START);
        keywords.put("END", TokenType.END);
        keywords.put("{", TokenType.L_BRACE);
        keywords.put("}", TokenType.R_BRACE);
        keywords.put("(", TokenType.L_PARENTHESES);
        keywords.put(")", TokenType.R_PARENTHESES);

    }

    public static List<Token> tokenize(String input) {
        Scanner inp = new Scanner(input);
        List<Token> tokens = new ArrayList<>();

        int cursor = 0;
        while(cursor < input.length()){
            char c = input.charAt(cursor);

            if(Character.isWhitespace(c)){
                cursor++;
                continue;
            }
            else if(Character.isLetter(c)){
                // Word mode
                StringBuilder value = new StringBuilder();
                do{
                    value.append(input.charAt(cursor));
                    cursor++;
                }while (Character.isLetter(input.charAt(cursor)));
                tokens.add(new Token(value.toString(), keywords.get(value.toString().toUpperCase())));
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

        System.out.println(printTokens(tokens));
        return tokens;
    }

    public static boolean isValidSymbol(char c){
        return "{}()".indexOf(c) != -1;
    }

    public static String printTokens(List<Token> tokens){
        String otp = "";
        for (Token t : tokens){
            otp += t.toString() + "\n";
        }
        return otp;
    }
}