package tech.isuru.rssparser.feed;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Component
public class FeedParser {

    public List<FeedItem> getFeedItems(String url) throws FeedException, IOException {
        URL feedUrl = new URL(url);
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedUrl));
        List<SyndEntry> entries = feed.getEntries();
        return entries
                .stream()
                .map(this::mapToFeedItem)
                .toList();
    }

    private FeedItem mapToFeedItem(SyndEntry entry) {
        return new FeedItem(entry.getTitle(), entry.getDescription().getValue(), entry.getPublishedDate(), entry.getUpdatedDate());
    }
}
