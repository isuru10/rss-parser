package tech.isuru.rssparser.feed;

import com.sun.syndication.io.FeedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;

class FeedParserTest {

    private FeedParser feedParser;

    @BeforeEach
    void initFeedParser() {
        feedParser = new FeedParser();
    }

    @Test
    void givenValidFeedUrl_whenGetFeedItemsIsCalled_thenReturnedFeedItemsAreNotNull() throws FeedException, IOException {
        String url = "https://feeds.simplecast.com/54nAGcIl";
        List<FeedItem> feedItems = feedParser.getFeedItems(url);
        assertThat(feedItems.size(), greaterThan(0));
        assertNotNull(feedItems);
    }

    @Test
    void givenValidNonFeedUrl_whenGetFeedItemsIsCalled_thenFeedExceptionIsThrown() {
        String url = "https://example.com/";
        assertThrows(FeedException.class, () ->{feedParser.getFeedItems(url);});
    }

    @Test
    void givenInvalidUrl_whenGetFeedItemsIsCalled_thenIOExceptionIsThrown() {
        String url = "invalid";
        assertThrows(IOException.class, () ->{feedParser.getFeedItems(url);});
    }
}