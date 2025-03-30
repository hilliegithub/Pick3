package hylton.pick3.game.rng;

import org.springframework.stereotype.Service;

import lombok.val;

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
        return random.nextInt(MAXIMUM_DIGIT + 1 - MINIMUM_DIGIT) + MINIMUM_DIGIT;
    }

    private int validNumberOfDigitsRequest(int numOfDigits) {
        if (numOfDigits < MINIMUM_DIGIT)
            numOfDigits = MINIMUM_DIGIT;
        if (numOfDigits > MAXIMUM_DIGIT)
            numOfDigits = MAXIMUM_DIGIT;
        return numOfDigits;
    }

    private Set<Integer> fillSetWithRandomNumbers(Set<Integer> digits, int amount) {
        for (int i = 0; i < amount; i++) {
            boolean didAddUnique = true;
            do {
                didAddUnique = digits.add(next());
            } while (!didAddUnique);
        }
        return digits;
    }

    @Override
    public Set randomDigits(int numOfDigits) {
        numOfDigits = validNumberOfDigitsRequest(numOfDigits);

        Set<Integer> digits = new HashSet<Integer>();

        return fillSetWithRandomNumbers(digits, numOfDigits);
    }

    @Override
    public Set<Integer> randomDigits(int digit, int numOfDigits) {
        Set<Integer> digits = new HashSet<Integer>();

        digits.add(digit);

        return fillSetWithRandomNumbers(digits, numOfDigits);
    }

}
