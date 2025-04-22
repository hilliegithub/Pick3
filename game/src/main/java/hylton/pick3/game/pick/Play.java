package hylton.pick3.game.pick;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Play {
    private Long id;
    private Long playerId;
    private Set<Integer> digits;
    private int pickId;
    private LocalDateTime createdAt;
    private boolean isPlayAMatch;
}
