package hylton.pick3.game.rng;

import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RngServiceTest {
    private RngService rngService;

    @Spy
    private Random random;

    @BeforeEach
    public void setUp() {
        rngService = new RngServiceImpl(random);
    }

    @Test
    public void generateRandomDigitsOfRequestedSize() {
        given(random.nextInt(10)).willReturn(9, 8, 4, 2);

        // When we request random (3) digits
        Set<Integer> digits = rngService.randomDigits(3);

        // then the digits should be unique and of the requested amount.
        assertThat(digits).hasSize(3);
        assertThat(digits).containsExactlyInAnyOrder(9, 8, 4);
    }

    @Test
    public void generateRandomDigitsShouldBeBetweenZeroAndNine() {
        given(random.nextInt(10)).willReturn(0, 5, 9);

        // When we request 3 random digits
        Set<Integer> digits = rngService.randomDigits(3);

        // Then the digits should be within the range 0 to 9
        assertThat(digits).hasSize(3);
        assertThat(digits).allMatch(digit -> digit >= 0 && digit <= 9);
    }

    @Test
    public void generateRandomDigitsShouldBeUnique() {
        given(random.nextInt(10)).willReturn(1, 2, 2, 4, 1);

        // When we request 3 random digits
        Set<Integer> digits = rngService.randomDigits(3);

        // Then all digits should be unique
        assertThat(digits).hasSize(3);
    }

    @Test
    public void generateAdditionalRandomDigitsOfRequestedSize() {
        given(random.nextInt(10)).willReturn(8, 4);

        // When we request random (3) digits
        Set<Integer> digits = rngService.randomDigits(9, 2);

        // then the digits should be unique and of the requested amount.
        assertThat(digits).hasSize(3);
        assertThat(digits).containsExactlyInAnyOrder(9, 8, 4);
    }
}
