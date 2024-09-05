import java.util.ArrayList;

public class kmeans {
    public static void main(String[] args) {
        // ArrayList<Integer> arr = new ArrayList<Integer>();
        // arr.add(1);
        // arr.add(2);
        // arr.add(4);
        // arr.add(7);
        // arr.add(11);
        // arr.add(16);
        float m1 = 4, m2 = 16;
        int flag = 0;
        ArrayList<Integer> cluster1 = new ArrayList<Integer>();
        cluster1.add(1);
        cluster1.add(2);
        cluster1.add(4);
        cluster1.add(14);
        ArrayList<Integer> cluster2 = new ArrayList<Integer>();
        cluster2.add(7);
        cluster2.add(11);
        cluster2.add(16);
        while (flag == 0) {
            // for (int i = 0; i < cluster1.size(); i++) {
            // if (Math.abs(cluster1.get(i) - m1) < Math.abs(cluster1.get(i) - m2)) {
            // continue;
            // } else {
            // cluster2.add(cluster1.get(i));
            // cluster1.remove(i);
            // }
            // }
            // Create a temporary list to hold elements to be removed from cluster1
            ArrayList<Integer> toRemoveFromCluster1 = new ArrayList<>();
            for (int i : cluster1) {
                if (Math.abs(i - m1) < Math.abs(i - m2)) {
                    continue;
                } else if (Math.abs(i - m1) > Math.abs(i - m2)) {
                    cluster2.add(i);
                    toRemoveFromCluster1.add(i);
                }
            }
            // Remove elements from cluster1 after the loop
            cluster1.removeAll(toRemoveFromCluster1);

            ArrayList<Integer> toRemoveFromCluster2 = new ArrayList<>();
            for (int i : cluster2) {
                if (Math.abs(i - m1) < Math.abs(i - m2)) {
                    cluster1.add(i);
                    toRemoveFromCluster2.add(i);
                    // cluster2.remove(i);
                    break;
                } else if (Math.abs(i - m1) > Math.abs(i - m2)) {
                    continue;
                }
            }
            cluster2.removeAll(toRemoveFromCluster2);

            int s1 = 0;
            for (int i : cluster1) {
                s1 += i;
            }
            float prevm1 = m1, prevm2 = m2;
            m1 = (float) s1 / cluster1.size();
            int s2 = 0;
            for (int i : cluster2) {
                s2 += i;
            }
            m2 = (float) s2 / cluster2.size();
            System.out.println(cluster1);
            System.out.println(cluster2);
            System.out.println(m1 + "," + m2);
            if (prevm1 == m1 && prevm2 == m2) {
                break;
            }
        }
    }
}
