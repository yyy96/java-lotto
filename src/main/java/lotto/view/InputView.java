package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.LottoBall;
import lotto.domain.Money;

public class InputView {

  private static final String INPUT_MONEY = "구입금액을 입력해 주세요.";
  private static final String INPUT_WIN_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
  private static final String INPUT_BONUS_BALL = "보너스 볼을 입력해 주세요.";
  private final Scanner scanner = new Scanner(System.in);

  public Money inputMoney() {
    System.out.println(INPUT_MONEY);
    int money = scanner.nextInt();
    return new Money(money);
  }

  public List<LottoBall> inputWinNumbers() {
    System.out.println(INPUT_WIN_NUMBERS);
    String winNumberString = scanner.next();
    return Arrays.stream(winNumberString.split(","))
        .map(number -> new LottoBall(Integer.parseInt(number)))
        .collect(Collectors.toList());
  }

  public LottoBall inputBonusBall() {
    System.out.println(INPUT_BONUS_BALL);
    return new LottoBall(scanner.nextInt());
  }
}
