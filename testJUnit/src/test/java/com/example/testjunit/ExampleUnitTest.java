package com.example.testjunit;

import com.example.testjunit.dto.Animal;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExampleUnitTest {

    @Mock
    Animal animal;


    // 참고 사이트 : https://www.crocus.co.kr/1556
    @Test
    public void mockTest(){

        // Mock 객체 생성
        MockitoAnnotations.initMocks(this);
        // Animal animal = mock(Animal.class);

        assertTrue(animal != null);

    }

    @Test
    public void mockTest2(){

        Animal animal = mock(Animal.class);
        assertTrue(animal != null);

//        when(animal.getAge()).thenReturn(30);
//        when(animal.getName()).thenReturn("참새");
//        when(animal.getIsFly()).thenReturn(true);

        doReturn(30).when(animal).getAge();
        doReturn("참새").when(animal).getName();
        doReturn(true).when(animal).getIsFly();

        assertTrue(animal.getAge() == 30);
        assertTrue(animal.getName().equals("참새"));
//        assertTrue(animal.getIsFly() == false);

    }

    @Test
    public void mockTest3(){

        // 객체 자체를 stub로 만드는 과정
        Animal animal2 = mock(Animal.class);

        List<String> animalList = new ArrayList<>();
        animalList.add("호랑이");
        animalList.add("코끼리");
        animalList.add("독수리");

        when(animal2.getAnimalList()).thenReturn(animalList);

        assertNotNull(animalList);
        assertEquals(3, animalList.size());

        System.out.println(animal2.getAnimalList().get(0));

    }

    @Test
    public void mockTest4(){
        Animal animal = mock(Animal.class);

        // animal의 setAge를 20 이라고 호출한다면 예외를 발생시키는 코드
        // eq : 정확히 / eq(20)은 정확히 20이라는 의미
        doThrow(new RuntimeException()).when(animal).setAge(eq(20));

        animal.setAge(30);
        animal.setAge(15);
        animal.setAge(222);
        animal.setAge(123);
        animal.setAge(20);
        animal.setAge(11);
        animal.setAge(60);

    }

    // Verify를 이용한 검증
    @Test
    public void mockTest5(){
        Animal animal = mock(Animal.class);
        animal.setName("참새");
        animal.setName("강아지");
        animal.setName("고양이");

        // n번 호출했는지 체크
        verify(animal, times(3)).setName(any(String.class));

        // 호출 안 했는지 체크 (## 확인사항 : 호출이 정확히 무엇인지 확인)
//        verify(animal,never()).setName(any(String.class));
        verify(animal, never()).setAge(any(int.class));
//        verify(animal, never()).getAge();


        // 최소한 1번 이상 호출했는지 체크
//        verify(animal, atLeastOnce()).setAge(any(int.class));
    }


}

