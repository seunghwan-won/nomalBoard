package cc.ewqr.config.auth.dto;

import cc.ewqr.domain.user.User;

import java.io.Serializable;

public class SessionUser implements Serializable {
    private final String picture;
    private final String name;
    private final String email;

    public String getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
