package com.example.testjunit;

import com.example.testjunit.dto.Animal;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class MockTest {

    @Mock
    Animal animal;


    @Test
    public void mockTest(){

        // Mock 객체 생성 (가짜 객체 만들기)
        MockitoAnnotations.initMocks(this);
        // Animal animal = mock(Animal.class);

        assertTrue(animal != null);

    }

    /* ---- given/when/then 패턴 ---- */
    @Test
    public void mockTest2(){


        // 1) given(준비) : 어떠한 데이터가 준비되었을 때
        Animal animal = mock(Animal.class);
        assertTrue(animal != null);

//        when(animal.getAge()).thenReturn(30);
//        when(animal.getName()).thenReturn("참새");
//        when(animal.getIsFly()).thenReturn(true);

        // 2) when(실행) : 어떠한 함수를 실행하면
        // 가짜 각체의 메서드 호출에 대한 Stubbing
        // thenReturn() 보다 doReturn() 이 사용되도록 권장
        doReturn(30).when(animal).getAge();
        doReturn("참새").when(animal).getName();
        doReturn(true).when(animal).getIsFly();

        // 3) then(결과 검증) : 어떠한 결과가 나와야 한다. => 단정문 사용
        assertTrue(animal.getAge() == 30);
        assertTrue(animal.getName().equals("참새"));
//        assertTrue(animal.getIsFly() == false);

    }

    @Test
    public void mockTest3(){

        // 객체 자체를 stub로 만드는 과정 = stubbing
        Animal animal2 = mock(Animal.class);

        List<String> animalList = new ArrayList<>();
        animalList.add("호랑이");
        animalList.add("코끼리");
        animalList.add("독수리");


        // mock 객체 stubbing
        when(animal2.getAnimalList()).thenReturn(animalList);

        assertNotNull(animal2.getAnimalList());
        assertNull(animal2.getAnimalList());
        //assertEquals(3, animalList.size());

        //System.out.println(animal2.getAnimalList().get(0));

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

    // verify()를 이용한 검증
    @Test
    public void mockTest5(){
        Animal animal = mock(Animal.class);
        animal.setName("참새");
        animal.setName("강아지");
        animal.setName("고양이");

        animal.getName();
        animal.getName();
        animal.getName();

        // n번 호출했는지 체크
        verify(animal, times(3)).setName(any(String.class));

        // 호출 안 했는지 체크 (호출하면 오류 발생)
//        verify(animal,never()).setName(any(String.class));
//        verify(animal,never()).setAge(any(int.class));
//        verify(animal, never()).getName();
//        verify(animal, never()).setAge(any(int.class));
//        verify(animal, never()).getAge();

        // 최소한 1번 이상 호출했는지 체크
        verify(animal, atLeastOnce()).setName(any(String.class));
        // 2번 이하 호출했는지 체크
//        verify(animal, atMost(2)).getName();
//        verify(animal, atMost(2)).setName(any(String.class));
        // 2번 이상 호출했는지 체크
//        verify(animal, atLeast(4)).setName(any(String.class));
        // 지정된 시간(millis)안으로 메소드를 호출했는지 체크
//        verify(animal, timeout(100)).setName(any(String.class));
        // 지정된 시간(millis)안으로 1번 이상 메소드를 호출했는지 체크

    }

}

