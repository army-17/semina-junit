package com.example.testjunit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class LottoNumberGeneratorTest {

    @DisplayName("로또 번호 갯수 테스트")
    @Test
    void lottoNumberSizeTest(){

        //given
        final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();
        final int price = 1000;

        //when
        final List<Integer> lottoNumber = lottoNumberGenerator.generate(price);

        //then
//        assertEquals(6, lottoNumber.size());
//        assertThat(lottoNumber.size()).isEqualTo(1);
        assertThat(lottoNumber.size()).isSameAs(6);
    }
}
