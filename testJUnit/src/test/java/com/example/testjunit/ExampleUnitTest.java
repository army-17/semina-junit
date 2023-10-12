package com.example.testjunit;

import com.example.testjunit.dto.Animal;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExampleUnitTest {

    @Mock
    Animal animal;

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

        when(animal.getAge()).thenReturn(30);
        when(animal.getName()).thenReturn("참새");
        when(animal.getIsFly()).thenReturn(true);

        assertTrue(animal.getAge() == 20);
        assertTrue(animal.getName().equals("참새"));
//        assertTrue(animal.getIsFly() == false);

    }

    @Test
    public void mockTest3(){
        Animal animal2 = mock(Animal.class);

        List<String> animalList = new ArrayList<>();
        animalList.add("호랑이");
        animalList.add("코끼리");
        animalList.add("독수리");

        when(animal.getAnimalList()).thenReturn(animalList);

        assertNotNull(animalList);
        assertEquals(3, animalList.size());


    }

}

