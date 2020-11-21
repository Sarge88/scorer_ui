package gkalapis.scorerui.model.api;

import java.util.Objects;

public class User {

    private Long id;

    private String name;

    String password;

    private int points;

    public User() {}

    public User(Long id, String name, String password, int points) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return points == user.points &&
                id.equals(user.id) &&
                name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, points);
    }
}

