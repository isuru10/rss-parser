package tech.isuru.rssparser.feed;

import org.springframework.data.domain.Page;

public class FeedResponse {
    private String message;
    private Page<FeedItem> content;

    public FeedResponse() {
    }

    public FeedResponse(String message, Page<FeedItem> content) {
        this.message = message;
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Page<FeedItem> getContent() {
        return content;
    }

    public void setContent(Page<FeedItem> content) {
        this.content = content;
    }
}
