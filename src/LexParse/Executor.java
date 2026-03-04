package LexParse;

import LexParse.Statements.Statement;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Executor {
    //Returns the list of statements ready to be executed
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

            //return tidied up statements
            return parser.parse();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeStatements(List<Statement> statements){
        //execute each statement one by one
        for(Statement s: statements){
            s.execute();
        }
    }
}
