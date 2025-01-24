package rcr.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import rcr.model.entity.RcrCommand;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

@Service
public class RcrBinaryTreeBuilder {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final String ZERO_STRING = String.valueOf(ZERO);
    private static final String ONE_STRING = String.valueOf(ONE);

    private static class TreeNode {
        String command;
        TreeNode left;
        TreeNode right;

        TreeNode(String command) {
            this.command = command;
        }
    }

    public String buildAndGetCommand(List<RcrCommand> allByOrderByIdAsc, String command) {
        TreeNode root = buildTree(allByOrderByIdAsc);
        return getCommand(root, command, StringUtils.EMPTY);
    }

    private TreeNode buildTree(List<RcrCommand> allByOrderByIdAsc) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (RcrCommand rcrCommand : allByOrderByIdAsc) {
            frequencyMap.put(rcrCommand.getCommand(), frequencyMap.getOrDefault(rcrCommand.getCommand(), ZERO) + ONE);
        }

        PriorityQueue<TreeNode> queue = new PriorityQueue<>(
                Comparator.comparingInt(cmd -> frequencyMap.getOrDefault(cmd.command, ZERO)));
        for (String cmd : frequencyMap.keySet()) {
            queue.add(new TreeNode(cmd));
        }

        while (queue.size() > 1) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            TreeNode combined = new TreeNode(null);
            combined.left = left;
            combined.right = right;
            queue.add(combined);
        }

        return queue.poll();
    }

    private String getCommand(TreeNode node, String command, String path) {
        if (Objects.isNull(node)) {
            return null;
        }
        if (command.equals(node.command)) {
            return path;
        }
        String leftPath = getCommand(node.left, command, path + ZERO_STRING);
        if (Objects.nonNull(leftPath)) {
            return leftPath;
        }
        return getCommand(node.right, command, path + ONE_STRING);
    }
}
