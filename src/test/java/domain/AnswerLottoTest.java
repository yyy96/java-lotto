package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class AnswerLottoTest {

    @Test
    @DisplayName("정답 개수가 6개 초과일시 Exception 테스트")
    void checkInputNumberCount() {
        assertThatThrownBy(() -> new AnswerLotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)), 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(AnswerLotto.MESSAGE_INPUT_ANSWER_NUMBER_COUNT_OVER);
    }

    @Test
    @DisplayName("정답 범위가 1~45 벗어나면 Exception 테스트")
    void checkNumberRange() {
        assertThatThrownBy(() -> new AnswerLotto(new ArrayList<>(Arrays.asList(0, 2, 3, 4, 5, 99)), 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(AnswerLotto.MESSAGE_INPUT_ANSWER_NUMBER_RANGE_OVER);
    }

    @Test
    @DisplayName("보너스 숫자 범위가 1~45 벗어나면 Exception 테스트")
    void checkBonusNumberRange() {
        assertThatThrownBy(() -> new AnswerLotto(new ArrayList<>(Arrays.asList(0, 2, 3, 4, 5, 6)), 50))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(AnswerLotto.MESSAGE_INPUT_ANSWER_NUMBER_RANGE_OVER);
    }

    @Test
    @DisplayName("로또들을 총 몇 개 맞췄는지 테스트")
    void checkLottoAnswerMatch() {
        //given
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.addLottoTicket(new Lotto(() -> new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6))));
        lottoTickets.addLottoTicket(new Lotto(() -> new ArrayList<>(Arrays.asList(7, 8, 9, 10, 11, 12))));
        AnswerLotto answerLotto = new AnswerLotto(new ArrayList<>(Arrays.asList(1, 2, 3, 7, 8, 9)), 11);

        //when
        Map<Rank, Integer> matchCount = answerLotto.checkLottoAnswer(lottoTickets.getLottoTickets());

        //then
        assertThat(matchCount.getOrDefault(Rank.FIFTH, 0)).isEqualTo(2);
    }

    @Test
    @DisplayName("보너스까지 맞췄는지 테스트")
    void checkLottoBonusMatch() {
        //given
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.addLottoTicket(new Lotto(() -> new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6))));
        lottoTickets.addLottoTicket(new Lotto(() -> new ArrayList<>(Arrays.asList(7, 8, 9, 10, 11, 12))));
        AnswerLotto answerLotto = new AnswerLotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 9)), 6);

        //when
        Map<Rank, Integer> matchCount = answerLotto.checkLottoAnswer(lottoTickets.getLottoTickets());

        //then
        assertThat(matchCount.getOrDefault(Rank.SECOND, 0)).isEqualTo(1);
    }


    @Test
    @DisplayName("로또들을 하나도 못 맞췄을 때 테스트")
    void checkLottoAnswerNonMatch() {
        //given
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.addLottoTicket(new Lotto(() -> new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6))));
        lottoTickets.addLottoTicket(new Lotto(() -> new ArrayList<>(Arrays.asList(7, 8, 9, 10, 11, 12))));
        AnswerLotto answerLotto = new AnswerLotto(new ArrayList<>(Arrays.asList(13, 14, 15, 16, 17, 18)), 12);

        //when
        Map<Rank, Integer> matchCount = answerLotto.checkLottoAnswer(lottoTickets.getLottoTickets());

        //then
        assertThat(matchCount.getOrDefault(Rank.FAIL, 0)).isEqualTo(2);
    }
}