package it.consulting.coding.demo;

import it.consulting.coding.demo.model.UserDTO;
import it.consulting.coding.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void should_return_create_user() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .lastname("TARFA")
                .birthday("14/02/1995")
                .address("Paris")
                .salary(37L)
                .experiences(1)
                .build();
        Mockito.when(userService.create(userDTO))
                .thenAnswer(invocationOnMock -> {
                    userDTO.setId("azertyuiop");
                    return userDTO;
                });
        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.toJson(userDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.lastname").value(userDTO.getLastname()))
                .andDo(print());
    }

    @Test
    void should_return_update_user() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .lastname("TARFA")
                .birthday("14/02/1995")
                .address("Paris")
                .salary(37L)
                .experiences(1)
                .build();
        Mockito.when(userService.update(userDTO))
                .thenAnswer(invocationOnMock -> {
                    userDTO.setId("azertyuiop");
                    return userDTO;
                });
        mockMvc.perform(put("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.toJson(userDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.lastname").value(userDTO.getLastname()))
                .andDo(print());
    }

    @Test
    void should_return_200_code_state_when_delete_user_by_id() throws Exception {
        Mockito.doNothing().when(userService).delete(Mockito.anyString());
        mockMvc.perform(delete("/api/user/aziteir")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void should_return_list_users() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .lastname("TARFA")
                .birthday("14/02/1995")
                .address("Paris")
                .salary(37L)
                .experiences(1)
                .build();
        List<UserDTO> users = Arrays.asList(userDTO, userDTO, userDTO);
        Mockito.when(userService.findAll(Mockito.anyInt(), Mockito.anyInt())).thenReturn(users);
        mockMvc.perform(get("/api/user")
                .param("page", "0")
                .param("size", "5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(print());
    }
}

