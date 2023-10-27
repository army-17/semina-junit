package com.example.testjunit;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;


@Slf4j
public class AnnotationTest {

    @DisplayName("JUnit 기본 Annotation Test")
    @org.junit.Test
    public void JunitAnnotationTest() {


    }

    @DisplayName("Timeout 에러 테스트")
    @Test(timeout = 1)
    public void JunitAnnotationTest2() throws Exception {
        Thread.sleep(100);

    }

    @DisplayName("RuntimeException 오류 예상 테스트")
    @Test(expected = RuntimeException.class)
    public void JunitAnnotationTest3() throws Exception {

        throw new RuntimeException("RuntimeException 발생");

    }

    @DisplayName("NullPointerException 오류 예상 테스트")
    @Test(expected= NullPointerException.class)
    public void JunitAnnotationTest4() throws Exception {

        String str = null;
//        int length = str.length();

    }

    @BeforeClass
    public static void beforeClassTest(){
        System.out.println("@BeforeClass");
    }

    @Before
    public void beforeTest(){
        System.out.println("테스트 메소드 시작 전 @Before");

    }



}
