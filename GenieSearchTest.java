package com.genie.ai.engine.search.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genie.ai.engine.search.api.controller.GenieSearchController;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("GENIE-SEARCH 통합 테스트")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(GenieSearchController.class)
@AutoConfigureMockMvc
class GenieSearchTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * URL을 읽어서 key, value 형태로 queryString 파싱하는 함수
     */
    public static MultiValueMap<String, String> parseQueryString(String url) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        int queryStartIndex = url.indexOf('?');
        if (queryStartIndex != -1) {
            String queryString = url.substring(queryStartIndex + 1);
            String[] keyValuePairs = queryString.split("&");
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    params.add(key, value);
                }
            }
        }
        return params;
    }

    /* (구) - 자동완성 검색 테스트 데이터 */
    private MultiValueMap<String, String> createRequestBySearchAutoComplete() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", "아이유");
        params.add("size", "11");
        params.add("unm", "302190984");
        params.add("uxtk", "30219098436342.962");
        params.add("stk", "YXZsUFZ3M2xkaXZpUU16WjNwVGFqQT09");
        params.add("vmd", "A");
        params.add("svc", "DI");
        params.add("svn", "050601");
        params.add("apvn", "050601");
        params.add("mxa", "ODg6NjY6NUE6MDM6OEI6NEI%3D");
        params.add("udid", "E158D842-43AA-4D4D-8EE0-B5082FB90A01");
        params.add("mts", "Y");
        params.add("ovn", "16.2");
        params.add("dvm", "x86_64");
        params.add("tct", "");
        params.add("mccmnc", "");
        return params;
    }

    @DisplayName("(구) - 자동완성 검색 테스트")
    @Test
    @Disabled
    void searchAutoComplete() throws Exception {
        MultiValueMap<String, String> requestBySearchAutoComplete = createRequestBySearchAutoComplete();

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/f_Search_AutoComplete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
//                        .header("x-search-loggable", "false")
                        .params(requestBySearchAutoComplete)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(print());
//                .andExpect(jsonPath("$.song[0].image").exists())
//                .andExpect(jsonPath("$.song[0].field1").exists())
//                .andExpect(jsonPath("$.song[0].type").exists())
//                .andExpect(jsonPath("$.song[0].field3").exists())
//                .andExpect(jsonPath("$.song[0].word").exists())
//                .andExpect(jsonPath("$.song[0].field2").exists())
//                .andExpect(jsonPath("$.artist[0].image").exists())
//                .andExpect(jsonPath("$.artist[0].field1").exists())
//                .andExpect(jsonPath("$.artist[0].type").exists())
//                .andExpect(jsonPath("$.artist[0].field3").exists())
//                .andExpect(jsonPath("$.artist[0].word").exists())
//                .andExpect(jsonPath("$.artist[0].field2").exists())
//                .andExpect(jsonPath("$.album[0].image").exists())
//                .andExpect(jsonPath("$.album[0].field1").exists())
//                .andExpect(jsonPath("$.album[0].type").exists())
//                .andExpect(jsonPath("$.album[0].field3").exists())
//                .andExpect(jsonPath("$.album[0].word").exists())
//                .andExpect(jsonPath("$.album[0].field2").exists());
    }

    /* 통합 검색 메인 테스트 데이터 */
    private static MultiValueMap<String, String> createRequestByMainContentsParamsMap() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("query", "재즈");
        params.add("hl", "false");
        params.add("src", "E");
        params.add("sid", "GMS");
        params.add("fscount", "true");
        params.add("order", "false");
        params.add("suggest", "true");
        params.add("loggable", "false");
        params.add("unm", "302190984");
        params.add("uxtk", "30219098436342.962");
        params.add("stk", "YXZsUFZ3M2xkaXZpUU16WjNwVGFqQT09");
        params.add("vmd", "A");
        params.add("svc", "DI");
        params.add("svn", "050601");
        params.add("apvn", "50601");
        params.add("ver", "50601");
        params.add("mxa", "ODg6NjY6NUE6MDM6OEI6NEI%3D");
        params.add("udid", "E158D842-43AA-4D4D-8EE0-B5082FB90A01");
        params.add("mts", "Y");
        params.add("ovn", "16.2");
        params.add("dvm", "x86_64");
        params.add("callType", "iPhone");
        params.add("tct", "");
        params.add("mccMnc", "");
        params.add("aid", "");
        return params;
    }

    @DisplayName("통합 검색 메인 테스트 - search")
    @Test
    void mainContentsBySearch() throws Exception {
        MultiValueMap<String, String> requestByMainContentsParamsMap = createRequestByMainContentsParamsMap();
        String type = "search";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/main/{type}", type)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
//                        .header("x-search-loggable", "false")
                        .params(requestByMainContentsParamsMap)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(print())
                //ALBUM
                .andExpect(jsonPath("$.searchResult.result.albums.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].artist_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].country").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_series_no").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].middlecode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].middlecode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_era").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].lowcode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].lowcode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].restricted_rated").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_producer").doesNotExist())
                //.andExpect(jsonPath("$.searchResult.result.albums.items[0]._highlight.artist_name_original").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_planner").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_track_cnt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].country_name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].image_infos.view_code").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].image_infos.image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].image_infos.image_type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].arranged_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].arranged_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].lyrics_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].lyrics_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].additional_info.collapse.count").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].additional_info.collapse.key").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].cd_no").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_track_no").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].representation_song").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].artist_id").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].artist_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_id").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_type_name").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_release_dt").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].representation.song_id").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].representation.song_name").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].service_available.rep_stream_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].image_path").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].thumbnail_image_path").exists())
                //PLAYLIST
                .andExpect(jsonPath("$.searchResult.result.playlist.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].maker.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].maker.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.start_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.upt_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.end_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.reg_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.description").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].sort_flag").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].contents").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.popular_all").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.scrap").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.popular_recent").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].uno").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].category.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].category.id").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].disp_dt").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].title").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].img_path").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.song").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.view").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.favorite").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.listen").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].tag[0].name").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].tag[0].id").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].seq").exists())
                //ISSUE
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].main_info.thumbnail").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].main_info.title").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].web_info.thumbnail").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].web_info.contents").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].web_info.serviceYn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].web_info.title").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].app_info.thumbnail").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].app_info.contents").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].app_info.serviceYn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].app_info.title").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].relation_contents").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.contents").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.image_path").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.link").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.title").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.telecom_type").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.version").exists())
                //ARTIST : artist의 경우 stage 검색 엔진에 데이터가 존재하지 않아 검증이 불가능
                /*.andExpect(jsonPath("$.searchResult.result.artists.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_name.kor").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].relation_artist").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].image_infos.view_code").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].image_infos.image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].image_infos.image_type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].member_info.idx").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].member_info.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].member_info.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].member_info.status").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].member_info.role_name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0]._highlight.artist_name.original").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].similarity_artist.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].similarity_artist.idx").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_prof").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].debut_info.date").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].debut_info.song_id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].debut_info.song_name").doesNotExist())*/
                //EXISTS
                /*.andExpect(jsonPath("$.searchResult.result.artists.items[0].country").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].gender").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].lowcode.name").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].lowcode.id").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_active_term").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0]._score").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].represent_song_id").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_id").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].join_artist_info.name").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].join_artist_info.id").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].image_path").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].country_name").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_type").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].popular.last_3_month").exists())*/
                //SONGS
                .andExpect(jsonPath("$.searchResult.result.songs.items").exists())
                //.andExpect(jsonPath("$.searchResult.result.songs.items[0].country").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].country").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].middlecode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].middlecode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].service_available.lyrics_service_yn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].lowcode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].lowcode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].song_name.full").doesNotExist())
                //.andExpect(jsonPath("$.searchResult.result.songs.items[0]._highlight.artist_name.original").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].album_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].country_name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].composed_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].composed_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].mv_service_cnt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].lyrics").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].image_infos.view_code").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].image_infos.image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].image_infos.image_type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].arranged_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].arranged_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].album_release_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].lyrics_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].lyrics_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].album_track_no").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].file_infos[0].type").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].file_infos[0].path").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].file_infos[0].bitrate").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].artist_name").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].service_available.down_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].service_available.stream_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].service_available.down_mp3_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].restricted_rated").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].duration.text").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].duration.time").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].song_id").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].album_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].artist_id").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].additional_info.collapse.count").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].additional_info.collapse.key").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].album_id").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].thumbnail_image_path").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].cd_no").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].representation_song").exists())
                //VIDEOS
                .andExpect(jsonPath("$.searchResult.result.videos.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].artist_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].middlecode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].middlecode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].service_available.down_service_yn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].service_available.down_mp3_service_yn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].lowcode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].lowcode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].song_name.full").doesNotExist())
                //.andExpect(jsonPath("$.searchResult.result.videos.items[0]._highlight.artist_name.original").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].album_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].country_name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_theme").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_rep_code").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].album_release_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_name.full").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_type_code").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].is_service_code").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_img_path").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].artist_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_id").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].song_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].vr_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].duration.text").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].duration.time").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].song_id").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].resolution_code").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].album_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].song_adlt_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].pip_type").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_thumbnail_path").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].artist_id").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].song_rep_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_adlt_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].album_id").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].ismp").exists())
                //MAGAZINE
                .andExpect(jsonPath("$.searchResult.result.magazine.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].view_cnt").doesNotExist())
                //.andExpect(jsonPath("$.searchResult.result.magazine.items[0]._highlight.title").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].event_info.end_date").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].event_info.start_date").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].list_img_url").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].is_view").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].track_list[0].artist_id").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].open_dt").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].event_info.name").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].event_info.event_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].top_img_url").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].title").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].category.name").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].category.id").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].track_list[0].song_id").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].track_list[0].artist_name").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].track_list[0].song_name").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].seq").exists())
                //LYRICS
                .andExpect(jsonPath("$.searchResult.result.lyrics.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].country").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].song_tts").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].file_infos.path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].file_infos.bitrate").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].file_infos.type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].middlecode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].middlecode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].service_available.lyrics_service_yn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].lowcode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].lowcode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].song_name.full").doesNotExist())
                //.andExpect(jsonPath("$.searchResult.result.lyrics.items[0]._highlight.artist_name.original").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].album_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].country_name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].composed_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].composed_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].mv_service_cnt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].popular.total").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].popular.last_1_month").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].popular.last_3_month").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].popular.last_1_week").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].image_infos.view_code").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].image_infos.image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].image_infos.image_type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].arranged_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].arranged_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].artist_tts").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].album_release_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].lyrics_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].lyrics_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].boost_info.limit").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].boost_info.boost").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].album_track_no").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].file_infos").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].artist_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].service_available.down_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].service_available.stream_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].service_available.down_mp3_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].song_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0]._score").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].restricted_rated").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].duration.text").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].duration.time").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].song_id").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].album_name.original").exists())
                //.andExpect(jsonPath("$.searchResult.result.lyrics.items[0]._highlight.song_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].lyrics").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].additional_info.collapse.count").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].additional_info.collapse.key").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].album_id").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].thumbnail_image_path").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].cd_no").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].representation_song").exists())
                //RADIO
                .andExpect(jsonPath("$.searchResult.result.radio.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].view_cnt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].sort_num").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].radio_channel_id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].keyword").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].channel_title").exists())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].img_path").exists())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].category.name").exists())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].category.id").exists())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].seq").exists())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].channel_tags").exists());

    }

    @DisplayName("통합 검색 메인 테스트 - bottom")
    @Test
    void mainContentsByBottom() throws Exception {
        MultiValueMap<String, String> requestByMainContentsParamsMap = createRequestByMainContentsParamsMap();
        String type = "bottom";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/main/{type}", type)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
//                        .header("x-search-loggable", true)
                        .params(requestByMainContentsParamsMap)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(print())
                //.andExpect(jsonPath("$.popularKeyword").isEmpty());
                .andExpect(jsonPath("$.popularKeyword").isNotEmpty())
                .andExpect(jsonPath("$.popularKeyword[0].CHANGES").isNotEmpty())
                .andExpect(jsonPath("$.popularKeyword[0].SORT").isNotEmpty())
                //.andExpect(jsonPath("$.popularKeyword[0].SORT").isEmpty())
                .andExpect(jsonPath("$.popularKeyword[0].KEYWORD").isNotEmpty());
    }

    @DisplayName("통합 검색 메인 테스트 - all")
    @Test
    void mainContentsByAll() throws Exception {
        MultiValueMap<String, String> requestByMainContentsParamsMap = createRequestByMainContentsParamsMap();
        String type = "all";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/main/{type}", type)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
//                        .header("loggable", true)
                        .params(requestByMainContentsParamsMap)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.searchResult.result.albums").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.playlist").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.issue").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.artists").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.songs").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.faq").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.videos").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.magazine").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.program").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.menu").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.lyrics").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.program").isNotEmpty()) // search
                //.andExpect(jsonPath("$.popularKeyword").isEmpty());
                .andExpect(jsonPath("$.popularKeyword").isNotEmpty())
                .andExpect(jsonPath("$.popularKeyword[0].CHANGES").isNotEmpty())
                .andExpect(jsonPath("$.popularKeyword[0].SORT").isNotEmpty())
                //.andExpect(jsonPath("$.popularKeyword[0].SORT").isEmpty())
                .andExpect(jsonPath("$.popularKeyword[0].KEYWORD").isNotEmpty()); // bottom
    }

    /* 카테고리로 검색 테스트 데이터 */
    private MultiValueMap<String, String> createRequestBySearchCategory() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String encodedString = "%EC%95%84%EC%9D%B4%EC%9C%A0";
        String decodedString = URLDecoder.decode(encodedString, StandardCharsets.UTF_8);
        params.add("src", "E");
        params.add("sid", "GMS");
        params.add("fscount", "true");
        params.add("page", "1");
        params.add("query", decodedString);
        params.add("order", "false");
        params.add("of", "SCORE");
        params.add("genre", "힙합");
        params.add("pagesize", "50");
        params.add("unm", "302190984");
        params.add("uxtk", "30219098436342.962");
        params.add("stk", "YXZsUFZ3M2xkaXZpUU16WjNwVGFqQT09");
        params.add("vmd", "A");
        params.add("svc", "DI");
        params.add("svn", "050601");
        params.add("apvn", "50601");
        params.add("ver", "50601");
        params.add("mxa", "ODg6NjY6NUE6MDM6OEI6NEI%3D");
        params.add("udid", "E158D842-43AA-4D4D-8EE0-B5082FB90A01");
        params.add("mts", "Y");
        params.add("ovn", "16.2");
        params.add("dvm", "x86_64");
        params.add("callType", "iPhone");
        params.add("tct", "");
        params.add("mccMnc", "");
        params.add("aid", "");
        return params;
    }

    @DisplayName("카테고리로 검색 테스트 - songs")
    @Test
    void searchCategoryBySongs() throws Exception {
        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategory();
        String category = "songs";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCategory)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.searchResult.result.songs.items").exists())
