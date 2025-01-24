package rcr.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import rcr.model.entity.RcrCommand;
import rcr.model.entity.RcrCommandResponse;
import rcr.repository.RcrCommandRepository;
import rcr.service.validation.RcrCommandsValidator;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class RcrService {

    private final RcrCommandRepository rcrCommandRepository;
    private final RcrCommandsValidator rcrCommandsValidator;
    private final RcrBinaryTreeBuilder rcrBinaryTreeBuilder;

    public void addCommands(List<String> commands) {
        List<RcrCommand> rcrCommands = commands.stream()
                .map(command -> RcrCommand.builder().command(command).build())
                .toList();
        if (!rcrCommandRepository.findAll().isEmpty()) {
            log.warn("Commands already exist in the database. Deleting all commands.");
            rcrCommandRepository.deleteAll();
        }
        rcrCommandRepository.saveAll(rcrCommands);
    }

    public RcrCommandResponse getRcr(String command) {
        List<RcrCommand> allByOrderByIdAsc = rcrCommandRepository.findAllByOrderByIdAsc();
        rcrCommandsValidator.validate(command, allByOrderByIdAsc);
        return RcrCommandResponse.builder()
                .rcr(rcrBinaryTreeBuilder.buildAndGetCommand(allByOrderByIdAsc, command)).build();
    }
}
