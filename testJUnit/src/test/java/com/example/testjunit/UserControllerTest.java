package com.example.testjunit;

import com.example.testjunit.dto.SignUpDto;
import com.example.testjunit.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.*;

@Log4j2
// @ExtendWith() : JUnit5와 Mockito 연동을 위한 annotation
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {


    @RequiredArgsConstructor
    @RestController
    @RequestMapping(value = "/user")


    public class UserController {

        @InjectMocks
        private UserController userController;

        @Mock
        private final UserService userService;

        private MockMvc mockMvc;


//        // @BeforeEach : 각 테스트 메소드 실행되기 전에 실행되어야함
//        @BeforeEach
//        public void init() {
//
//            mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        }

        /*
            mockMvc 객체를 초기화하고 userController라는 Spring MVC 컨트롤러를 단독을 테스트 가능하게 됨.
            즉, init() 메소드를 통해 MockMvc를 초기화하고 이를 사용하여 'userController'를 단위 테스트하기 위한 환경을 설정하는 것.
            이렇게 단위 테스트를 작성하면 컨트롤러의 동작이 예상대로 수행되는지 확인할 수 있으며, 버그를 식별하고 수정하는 데 도움이 된다.
         */


//        //회원가입 API
//        @PostMapping(value = "/signUp")
//        public ResponseEntity<String> signUp(@RequestBody SignUpDto signUpDto) {
//            return (userService.findByEmail(signUpDto.getEmail()) != null) ?
//                    ResponseEntity.badRequest().build() : ResponseEntity.ok("회원가입 가능");
//        }
//
//    }





}
