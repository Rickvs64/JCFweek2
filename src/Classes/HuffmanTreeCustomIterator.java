package Classes;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.BitSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HuffmanTreeCustomIterator implements Iterator<Character> {

    private int i = 0;
    private int currentCharIndex = 0;
    private int length;
    private BitSet bitSet;
    private Node startingNode;

    public HuffmanTreeCustomIterator(int length, BitSet bitset, Node startingNode) {
        this.length = length;
        this.bitSet = bitset;
        this.startingNode = startingNode;
    }

    @Override
    public boolean hasNext() {
        return currentCharIndex < length && i < bitSet.size();
    }

    @Override
    public Character next() {
        if (!hasNext())
            throw new NoSuchElementException();
        Node currentNode = startingNode;
        try {
            while (hasNext()) {
                boolean bit = bitSet.get(i);
                i++;

                if (bit) {
                    currentNode = currentNode.getRightNode();
                } else {
                    currentNode = currentNode.getLeftNode();
                }

                if (currentNode.isCharacterNode()) {
                    currentCharIndex++;
                    return currentNode.getCharacter();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
        throw new NotImplementedException(); // Not exactly right, but I haven't gotten around to handling a corrupted data set.
    }
}
