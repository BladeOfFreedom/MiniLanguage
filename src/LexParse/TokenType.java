package LexParse;

public enum TokenType {
    // Keywords
    MOVE, REPEAT,

    // Literals
    NUMBER,

    // Symbols
    L_BRACE, R_BRACE, L_PARENTHESES, R_PARENTHESES,

    // Termination and Start
    START, END
}
