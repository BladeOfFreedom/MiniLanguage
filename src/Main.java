import LexParse.Lexer;
import LexParse.Parser;
import LexParse.Statements.Statement;
import LexParse.Token;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file = null;
        try {
            file = new File("input.txt");
            Scanner inp = new Scanner(file);
            String inputString = "";
            while(inp.hasNextLine()){
                inputString += inp.nextLine() + " ";
            }
            //System.out.println(inputString);

            List<Token> tokens = Lexer.tokenize(inputString);
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