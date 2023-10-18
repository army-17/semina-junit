package com.example.testjunit;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.*;


import java.util.List;

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



        assertThat(actual)
                .isAlphabetic()
                .isEqualTo(expected)
                .isASCII()
                .isLowerCase()
                .isNotEmpty();

        // 코드 실행 시 오류 발생하면 테스트 멈추게 된다.
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
        assertThat(2*4, equalTo(8));


    }

    // 사용자 정의 매처 : isEven()
    public static Matcher<Integer> isEven(){
        return new TypeSafeMatcher<Integer>() {
            @Override
            protected boolean matchesSafely(Integer number) {
                return number % 2 == 0;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("an even number");
            }
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


}
