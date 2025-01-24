package rcr.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder
public class RcrCommandResponse {

    private final String rcr;
}
