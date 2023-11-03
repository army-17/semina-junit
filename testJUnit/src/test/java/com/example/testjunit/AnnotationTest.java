package com.example.testjunit;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;


@Slf4j
public class AnnotationTest {

    @Test
    @DisplayName("JUnit 기본 Annotation Test")
    public void JunitAnnotationTest1() {
        log.info("@DisplayName 확인");

    }


    @Test(timeout = 1)
    @DisplayName("Timeout 에러 테스트")
    public void JunitAnnotationTest2() throws Exception {
        Thread.sleep(100);

    }


    @Test(expected = RuntimeException.class)
    @DisplayName("RuntimeException 오류 예상 테스트")
    public void JunitAnnotationTest3() throws Exception {

        throw new RuntimeException("RuntimeException 발생");

    }


    @Test(expected= NullPointerException.class)
    @DisplayName("NullPointerException 오류 예상 테스트")
    public void JunitAnnotationTest4() throws Exception {

        String str = null;
        int length = str.length();

    }

    /******************************************/
    /*******  @BeforeClass 와 @Before  *******/

//    @BeforeClass
//    public static void beforeClassTest(){
//        System.out.println("@BeforeClass");
//    }
//
//    @AfterClass
//    public static void afterClassTest(){
//        System.out.println("@AfterClass");
//    }

    @Before
    public void beforeTest(){
        System.out.println("각 메소드 시작 전 @Before");

    }

    @After
    public void afterTest(){
        System.out.println("각 메소드 시작 후 @After");
    }


}
