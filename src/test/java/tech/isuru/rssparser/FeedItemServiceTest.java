package tech.isuru.rssparser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.env.Environment;
import tech.isuru.rssparser.feed.FeedItem;
import tech.isuru.rssparser.feed.FeedItemRepository;
import tech.isuru.rssparser.feed.FeedItemService;
import tech.isuru.rssparser.feed.FeedParser;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FeedItemServiceTest {

    // TODO: Use given when then syntax
    private final FeedItemRepository feedItemRepository = Mockito.mock(FeedItemRepository.class);
    private final FeedParser feedParser = Mockito.mock(FeedParser.class);
    private final Environment env = Mockito.mock(Environment.class);

    private FeedItemService feedItemService;

    @BeforeEach
    void initFeedItemService() {
        feedItemService = new FeedItemService(feedItemRepository, feedParser, env);
    }

    @Test
    void userIsSavedWithGivenData() {
        FeedItem feedItem = new FeedItem("Title", "Description", new Date(), new Date());
        when(feedItemRepository.save(any(FeedItem.class))).then(returnsFirstArg());
        FeedItem savedItem = feedItemService.saveOrUpdateItem(feedItem);
        assertEquals(savedItem.getTitle(), feedItem.getTitle());
        assertEquals(savedItem.getDescription(), feedItem.getDescription());
        assertEquals(savedItem.getPublishedDate(), feedItem.getPublishedDate());
    }

    @Test
    void rssFeedIsPolled() {
        feedItemService.pollRssFeed();
    }
}