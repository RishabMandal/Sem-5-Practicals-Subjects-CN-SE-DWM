import java.util.*;

public class aprioriagain2 {
    static HashMap<Set<String>, Integer> frequency = new HashMap<Set<String>, Integer>();

    public static void findItemFrequency(List<Set<String>> transactions, float minSupport, int maxnooftransactions) {
        // Math.ceil
        int minSupportCount = (int) Math.ceil(transactions.size() * minSupport);

        List<Set<String>> candidates = new ArrayList<Set<String>>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Set<String> set : transactions) {
            for (String item : set) {
                map.put(item, map.getOrDefault(item, 0) + 1);
            }
        }
        for (String item : map.keySet()) {
            if (map.get(item) >= minSupportCount) {
                Set<String> set = new HashSet<>();
                set.add(item);
                candidates.add(set);
                // frequency map
                frequency.put(set, map.get(item));
            }
        }
        System.out.println(candidates);

        int k = 2;
        while (k < maxnooftransactions) {
            candidates.addAll(generateCandidates(candidates, transactions, minSupportCount, k));
            k++;
        }
    }

    public static List<Set<String>> generateCandidates(List<Set<String>> availableCandidates,
            List<Set<String>> transactions, int minSupportCount,
            int k) {
        List<Set<String>> candidates = new ArrayList<Set<String>>();
        for (int i = 0; i < availableCandidates.size(); i++) {
            if (availableCandidates.get(i).size() == k - 1) {
                for (int j = i + 1; j < availableCandidates.size(); j++) {
                    int count = 0;
                    Set<String> candidate = new HashSet<String>();
                    candidate.addAll(availableCandidates.get(i));
                    candidate.addAll(availableCandidates.get(j));
                    for (Set<String> transaction : transactions) {
                        if (transaction.containsAll(candidate))
                            count++;
                    }
                    if (count >= minSupportCount) {
                        candidates.add(candidate);
                        frequency.put(candidate, count);
                    }
                }
            }
        }
        System.out.println(candidates);
        return candidates;
    }

    public static void generateAssociationRules(HashMap<Set<String>, Integer> frequency, float minConfidence) {
        for (Set<String> itemset : frequency.keySet()) {
            if (itemset.size() > 1) {
                List<Set<String>> itemsetList = new ArrayList<>();
                itemsetList.addAll(itemsetList);
                for (String item : itemset) {
                    Set<String> antecedent = new HashSet<>();
                    antecedent.add(item);

                    // Calculate the confidence of the rule
                    double confidence = (double) frequency.get(itemset) / frequency.get(antecedent);

                    if (confidence >= minConfidence) {
                        Set<String> consequent = new HashSet<>(itemsetList);
                        consequent.remove(item);

                        // Print the association rule
                        System.out.println(antecedent + " => " + consequent + " (Confidence: " + confidence + ")");
                    }
                }
            }
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

        // getConfidence();
        float minConfidence = (float) 0.6;
        generateAssociationRules(frequency, minConfidence);
    }

}
