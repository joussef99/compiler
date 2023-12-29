import java.util.Scanner;

public class Syntax {
    private static final String[] KEYWORDS = {"KEY", "BEGIN", "END", "IF", "THEN", "ELSE", "FI"};
    private static final String[] SEPARATORS = {";"};
    private static final String[] BRACES = {"{", "}"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int state = 0;
        String input;

        while (scanner.hasNextLine()) {
            input = scanner.nextLine().trim();

            if (isKeyword(input)) {
                state = processKeyword(input, state);
            } else if (isSeparator(input)) {
                state = processSeparator(state);
            } else if (isBrace(input)) {
                state = processBrace(input, state);
            } else if (isIdentifier(input)) {
                state = processIdentifier(state);
            } else if (isInteger(input)) {
                state = processInteger(state);
            } else {
                System.out.println("Unexpected input: " + input);
            }
        }

        scanner.close();
    }

    private static boolean isKeyword(String input) {
        for (String keyword : KEYWORDS) {
            if (keyword.equals(input)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSeparator(String input) {
        for (String separator : SEPARATORS) {
            if (separator.equals(input)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBrace(String input) {
        for (String brace : BRACES) {
            if (brace.equals(input)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIdentifier(String input) {
        return input.matches("^[a-zA-Z_][a-zA-Z0-9_]*$");
    }

    private static boolean isInteger(String input) {
        return input.matches("^[0-9]+$");
    }

    private static int processKeyword(String input, int state) {
        // Add logic to process the keyword based on the current state
        return state;
    }

    private static int processSeparator(int state) {
        // Add logic to process the separator based on the current state
        return state;
    }

    private static int processBrace(String input, int state) {
        // Add logic to process the brace based on the current state
        return state;
    }

    private static int processIdentifier(int state) {
        // Add logic to process the identifier based on the current state
        return state;
    }

    private static int processInteger(int state) {
        // Add logic to process the integer based on the current state
        return state;
    }
}