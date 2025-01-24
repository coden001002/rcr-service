package rcr.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
@Builder
public class RcrCommandsRequest {

    private final List<String> commands;
}
