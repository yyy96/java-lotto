package lotto.domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoNumbers {
    static final int LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public static LottoNumbers of(List<LottoNumber> lottoNumbers) {
        return new LottoNumbers(lottoNumbers);
    }

    public static LottoNumbers of(int... lottoNumbers) {
        return new LottoNumbers(Arrays.stream(lottoNumbers)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList()));
    }

    private LottoNumbers(List<LottoNumber> lottoNumbers) {
        verifyDuplicate(lottoNumbers);
        verifySize(lottoNumbers);
        this.lottoNumbers = Collections.unmodifiableList(lottoNumbers);
    }

    private void verifySize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개만 가능합니다.");
        }
    }

    private void verifyDuplicate(List<LottoNumber> lottoNumbers) {
        HashSet<LottoNumber> nonDuplicatedLottoNumbers = new HashSet<>(lottoNumbers);
        if (lottoNumbers.size() != nonDuplicatedLottoNumbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복 될 수 없습니다.");
        }
    }

    boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    int match(LottoNumbers anotherLottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(anotherLottoNumbers::contains)
                .count();
    }

    List<Integer> getLottoNumbers() {
        return lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumbers)) return false;
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}