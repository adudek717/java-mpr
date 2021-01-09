package pl.pjatk.gameplay.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.PlayerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerService playerService;

    @Test
    void shouldReturnEmptyList() throws Exception {
        mockMvc.perform(get("/player")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldMatchContentForHelloWorld() throws Exception {
        mockMvc.perform(get("/player/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello world")));
    }

    @Test
    void shouldBeError404() throws Exception {
        mockMvc.perform(get("/player/100"))
                .andDo(print())
                .andExpect(status().is(404));
    }

    @Test
    void shouldGetPlayerContent() throws Exception {
        Player player = playerService.save(new Player(1L, "nickname", 100, 10));
        mockMvc.perform(get("/player/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"id\":1,\"nickname\":\"nickname\",\"health\":100,\"attack\":10}")));
    }
}
// 404?
// is present z contentem?