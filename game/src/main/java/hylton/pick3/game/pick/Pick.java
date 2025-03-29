package hylton.pick3.game.pick;

import lombok.*;
import java.time.LocalDateTime;

/**
 * This class represents a Pick - A unique selection of 3 digits.
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Pick {
    private int pickNumber;
    private int slotA;
    private int slotB;
    private int slotC;
    private LocalDateTime endAt;
}
