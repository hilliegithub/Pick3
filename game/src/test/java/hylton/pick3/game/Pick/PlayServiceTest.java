package hylton.pick3.game.Pick;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import hylton.pick3.game.pick.Pick;
import hylton.pick3.game.pick.PickService;
import hylton.pick3.game.pick.PickServiceImpl;
import hylton.pick3.game.pick.Play;
import hylton.pick3.game.pick.PlayDTO;
import hylton.pick3.game.pick.PlayService;
import hylton.pick3.game.pick.PlayServiceImpl;
import hylton.pick3.game.rng.RngService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class PlayServiceTest {
    private PlayService playService;

    @Mock
    public RngService rngService;

    @Mock
    public PickService pickService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rngService = mock(RngService.class);
        // pickService = new PickServiceImpl(rngService, new Pick(1, Set.of(3, 4, 5),
        // LocalDateTime.now()));
        pickService = mock(PickService.class);
        playService = new PlayServiceImpl(rngService, pickService);
    }

    @Test
    public void GeneratePlayTest() {
        // given
        PlayDTO testPlayDTO = new PlayDTO("Nicky", 5, 1);
        LocalDateTime playCreatedTime = LocalDateTime.now();
        Play testPlay = new Play(Long.valueOf(1), Long.valueOf(2), Set.of(6, 7, 5), 1, playCreatedTime, false);
        given(rngService.randomDigits(testPlayDTO.getDigit(), 2)).willReturn(Set.of(5, 6, 7));
        // when
        Play resultPlay = playService.generatePlay(testPlayDTO);

        // then
        assertThat(resultPlay.getDigits()).containsExactlyInAnyOrder(testPlay.getDigits().toArray(new Integer[0]));
        assertThat(resultPlay.getCreatedAt()).isBetween(playCreatedTime, LocalDateTime.now());
        assertThat(resultPlay.getPickId()).isEqualTo(testPlayDTO.getPickId());
    }

    // Given a play object, test to verify if play is
    // a valid match or not
    @Test
    public void VerifyPlayIsMatchingToCurrentPickTest() {
        // given
        LocalDateTime playCreatedTime = LocalDateTime.now();
        Play testPlay = new Play(Long.valueOf(1), Long.valueOf(2), Set.of(9, 8, 7), 1, playCreatedTime, false);
        given(pickService.getCurrentPick()).willReturn(new Pick(1, Set.of(9, 8, 7), testPlay.getCreatedAt()));
        // when
        Play resultPlay = playService.VerifyPlayIsMatchingToCurrentPick(testPlay);

        // then
        assertThat(resultPlay.getDigits()).containsExactlyInAnyOrder(testPlay.getDigits().toArray(new Integer[0]));
        assertThat(resultPlay.getCreatedAt()).isBetween(playCreatedTime, LocalDateTime.now());
        assertThat(resultPlay.getPickId()).isEqualTo(testPlay.getPickId());
        assertThat(resultPlay.isPlayAMatch()).isTrue();
    }
}
