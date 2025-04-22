package hylton.pick3.game.pick;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * This class represents a Pick - A unique selection of 3 digits.
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Pick {
    private int pickNumber;
    private Set<Integer> digits;
    private LocalDateTime TimeEndAt;
}
