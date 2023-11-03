package com.example.testjunit;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

public class AssertionTest {


    @Test
    @DisplayName("assertTrue() 테스트")
    public void testAssertTrue(){

        int num = 3;

//        Assert.assertTrue(num == 3);
        assertTrue((num < 2), "false 입니다.");

    }

    @Test
    @DisplayName("assertEquals() 테스트")
    public void testAssertEquals(){

        int num = 1;
        assertEquals(12, num);
    }


    @Test
    @DisplayName("assertThat() 테스트")
    public void testAssertThat(){

        String actual = "hello world23433";

        assertThat(actual)
                .isEqualTo("hello world23433")
                .isNotNull()
                .startsWith("hello")
                .endsWith("1");



        /**
        // assertJ 라이브러리의 전통적인 방식
        assertThat(actual)
                .isAlphabetic()
                .isEqualTo(expected)
                .isASCII()
                .isLowerCase()
                .isNotEmpty();


        // Hamcrest 방식
        assertThat(actual, equalTo(expected));

         **/
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

//    @Test
//    @DisplayName("assertAll 테스트")
//    public void testAssertAll(){
//        int[] numbers = {1,2,3,4,5,6,7,8,9};
//        assertAll("numbers",
//                () -> assertEquals(numbers[0], 8),
//                () -> assertEquals(numbers[1], 5),
//                ()  -> assertEquals(numbers[2], 6)
//                );
//
//    }


}
