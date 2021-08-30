package string_calculator;

import java.util.Arrays;

public final class StringCalculator {
    private static final int INIT_NUMBER = 0;
    private static final PatternRegex PATTERN_REGEX = new PatternRegex();

    private static StringCalculator stringCalculator = null;

    private StringCalculator(){}

    public static StringCalculator getCalculator(){
        if(stringCalculator == null){
            stringCalculator = new StringCalculator();
        }
        return stringCalculator;
    }

    public boolean empty(final String expression) {
        return expression == null || expression.isEmpty();
    }

    public void validate(final int operand) {
        if(operand < INIT_NUMBER){
            throw new IllegalArgumentException("양수값만 입력해주세요.");
        }
    }

    public int calculate(final String expression){
        if(empty(expression)) return INIT_NUMBER;

        return Arrays.stream(split(expression))
                .map(number -> {
                    int operand = convert(number);
                    validate(operand);
                    return operand;
                }).reduce(0, Integer::sum);
    }

    private String[] split(String expression){
        return PATTERN_REGEX.split(expression);
    }

    private int convert(final String number){
        return Integer.parseInt(number);
    }

}