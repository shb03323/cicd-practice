package practice.cicd;

import java.util.Objects;

public class Crew {

    private final String name;
    private final String nickname;
    private final int age;

    public Crew(final String name, final String nickname, final int age) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        validateNickname(nickname);
    }

    private void validateNickname(final String nickname) {
        if (nickname.length() > 4) {
            throw new IllegalArgumentException("닉네임의 길이는 4를 초과할 수 없습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Crew crew)) return false;
        return age == crew.age && Objects.equals(name, crew.name) && Objects.equals(nickname, crew.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nickname, age);
    }
}
