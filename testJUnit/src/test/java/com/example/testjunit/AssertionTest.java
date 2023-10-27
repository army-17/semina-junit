package com.example.testjunit;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

public class AssertionTest {

    @DisplayName("로또 번호 갯수 테스트 1")
    @Test
    void lottoNumberSizeTest(){

        System.out.println("로또 번호 갯수 테스트 1");
        //given
        final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        final int price = 1000;

        //when
        final List<Integer> lottoNumber = lottoNumberGenerator.generate(price);

        //then
//        assertEquals(6, lottoNumber.size());
//        assertThat(lottoNumber.size()).isEqualTo(1);

        int lottoSize = lottoNumber.size();
//        assertTrue(isRightLottoSize(lottoSize, 9), "lotto 갯수 잘못 출력됨");
        assertEquals(2,2, "두 개의 값은 다르다.");

    }

    @DisplayName("로또 번호 갯수 테스트 2")
    @Test
    void lottoNumberSizeTest2(){
        System.out.println("로또 번호 갯수 테스트 2");

        //given
        final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        final int price = 1000;

        //when
        final List<Integer> lottoNumber = lottoNumberGenerator.generate(price);

        //then
//        assertEquals(6, lottoNumber.size());
//        assertThat(lottoNumber.size()).isEqualTo(1);

        int lottoSize2 = lottoNumber.size();
        assertTrue(isRightLottoSize(lottoSize2), "lotto 갯수 잘못 출력됨");

    }

    private boolean isRightLottoSize(int num1) {

        if(num1 == 6){
            return true;

        } else {
            return false;
        }
    }

    // *** @BeforeEach와 @AfterEach
    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach");
    }
    
    @BeforeAll
    public static void beforeAll() {
        System.out.println("*** beforeAll **"); 
    }
    
    @AfterAll
    public static void afterAll() {
        System.out.println("*** afterAll *** ");
    }

    @Disabled("Not implemented yet")
    @Test
    public void testDisabled(){
        System.out.println("@Disabled 테스트");
    }

    @Test
    @DisplayName("assertAll 테스트")
    public void testAssertAll(){
        int[] numbers = {1,2,3,4,5,6,7,8,9};
        assertAll("numbers",
                () -> assertEquals(numbers[0], 8),
                () -> assertEquals(numbers[1], 5),
                ()  -> assertEquals(numbers[2], 6)
                );

    }

    @Test
    @DisplayName("assertThat() 테스트")
    public void testAssertThat(){

        String expected = "Hello, World";
        String actual = "hello world23433";
//
        assertThat(actual, is(equalTo(expected)));


        // assertJ 라이브러리의 전통적인 방식
//        assertThat(actual)
//                .isAlphabetic()
//                .isEqualTo(expected)
//                .isASCII()
//                .isLowerCase()
//                .isNotEmpty();

        // 코드 실행 시 오류 발생하면 테스트 멈추게 된다.

        // Hamcrest 방식
        assertThat(actual, equalTo(expected));
    }

    @Test
    @DisplayName("assertThat() int형 테스트")
    public void testAssertThatInt(){

        int expectedNum = 1234;

//        assertThat(expectedNum)
//                .isBetween(12, 1280);
    }

    // 라이브러리 AssertJ vs Hamcrest
    @Test
    @DisplayName("Hamcrest 테스트")
    public void testHamcrest(){

        // AssertJ 라이브러리에서 주로 사용되는 구문
        assertThat(10)
                .isEqualTo(20);

        // Hamcrest 라이브러리에서 주로 사용되는 구문 : 매치어를 기반으로 하는 테스트 라이브러리로 강력한 맞춤 검사를 지원
        // operand (피연산자) : 처리되어야 하는 데이터
        assertThat(2*4, equalTo(8));


    }

    // 사용자 정의 매처 : isEven()
    public static Matcher<Integer> isEven(){
        return new TypeSafeMatcher<Integer>() {
            @Override
            protected boolean matchesSafely(Integer number) {
                return number % 2 == 0;
            } // true 반환 시 매칭 성공

            @Override
            public void describeTo(Description description) {
                description.appendText("a even number");
            } // matcher가 실패했을 때 실패 이유를 설명하는 데에 사용 (기대 결과 설명)
        };
    }

    @Test
    @DisplayName("사용자 정의 매처 Test")
    public void testCustomizingMatcher(){
        int evenNumber = 4;
        int oddNumber = 7;

        assertThat(evenNumber, isEven());
        assertThat(oddNumber, isEven());

    }

    // 사용자 정의 매처2 : resultCode가 0인지 아닌지 판단
    public static Matcher<String> isCorrectResponse(){

        return new TypeSafeMatcher<String>() {
            @Override
            protected boolean matchesSafely(String resultCode) {
                return "0".equals(resultCode);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("resultCode : 0");
            }
        };

    }

    @Test
    @DisplayName("사용자 정의 매처 Test2")
    public void testCustomizingMatcher2(){
        String resultCode = "0";

        assertThat(resultCode, isCorrectResponse());
//        assertEquals(0, resultCode);

    }

    @Test
    public void testPositiveNumber() {

        int number = 0;

        assertThat("숫자 크기 테스트", number, is(greaterThan(0)));
        assertThat(number, is(greaterThan(0)));

    }
    

}
