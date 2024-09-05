import java.util.*;

public class aprioriagain {
    static HashMap<Set<String>, Integer> frequency = new HashMap<Set<String>, Integer>();

    public static void findItemFrequency(List<Set<String>> transactions, float minSupport, int maxnooftransactions) {
        int minSupportCount = (int) Math.ceil(minSupport * transactions.size());
        System.out.println("\nMin support count: " + minSupportCount);

        List<Set<String>> candidates = new ArrayList<Set<String>>();
        HashMap<String, Integer> map = new HashMap<>();
        for (Set<String> transaction : transactions) {
            for (String item : transaction) {
                map.put(item, map.getOrDefault(item, 0) + 1);
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key) >= minSupportCount) {
                HashSet<String> set = new HashSet<>();
                set.add(key);
                candidates.add(set);
                // Add freq to frequency map
                HashSet<String> freq = new HashSet<>();
                freq.add(key);
                frequency.put(freq, map.get(key));
            }
        }
        System.out.println();
        for (Set<String> set : candidates) {
            System.out.println(set + ": " + map.get(set));
        }
        System.out.println();

        int k = 2;
        while (k <= maxnooftransactions) {
            candidates.addAll(generateCandidates(candidates, transactions, k, minSupportCount));
            k++;
        }

    }

    public static List<Set<String>> generateCandidates(List<Set<String>> candidates, List<Set<String>> transactions,
            int k, int minSupportCount) {
        List<Set<String>> candidate = new ArrayList<Set<String>>();
        for (int i = 0; i < candidates.size(); i++) {
            if (candidates.get(i).size() == k - 1) {
                for (int j = i + 1; j < candidates.size(); j++) {
                    int count = 0;
                    HashSet<String> set = new HashSet<String>();
                    set.addAll(candidates.get(i));
                    set.addAll(candidates.get(j));
                    for (Set<String> transaction : transactions) {
                        if (transaction.containsAll(set)) {
                            count++;
                        }
                    }
                    if (count >= minSupportCount) {
                        System.out.println(set + ": " + count);
                        candidate.add(set);
                        // Add freq to frequency map
                        if (!frequency.containsKey(set))
                            frequency.put(set, count);
                    }
                }
            }
        }
        System.out.println();
        return candidate;
    }

    public static void getConfidence() {
        Set<String> maxSet = new HashSet<String>();
        int support = -1;
        for (Set<String> set : frequency.keySet()) {
            if (set.size() == 3) {
                maxSet.addAll(set);
                support = frequency.get(set);
            }
        }
        System.out.println(maxSet + ": " + support);
        for (String character : maxSet) {
            HashSet<String> set2 = new HashSet<String>();
            set2.add(character);
            int fr = frequency.get(set2);
            System.out.println(
                    "Confidence is: -> " + character + " : " + (float) ((float) support / (float) fr) * 100 + "%");
        }
    }

    public static void main(String[] args) {
        List<Set<String>> transactions = new ArrayList<Set<String>>();
        HashSet<String> transaction1 = new HashSet<String>();
        transaction1.add("A");
        transaction1.add("C");
        transaction1.add("D");

        transactions.add(transaction1);

        HashSet<String> transaction2 = new HashSet<String>();
        transaction2.add("B");
        transaction2.add("C");
        transaction2.add("E");

        transactions.add(transaction2);

        HashSet<String> transaction3 = new HashSet<String>();
        transaction3.add("A");
        transaction3.add("B");
        transaction3.add("C");
        transaction3.add("E");

        transactions.add(transaction3);

        HashSet<String> transaction4 = new HashSet<String>();
        transaction4.add("B");
        transaction4.add("E");

        transactions.add(transaction4);
        System.out.println("Transactions: " + transactions);

        float minSupport = (float) 0.4;
        findItemFrequency(transactions, minSupport, 4);

        System.out.println();
        System.out.println(frequency);
        System.out.println();

        getConfidence();

    }
}
