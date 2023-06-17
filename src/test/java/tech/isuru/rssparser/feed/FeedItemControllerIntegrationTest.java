package tech.isuru.rssparser.feed;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import tech.isuru.rssparser.RssParserApplication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RssParserApplication.class)
@AutoConfigureMockMvc
class FeedItemControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void whenGetAllItems_thenDefaultPageIsReturned() throws Exception {
        mvc.perform(get("/items"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.content").exists())
                .andExpect(jsonPath("$.content.numberOfElements", is(10)));
    }
}