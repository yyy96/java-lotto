package lotto.model;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.utils.LottoNumberGenerator;

class LottosTest {

    @DisplayName("Lottos 객체가 주어졌을 때 unmodifiableList를 반환하는지 검증")
    @Test
    void unmodifiableTest() {
        Lottos lottos = new Lottos(Arrays.asList(new Lotto(LottoNumberGenerator.generate())));
        assertThatThrownBy(() -> lottos.getLottos().add(new Lotto(LottoNumberGenerator.generate())))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Lottos, 당첨번호, bonusNumber가 주어졌을 때 기대한 Rank의 Count가 1이 반환되는지 검증")
    @ParameterizedTest
    @MethodSource("ResultParameter")
    void makeResultTest(Lottos lottos, WinningNumber winningNumber, Rank expected) {
        Map<Rank, Integer> results = lottos.makeResult(winningNumber)
                                           .getRanks();
        assertThat(results.get(expected)).isEqualTo(1);
    }

    private static Stream<Arguments> ResultParameter() {
        List<Number> pickedNumbers = Arrays.asList(new Number(1),
                                                   new Number(2),
                                                   new Number(3),
                                                   new Number(4),
                                                   new Number(5),
                                                   new Number(6));
        Number bonusNumber = new Number(7);

        return Stream.of(Arguments.of(new Lottos(Arrays.asList(new Lotto(pickedNumbers))),
                                      new WinningNumber(new Lotto(Arrays.asList(new Number(1),
                                                                                new Number(2),
                                                                                new Number(3),
                                                                                new Number(4),
                                                                                new Number(5),
                                                                                new Number(6))), bonusNumber),
                                      Rank.FIRST),
                         Arguments.of(new Lottos(Arrays.asList(new Lotto(Arrays.asList(new Number(1),
                                                                                       new Number(2),
                                                                                       new Number(3),
                                                                                       new Number(4),
                                                                                       new Number(5),
                                                                                       new Number(7))))),
                                      new WinningNumber(new Lotto(Arrays.asList(new Number(1),
                                                                                new Number(2),
                                                                                new Number(3),
                                                                                new Number(4),
                                                                                new Number(5),
                                                                                new Number(6))), bonusNumber),
                                      Rank.SECOND),
                         Arguments.of(new Lottos(Arrays.asList(new Lotto(pickedNumbers))),
                                      new WinningNumber(new Lotto(Arrays.asList(new Number(1),
                                                                                new Number(2),
                                                                                new Number(3),
                                                                                new Number(4),
                                                                                new Number(5),
                                                                                new Number(8))), bonusNumber),
                                      Rank.THIRD),
                         Arguments.of(new Lottos(Arrays.asList(new Lotto(pickedNumbers))),
                                      new WinningNumber(new Lotto(Arrays.asList(new Number(1),
                                                                                new Number(2),
                                                                                new Number(3),
                                                                                new Number(4),
                                                                                new Number(9),
                                                                                new Number(10))), bonusNumber),
                                      Rank.FOURTH),
                         Arguments.of(new Lottos(Arrays.asList(new Lotto(pickedNumbers))),
                                      new WinningNumber(new Lotto(Arrays.asList(new Number(1),
                                                                                new Number(2),
                                                                                new Number(3),
                                                                                new Number(8),
                                                                                new Number(9),
                                                                                new Number(10))), bonusNumber),
                                      Rank.FIFTH),
                         Arguments.of(new Lottos(Arrays.asList(new Lotto(pickedNumbers))),
                                      new WinningNumber(new Lotto(Arrays.asList(new Number(36),
                                                                                new Number(22),
                                                                                new Number(15),
                                                                                new Number(17),
                                                                                new Number(26),
                                                                                new Number(44))), bonusNumber),
                                      Rank.MISS));
    }
}