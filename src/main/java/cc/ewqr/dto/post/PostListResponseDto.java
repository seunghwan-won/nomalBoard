package cc.ewqr.dto.post;

import cc.ewqr.domain.post.Post;

import java.time.LocalDateTime;

public class PostListResponseDto {
    private final String author;
    private final String title;
    private final Long id;
    private final LocalDateTime modifiedDate;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PostListResponseDto{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
