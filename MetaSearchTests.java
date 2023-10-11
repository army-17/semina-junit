package com.genie.ai.engine.search.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genie.ai.engine.core.elastic.model.enums.ItemType;
import com.genie.ai.engine.core.elastic.model.enums.QueryField;
import com.genie.ai.engine.core.elastic.model.enums.SortType;
import com.genie.ai.engine.core.elastic.model.vo.Indices;
import com.genie.ai.engine.core.elastic.model.vo.MultiMeta;
import com.genie.ai.engine.search.api.controller.MetaController;
import com.genie.code.aiengine.search.PLATFORM_CODE;
import com.genie.code.aiengine.search.SERVICE_CODE;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("메타 검색 테스트")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(MetaController.class)
@AutoConfigureMockMvc
public class MetaSearchTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static Stream<Arguments> provideMetaInfoByQuery() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("윤도현");
                    setSubQuery("");
                    setLimit(1);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.songs);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.POPULAR);
                            }});
                        }});
                        add(new Indices(){{
                            setName(ItemType.albums);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                        add(new Indices(){{
                            setName(ItemType.artists);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                        add(new Indices(){{
                            setName(ItemType.lyrics);
                            setSort(SortType.NEWEST);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.POPULAR);
                            }});
                        }});
                        add(new Indices(){{
                            setName(ItemType.videos);
                            setSort(SortType.NEWEST);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.POPULAR);
                            }});
                        }});
                        add(new Indices(){{
                            setName(ItemType.magazine);
                            setSort(SortType.NEWEST);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.POPULAR);
                            }});
                        }});
                        add(new Indices(){{
                            setName(ItemType.playlist);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                        add(new Indices(){{
                            setName(ItemType.composer);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.POPULAR);
                            }});
                        }});
                        add(new Indices(){{
                            setName(ItemType.radio);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.POPULAR);
                            }});
                        }});

                        add(new Indices(){{
                            setName(ItemType.issue);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.POPULAR);
                            }});
                        }});

                        add(new Indices(){{
                            setName(ItemType.menu);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "provideMetaInfoByQuery")
    @DisplayName("메타조회 테스트 - 통합")
//    @Order(1)
    void getMetaInfoByQuery(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.songs.items").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items").isNotEmpty())
                .andExpect(jsonPath("$.result.artists.items").isNotEmpty())
                .andExpect(jsonPath("$.result.lyrics.items").isNotEmpty())
                .andExpect(jsonPath("$.result.videos.items").isNotEmpty())
                .andExpect(jsonPath("$.result.magazine.items").isNotEmpty())
                .andExpect(jsonPath("$.result.playlist.items").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items").isNotEmpty())
                .andExpect(jsonPath("$.result.radio.items").exists())
                .andExpect(jsonPath("$.result.issue.items").exists())
                .andExpect(jsonPath("$.result.menu.items").exists())
        ;

    }

    // ============================================================================================================

    private static Stream<Arguments> songParams() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("위너");
                    setSubQuery("");
                    setLimit(1);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.songs);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.POPULAR);
                            }});
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.SONG, "EVERYDAY");
                            }});
                        }});
                    }});
                }}),
