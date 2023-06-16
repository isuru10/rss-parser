package tech.isuru.rssparser.feed;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class FeedItemController {

    private final FeedItemService feedItemService;

    public FeedItemController(FeedItemService feedItemService) {
        this.feedItemService = feedItemService;
    }

    @Operation(summary = "Get the 10 newest items in the feed")
    @GetMapping
    public Page<FeedItem> getSortedItems(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "publishedDate") String sort,
            @RequestParam(defaultValue = "desc") String direction) {
        return feedItemService.getItems(page, size, Sort.Direction.fromString(direction), sort);
    }
}
