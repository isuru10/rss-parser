package tech.isuru.rssparser.feed;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedItemRepository extends JpaRepository<FeedItem, Long> {
    FeedItem findFeedItemByTitle(String title);
}
