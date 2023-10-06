package com.example.testjunit;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LottoNumberGenerator {

    public List<Integer> generate(final int money) {
        if(money != 1000){
            throw new RuntimeException("올바른 금액이 아닙니다.");

        }

        return generate();
    }

    private List<Integer> generate() {
        return new Random()
                .ints(1, 45+1)
                .distinct()
                .limit(6)
                .boxed()
                .collect(Collectors.toList());
    }

}