//                .andExpect(jsonPath("$.searchResult.result.songs.items[0].country").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].country").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].middlecode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].middlecode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].service_available.lyrics_service_yn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].lowcode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].lowcode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].song_name.full").doesNotExist())
                //.andExpect(jsonPath("$.searchResult.result.songs.items[0]._highlight.artist_name.original").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].album_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].country_name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].composed_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].composed_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].mv_service_cnt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].lyrics").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].image_infos.view_code").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].image_infos.image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].image_infos.image_type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].arranged_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].arranged_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].album_release_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].lyrics_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].lyrics_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].album_track_no").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].file_infos[0].type").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].file_infos[0].path").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].file_infos[0].bitrate").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].artist_name").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].service_available.down_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].service_available.stream_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].service_available.down_mp3_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].song_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].restricted_rated").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].duration.text").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].duration.time").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].song_id").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].album_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].artist_id").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].additional_info.collapse.count").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].additional_info.collapse.key").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].album_id").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].thumbnail_image_path").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].cd_no").exists())
                .andExpect(jsonPath("$.searchResult.result.songs.items[0].representation_song").exists());
//                .andExpect(jsonPath("$.searchResult.result.songs.items[0].representation_song").doesNotExist());
    }

    @DisplayName("카테고리로 검색 테스트 - albums")
    @Test
    void searchCategoryByAlbums() throws Exception {
        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategory();
        String category = "albums";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCategory)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.searchResult.result.albums.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].artist_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].country").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_series_no").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].middlecode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].middlecode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_era").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].lowcode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].lowcode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].restricted_rated").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_producer").doesNotExist())
                //.andExpect(jsonPath("$.searchResult.result.albums.items[0]._highlight.artist_name_original").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_planner").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_track_cnt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].country_name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].image_infos.view_code").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].image_infos.image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].image_infos.image_type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].arranged_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].arranged_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].lyrics_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].lyrics_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].additional_info.collapse.count").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].additional_info.collapse.key").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].cd_no").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_track_no").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].representation_song").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].artist_id").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].artist_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_id").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_type_name").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].album_release_dt").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].representation.song_id").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].representation.song_name").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].service_available.rep_stream_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].image_path").exists())
                .andExpect(jsonPath("$.searchResult.result.albums.items[0].thumbnail_image_path").exists());
    }

    @DisplayName("카테고리로 검색 테스트 - artists")
    @Test
    void searchCategoryByArtists() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String encodedString = "10cm";
        String decodedString = URLDecoder.decode(encodedString, StandardCharsets.UTF_8);
        params.add("src", "E");
        params.add("sid", "GMS");
        params.add("fscount", "true");
        params.add("page", "1");
        params.add("query", decodedString);
        params.add("order", "false");
        params.add("of", "SCORE");
        params.add("genre", "");
        params.add("pagesize", "50");
        params.add("unm", "302190984");
        params.add("uxtk", "30219098436342.962");
        params.add("stk", "YXZsUFZ3M2xkaXZpUU16WjNwVGFqQT09");
        params.add("vmd", "A");
        params.add("svc", "DI");
        params.add("svn", "050601");
        params.add("apvn", "50601");
        params.add("ver", "50601");
        params.add("mxa", "ODg6NjY6NUE6MDM6OEI6NEI%3D");
        params.add("udid", "E158D842-43AA-4D4D-8EE0-B5082FB90A01");
        params.add("mts", "Y");
        params.add("ovn", "16.2");
        params.add("dvm", "x86_64");
        params.add("callType", "iPhone");
        params.add("tct", "");
        params.add("mccMnc", "");
        params.add("aid", "");