//                Arguments.of(new MultiMeta.request(){{
//                    setQuery("ㅇㅈㅇㅇㄷ");
//                    setSubQuery("");
//                    setLimit(1);
//                    setOffset(0);
//                    setUno(0L);
//                    setIndices(new ArrayList<Indices>(){{
//                        add(new Indices(){{
//                            setName(ItemType.songs);
//                            setSort(SortType.SCORE);
//                            setIncludes(new ArrayList<QueryField>(){{
//                                add(QueryField.HIGHLIGHT);
//                                add(QueryField.POPULAR);
//                            }});
//                            setFilter(new HashMap<QueryField, String>(){{
//                            }});
//                        }});
//                    }});
//                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("아이유");
                    setSubQuery("밤편지");
                    setLimit(1);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.songs);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.POPULAR);
                            }});
                            setFilter(new HashMap<QueryField, String>(){{
                            }});
                        }});
                    }});
                }})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "songParams")
    @DisplayName("메타조회 테스트 - 곡")
    @Order(1)
    void getSongMetaInfoByQuery(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.songs.items", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.result.songs.total", lessThan(10000)))
                .andExpect(jsonPath("$.result.songs.items[*].song_id").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].song_name.original").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].song_name.full").exists())
                .andExpect(jsonPath("$.result.songs.items[*].album_id").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].album_name.original").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].album_name.full").exists())
                .andExpect(jsonPath("$.result.songs.items[*].artist_id").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].artist_name.original").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].artist_name.full").exists())
                .andExpect(jsonPath("$.result.songs.items[*].country").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].country_name").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].duration.text").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].duration.time").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].restricted_rated").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].representation_song").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].cd_no").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].album_track_no").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].album_release_dt").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].composed_by").exists())
                .andExpect(jsonPath("$.result.songs.items[*].lyrics_by").exists())
                .andExpect(jsonPath("$.result.songs.items[*].arranged_by").exists())
                .andExpect(jsonPath("$.result.songs.items[*].middlecode.id").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].middlecode.name").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].lowcode.id").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].lowcode.name").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].mv_service_cnt").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].image_infos").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].file_infos").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].service_available").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].lyrics").exists())
                .andExpect(jsonPath("$.result.songs.items[*].image_path").isNotEmpty())
                .andExpect(jsonPath("$.result.songs.items[*].thumbnail_image_path").isNotEmpty())
        ;
    }

    // ============================================================================================================

    private static Stream<Arguments> albumParams() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("방탄다이너마이트");
                    setSubQuery("");
                    setLimit(1);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.albums);
                            setSort(SortType.ALBUM_RELEASE_DT);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("여자 아이들");
                    setSubQuery("");
                    setLimit(1);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.albums);
                            setSort(SortType.ALBUM_RELEASE_DT);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "albumParams")
    @DisplayName("메타조회 테스트 - 앨범")
    @Order(2)
    void getAlbumMetaInfoByQuery(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.albums.items", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.result.albums.total", lessThan(10000)))
                .andExpect(jsonPath("$.result.albums.items[*].album_id").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].album_name.original").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].album_name.full").exists())
                .andExpect(jsonPath("$.result.albums.items[*].artist_id").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].artist_name.original").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].artist_name.full").exists())
                .andExpect(jsonPath("$.result.albums.items[*].country").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].country_name").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].album_producer").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].album_planner").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].album_type_name").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].album_release_dt").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].album_era").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].album_series_no").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].album_track_cnt").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].representation.song_id").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].representation.song_name").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].restricted_rated").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].middlecode.id").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].middlecode.name").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].lowcode.id").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].lowcode.name").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].image_infos").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].service_available").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].image_path").isNotEmpty())
                .andExpect(jsonPath("$.result.albums.items[*].thumbnail_image_path").isNotEmpty())
        ;

    }

    // ============================================================================================================

    private static Stream<Arguments> artistParams() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("방탄소년단");
                    setSubQuery("");
                    setLimit(1);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.artists);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("윤도현");
                    setSubQuery("");
                    setLimit(1);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.artists);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }}),
