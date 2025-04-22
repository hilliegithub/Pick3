package hylton.pick3.game.pick;

public interface PlayService {
    /**
     * Verifies if the generated play matches the current pick.
     */
    Play VerifyPlayIsMatchingToCurrentPick(Play play);

    /**
     * Generate Play using user's guess along with randomly generated
     *
     */
    Play generatePlay(PlayDTO playAttempt);
}
