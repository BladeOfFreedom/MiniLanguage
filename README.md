## Description:
A lightweight, recursive-descent interpreter built from scratch in Java. 
This project demonstrates the full lifecycle of a programming language, 

from raw text input to an executable Abstract Syntax Tree (AST).

## Features:

- Custom Lexer: Tokenizes source code into atomic units (Keywords, Identifiers, Numbers, Symbols).

- Recursive Descent Parser: Validates syntax and builds a hierarchical AST, supporting nested blocks.

- Strongly-Typed AST: Utilizes a polymorphic Statement interface to decouple parsing from execution.

- Recursive Loops: Supports the REPEAT command with infinite nesting capability.

- Physics Support: Implemented a physics enginge for rigid body manipulations with the given string

## Example Syntax for the Input.txt file

MOVE 5
REPEAT 4 {
    MOVE 2
    TURN
    JUMP
    REPEAT 2 {
        MOVE 1
    }
}

## Code Snippets
- Lexer:
```java
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
```

- Parser:
```java
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
```
- Player Control:
```java
  //This gets rid of the statements that has nothing to do with the player but manipulates other statements (REPEAT)
    public static List<Statement> parseToPlayer(List<Statement> parsedStatements){
        List<Statement> playerReadyStatements = new ArrayList<Statement>();

        for(Statement s: parsedStatements){
            if(s instanceof RepeatStatement)
                playerReadyStatements.addAll(((RepeatStatement) s).createInnerList());
            else
                playerReadyStatements.add(s);
        }
        return playerReadyStatements;
    }

 public static void executePlayerReadyStatements(List<Statement> playerReadtStatements, Player p){
        Iterator<Statement> it = playerReadtStatements.iterator();
        Statement currentSt = null;

        long updateInterval = 1000000000;
        long lastTime = System.nanoTime();
        long accummulatedTime = 0;
        long currentTime;

        while(it.hasNext()){
            currentTime = System.nanoTime();
            accummulatedTime = currentTime - lastTime;
            lastTime = currentTime;

            while (accummulatedTime >= updateInterval){
                currentSt = it.next();
                executeStatement(currentSt, p);
                accummulatedTime -= updateInterval;
            }
        }
    }

```

## Demo
- Here is a demo for the given script
- move 6 move 0 turn repeat 3 { jump move 20 move 0 turn } descent move 20 move 0 end
![ScriptDemo](https://github.com/user-attachments/assets/da92f6bc-a93f-4725-8fac-c76d6cb70bb3)

## Quick Notes
- Unfortunately the MOVE command changes the players velocity instead of actually moving the player for X amounts, so you have to do MOVE 0 to rest the velocity
or you can give the next MOVE X higher so it cancels out

## Getting Started:

1. Clone the repository.

2. Ensure you have JDK 11+ installed.

3. Place your script in input.txt in the root directory.

4. Run GameScene.Main.java.

## Future Plans
- Planning to add more controls on the player
- Add enemies to attack (maybe, too hard for now)
- Add projectile shooting
- Make new command for making commands instantaneous so it is possible to chain commands faster

