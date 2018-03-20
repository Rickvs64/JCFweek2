package Classes;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

/**
 * HuffmanTree class can compress data into a non-readable format.
 */
public class HuffmanTree implements Iterable<Character>, Serializable {

    private Node startingNode; // Start/root of tree.
    private BitSet bitSet; // Collection of data.
    private int length; // Length of input string.
    // private String input; // Input string. Don't use it to prove this class can decode back to readable string.

    private IWoordenProcessor wp = new WoordenProcessor(); // Needed in order to prove sort/frequency functionality.

    /**
     * Constructor, note how it never saves the input string anywhere.
     * @param text The original input string.
     */
    public HuffmanTree(String text) {
        length = text.length();

        compress(text);
    }

    /**
     * First sorts characters from the input string by frequency, then polls them into different nodes.
     * @param text
     */
    private void compress(String text) {
        Map<Character, Integer> frequency = wp.frequentieRaw(text);

        // Contrary to a regular queue, a PriorityQueue does NOT follow FIFO rules.
        PriorityQueue<Node> queue = new PriorityQueue<>();

        frequency.forEach((c , i) -> {
            Node node = new Node(c, i);
            queue.add(node);
        });

        while (!queue.isEmpty()) {
            Node node1 = queue.poll();
            Node node2 = queue.poll();

            Node next = new Node(node1, node2);

            if (queue.isEmpty())
                startingNode = next;
            else
                queue.add(next);
        }
        bitSet = generateBitmap(text);
    }

    /**
     * Stores a "dictionary" of sorts that defines which character belongs to which code.
     * @param text
     * @return
     */
    private BitSet generateBitmap(String text) {
        // Using a HashMap since order of definitions is not essential.
        HashMap<Character, CharacterCode> map = new HashMap<>();
        fillBitmap(map, startingNode, new BitSet(), (byte)0);

        BitSet encodedText = new BitSet();

        int i = 0;
        for (char w : text.toCharArray()) {
            CharacterCode code = map.get(w);
            BitSet bits =  code.getCode();

            for (int i2 = 0; i2 < code.getCodeSize(); i2++) {
                boolean b = bits.get(i2);
                encodedText.set(i, b);
                i++;
            }
        }

        return encodedText;
    }

    /**
     * Specific method that binds prefix codes at the current position.
     * @param map
     * @param node
     * @param bits
     * @param index
     */
    private void fillBitmap(HashMap<Character, CharacterCode> map, Node node, BitSet bits, byte index) {
        if (node.isCharacterNode()) {
            char character = node.getCharacter();
            map.put(character, new CharacterCode(bits, index));
            return;
        }

        if (node.getLeftNode() != null) {
            Node leftNode = node.getLeftNode();
            bits.set(index, false);
            fillBitmap(map, leftNode, bits, (byte)(index+1));
            bits.clear(index);
        }

        if (node.getRightNode() != null) {
            Node rightNode = node.getRightNode();
            bits.set(index, true);
            fillBitmap(map, rightNode, bits, (byte)(index+1));
            bits.clear(index);
        }
    }

    /**
     * Literally returns the serialized class character by character.
     * @return
     */
    public String getDecoded() {
        StringBuilder sb = new StringBuilder();

        for (char character: this) {
            sb.append(character);
        }

        return sb.toString();
    }


    /**
     * Manual iterator class to allow cycling through a loop properly.
     * @return
     */
    @Override
    public Iterator<Character> iterator() {
        return new HuffmanTreeCustomIterator(length, bitSet, startingNode);
    }


    /**
     * Manual FOREACH override to allow this class to be iterated through in a loop.
     * @param action
     */
    @Override
    public void forEach(Consumer<? super Character> action) {
        for (char c: this) {
            action.accept(c);
        }
    }


}
