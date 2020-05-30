package cc.ewqr.dto.post;

import cc.ewqr.domain.post.Post;

public class PostSaveRequestDto {
    private String title;
    private String content;
    private String author;

    public PostSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    private PostSaveRequestDto(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.author = builder.author;
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

    public Post toEntity() {
        return new Post.Builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
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

        public PostSaveRequestDto build() {
            return new PostSaveRequestDto(this);
        }
    }
}
