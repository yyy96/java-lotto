package lotto.domain;

import java.util.List;

public final class LottoGame {

  private final List<LottoBalls> lottoBallsList;

  public LottoGame(final List<LottoBalls> lottoBallsList) {
    this.lottoBallsList = lottoBallsList;
  }

  public int size() {
    return lottoBallsList.size();
  }

  public Statistics selectPrizeWinning(WinningBall winBalls) {
    Statistics statistics = new Statistics();
    for (LottoBalls lottoBalls : lottoBallsList) {
      Ranking ranking = winBalls.calculateRank(lottoBalls);
      statistics.recordRanking(ranking);
    }
    return statistics;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (LottoBalls lottoBalls : lottoBallsList) {
      builder.append("[");
      builder.append(lottoBalls.toString());
      builder.append("]");
      builder.append("\n");
    }
    return builder.toString();
  }

}
