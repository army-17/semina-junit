package com.genie.ai.engine.search.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genie.ai.engine.search.api.controller.AddressSearchController;
import com.genie.code.aiengine.search.PLATFORM_CODE;
import com.genie.code.aiengine.search.SERVICE_CODE;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("주소 검색 위한 테스트 클래스")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(AddressSearchController.class)
@AutoConfigureMockMvc
public class AddressSearchTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("주소조회 테스트")
    void getSongMetaInfoByQuery() throws Exception {

        ResultActions result = this.mockMvc.perform(
                get("/explore/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name()+SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
//                        .param("query", "성남시 정자동")
                        .param("query", "서울특별시 관악구")
                        .param("page", "1")
                        .param("size", "5")

        );
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.items").exists());

    }
}
