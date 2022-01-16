package view;

import domain.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    Scanner scanner = new Scanner(System.in);
    private static final String MESSAGE_INPUT_PURCHASE_PRICE = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_INPUT_ANSWER_NUMBER = "지난 주 당첨 번호를 입력해주세요.";
    public static final String MESSAGE_INPUT_ANSWER_NUMBER_COUNT_OVER = "[오류] 숫자를 6개 이상 입력하셨습니다.";
    public static final String MESSAGE_INPUT_ANSWER_NUMBER_RANGE_OVER = "[오류] 숫자의 범위를 넘어갔습니다.";
    private static final String COMMA = ",";

    public int inputPurchasePrice() {
        System.out.println(MESSAGE_INPUT_PURCHASE_PRICE);
        return scanner.nextInt();
    }

    public List<Integer> inputAnswerNumber() {
        System.out.println("\n" + MESSAGE_INPUT_ANSWER_NUMBER);
        List<Integer> answerNumbers = splitAnswerNumber(scanner.next());
        overInputNumberCount(answerNumbers);
        exceedNumberRange(answerNumbers);
        return answerNumbers;
    }

    public void overInputNumberCount(List<Integer> answerNumbers) {
        if (answerNumbers.size() > LottoNumber.COUNT_LOTTO_NUMBER) {
            throw new IllegalArgumentException(MESSAGE_INPUT_ANSWER_NUMBER_COUNT_OVER);
        }
    }

    public void exceedNumberRange(List<Integer> answerNumbers) {
        if (answerNumbers.stream()
                .anyMatch(number -> number > LottoNumber.END_LOTTO_NUMBER || number < LottoNumber.START_LOTTO_NUMBER)) {
            throw new IllegalArgumentException(MESSAGE_INPUT_ANSWER_NUMBER_RANGE_OVER);
        }
    }

    public List<Integer> splitAnswerNumber(String answerNumbers) {
        return Arrays.stream(answerNumbers.split(COMMA))
                .map(stringNumber -> Integer.parseInt(stringNumber.trim()))
                .collect(Collectors.toList());
    }
}
