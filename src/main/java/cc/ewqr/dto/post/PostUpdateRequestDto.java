package cc.ewqr.dto.post;

public class PostUpdateRequestDto {
    private String title;
    private String content;

    public PostUpdateRequestDto() {
    }

    public PostUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    private PostUpdateRequestDto(Builder builder) {
        this.title = builder.title;
        this.content = builder.content;
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "PostUpdateRequestDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public static class Builder {
        private String title;
        private String content;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public PostUpdateRequestDto build() {
            return new PostUpdateRequestDto(this);
        }
    }
}
