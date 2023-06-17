package tech.isuru.rssparser.feed;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get the latest items in the feed")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Items retrieved successfully" ),
            @ApiResponse(
                    responseCode = "400",
                    description = "One or more request parameters are invalid",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"message\": \"Page index must not be less than zero\", \"content\": null}")
                    )
            )
    })
    @GetMapping
    public ResponseEntity<FeedResponse> getSortedItems(
            @Parameter(description = "Page number of the result. Starts at 0") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "Size of the page") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "Field to sort by") @Schema(allowableValues = {"publishedDate", "updatedDate", "title", "description"}) @RequestParam(defaultValue = "publishedDate") String sort,
            @Parameter(description = "Sort order") @Schema(allowableValues = {"asc", "desc"}) @RequestParam(defaultValue = "desc") String direction
    ) {

        FeedResponse response = new FeedResponse();
        Sort.Direction sortDirection;

        // Validate sort direction request parameter
        try {
            sortDirection = Sort.Direction.fromString(direction);
        } catch (IllegalArgumentException e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Page<FeedItem> items;

        // Validate page, size and sort field request parameters
        try {
            items = feedItemService.getItems(page, size, sortDirection, sort);
        } catch (IllegalArgumentException e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.setMessage("Success");
        response.setContent(items);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
