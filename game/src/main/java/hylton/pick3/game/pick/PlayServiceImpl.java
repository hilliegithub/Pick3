package hylton.pick3.game.pick;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hylton.pick3.game.rng.RngService;

@Service
public class PlayServiceImpl implements PlayService {

    private final RngService rngService;
    private final PickService pickService;

    public PlayServiceImpl(RngService rngService, PickService pickService) {
        this.rngService = rngService;
        this.pickService = pickService;
    }

    @Override
    public Play generatePlay(PlayDTO playAttemptDTO) {
        Set<Integer> digits = rngService.randomDigits(playAttemptDTO.getDigit(), 2);

        Play play = new Play(null, null, digits, playAttemptDTO.getPickId(), LocalDateTime.now(), false);

        return play;
    }

    @Override
    public Play VerifyPlayIsMatchingToCurrentPick(Play play) {
        Pick currentPick = pickService.getCurrentPick();

        if (currentPick.getDigits().equals(play.getDigits())) {
            // if they match, then the player wins
            System.out.println("The picks match - VerifyPlayIsMatchingToCurrentPick");
            Play playUpdated = new Play(play.getId(), play.getPlayerId(), play.getDigits(), play.getPickId(),
                    play.getCreatedAt(), true);
            return playUpdated;
        }

        // ELSE - player did not win, so return play

        return play;
    }

}
