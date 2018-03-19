package Classes;

import java.io.Serializable;

public class Node implements Comparable<Node>, Serializable {
    private Node leftNode;
    private Node rightNode;
    private char character;
    private int frequency;

    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public Node(Node leftNode, Node rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;

        frequency = 0;
        if (leftNode != null)
            frequency = leftNode.frequency;
        if (rightNode != null)
            frequency += rightNode.frequency;

    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public char getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public int compareTo(Node o) {
        int result = getFrequency() - o.getFrequency();
        if (result == 0)
            return getCharacter() - o.getCharacter();
        else
            return  result;
    }

    public boolean isCharacterNode() {
        return leftNode == null && rightNode == null;
    }
}
