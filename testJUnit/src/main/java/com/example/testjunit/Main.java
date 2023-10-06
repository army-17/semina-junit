package com.example.testjunit;

public class Main {

    public static void main(String[] args){

        Multiple multiple = new Multiple();

        System.out.println("result : {}" + multiple.multiply(2,3));
        System.out.println("result : {}" + multiple.multiply(3,4,7));

    }
}
