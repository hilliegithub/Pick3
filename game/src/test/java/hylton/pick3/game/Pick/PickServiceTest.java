package hylton.pick3.game.Pick;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import hylton.pick3.game.pick.Pick;
import hylton.pick3.game.pick.PickService;
import hylton.pick3.game.pick.PickServiceImpl;
import hylton.pick3.game.rng.RngService;

@ExtendWith(MockitoExtension.class)
public class PickServiceTest {
    private PickService pickService;

    @Mock
    public RngService rngService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        rngService = mock(RngService.class);
        pickService = new PickServiceImpl(rngService);
    }

    @Test
    public void testGeneratingPick() {
        // given
        given(rngService.randomDigits(3)).willReturn(Set.of(5, 7, 9));
        LocalDateTime before = LocalDateTime.now();

        // when
        Pick pick = pickService.generatePick();
        LocalDateTime after = LocalDateTime.now();

        // then
        assertThat(pick.getDigits()).containsExactlyInAnyOrder(5, 7, 9);
        assertThat(pick.getTimeEndAt()).isBetween(before, after);
    }

    @Test
    public void testGetCurrentValidPick() {
        // given
        // Have a pick (create pick to pass to constructor)
        LocalDateTime endTime = LocalDateTime.now().plusMinutes(3);
        Pick pick = new Pick(1, Set.of(3, 4, 5), endTime);

        // when
        pickService = new PickServiceImpl(rngService, pick);

        // then
        assertThat(pickService.getCurrentPick().getDigits()).containsExactlyInAnyOrder(3, 4, 5);
        assertThat(pickService.getCurrentPick().getTimeEndAt()).isEqualTo(endTime);
        assertThat(pickService.getCurrentPick().getPickNumber()).isEqualTo(1);
    }

    // Add Test to check for invalid (expired) picks
    @Test
    public void testGetCurrentInValidPick() {
        // given
        // Have a pick (create pick to pass to constructor)
        LocalDateTime endTime = LocalDateTime.now();
        Pick pick = new Pick(1, Set.of(9, 8, 7), endTime.plusMinutes(-3));
        given(rngService.randomDigits(3)).willReturn(Set.of(3, 4, 5));

        // when
        pickService = new PickServiceImpl(rngService, pick);

        // then
        Pick temp = pickService.getCurrentPick();
        // System.out.println("The pick Number (In test 3): " + temp.getPickNumber());
        assertThat(temp.getDigits()).containsExactlyInAnyOrder(3, 4, 5);
        assertThat(temp.getTimeEndAt()).isBetween(endTime, LocalDateTime.now());
        assertThat(pickService.getCurrentPick().getPickNumber()).isEqualTo(2);
    }
}
