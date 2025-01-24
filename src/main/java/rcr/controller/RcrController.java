package rcr.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rcr.model.entity.RcrCommandsRequest;
import rcr.model.entity.RcrCommandResponse;
import rcr.service.RcrService;

@RestController
@RequiredArgsConstructor
public class RcrController {

    private final RcrService rcrService;

    @PostMapping("/commands")
    public void commands(@RequestBody RcrCommandsRequest request) {
        rcrService.addCommands(request.getCommands());
    }

    @GetMapping("/rcrs/{command}")
    public RcrCommandResponse rcrs(@PathVariable(value="command") String command) {
        return rcrService.getRcr(command);
    }
}
