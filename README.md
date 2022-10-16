<br>

## 🎡 로또 서비스
<br>

### 💻 프로젝트의 목표와 성과
#### 목표
- 모든 기능을 단위 테스트를 작성하여 TDD로 구현한다.
- 객체지향적 설계를 지키기 위하여 일급 콜렉션으로 설계한다.
- java enum을 적용해 프로그래밍을 구현한다.
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.

#### 성과
- Lotto의 필요한 로직들을 일급 컬렉션으로 만들어 관리하므로써 객체지향적 설계 역량을 향상시킬 수 있었다. <br>
  [↳ 일급 컬렉션에 관한 블로그 포스팅](https://velog.io/@yyy96/FirstClassCollection)
  
- 전략 패턴을 사용하여 테스트 코드를 유연하게 만들며 디자인 패턴에 대한 중요성을 깨달았다. <br>
  [↳ 전략 패턴에 관한 블로그 포스팅](https://velog.io/@yyy96/FirstClassCollection)
<br>
<br>

### [💵 자동화 version 로또 서비스](https://github.com/yyy96/oop-lotto-service/tree/yeon)
- 로또 구입 금액을 입력하면 로또를 자동으로 랜덤 발급해준다.
- 지난 주 당첨 번호를 입력하면 현재 몇 개의 로또가 당첨되었는지 알려준다.
<br>

### [💵 수동화 version 로또 서비스](https://github.com/yyy96/oop-lotto-service/tree/lotto3)
- 로또 구입 금액과 구입할 로또의 개수를 입력하고, 직접 구입할 로또의 번호를 지정하여 로또를 구입한다.
- 지난 주 당첨 번호와 '보너스 번호'를 입력하면 '수익률'과 현재 몇 개의 로또가 당첨되었는지 알려준다.
<br>
<br>

### [📝 코드 리뷰 및 리팩터링 과정](https://github.com/next-step/java-lotto/pulls?q=is%3Apr+is%3Aclosed+author%3Ayyy96)
![image](https://user-images.githubusercontent.com/65826145/196030991-45ef6f92-786a-44bb-9994-76d8c0e164df.png)


#### 📖 로또 기능 요구사항

<details>
<summary>자세한 로또 기능 요구사항 정리</summary>
<div markdown="1">       

<br>

1. 구입 금액을 입력받는다 `(input_view)`
2. **(추가기능)** 수동으로 구입할 로또 수를 입력받는다 `(input_view)`
3. 로또 1장당 가격은 1000원이다. `(lottoPrice)`
4. **(추가기능)** 구입 금액보다 구매할 로또 수가 많은 경우  *Exception* 발생 `(lottoPrice)`
5. **(추가기능)** 수동으로 구매할 로또 번호들을 입력받는다 `(input_view)`
6. **(추가기능)** 수동으로 구매한 로또 번호들이 1~45 사이의 범위를 벗어날 경우 *Exception* 발생  `(lotto)`
7. **(추가기능)** 구입금액에서 수동으로 구입한 로또를 제외한 금액의 액수만큼 자동의 로또가 추가적으로 발급된다. 
8. 자동 로또의 숫자 범위를 Collections.shuffle( ) 메소드를 통해 섞는다. `(lottoGenerator)`
9. 자동 로또 1장당 총 6개의 숫자를 발급받는다. `(lottoGenerator)`
10. 자동 로또의 숫자들은 Collection.sort( ) 메소드를 통해 정렬시킨다. `(lottoGenerator)`
11. **(추가기능)** 자동으로 구매한 로또들의 범위가 벗어날 경우 *Exception* 발생 `(lotto)`
12. 수동 및 자동으로 구매한 로또들을 보여준다 `(print_view)`
13. 지난 주 당첨 번호 숫자를 ',' 기준으로 6개를 String으로 입력받는다 `(input_view)`
14. 입력받은 당첨번호 String을 ','를 기준으로 split 하여 List에 담아둔다. `(input_view)`
15. 보너스 볼을 입력받는다.  `(input_view)` 
16. 만약 범위 안의 숫자를 입력하지 않으면 *Exception* 발생 `(lottoAnswer)`
17. 보너스 볼의 숫자 범위를 확인하고 아닐 경우 *Exception* 발생 `(lottoAnswer)`
18. 모든 로또를 돌며 1장의 로또에서 당첨 번호가 몇 개가 일치하는지를 확인한다 `(lotto)`
19. 5개를 일치하였을 경우, 보너스 볼이 일치 하였는지 확인한다 `(lottoAnswer)`
20. 로또의 당첨 번호 개수를 업데이트 해준다 `(lotto)`
21. 3~6개(+보너스 추가 일치) 일치한 로또들의 개수를 출력한다. `(print_view)`
22. 3~6개(+보너스 추가 일치) 일치한 로또들의 당첨 금액을 계산한다. `(lottoTickets)`
23. 당첨 금액 / 구입 금액 으로 총 수익률을 계산한다. `(lottoTickets)`
24. 총 수익률을 출력한다. `(print_view)`

<br>

</div>
</details>
