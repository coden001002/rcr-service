package rcr.service;

import org.junit.jupiter.api.Test;
import rcr.model.entity.RcrCommand;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RcrBinaryTreeBuilderTest {

	private final RcrBinaryTreeBuilder underTest = new RcrBinaryTreeBuilder();

	@Test
	void shouldBuildAndGetCommand() {

		// given
		List<RcrCommand> commands = Arrays.asList(
				RcrCommand.builder().command("LEFT").build(),
				RcrCommand.builder().command("GRAB").build(),
				RcrCommand.builder().command("LEFT").build(),
				RcrCommand.builder().command("BACK").build(),
				RcrCommand.builder().command("LEFT").build(),
				RcrCommand.builder().command("BACK").build(),
				RcrCommand.builder().command("LEFT").build());

		// when / then
		assertEquals("00", underTest.buildAndGetCommand(commands, "GRAB"));
	}
}
