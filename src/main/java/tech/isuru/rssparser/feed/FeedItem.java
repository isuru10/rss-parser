package tech.isuru.rssparser.feed;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class FeedItem {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column( length = 10000 )
    private String description;

    @Temporal(TemporalType.DATE)
    private Date publishedDate;

    @Temporal(TemporalType.DATE)
    private Date updatedDate;

    public FeedItem() {
    }

    public FeedItem(String title, String description, Date publishedDate, Date updatedDate) {
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
