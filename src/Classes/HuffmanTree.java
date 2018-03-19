package Classes;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class HuffmanTree implements Iterable<Character>, Serializable {

    private Node startingNode; // Start/root of tree.
    private BitSet bitSet; // Collection of data.
    private int length; // Length of input string.
    private String input; // Input string.

    public HuffmanTree(String text) {
        input = text;
        length = input.length();

        compress(input);
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
