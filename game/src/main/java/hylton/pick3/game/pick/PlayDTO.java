package hylton.pick3.game.pick;

import java.time.LocalDateTime;

import lombok.Value;

/**
 * Play coming from the user
 */
@Value
public class PlayDTO {
    String playerAlias;
    int digit;
    int pickId;
}
