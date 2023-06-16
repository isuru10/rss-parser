package tech.isuru.rssparser.feed;

import com.sun.syndication.io.FeedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FeedItemService {

    private final FeedItemRepository feedItemRepository;
    private final FeedParser feedParser;

    Logger logger = LoggerFactory.getLogger(FeedItemService.class);

    private final String url;

    public FeedItemService(FeedItemRepository feedItemRepository, FeedParser feedParser, Environment env) {
        this.feedItemRepository = feedItemRepository;
        this.feedParser = feedParser;
        this.url = env.getProperty("feed.url");
    }

    @Scheduled(fixedRate = 5_000) // TODO: Change fixedRate to 300_000
    public void pollRssFeed() {
        try {
            List<FeedItem> feedItems = feedParser.getFeedItems(url);
            feedItems.forEach(this::saveOrUpdateItem);
            logger.info("Poll successful");
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
    }

    public FeedItem saveOrUpdateItem(FeedItem item) {
        FeedItem existingItem = feedItemRepository.findFeedItemByTitle(item.getTitle());
        if(existingItem != null) {
            existingItem.setDescription(item.getDescription());
            existingItem.setPublishedDate(item.getPublishedDate());
            existingItem.setUpdatedDate(item.getUpdatedDate());
            return feedItemRepository.save(existingItem);
        } else {
            return feedItemRepository.save(item);
        }
    }

    public Page<FeedItem> getItems(int page, int size, Sort.Direction direction, String sortByField) {
        return feedItemRepository.findAll(PageRequest.of(page, size, direction, sortByField));
    }
}
