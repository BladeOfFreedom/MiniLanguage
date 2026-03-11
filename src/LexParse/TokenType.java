package LexParse;

public enum TokenType {
    // Keywords
    MOVE, REPEAT, JUMP, TURN, DESCENT,

    // Literals
    NUMBER,

    // Symbols
    L_BRACE, R_BRACE, L_PARENTHESES, R_PARENTHESES,

    // Termination
    END

}
