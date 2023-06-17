package tech.isuru.rssparser.feed;

import com.sun.syndication.io.FeedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FeedItemServiceTest {

    private final FeedItemRepository feedItemRepository = Mockito.mock(FeedItemRepository.class);
    private final FeedParser feedParser = Mockito.mock(FeedParser.class);
    private final Environment env = Mockito.mock(Environment.class);
    private final String feedUrl = "https://feeds.simplecast.com/54nAGcIl";

    private FeedItemService feedItemService;

    @BeforeEach
    void initFeedItemService() {
        when(env.getProperty(anyString())).thenReturn(feedUrl);
        feedItemService = new FeedItemService(feedItemRepository, feedParser, env);
    }

    @Test
    void whenPollRssFeedIsCalled_thenGetFeedItemsIsCalledOnce() throws FeedException, IOException {
        feedItemService.pollRssFeed();
        verify(feedParser, times(1)).getFeedItems(feedUrl);
    }

    @Test
    void givenDefaultItemDetails_whenGetItemsIsCalled_thenDefaultPageIsReturned() {
        List<FeedItem> feedItems = new ArrayList<>();
        feedItems.add(new FeedItem("Title1", "description1", new Date(), null));
        feedItems.add(new FeedItem("Title2", "description2", new Date(), null));
        feedItems.add(new FeedItem("Title3", "description3", new Date(), null));
        feedItems.add(new FeedItem("Title4", "description4", new Date(), null));
        feedItems.add(new FeedItem("Title5", "description5", new Date(), null));
        feedItems.add(new FeedItem("Title6", "description6", new Date(), null));
        feedItems.add(new FeedItem("Title7", "description7", new Date(), null));
        feedItems.add(new FeedItem("Title8", "description8", new Date(), null));
        feedItems.add(new FeedItem("Title9", "description9", new Date(), null));
        feedItems.add(new FeedItem("Title10", "description10", new Date(), null));

        Page<FeedItem> expectedItems = new PageImpl<>(feedItems);
        when(feedItemRepository.findAll(any(PageRequest.class))).thenReturn(expectedItems);

        int page = 0;
        int size = 10;
        Sort.Direction direction = Sort.Direction.DESC;
        String sort = "publishedDate";
        Page<FeedItem> actualItems = feedItemService.getItems(page, size, direction, sort);
        assertEquals(actualItems, expectedItems);
    }
}