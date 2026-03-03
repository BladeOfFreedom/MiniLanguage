Description:
A lightweight, recursive-descent interpreter built from scratch in Java. 
This project demonstrates the full lifecycle of a programming language, 

from raw text input to an executable Abstract Syntax Tree (AST).

Features:

-Custom Lexer: Tokenizes source code into atomic units (Keywords, Identifiers, Numbers, Symbols).

-Recursive Descent Parser: Validates syntax and builds a hierarchical AST, supporting nested blocks.

-Strongly-Typed AST: Utilizes a polymorphic Statement interface to decouple parsing from execution.

-Recursive Loops: Supports the REPEAT command with infinite nesting capability.


Example Syntax for the Input.txt file

MOVE 50
REPEAT 4 {
    MOVE 25
    REPEAT 2 {
        MOVE 10
    }
}

Getting Started:

1.Clone the repository.

2.Ensure you have JDK 11+ installed.

3.Place your script in input.txt in the root directory.

4.Run GameScene.Main.java.



