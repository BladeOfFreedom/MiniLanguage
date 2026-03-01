package LexParse;

public class Token {
    private TokenType tokenType;
    private String value;

    public Token(String value, TokenType tokenType) {
        this.value = value;
        this.tokenType = tokenType;
    }

    public TokenType getTokenType(){
        return tokenType;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "LexParse.Token{" +
                "tokenType=" + tokenType +
                ", value='" + value + '\'' +
                '}';
    }
}