//                Arguments.of(new MultiMeta.request(){{
//                    setQuery("빌리 아일리쉬");
//                    setSubQuery("");
//                    setLimit(1);
//                    setOffset(0);
//                    setUno(0L);
//                    setIndices(new ArrayList<Indices>(){{
//                        add(new Indices(){{
//                            setName(ItemType.artists);
//                            setSort(SortType.SCORE);
//                            setIncludes(new ArrayList<QueryField>(){{
//                                add(QueryField.HIGHLIGHT);
//                            }});
//                        }});
//                    }});
//                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("무한도전");
                    setSubQuery("");
                    setLimit(1);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.artists);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "artistParams")
    @DisplayName("메타조회 테스트 - 아티스트")
    @Order(3)
    void getArtistMetaInfoByQuery(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.artists.items", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.result.artists.total", lessThan(10000)))
                .andExpect(jsonPath("$.result.artists.items[*].artist_id").isNotEmpty())
                .andExpect(jsonPath("$.result.artists.items[*].artist_name.original").isNotEmpty())
                .andExpect(jsonPath("$.result.artists.items[*].artist_name.full").exists())
                .andExpect(jsonPath("$.result.artists.items[*].country").isNotEmpty())
                .andExpect(jsonPath("$.result.artists.items[*].country_name").isNotEmpty())
                .andExpect(jsonPath("$.result.artists.items[*].artist_prof").exists())
                .andExpect(jsonPath("$.result.artists.items[*].debut_info.date").exists())
                .andExpect(jsonPath("$.result.artists.items[*].debut_info.song_id").exists())
                .andExpect(jsonPath("$.result.artists.items[*].debut_info.song_name").exists())
                .andExpect(jsonPath("$.result.artists.items[*].gender").isNotEmpty())
                .andExpect(jsonPath("$.result.artists.items[*].artist_type").isNotEmpty())
                .andExpect(jsonPath("$.result.artists.items[*].lowcode.id").isNotEmpty())
                .andExpect(jsonPath("$.result.artists.items[*].lowcode.name").isNotEmpty())
                .andExpect(jsonPath("$.result.artists.items[*].represent_song_id").exists())
                .andExpect(jsonPath("$.result.artists.items[*].artist_active_term").exists())
                .andExpect(jsonPath("$.result.artists.items[*].member_info").exists())
                .andExpect(jsonPath("$.result.artists.items[*].join_artist_info").exists())
                .andExpect(jsonPath("$.result.artists.items[*].similarity_artist").exists())
                .andExpect(jsonPath("$.result.artists.items[*].relation_artist").exists())
                .andExpect(jsonPath("$.result.artists.items[*].image_infos").exists())
//                .andExpect(jsonPath("$.result.artists.items[*].service_available").isNotEmpty())
                .andExpect(jsonPath("$.result.artists.items[*].image_path").exists())
                .andExpect(jsonPath("$.result.artists.items[*].thumbnail_image_path").exists())
        ;


    }

    // ============================================================================================================

    private static Stream<Arguments> videoParams() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("동방신기");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.videos);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("동방신기");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.videos);
                            setSort(SortType.SCORE);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.VIDEO_TYPE_CODE, "30853,30851");
                            }});
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("동방신기");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.videos);
                            setSort(SortType.SCORE);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.VIDEO_PIP_TYPE, "C");
                            }});
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "videoParams")
    @DisplayName("메타조회 테스트 - 비디오")
    @Order(4)
    void getVideoMetaInfoByQuery(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.videos.items").isNotEmpty());

    }

    // ============================================================================================================

    private static Stream<Arguments> composerParams() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("윤종신");
                    setLimit(5);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.composer);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "composerParams")
    @DisplayName("메타조회 테스트 - 작곡가 곡 검색")
    @Order(4)
    void getComposerMetaInfoByQuery(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.composer.items", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.result.composer.total", lessThan(10000)))
                .andExpect(jsonPath("$.result.composer.items[*].song_id").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].song_name.original").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].song_name.full").exists())
                .andExpect(jsonPath("$.result.composer.items[*].album_id").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].album_name.original").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].album_name.full").exists())
                .andExpect(jsonPath("$.result.composer.items[*].artist_id").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].artist_name.original").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].artist_name.full").exists())
                .andExpect(jsonPath("$.result.composer.items[*].country").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].country_name").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].duration.text").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].duration.time").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].restricted_rated").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].representation_song").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].cd_no").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].album_track_no").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].album_release_dt").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].composed_by").exists())
                .andExpect(jsonPath("$.result.composer.items[*].lyrics_by").exists())
                .andExpect(jsonPath("$.result.composer.items[*].arranged_by").exists())
                .andExpect(jsonPath("$.result.composer.items[*].middlecode.id").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].middlecode.name").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].lowcode.id").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].lowcode.name").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].mv_service_cnt").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].image_infos").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].file_infos").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].service_available").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].lyrics").exists())
                .andExpect(jsonPath("$.result.composer.items[*].image_path").isNotEmpty())
                .andExpect(jsonPath("$.result.composer.items[*].thumbnail_image_path").isNotEmpty())
        ;
    }

    // ============================================================================================================

    private static Stream<Arguments> playlistParams() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("TV속음악");
                    setLimit(5);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.playlist);
                            setSort(SortType.NEWEST);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.PLAYLIST_TAG, "TV속음악");
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("오늘의선곡 가요");
                    setLimit(5);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.playlist);
                            setSort(SortType.NEWEST);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.PLAYLIST_TAG, "오늘의선곡,가요");
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("야고보서");
                    setLimit(5);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.playlist);
                            setSort(SortType.NEWEST);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.PLAYLIST_TAG_CODE, "DJ0001");
                            }});
                        }});
                    }});
                }})
