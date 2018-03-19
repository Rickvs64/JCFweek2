package Classes;

import java.util.BitSet;
import java.util.Map;
import java.util.PriorityQueue;

public interface IWoordenProcessor {
    String sorteer(String text);
    String frequentie(String text);

    // The following are similar methods but return an uncompressed list instead of a mashed up string.
    PriorityQueue<Node> sorteerRaw(String text);
    Map<Character, Integer> frequentieRaw(String text);
}
