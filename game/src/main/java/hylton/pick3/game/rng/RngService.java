package hylton.pick3.game.rng;

import java.util.Set;

public interface RngService {
    /**
     * @param numOfDigits number of unique digits to generate.
     * @return unique, randomly generated digits between 0 and 9
     */
    Set<Integer> randomDigits(int numOfDigits);

    /**
     * Generates additional unique digits between 0 and 9, and adds it to the passed
     * Set object
     *
     * @param numOfDigits is the number of additional unique digits to add to set.
     * @param guess       is the partial generated Set
     * @return unique, randomly generated digits between 0 and 9
     */
    Set<Integer> randomDigits(int digit, int numOfDigits);
}
