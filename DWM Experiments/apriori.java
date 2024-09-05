import java.util.*;

public class apriori {
    // public static List<Set<String>> findFrequentItemSets(List<Set<String>>
    // transactions, int minSupport,
    public static void findFrequentItemSets(List<Set<String>> transactions, double minSupport,
            int maxItemsPerTransaction) {
        List<Set<String>> candidates = new ArrayList<Set<String>>();

        // int k = 0;
        int minSupportCount = (int) Math.ceil(minSupport * transactions.size());
        System.out.println(minSupportCount);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Set<String> transaction : transactions) {
            for (String items : transaction) {
                map.put(items, map.getOrDefault(items, 0) + 1);
            }
        }
        // Adding according to minsupport count
        for (String key : map.keySet()) {
            if (map.get(key) >= minSupportCount) {
                HashSet<String> set = new HashSet<String>();
                set.add(key);
                candidates.add(set);
            }
        }
        System.out.println(candidates);

        int k = 2;
        while (k <= maxItemsPerTransaction) {
            candidates.addAll(generateCandidates(transactions, candidates, k, minSupportCount));
            System.out.println("Actual: " + candidates);
            k++;
        }
    }

    public static List<Set<String>> generateCandidates(List<Set<String>> transactions,
            List<Set<String>> availableCandidates, int k,
            int minSupportCount) {
        List<Set<String>> candidates = new ArrayList<Set<String>>();
        for (int i = 0; i < availableCandidates.size(); i++) {
            if (availableCandidates.get(i).size() < k - 1)
                continue;
            for (int j = i + 1; j < availableCandidates.size(); j++) {
                HashSet<String> set = new HashSet<String>();
                set.addAll(availableCandidates.get(i));
                set.addAll(availableCandidates.get(j));
                int count = 0;
                for (Set<String> transaction : transactions) {
                    if (transaction.containsAll(set)) {
                        count++;
                    }
                }
                if (count >= minSupportCount)
                    candidates.add(set);
            }
        }
        System.out.println(candidates);

        return candidates;
    }

    public static void main(String[] args) {
        List<Set<String>> transactions = new ArrayList<>();
        // Set<String> transaction1 = new HashSet<>();
        // transaction1.add("A");
        // transaction1.add("B");
        // transaction1.add("C");

        // Set<String> transaction2 = new HashSet<>();
        // transaction2.add("A");
        // transaction2.add("B");
        // transaction2.add("D");

        // Set<String> transaction3 = new HashSet<>();
        // transaction3.add("B");
        // transaction3.add("C");
        // transaction3.add("D");

        // Set<String> transaction4 = new HashSet<>();
        // transaction4.add("A");
        // transaction4.add("C");
        // transaction4.add("D");

        // Set<String> transaction5 = new HashSet<>();
        // transaction5.add("A");
        // transaction5.add("C");
        // transaction5.add("D");

        // transactions.add(transaction1);
        // transactions.add(transaction2);
        // transactions.add(transaction3);
        // transactions.add(transaction4);
        // transactions.add(transaction5);

        // double minSupport = 0.4;
        // int maxItemsPerTransaction = 3;
        Set<String> transaction1 = new HashSet<>();
        transaction1.add("A");
        transaction1.add("C");
        transaction1.add("D");

        Set<String> transaction2 = new HashSet<>();
        transaction2.add("B");
        transaction2.add("C");
        transaction2.add("E");

        Set<String> transaction3 = new HashSet<>();
        transaction3.add("A");
        transaction3.add("B");
        transaction3.add("C");
        transaction3.add("E");

        Set<String> transaction4 = new HashSet<>();
        transaction4.add("B");
        transaction4.add("E");

        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        // transactions.add(transaction5);

        double minSupport = 0.4;
        int maxItemsPerTransaction = 3;
        // List<Set<String>> frequentItemSets = findFrequentItemSets(transactions,
        // minSupport, maxItemsPerTransaction);
        findFrequentItemSets(transactions, minSupport, maxItemsPerTransaction);

        // System.out.println("Frequent Item Sets:");
        // for (Set<String> itemSet : frequentItemSets) {
        // System.out.println(itemSet);
        // }
    }
}
