package edu.utdallas.bbsm.follower;

import java.util.Objects;

public class Follow {
    private String follower;
    private String followed;

    public Follow(String follower, String followed) {
        this.follower = follower;
        this.followed = followed;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowed() {
        return followed;
    }

    public void setFollowed(String followed) {
        this.followed = followed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follow that = (Follow) o;
        return Objects.equals(follower, that.follower) && Objects.equals(followed, that.followed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, followed);
    }

    @Override
    public String toString() {
        return "Follow{" +
            "follower='" + follower + '\'' +
            ", followed='" + followed + '\'' +
            '}';
    }
}