//        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategory();
        String category = "artists";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(params)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.searchResult.result.artists.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_name.kor").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_name.eng").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].relation_artist").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].image_infos.view_code").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].image_infos.image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].image_infos.image_type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].member_info.idx").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].member_info.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].member_info.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].member_info.status").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].member_info.role_name").doesNotExist())
                //.andExpect(jsonPath("$.searchResult.result.artists.items[0]._highlight.artist_name.original").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].similarity_artist.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].similarity_artist.idx").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_prof").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].debut_info.date").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].debut_info.song_id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].debut_info.song_name").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].country").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].gender").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].lowcode.name").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].lowcode.id").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_active_term").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0]._score").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].represent_song_id").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_id").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].join_artist_info[0].name").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].join_artist_info[0].id").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].image_path").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].country_name").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].artist_type").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].popular.total").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].popular.last_1_week").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].popular.last_1_month").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].popular.last_3_month").exists())
                .andExpect(jsonPath("$.searchResult.result.artists.items[0].thumbnail_image_path").exists());
    }

    @DisplayName("카테고리로 검색 테스트 - lyrics")
    @Test
    void searchCategoryByLyrics() throws Exception {
        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategory();
        String category = "lyrics";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCategory)
        );

        //then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].country").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].song_tts").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].file_infos.path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].file_infos.bitrate").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].file_infos.type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].middlecode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].middlecode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].service_available.lyrics_service_yn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].lowcode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].lowcode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].song_name.full").doesNotExist())
                //.andExpect(jsonPath("$.searchResult.result.lyrics.items[0]._highlight.artist_name.original").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].album_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].country_name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].composed_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].composed_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].mv_service_cnt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].popular.total").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].popular.last_1_month").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].popular.last_3_month").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].popular.last_1_week").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].image_infos.view_code").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].image_infos.image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].image_infos.image_type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].arranged_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].arranged_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].artist_tts").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].album_release_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].lyrics_by.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].lyrics_by.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].image_path").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].boost_info.limit").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].boost_info.boost").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].album_track_no").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].file_infos").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].artist_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].artist_name.full").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].service_available.down_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].service_available.stream_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].service_available.down_mp3_service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].song_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0]._score").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].restricted_rated").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].duration.text").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].duration.time").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].song_id").exists())
                //.andExpect(jsonPath("$.searchResult.result.lyrics.items[0]._highlight.song_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].album_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].lyrics").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].additional_info.collapse.count").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].additional_info.collapse.key").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].album_id").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].thumbnail_image_path").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].cd_no").exists())
                .andExpect(jsonPath("$.searchResult.result.lyrics.items[0].representation_song").exists());
    }

    @DisplayName("카테고리로 검색 테스트 - videos")
    @Test
    void searchCategoryByVideos() throws Exception {
        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategory();
        String category = "videos";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCategory)
        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.searchResult.result.videos.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].artist_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].middlecode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].middlecode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].service_available.down_service_yn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].service_available.down_mp3_service_yn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].lowcode.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].lowcode.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].song_name.full").doesNotExist())
                //.andExpect(jsonPath("$.searchResult.result.videos.items[0]._highlight.artist_name.original").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].album_name.full").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].country_name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_theme").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_rep_code").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].album_release_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_name.full").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_type_code").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].is_service_code").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_img_path").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].artist_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_id").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].song_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].vr_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].duration.text").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].duration.time").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].song_id").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].resolution_code").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].album_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].song_adlt_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].pip_type").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_thumbnail_path").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].artist_id").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_name.original").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].song_rep_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].mv_adlt_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].album_id").exists())
                .andExpect(jsonPath("$.searchResult.result.videos.items[0].ismp").exists());
    }

    @DisplayName("카테고리로 검색 테스트 - magazine")
    @Test
    void searchCategoryByMagazine() throws Exception {
        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategory();
        String category = "magazine";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCategory)
        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.searchResult.result.magazine.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].view_cnt").doesNotExist())
                //.andExpect(jsonPath("$.searchResult.result.magazine.items[0]._highlight.title").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].event_info.end_date").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].event_info.start_date").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].list_img_url").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].is_view").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].track_list[0].artist_id").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].open_dt").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].event_info.name").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].event_info.event_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].event_info.type").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].top_img_url").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].title").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].category.name").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].category.id").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].track_list[0].song_id").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].track_list[0].artist_name").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].track_list[0].song_name").exists())
                .andExpect(jsonPath("$.searchResult.result.magazine.items[0].seq").exists());
    }

    /* 카테고리로 검색 테스트 데이터 */
    private MultiValueMap<String, String> createRequestBySearchCategoryPlayList() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("src", "E");
        params.add("sid", "GMS");
        params.add("fscount", "true");
        params.add("page", "1");
        params.add("query", "아이유");
        params.add("order", "false");
        params.add("of", "SCORE");
        params.add("genre", "");
        params.add("pagesize", "50");
        params.add("unm", "302190984");
        params.add("uxtk", "30219098436342.962");
        params.add("stk", "YXZsUFZ3M2xkaXZpUU16WjNwVGFqQT09");
        params.add("vmd", "A");
        params.add("svc", "DI");
        params.add("svn", "050601");
        params.add("apvn", "50601");
        params.add("ver", "50601");
        params.add("mxa", "ODg6NjY6NUE6MDM6OEI6NEI%3D");
        params.add("udid", "E158D842-43AA-4D4D-8EE0-B5082FB90A01");
        params.add("mts", "Y");
        params.add("ovn", "16.2");
        params.add("dvm", "x86_64");
        params.add("callType", "iPhone");
        params.add("tct", "");
        params.add("mccMnc", "");
        params.add("aid", "");
        return params;
    }

    @DisplayName("카테고리로 검색 테스트 - playlist")
    @Test
    void searchCategoryByPlaylist() throws Exception {
        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategoryPlayList();
        String category = "playlist";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCategory)
        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.searchResult.result.playlist.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].maker.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].maker.id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.start_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.upt_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.end_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.reg_dt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.description").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].main.type").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].sort_flag").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].contents").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.popular_all").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.scrap").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.popular_recent").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].uno").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].category.name").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].category.id").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].disp_dt").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].title").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].img_path").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.song").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.view").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.favorite").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].count_info.listen").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].tag[0].name").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].tag[0].id").exists())
                .andExpect(jsonPath("$.searchResult.result.playlist.items[0].seq").exists());
    }

    /* 카테고리로 검색 테스트 데이터 */
    private MultiValueMap<String, String> createRequestBySearchCategoryRadio() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("src", "G");
        params.add("sid", "GMS");
        params.add("fscount", "true");
        params.add("page", "1");
        params.add("query", "장르");
        params.add("order", "false");
        params.add("of", "SCORE");
        params.add("genre", "");
        params.add("pagesize", "50");
        params.add("unm", "302190984");
        params.add("uxtk", "30219098436342.962");
        params.add("stk", "YXZsUFZ3M2xkaXZpUU16WjNwVGFqQT09");
        params.add("vmd", "A");
        params.add("svc", "DI");
        params.add("svn", "050601");
        params.add("apvn", "50601");
        params.add("ver", "50601");
        params.add("mxa", "ODg6NjY6NUE6MDM6OEI6NEI%3D");
        params.add("udid", "E158D842-43AA-4D4D-8EE0-B5082FB90A01");
        params.add("mts", "Y");
        params.add("ovn", "16.2");
        params.add("dvm", "x86_64");
        params.add("callType", "iPhone");
        params.add("tct", "");
        params.add("mccMnc", "");
        params.add("aid", "");
        return params;
    }

    @DisplayName("카테고리로 검색 테스트 - radio")
    @Test
    void searchCategoryByRadio() throws Exception {
        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategoryRadio();
        String category = "radio";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCategory)
        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.searchResult.result.radio.items").isNotEmpty())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].view_cnt").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].sort_num").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].radio_channel_id").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].keyword").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].channel_title").exists())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].img_path").exists())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].category.name").exists())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].category.id").exists())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].seq").exists())
                .andExpect(jsonPath("$.searchResult.result.radio.items[0].channel_tags").exists());

    }

    private MultiValueMap<String, String> createRequestBySearchCategoryIssue() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("src", "E");
        params.add("sid", "GMS");
        params.add("fscount", "true");
        params.add("page", "1");
        params.add("query", "배캠");
        params.add("order", "false");
        params.add("of", "SCORE");
        params.add("genre", "");
        params.add("pagesize", "50");
        params.add("unm", "302190984");
        params.add("uxtk", "30219098436342.962");
        params.add("stk", "YXZsUFZ3M2xkaXZpUU16WjNwVGFqQT09");
        params.add("vmd", "A");
        params.add("svc", "DI");
        params.add("svn", "050601");
        params.add("apvn", "50601");
        params.add("ver", "50601");
        params.add("mxa", "ODg6NjY6NUE6MDM6OEI6NEI%3D");
        params.add("udid", "E158D842-43AA-4D4D-8EE0-B5082FB90A01");
        params.add("mts", "Y");
        params.add("ovn", "16.2");
        params.add("dvm", "x86_64");
        params.add("callType", "iPhone");
        params.add("tct", "");
        params.add("mccMnc", "");
        params.add("aid", "");
        return params;
    }

    @DisplayName("카테고리로 검색 테스트 - issue")
    @Test
    void searchCategoryByIssue() throws Exception {
        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategoryIssue();
        String category = "issue";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCategory)
        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].main_info.thumbnail").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].main_info.title").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].web_info.thumbnail").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].web_info.contents").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].web_info.serviceYn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].web_info.title").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].app_info.thumbnail").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].app_info.contents").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].app_info.serviceYn").doesNotExist())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].app_info.title").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].relation_contents").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.contents").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.image_path").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.link").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.title").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.telecom_type").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.service_yn").exists())
                .andExpect(jsonPath("$.searchResult.result.issue.items[0].info.version").exists());
    }

    private MultiValueMap<String, String> createRequestBySearchCategoryMenu() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("src", "E");
        params.add("sid", "GMS");
        params.add("fscount", "true");
        params.add("page", "1");
        params.add("query", "로그인");
        params.add("order", "false");
        params.add("of", "SCORE");
        params.add("genre", "");
        params.add("pagesize", "50");
        params.add("unm", "302190984");
        params.add("uxtk", "30219098436342.962");
        params.add("stk", "YXZsUFZ3M2xkaXZpUU16WjNwVGFqQT09");
        params.add("vmd", "A");
        params.add("svc", "DI");
        params.add("svn", "050601");
        params.add("apvn", "50601");
        params.add("ver", "50601");
        params.add("mxa", "ODg6NjY6NUE6MDM6OEI6NEI%3D");
        params.add("udid", "E158D842-43AA-4D4D-8EE0-B5082FB90A01");
        params.add("mts", "Y");
        params.add("ovn", "16.2");
        params.add("dvm", "x86_64");
        params.add("callType", "iPhone");
        params.add("tct", "");
        params.add("mccMnc", "");
        params.add("aid", "");
        return params;
    }

    @DisplayName("카테고리로 검색 테스트 - menu")
    @Test
    void searchCategoryByMenu() throws Exception {
        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategoryMenu();
        String category = "menu";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCategory)
        );

        result.andExpect(status().isOk())
                .andDo(print());
    }

    private MultiValueMap<String, String> createRequestBySearchCategoryProgram() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("src", "E");
        params.add("sid", "GMS");
        params.add("fscount", "true");
        params.add("page", "1");
        params.add("query", "쇼챔피언");
        params.add("order", "false");
        params.add("of", "SCORE");
        params.add("genre", "");
        params.add("pagesize", "50");
        params.add("unm", "302190984");
        params.add("uxtk", "30219098436342.962");
        params.add("stk", "YXZsUFZ3M2xkaXZpUU16WjNwVGFqQT09");
        params.add("vmd", "A");
        params.add("svc", "DI");
        params.add("svn", "050601");
        params.add("apvn", "50601");
        params.add("ver", "50601");
        params.add("mxa", "ODg6NjY6NUE6MDM6OEI6NEI%3D");
        params.add("udid", "E158D842-43AA-4D4D-8EE0-B5082FB90A01");
        params.add("mts", "Y");
        params.add("ovn", "16.2");
        params.add("dvm", "x86_64");
        params.add("callType", "iPhone");
        params.add("tct", "");
        params.add("mccMnc", "");
        params.add("aid", "");
        return params;
    }

    @DisplayName("카테고리로 검색 테스트 - program")
    @Test
    void searchCategoryByProgram() throws Exception {
        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategoryProgram();
        String category = "program";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCategory)
        );

        result.andExpect(status().isOk())
                .andDo(print());
    }

    private MultiValueMap<String, String> createRequestBySearchCategoryFaq() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("src", "E");
        params.add("sid", "GMS");
        params.add("fscount", "true");
        params.add("page", "1");
        params.add("query", "로그인");
        params.add("order", "false");
        params.add("of", "SCORE");
        params.add("genre", "");
        params.add("pagesize", "50");
        params.add("unm", "302190984");
        params.add("uxtk", "30219098436342.962");
        params.add("stk", "YXZsUFZ3M2xkaXZpUU16WjNwVGFqQT09");
        params.add("vmd", "A");
        params.add("svc", "DI");
        params.add("svn", "050601");
        params.add("apvn", "50601");
        params.add("ver", "50601");
        params.add("mxa", "ODg6NjY6NUE6MDM6OEI6NEI%3D");
        params.add("udid", "E158D842-43AA-4D4D-8EE0-B5082FB90A01");
        params.add("mts", "Y");
        params.add("ovn", "16.2");
        params.add("dvm", "x86_64");
        params.add("callType", "iPhone");
        params.add("tct", "");
        params.add("mccMnc", "");
        params.add("aid", "");
        return params;
    }

    @DisplayName("카테고리로 검색 테스트 - faq")
    @Test
    void searchCategoryByFaq() throws Exception {
        MultiValueMap<String, String> requestBySearchCategory = createRequestBySearchCategoryFaq();
        String category = "faq";

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/category/{category}", category)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCategory)
        );

        result.andExpect(status().isOk())
                .andDo(print());
    }

    /* 미니 자동 검색 테스트 데이터 */
    private MultiValueMap<String, String> createRequestByMiniAutoComplete() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String encodedString = "%EC%95%84%EC%9D%B4";
        String decodedString = URLDecoder.decode(encodedString, StandardCharsets.UTF_8);
        params.add("uxtk", "30219098436342.962");
        params.add("stk", "YXZsUFZ3M2xkaXZpUU16WjNwVGFqQT09");
        params.add("vmd", "A");
        params.add("svc", "DI");
        params.add("svn", "050601");
        params.add("apvn", "50601");
        params.add("ver", "50601");
        params.add("mxa", "ODg6NjY6NUE6MDM6OEI6NEI%3D");
        params.add("udid", "E158D842-43AA-4D4D-8EE0-B5082FB90A01");
        params.add("mts", "Y");
        params.add("ovn", "16.2");
        params.add("dvm", "x86_64");
        params.add("callType", "iPhone");
        params.add("tct", "");
        params.add("mccMnc", "");
        params.add("aid", "");
        params.add("q", decodedString);
        params.add("size", "11");
        return params;
    }

    @DisplayName("미니 자동 검색 테스트")
    @Test
    void miniAutoComplete() throws Exception {
        MultiValueMap<String, String> requestByMiniAutoComplete = createRequestByMiniAutoComplete();

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/miniAutoComplete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", true)
                        .params(requestByMiniAutoComplete)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.tags[0].boost_info.limit").doesNotExist())
                .andExpect(jsonPath("$.result.tags[0].boost_info.boosts").doesNotExist())
                .andExpect(jsonPath("$.result.artist.country").doesNotExist())
                .andExpect(jsonPath("$.result.artist.artist_name.kor").doesNotExist())
                .andExpect(jsonPath("$.result.artist.artist_name.eng").doesNotExist())
                .andExpect(jsonPath("$.result.artist.artist_name.full").doesNotExist())
                .andExpect(jsonPath("$.result.artist.debut_info.date").doesNotExist())
                .andExpect(jsonPath("$.result.artist.debut_info.song_id").doesNotExist())
                .andExpect(jsonPath("$.result.artist.debut_info.song_name").doesNotExist())
                .andExpect(jsonPath("$.result.artist.popular.last_1_month").doesNotExist())
                .andExpect(jsonPath("$.result.artist.popular.last_3_month").doesNotExist())
                .andExpect(jsonPath("$.result.artist.popular.last_1_week").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.result.tags[0].tag_name").exists())
                .andExpect(jsonPath("$.result.tags[0].tag_id").exists())
                .andExpect(jsonPath("$.result.items[0].suggest_text").exists())
                .andExpect(jsonPath("$.result.items[0].suggest_text").isNotEmpty());
    }

    /* 중복곡 테스트 데이터 */
    private MultiValueMap<String, String> createRequestBySearchCollapseSong() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("svc", "IV");
        params.add("tct", "KT");
        params.add("dcd", "ANDROID_ID%3A19fd990826d692ec");
        params.add("ovn", "13");
        params.add("sign", "Y");
        params.add("apvn", "50604");
        params.add("stk", "VnA1S0VqM3hJRXNVTytlcnRrRlBHdz09");
        params.add("uxtk", "");
        params.add("mts", "Y");
        params.add("lpr", "");
        params.add("mccMnc", "45008");
        params.add("unm", "");
        params.add("collapseKey", "b1e4a310a98cb5dfc090339545ee1452");
        params.add("pagesize", "4");
        params.add("dvm", "SM-S916N");
        params.add("uip", "192.168.137.192");
        params.add("aid", "bbec04e9-0f0a-4589-a37e-a9405ad5e0a8");
        return params;
    }

    @DisplayName("중복곡 검색 테스트")
    @Test
    void searchCollapseSong() throws Exception {
        MultiValueMap<String, String> requestBySearchCollapseSong = createRequestBySearchCollapseSong();

        ResultActions result = this.mockMvc.perform(
                get("/v2/search/collapseSong")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
//                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .params(requestBySearchCollapseSong)
        );

        // then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.items[0].service_available.lyrics_service_yn").doesNotExist())
                .andExpect(jsonPath("$.items[0].song_name.full").doesNotExist())
                .andExpect(jsonPath("$.items[0].album_name.full").doesNotExist())
                .andExpect(jsonPath("$.items[0].image_path").doesNotExist())
                .andExpect(jsonPath("$.items[0].representation_song").doesNotExist())
                //EXISTS
                .andExpect(jsonPath("$.items[0].artist_name.original").exists())
                .andExpect(jsonPath("$.items[0].artist_name.full").exists())
                .andExpect(jsonPath("$.items[0].service_available.down_service_yn").exists())
                .andExpect(jsonPath("$.items[0].service_available.stream_service_yn").exists())
                .andExpect(jsonPath("$.items[0].service_available.down_mp3_service_yn").exists())
                .andExpect(jsonPath("$.items[0].song_name.original").exists())
                .andExpect(jsonPath("$.items[0]._score").exists())
                .andExpect(jsonPath("$.items[0].artist_id").exists())
                .andExpect(jsonPath("$.items[0].restricted_rated").exists())
                .andExpect(jsonPath("$.items[0].duration.text").exists())
                .andExpect(jsonPath("$.items[0].duration.time").exists())
                .andExpect(jsonPath("$.items[0].song_id").exists())
                .andExpect(jsonPath("$.items[0].album_name.original").exists())
                .andExpect(jsonPath("$.items[0].album_id").exists())
                .andExpect(jsonPath("$.items[0].thumbnail_image_path").exists());
    }
}
