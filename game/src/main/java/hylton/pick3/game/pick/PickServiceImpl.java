package hylton.pick3.game.pick;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.stereotype.Service;
import hylton.pick3.game.rng.RngService;

@Service
public class PickServiceImpl implements PickService {
    private Pick tempLocalPick;
    private final RngService rngService;

    public PickServiceImpl(RngService rngService) {
        this.rngService = rngService;
        this.tempLocalPick = new Pick(0, Set.of(1, 2, 3), null);
        System.out.println("PickServiceimpl created");
    }

    // Constructor for testing - To mimick local Pick that would normally be
    // retrieved from some local datastore
    public PickServiceImpl(RngService rngService, Pick tempLocalPick) {
        this.tempLocalPick = tempLocalPick;
        this.rngService = rngService;
    }

    @Override
    public Pick getCurrentPick() {
        if (LocalDateTime.now().isAfter(this.tempLocalPick.getTimeEndAt())) {
            Pick temp = generatePick();
            tempLocalPick = temp;
            return tempLocalPick;
        }
        return this.tempLocalPick;
    }

    @Override
    public Pick generatePick() {
        int newPickNumber = this.tempLocalPick.getPickNumber() + 1;
        Set<Integer> digits = rngService.randomDigits(3);
        tempLocalPick = new Pick(newPickNumber, digits, LocalDateTime.now());
        return tempLocalPick;
    }
}
