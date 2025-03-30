package hylton.pick3.game.rng;

import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;
import java.util.HashSet;

@Service
public class RngServiceImpl implements RngService {

    private final static int MINIMUM_DIGIT = 0;
    private final static int MAXIMUM_DIGIT = 9;

    private final Random random;

    RngServiceImpl() {
        this.random = new Random();
    }

    protected RngServiceImpl(final Random random) {
        this.random = random;
    }

    private int next() {
        return random.nextInt(MAXIMUM_DIGIT - MINIMUM_DIGIT) + MINIMUM_DIGIT;
    }

    @Override
    public Set randomDigits(int numOfDigits) {
        // if (numOfDigits < MINIMUM_DIGIT)
        // numOfDigits = MINIMUM_DIGIT;
        // if (numOfDigits > MAXIMUM_DIGIT)
        // numOfDigits = MAXIMUM_DIGIT;
        // Set<Integer> digits = new HashSet<Integer>();
        // for (int i = 0; i < numOfDigits; i++) {
        // boolean didAddUnique = true;
        // do {
        // didAddUnique = digits.add(next());
        // } while (!didAddUnique);
        // }
        // return digits;
        return null;
    }

}
