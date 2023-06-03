package com.restfulmovies.restfulmovies.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfulmovies.restfulmovies.model.dto.genre.CreateGenreDto;
import com.restfulmovies.restfulmovies.model.dto.genre.GenreDto;
import com.restfulmovies.restfulmovies.model.dto.user.AuthUserDto;
import com.restfulmovies.restfulmovies.model.dto.user.UserDto;
import com.restfulmovies.restfulmovies.model.repository.GenreRepository;
import com.restfulmovies.restfulmovies.model.repository.UserRepository;
import com.restfulmovies.restfulmovies.security.SecurityConfig;
import com.restfulmovies.restfulmovies.service.GenreService;
import com.restfulmovies.restfulmovies.service.UserService;
import com.restfulmovies.restfulmovies.service.impl.UserServiceImpl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Set;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCollection;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GenreControllerTest {

//    @LocalServerPort
//    private Integer port;
//
//    @Autowired
//    private GenreRepository genreRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private static final String BASE_URL = "/api/v1";
//    private String token;
//
//    @BeforeEach
//    void setup() throws Exception {
//        RestAssured.baseURI = "http://localhost:" + port + BASE_URL;
//
//        final String user = objectMapper.writeValueAsString(new AuthUserDto("admin", "123"));
//
//        final String accessToken = given()
//                .contentType(JSON)
//                .body(user)
//                .when()
//                .post("/users/auth")
//                .jsonPath()
//                .getString("accessToken");
//
//        token = "Bearer " + accessToken;
//    }
//
//    @Test
//    @Order(1)
//    @Sql("/scripts/init-genres.sql")
//    void getAll()  {
//
//        given()
//                .header(AUTHORIZATION, token)
//                .when()
//                .get("/genres")
//                .then()
//                .statusCode(200)
//                .body(".", hasSize(2));
//    }
//
////    @Test
////    @Order(2)
////    void getOne() {
////
////        given()
////                .header(AUTHORIZATION, token)
////                .when()
////                .get("/genres/{id}", 1)
////                .then()
////                .statusCode(200)
////                .body("name", equalTo("Action"));
////    }
//
//    @Test
//    @Order(3)
//    void create() throws JsonProcessingException {
//
//        final var createDto = objectMapper.writeValueAsString(new CreateGenreDto("Test"));
//
//        final ExtractableResponse<Response> response = given()
//                .header(AUTHORIZATION, token)
//                .contentType(JSON)
//                .body(createDto)
//                .when()
//                .post("/genres")
//                .then()
//                .log().body()
//                .statusCode(201)
//                .extract();
//
//
//
//        given()
//                .header(AUTHORIZATION, token)
//                .when()
//                .get(response.header(LOCATION))
//                .then()
//                .statusCode(200);
//    }

//    @Test
//    @Order(4)
//    void update() throws JsonProcessingException {
//        final var updateDto = objectMapper.writeValueAsString(new CreateGenreDto("Update"));
//
//        given()
//                .header(AUTHORIZATION, token)
//                .contentType(JSON)
//                .body(updateDto)
//                .when()
//                .put("/genres/{id}", 1)
//                .then()
//                .statusCode(200)
//                .body("name", equalTo("Update"));
//    }

//    @Test
//    @Order(5)
//    void delete() throws Exception {
//        given()
//                .header(AUTHORIZATION, token)
//                .when()
//                .delete("/genres/{id}", 1)
//                .then()
//                .statusCode(204);
//
//        assertThatCollection(genreRepository.findAll()).hasSize(23);
//    }
}