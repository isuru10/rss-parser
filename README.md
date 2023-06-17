# RSS Parser

A Spring Boot application that polls an RSS feed every 5 minutes, and stores any
items or updates in an in-memory H2 database.

## Running the Development Server

Go to the project root directory and run the following command to run the development server.

```shell
./mvnw spring-boot:run
```

## Running Tests

Go to the project root directory and run the following command to run the unit tests and integration tests

```shell
./mvnw test
```

## Accessing the API Documentation

This API is documented with OpenAPI and Swagger. Once the development server is up and running, use the following URL to access the interactive Swagger documentation.

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

# REST API Endpoints

## Get the latest items in the feed

This request will return the 10 latest items in the database.

### Request

`GET /items`

```shell
curl -X 'GET' \
  'http://localhost:8080/items' \
  -H 'accept: application/json'
```

### Response

```shell
HTTP/1.1 200 OK
Date: Sat, 17 Jun 2023 10:38:12 GMT
Status: 200 OK
Connection: close
Content-Type: application/json

{
    "message": "Success",
    "content": {
        "content": [
            {
                "id": 1,
                "title": "The Kids Take the Climate Change Fight to Court",
                "description": "<p>This week, a historic case has landed in a Montana courtroom. A group of young environmentalists is suing the state, arguing that its embrace of fossil fuels is destroying pristine environments, upending cultural traditions and robbing young residents of a healthy future.</p><p>David Gelles, a climate correspondent for The Times, explains why the case could be a turning point, and what a win in Montana would mean for the future of the climate fight.</p><p>Guest: <a href=\"https://www.nytimes.com/by/david-gelles\">David Gelles</a>, a climate correspondent for The New York Times.</p><p>Background reading: </p><ul><li><a href=\"https://www.nytimes.com/2023/06/12/us/montana-youth-climate-trial.html?searchResultPosition=1\">The landmark youth climate trial</a>, which has been more than a decade in the making, began on Monday in Montana.</li><li><a href=\"https://www.nytimes.com/2023/03/24/climate/montana-youth-climate-lawsuit.html\">Sixteen young Montanans have sued their state</a>, arguing that its support of fossil fuels violates the state Constitution.</li></ul><p>For more information on today’s episode, visit <a href=\"http://nytimes.com/thedaily?smid=pc-thedaily\">nytimes.com/thedaily</a>. Transcripts of each episode will be made available by the next workday.</p>\n",
                "publishedDate": "2023-06-16",
                "updatedDate": null
            },
            ...
        ],
        "pageable": {
            "sort": {
                "empty": false,
                "unsorted": false,
                "sorted": true
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 10,
            "paged": true,
            "unpaged": false
        },
        "last": false,
        "totalPages": 187,
        "totalElements": 1863,
        "first": true,
        "size": 10,
        "number": 0,
        "sort": {
            "empty": false,
            "unsorted": false,
            "sorted": true
        },
        "numberOfElements": 10,
        "empty": false
    }
}
```

This request can also be coupled with a set of request parameters to sort and order according to given fields.

| Parameter   | Description                                                                        |
|-------------|------------------------------------------------------------------------------------|
| `page`      |   Page number of the result. Starts at 0                                           |
| `size`      | Size of the page                                                                   |
| `sort`      | Field to sort by. Available values: publishedDate, updatedDate, title, description |
| `direction` | Sort order. Available values: asc, desc                                            |

### Request

```shell
curl -X 'GET' \
  'http://localhost:8080/items?page=0&size=5&sort=title&direction=asc' \
  -H 'accept: application/json'
```

### Response

```shell
HTTP/1.1 200 OK
Date: Sat, 17 Jun 2023 10:48:23 GMT
Status: 200 OK
Connection: close
Content-Type: application/json

{
    "message": "Success",
    "content": {
        "content": [
            {
                "id": 1273,
                "title": "$1 Billion in Losses: A Decade of Trump’s Taxes",
                "description": "<p>In October, The New York Times published an investigation into the tax returns of President Trump’s father, revealing the president’s past involvement in tax evasion and stark inconsistencies in his account of his success. Two reporters who broke that story are back with new information about the president’s own taxes. Guests: Russ Buettner and Susanne Craig, investigative reporters for The Times. For more information on today’s episode, visit <a href=\"https://www.nytimes.com/thedaily\" target=\"_blank\">nytimes.com/thedaily</a>. </p><p>Background reading: </p><ul><li>The Times has obtained figures from President Trump’s federal income tax returns from 1985 through 1994. They<a href=\"https://www.nytimes.com/interactive/2019/05/07/us/politics/donald-trump-taxes.html?smid=pc-thedaily\" target=\"_blank\"> paint a far bleaker picture of his financial condition than was previously known</a>.</li><li>Here are<a href=\"https://www.nytimes.com/2019/05/07/us/trump-tax-figures.html?smid=pc-thedaily\" target=\"_blank\"> five takeaways of what the numbers show</a>.</li><li>Listen to an episode of “The Daily” about<a href=\"https://www.nytimes.com/2018/10/03/podcasts/the-daily/donald-trump-fred-trump-tax-money.html?smid=pc-thedaily\" target=\"_blank\"> Mr. Trump’s participation in dubious tax schemes during the 1990s</a>. </li></ul>\n",
                "publishedDate": "2019-05-08",
                "updatedDate": null
            },
            ...
        ],
        "pageable": {
            "sort": {
                "empty": false,
                "unsorted": false,
                "sorted": true
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 5,
            "paged": true,
            "unpaged": false
        },
        "last": false,
        "totalPages": 373,
        "totalElements": 1863,
        "first": true,
        "size": 5,
        "number": 0,
        "sort": {
            "empty": false,
            "unsorted": false,
            "sorted": true
        },
        "numberOfElements": 5,
        "empty": false
    }
}
```

# Future Work

- Further validations can be implemented, particularly on the FeedParser.
- More unit tests and integration tests can be added to cover edge cases.
- Documentation can be improved with more examples and descriptions.