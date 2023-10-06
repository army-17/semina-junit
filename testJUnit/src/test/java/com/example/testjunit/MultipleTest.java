package com.example.testjunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultipleTest {

    @DisplayName("multiply 테스트")
    @Test
    void multiply() {
        Multiple multiple = new Multiple();
        assertEquals(multiple.multiply(2,10), 20);

    }


    @DisplayName("divide 테스트")
    @Test
    void testDivide() {

        System.out.println("TEST");
        //given

        //when

        //then

    }
}