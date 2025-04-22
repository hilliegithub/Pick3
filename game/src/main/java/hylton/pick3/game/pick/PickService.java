package hylton.pick3.game.pick;

public interface PickService {
    /**
     * Get the current valid Pick.
     * A Pick is a set of randomly generated unique digits that
     * are valid for a set amount of time. If current pick is expired,
     * then a new set of picks showed be generated.
     */
    public Pick getCurrentPick();

    /**
     * Generate a new set of digits for the Pick.
     * This will generate new picks for a set time period
     */
    public Pick generatePick();
}
