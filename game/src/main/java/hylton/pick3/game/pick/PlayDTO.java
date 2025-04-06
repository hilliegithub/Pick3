package hylton.pick3.game.pick;

import java.time.LocalDateTime;

import lombok.Value;

/**
 * Play coming from the user
 */
@Value
public class PlayDTO {
    private String playerAlias;
    private int digit;
    private int pickId;
}
