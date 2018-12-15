package top.koguma.gymclub.event;

import top.koguma.gymclub.model.User;

public class ProfileUpdateEvent {

    private User user;

    private static final String DEFAULT_USER_NAME = "Kooguma";
    private static final String DEFAULT_USER_AVATAR_URL
        = "https://avatars0.githubusercontent.com/u/13082276?s=400&u=5a7e33754fd38905a6c69c4eee4eb376493f8823&v=4";

    public ProfileUpdateEvent(User user) {
        this.user = user;
    }

    public String getAvatarUrl() {
        return user == null ? DEFAULT_USER_AVATAR_URL : user.getAvatarUrl();
    }

    public String getUserName() {
        return user == null ? DEFAULT_USER_NAME : user.getUsername();
    }
}
