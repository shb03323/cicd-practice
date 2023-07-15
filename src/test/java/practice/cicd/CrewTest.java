package practice.cicd;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CrewTest {

    @Test
    void 크루의_닉네임의_길이는_4까지다() {
        assertThatThrownBy(() -> new Crew("옥상윤", "아디아디아디", 25))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
