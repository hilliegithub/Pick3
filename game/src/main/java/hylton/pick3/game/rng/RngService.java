package hylton.pick3.game.rng;

import java.util.Set;

public interface RngService {
    /**
     * @return unique, randomly generated digits between 0 and 9
     */
    Set randomDigits(int numOfDigits);
}
