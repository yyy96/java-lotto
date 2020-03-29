package lotto.model.wrapper;

import static lotto.utils.LottoConstant.LOTTO_PRICE;

public class Payment {
    private final Integer money;

    private Payment(final Integer money) {
        this.money = money;
    }

    public static Payment of(final Integer money) {
        if (money < 0) {
            throw new IllegalArgumentException("payment must be greater than zero.");
        }

        return new Payment(money);
    }

    public int countLottoTicket() {
        return money / LOTTO_PRICE;
    }
}