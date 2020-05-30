package cc.ewqr.domain.post;

import cc.ewqr.domain.BaseTimeEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author;

    public Post() {
    }

    private Post(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.author = builder.author;
    }

    public Long getId() {
        return id;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static class Builder {
        private String title;
        private String content;
        private String author;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder author(String author) {
            this.author = author;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}
