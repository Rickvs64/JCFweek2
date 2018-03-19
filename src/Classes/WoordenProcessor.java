package Classes;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class WoordenProcessor implements IWoordenProcessor {
    @Override
    public String sorteer(String text) {
        // Read the input field and put every word into a list.
        List<String> items = new ArrayList<String>();
        items = textToList(text);

        // OLD WAY
        // First fix special character sorting, then reverse the entirety of the list.
        // Collections.sort(items, Collator.getInstance(Locale.US));
        // Collections.sort(items, Collections.reverseOrder());

        // NEW WAY
        HashSet<String> uniques = new HashSet<String>(items);
        TreeSet<String> myTreeSet = new TreeSet(Collections.reverseOrder());
        myTreeSet.addAll(uniques);


        // Iterate through the list in a foreach loop and write to the output field.
        StringBuilder sb = new StringBuilder();
        for (String str: myTreeSet) {
            sb.append(str + "\n");
        }
        return sb.toString();
    }

    @Override
    public String frequentie(String text) {
        // Read the input field and put every word into a list.
        List<Character> items = new ArrayList<Character>();
        items = textToChars(text);

        // Look for unique values and dump their occurances in the output field.
        Set<Character> unique = new HashSet<Character>(items);

        StringBuilder sb = new StringBuilder();
        for (Character key : unique) {
            sb.append(key + ": " + Collections.frequency(items, key) + "\n");
        }
        return sb.toString();
    }

    @Override
    public PriorityQueue<Node> sorteerRaw(String text) {
        throw new NotImplementedException();
    }

    @Override
    public Map<Character, Integer> frequentieRaw(String text) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char w : text.toCharArray()){
            Integer n = frequency.get(w);

            if (n == null) {
                frequency.put(w, 1);
            } else {
                frequency.replace(w, n+1);
            }
        }
        return frequency;
    }

    private List<String> textToList(String text) {
        List<String> items = new ArrayList<String>();
        String regex = ",?(^|\\s)+";
        items = Arrays.asList(text.toLowerCase().split(regex));

        return items;
    }

    private List<Character> textToChars(String text) {
        List<Character> list = new ArrayList<Character>();
        for(char c : text.toCharArray()) {
            list.add(c);
        }

        return list;
    }
}
