package com.example.testjunit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class LottoNumberGeneratorTest {

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
//        assertThat(actual, is(equalTo(expected)));



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

        assertThat(expectedNum)
                .isBetween(12, 1280);
    }



}
