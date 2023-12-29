
package compiler.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LexicalAnalyzer {

    private final String input;
    private final Map<String, TokenType> reservedKeywords;
    private int index;
    private int lineCount;

    public LexicalAnalyzer(String input) {
        this.input = input;
        this.reservedKeywords = new HashMap<>();
        initializeReservedKeywords();
    }

    public List<Token> analyze() {
        List<Token> tokens = new ArrayList<>();
        while (index < input.length()) {
            skipWhiteSpaces();
            if (index == input.length()) {
                break;
            }
            char currentChar = input.charAt(index);
            Token token = null;
            if (Character.isLetter(currentChar)) {
                token = lexIdentifierOrKeyword();
            } else if (Character.isDigit(currentChar)) {
                token = lexNumericLiteral();
            } else if (currentChar == '+') {
                token = new Token(TokenType.PLUS, index++);
            } else if (currentChar == '-') {
                token = new Token(TokenType.MINUS, index++);
            } else if (currentChar == '*') {
                token = new Token(TokenType.STAR, index++);
            } else if (currentChar == '/') {
                token = new Token(TokenType.SLASH, index++);
            } else if (currentChar == '(') {
                token = new Token(TokenType.LEFT_PAREN, index++);
            } else if (currentChar == ')') {
                token = new Token(TokenType.RIGHT_PAREN, index++);
            } else if (currentChar == '<') {
                token = new Token(TokenType.LESS_THAN, index++);
            } else if (currentChar == '>') {
                token = new Token(TokenType.GREATER_THAN, index++);
            } else if (currentChar == '=') {
                token = new Token(TokenType.EQUAL, index++);
            } else if (currentChar == ',') {
                token = new Token(TokenType.COMMA, index++);
            } else if (currentChar == ';') {
                token = new Token(TokenType.SEMICOLON, index++);
            } else {
                throw new LexicalException("Invalid character: " + currentChar, lineCount);
            }
            tokens.add(token);
        }
        return tokens;
    }

    // Implementation of lexing methods

    private void initializeReservedKeywords() {
        reservedKeywords.put("int", TokenType.INT);
        reservedKeywords.put("float", TokenType.FLOAT);
        reservedKeywords.put("if", TokenType.IF);
        reservedKeywords.put("else", TokenType.ELSE);
        reservedKeywords.put("while", TokenType.WHILE);
        reservedKeywords.put("for", TokenType.FOR);
        reservedKeywords.put("return", TokenType.RETURN);
    }

    private Token lexIdentifierOrKeyword() {
        StringBuilder stringBuilder = new StringBuilder();
        while (index < input.length() && Character.isLetterOrDigit(input.charAt(index))) {
            stringBuilder.append(input.charAt(index++));
        }
        String value = stringBuilder.toString();
        TokenType tokenType = reservedKeywords.getOrDefault(value, TokenType.IDENTIFIER);
        return new Token(tokenType, value, index);
    }

    private Token lexNumericLiteral() {
        StringBuilder stringBuilder = new StringBuilder();
        while (index < input.length() && Character.isDigit(input.charAt(index))) {
            stringBuilder.append(input.charAt(index++));
        }
        if (index < input.length() && input.charAt(index) == '.') {
            stringBuilder.append('.');
            index++;
            while (index < input.length() && Character.isDigit(input.charAt(index))) {
                stringBuilder.append(input.charAt(index++));
            }
        }
        return new Token(TokenType.NUMERIC_LITERAL, stringBuilder.toString(), index);