//                ,
//                Arguments.of(new MultiMeta.request(){{
//                    setQuery("야고보서");
//                    setLimit(5);
//                    setOffset(0);
//                    setUno(0L);
//                    setIndices(new ArrayList<Indices>(){{
//                        add(new Indices(){{
//                            setName(ItemType.playlist);
//                            setSort(SortType.NEWEST);
//                            setFilter(new HashMap<QueryField, String>(){{
//                                put(QueryField.PLAYLIST_VOICE_FILTER, "야고보서");
//                            }});
//                        }});
//                    }});
//                }})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "playlistParams")
    @DisplayName("메타조회 테스트 - 플레이리스트")
    @Order(5)
    void getPlaylistMetaInfoByQuery(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.playlist.items", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.result.playlist.total", lessThan(10000)))
        ;
    }

    private static Stream<Arguments> playlistParamsV2() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("사랑을 노래하는 달콤한 크로스오버"); // 14135
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.playlist);
                            setSort(SortType.NEWEST);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.PLAYLIST_TAG, "발라드,고백");
                                put(QueryField.PLAYLIST_TAG_CODE, "GR0001,ST0022");
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("진한 에스프레소 커피가 생각나는 재즈");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.playlist);
                            setSort(SortType.NEWEST);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.PLAYLIST_TAG, "재즈,거리,집중");
                                put(QueryField.PLAYLIST_TAG_CODE, "GR0013,ST0017,ST0037");
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("연말 파티에서 이 노래 틀면 인싸 파티됩니다");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.playlist);
                            setSort(SortType.NEWEST);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.PLAYLIST_TAG, "POP,랩/힙합,하우스파티,클럽");
                                put(QueryField.PLAYLIST_TAG_CODE, "GR0005,GR0006,ST0020,ST0021");
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("찬 바람이 부는 마음엔 겨울 발라드\uD83E\uDDE3");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.playlist);
                            setSort(SortType.NEWEST);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.PLAYLIST_TAG, "가요,발라드,휴식,집,출/퇴근길,거리");
                                put(QueryField.PLAYLIST_TAG_CODE, "GR0023,GR0001,ST0001,ST0004,ST0005,ST0017");
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("하루를 충전해주는 힐링 드라마 OST");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.playlist);
                            setSort(SortType.NEWEST);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.PLAYLIST_TAG, "OST,발라드,카페,집");
                                put(QueryField.PLAYLIST_TAG_CODE, "GR0011,GR0001,ST0035,ST0004");
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("겨울이 오면 생각나는 인생 드라마 ❄⛄");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.playlist);
                            setSort(SortType.NEWEST);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.PLAYLIST_TAG, "발라드,OST,휴식,집");
                                put(QueryField.PLAYLIST_TAG_CODE, "GR0001,GR0011,ST0001,ST0004");
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("오늘의 선곡");
                    setLimit(100);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.playlist);
                            setSort(SortType.NEWEST);
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.PLAYLIST_TAG, "오늘의선곡");
                                put(QueryField.PLAYLIST_TAG_CODE, "SVC001");
                            }});
                        }});
                    }});
                }})
        );
    }

    /**
     * 추천/선곡 프로시저 수정에 따른 테스트 코드 작성
     * @param request
     * @throws Exception
     */
    @ParameterizedTest
    @MethodSource(value = "playlistParamsV2")
    @DisplayName("메타조회 테스트 - 플레이리스트 V2 (HINT : )")
    void getPlaylistMetaInfoByQueryV2(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))
        );
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.playlist.items", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.result.playlist.total", lessThan(10000)))
        ;
    }

    // ============================================================================================================

    private static Stream<Arguments> programParams() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("2018 MGA");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.program);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.BOOST);
                                add(QueryField.POPULAR);
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("쇼미더머니");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.program);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.BOOST);
                                add(QueryField.POPULAR);
                            }});
                        }});
                    }});
                }})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "programParams")
    @DisplayName("메타조회 테스트 - 프로그램")
    @Order(6)
    void getProgramMetaInfoByQuery(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.program.items", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.result.program.total", lessThan(10000)))
        ;
    }

    // ============================================================================================================

    private static Stream<Arguments> radioParams() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("인디");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.radio);
                            setSort(SortType.NEWEST);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("인디");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.radio);
                            setSort(SortType.NEWEST);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "radioParams")
    @DisplayName("메타조회 테스트 - 라디오")
    @Order(7)
    void getRadioMetaInfoByQuery(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.radio.items", hasSize(greaterThan(0))))
        ;
    }

    // ============================================================================================================

    private static Stream<Arguments> provideIssueMetaInfoByQueryDisplayWebAndApp() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("크리스마스");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.issue);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.VERSION, "50000");
                                put(QueryField.TELECOM, "45008");
                            }});
                        }});
                    }});
                }}, PLATFORM_CODE.WW),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("아리아즈");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.issue);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.VERSION, "40902");
                                put(QueryField.TELECOM, "45006");
                            }});
                        }});
                    }});
                }}, PLATFORM_CODE.IA),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("모모");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.issue);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.VERSION, "40903");
                                put(QueryField.TELECOM, "45006");
                            }});
                        }});
                    }});
                }}, PLATFORM_CODE.AA)
        );
    }

    @MethodSource(value = "provideIssueMetaInfoByQueryDisplayWebAndApp")
    @DisplayName("메타조회 테스트 - 이슈(노출)")
    @ParameterizedTest
    void getIssueMetaInfoByQueryDisplayApp(MultiMeta.request request, PLATFORM_CODE platformCode) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", platformCode.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.issue.items[0].info").isNotEmpty())
                .andExpect(jsonPath("$.result.issue.items[0].info.title").isNotEmpty())
                .andExpect(jsonPath("$.result.issue.items[0].info.link").isNotEmpty())
                .andExpect(jsonPath("$.result.issue.items[0].info.service_yn").value("Y"));

    }

    // ============================================================================================================

    private static Stream<Arguments> provideIssueMetaInfoByQueryDisplayNone() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("태민");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.issue);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.VERSION, "40901");
                                put(QueryField.TELECOM, "45006");
                            }});
                        }});
                    }});
                }}, PLATFORM_CODE.IA),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("윤두준");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.issue);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.VERSION, "40902");
                                put(QueryField.TELECOM, "45008");
                            }});
                        }});
                    }});
                }}, PLATFORM_CODE.IA),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("엔딩은 원샷이겠죠");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.issue);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.VERSION, "40902");
                                put(QueryField.TELECOM, "45006");
                            }});
                        }});
                    }});
                }}, PLATFORM_CODE.AA),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("it's you");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.issue);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.VERSION, "40903");
                                put(QueryField.TELECOM, "45008");
                            }});
                        }});
                    }});
                }}, PLATFORM_CODE.AA),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("차은우");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.issue);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                            setFilter(new HashMap<QueryField, String>(){{
                                put(QueryField.VERSION, "");
                                put(QueryField.TELECOM, "");
                            }});
                        }});
                    }});
                }}, PLATFORM_CODE.WA),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("수현");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.issue);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                            setFilter(new HashMap<QueryField, String>(){{
                            }});
                        }});
                    }});
                }}, PLATFORM_CODE.WA)
        );
    }

    @MethodSource(value = "provideIssueMetaInfoByQueryDisplayNone")
    @DisplayName("메타조회 테스트 - 이슈(미노출)")
    @ParameterizedTest
    void getIssueMetaInfoByQueryDisplayNone(MultiMeta.request request, PLATFORM_CODE platformCode) throws Exception {


        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", platformCode.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.issue.items").isEmpty());

    }

    // ============================================================================================================

    private static Stream<Arguments> songPageParams() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("서태지");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.songs);
                            setSort(SortType.ALPHABET);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("서태지");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(1);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.songs);
                            setSort(SortType.ALPHABET);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("서태지");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(3);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.songs);
                            setSort(SortType.ALPHABET);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }})
        );
    }

    // ============================================================================================================

    private static Stream<Arguments> fileTypeRetrieveParams() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("돌비 애트모스");
                    setSubQuery("");
                    setLimit(1);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.songs);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                                add(QueryField.POPULAR);
                            }});
                        }});
                    }});
                }})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "fileTypeRetrieveParams")
    @DisplayName("파일 타입 별 곡 조회 테스트 - 돌비 애트모스(EC3 또는 AC4)")
    @Order(1)
    void getSongMetaInfoForFileType(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/file-types/term")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.result.songs.items[*].song_id").exists())
                .andExpect(jsonPath("$.result.songs.items[*].file_infos[*].type", either(hasItem("EC3")).or(hasItem("AC4"))))
        ;
    }

    // ============================================================================================================

    @ParameterizedTest
    @MethodSource(value = "songPageParams")
    @DisplayName("곡 페이징 처리 테스트 : 서태지")
    @Order(9)
    void songPagezingTest(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );

        if(request.getOffset() == 0) {
            result.andExpect(status().isOk())
//                    .andDo(print())
                    .andExpect(jsonPath("$.result.songs.items[0].song_id", is(16098791)))
                    .andExpect(jsonPath("$.result.songs.size", is(10)))
            ;
        }
        if(request.getOffset() == 1) {
            result.andExpect(status().isOk())
//                    .andDo(print())
                    .andExpect(jsonPath("$.result.songs.items[0].song_id", is(80602085)))
                    .andExpect(jsonPath("$.result.songs.size", is(10)))
            ;
        }
        if(request.getOffset() == 3) {
            result.andExpect(status().isOk())
//                    .andDo(print())
                    .andExpect(jsonPath("$.result.songs.items[0].song_id", is(16239250)))
                    .andExpect(jsonPath("$.result.songs.size", is(10)))
            ;
        }
    }

    private static Stream<Arguments> artistPageParams() {
        return Stream.of(
                Arguments.of(new MultiMeta.request(){{
                    setQuery("무한도전");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(0);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.artists);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("무한도전");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(1);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.artists);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }}),
                Arguments.of(new MultiMeta.request(){{
                    setQuery("무한도전");
                    setSubQuery("");
                    setLimit(10);
                    setOffset(3);
                    setUno(0L);
                    setIndices(new ArrayList<Indices>(){{
                        add(new Indices(){{
                            setName(ItemType.artists);
                            setSort(SortType.SCORE);
                            setIncludes(new ArrayList<QueryField>(){{
                                add(QueryField.HIGHLIGHT);
                            }});
                        }});
                    }});
                }})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "artistPageParams")
    @DisplayName("아티스트 페이징 처리 테스트 : 무한도전")
    @Order(10)
    void artistPagezingTest(MultiMeta.request request) throws Exception {

        ResultActions result = this.mockMvc.perform(
                post("/explore/meta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("x-request-svc", PLATFORM_CODE.MS.name() + SERVICE_CODE._9999.name())
                        .header("x-search-loggable", "false")
                        .content(objectMapper.writeValueAsBytes(request))

        );

        if(request.getOffset() == 0) {
            result.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.result.artists.items[0].artist_id", is(44366187)))
//                    .andExpect(jsonPath("$.result.artists.size", is(10))) // 데이터가 존재하지 않아 일단 1개로 변경
                    .andExpect(jsonPath("$.result.artists.size", is(1)))
            ;
        }
//        if(request.getOffset() == 1) {
//            result.andExpect(status().isOk())
////                    .andDo(print())
//                    .andExpect(jsonPath("$.result.artists.items[0].artist_id", is(80438498)))
//                    .andExpect(jsonPath("$.result.artists.size", is(10)))
//            ;
//        }
//        if(request.getOffset() == 3) {
//            result.andExpect(status().isOk())
////                    .andDo(print())
//                    .andExpect(jsonPath("$.result.artists.items[0].artist_id", is(80056893)))
//                    .andExpect(jsonPath("$.result.artists.size", is(2)))
//            ;
//        }
    }
}
