package hylton.pick3.game.pick;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Play {
    private Long id;
    private Long playerId;
    private int slotOne;
    private int slotTwo;
    private int slotThree;
    private int pickNumber;
    private LocalDateTime createdAt;
}
