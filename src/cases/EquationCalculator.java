package cases;

import java.util.ArrayList;
import java.util.List;

public class EquationCalculator {
    public static void main(String[] args) {
        String equation = "222 + 3 - 2 * (2 * 5 + 2) * 4";
        List<Lexeme> lexemes = lexAnalyze(equation);
        LexemeBuffer buffer = new LexemeBuffer(lexemes);
        System.out.println(expr(buffer));
    }


    public enum LexemeType {
        LEFT_BRACKET, RIGHT_BRACKET, PLUS, MINUS, DIV, MUL, NUMBER, EOF
    }

    public static class Lexeme {
        LexemeType type;
        String value;

        public Lexeme(LexemeType type, String value) {
            this.type = type;
            this.value = value;
        }

        public Lexeme(LexemeType type, Character value) {
            this.type = type;
            this.value = value.toString();
        }

        @Override
        public String toString() {
            return "Lexeme{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static class LexemeBuffer {
        private int pos;
        private final List<Lexeme> lexemes;

        public LexemeBuffer(List<Lexeme> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexeme next() {
            return lexemes.get(pos++);
        }

        public void back() {
            pos--;
        }

        public int getPos() {
            return pos;
        }
    }

    public static List<Lexeme> lexAnalyze(String text) {
        List<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos < text.length()) {
            char c = text.charAt(pos);
            switch (c) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    pos++;
                    continue;

                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                    continue;

                case '+':
                    lexemes.add(new Lexeme(LexemeType.PLUS, c));
                    pos++;
                    continue;

                case '-':
                    lexemes.add(new Lexeme(LexemeType.MINUS, c));
                    pos++;
                    continue;

                case '*':
                    lexemes.add(new Lexeme(LexemeType.MUL, c));
                    pos++;
                    continue;

                case '/':
                    lexemes.add(new Lexeme(LexemeType.DIV, c));
                    pos++;
                    continue;

                default:
                    if (c <= '9' && c >= '0') {
                        StringBuilder builder = new StringBuilder();
                        do {
                            builder.append(c);
                            pos++;
                            if (pos >= text.length()) {
                                break;
                            }
                            c = text.charAt(pos);
                        } while (c <= '9' && c >= '0');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, builder.toString()));
                    } else {
                        if (c != ' ') {
                            throw new IllegalArgumentException("Unexpected character: " + c);
                        }
                        pos++;
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    public static int expr(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.EOF) return 0;
        else {
    lexemes.back();
    return plusMinus(lexemes);
        }
    }

    public static int plusMinus(LexemeBuffer lexemes) {
        int value = multDiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case PLUS:
                    value += multDiv(lexemes);
                    break;
                case MINUS:
                    value -= multDiv(lexemes);
                default:
                    lexemes.back();
                    return value;
            }
        }
    }

    public static int multDiv(LexemeBuffer lexemes) {
        int value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case MUL:
                    value *= factor(lexemes);
                    break;
                case DIV:
                    value /= factor(lexemes);
                default:
                    lexemes.back();
                    return value;
            }
        }
    }

    public static int factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();

        switch (lexeme.type) {
            case NUMBER:
                return Integer.parseInt(lexeme.value);

            case LEFT_BRACKET:
                int value = expr(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new IllegalArgumentException("Unexpected token: " + lexeme.value +
                            " at position :" + lexemes.getPos());
                }
                return value;

            default:
                throw new IllegalArgumentException("Unexpected token: " + lexeme.value +
                        " at position :" + lexemes.getPos());
        }

    }
}