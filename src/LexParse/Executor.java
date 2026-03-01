package LexParse;

import LexParse.Statements.Statement;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Executor {
    public static void executeInput(){
        File file;
        try {
            file = new File("input.txt");
            Scanner inp = new Scanner(file);
            StringBuilder inputString = new StringBuilder();
            while(inp.hasNextLine()){
                inputString.append(inp.nextLine()).append(" ");
            }
            //System.out.println(inputString);

            List<Token> tokens = Lexer.tokenize(inputString.toString());
            Parser parser = new Parser(tokens);
            List<Statement> parsedStatements = parser.parse();

            for(Statement s: parsedStatements){
                s.execute();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
