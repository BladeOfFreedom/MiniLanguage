package LexParse;

import LexParse.Statements.Statement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Executor {
    public static List<Statement> executeInput(){
        File file;
        try {
            //handle the input string
            file = new File("input.txt");
            Scanner inp = new Scanner(file);
            StringBuilder inputString = new StringBuilder();
            while(inp.hasNextLine()){
                inputString.append(inp.nextLine()).append(" ");
            }
            //System.out.println(inputString);

            //input string ----To tokens
            List<Token> tokens = Lexer.tokenize(inputString.toString());
            //parse the tokens to statements
            Parser parser = new Parser(tokens);
            List<Statement> parsedStatements = parser.parse();

            //return the ready to execute statements
            return parsedStatements;
            //execute each statement one by one
            /*for(Statement s: parsedStatements){
                s.execute();
            }*/

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
