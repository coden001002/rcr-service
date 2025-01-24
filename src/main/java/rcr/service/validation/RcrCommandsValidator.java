package rcr.service.validation;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import rcr.model.entity.RcrCommand;

import java.util.List;

@Log4j2
@Service
public class RcrCommandsValidator {

    public void validate(String command, List<RcrCommand> allByOrderByIdAsc) {
        if (isEmptyCommandsList(allByOrderByIdAsc)) {
            log.warn("Commands list is empty");
            throw new RcrValidationException("Commands list is empty");
        }
        if (isCommandNotFound(command, allByOrderByIdAsc)) {
            log.warn("Command not found in DB: " + command);
            throw new RcrValidationException("Command not found in DB: " + command);
        }
    }

    public boolean isEmptyCommandsList(List<RcrCommand> allByOrderByIdAsc) {
        return allByOrderByIdAsc.isEmpty();
    }

    private boolean isCommandNotFound(String command, List<RcrCommand> allByOrderByIdAsc) {
        return allByOrderByIdAsc.stream()
                .map(RcrCommand::getCommand)
                .noneMatch(command::equals);
    }
}
