package cc.ewqr.config.auth.dto;

import cc.ewqr.domain.user.Role;
import cc.ewqr.domain.user.User;

import java.util.Map;

public class OAuthAttribute {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String getNameAttributeKey() {
        return nameAttributeKey;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }

    public OAuthAttribute(Builder builder) {
        this.attributes = builder.attributes;
        this.nameAttributeKey = builder.nameAttributeKey;
        this.name = builder.name;
        this.email = builder.email;
        this.picture = builder.picture;
    }

    public static OAuthAttribute of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(registrationId, userNameAttributeName, attributes);
    }

    private static OAuthAttribute ofGoogle(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return new Builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return new User.Builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }

    public static class Builder {
        private Map<String, Object> attributes;
        private String nameAttributeKey;
        private String name;
        private String email;
        private String picture;

        public Builder attributes(Map<String, Object> attribute) {
            this.attributes = attribute;
            return this;
        }

        public Builder nameAttributeKey(String nameAttributeKey) {
            this.nameAttributeKey = nameAttributeKey;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder picture(String picture) {
            this.picture = picture;
            return this;
        }

        public OAuthAttribute build() {
            return new OAuthAttribute(this);
        }
    }
}
