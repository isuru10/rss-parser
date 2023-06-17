CREATE TABLE FEED_ITEM(
    ID int not null AUTO_INCREMENT,
    TITLE varchar(100),
    DESCRIPTION varchar(10000),
    PUBLISHED_DATE date,
    UPDATED_DATE date,
    PRIMARY KEY ( ID )
);