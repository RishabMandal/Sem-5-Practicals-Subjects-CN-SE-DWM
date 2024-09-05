import java.util.*;

public class singleLinkageagain {
    public static double euclideanDistance(double[] point1, double[] point2) {
        double sum = 0;
        for (int i = 0; i < point1.length; i++) {
            sum += Math.pow(point1[i] - point2[i], 2);
        }
        return Math.sqrt(sum);
    }

    public static double singlelinkageDistance(List<Integer> cluster1, List<Integer> cluster2,
            double distanceMatrix[][]) {
        double minDistance = Double.POSITIVE_INFINITY;
        for (Integer point1 : cluster1) {
            for (Integer point2 : cluster2) {
                double distance = distanceMatrix[point1][point2];
                if (distance < minDistance)
                    minDistance = distance;
            }
        }
        return minDistance;
    }

    public static void main(String[] args) {
        // double data[][] = {};
        double data[][] = {
                { 0, 99 }, { 9, 0 }, { 3, 7 }, { 6, 5 },
                { 11, 10 }
        };
        // List<List<Integer>>
        List<List<Integer>> clusters = new ArrayList<List<Integer>>();
        for (int i = 0; i < data.length; i++) {
            List<Integer> cluster = new ArrayList<Integer>();
            cluster.add(i);
            clusters.add(cluster);
        }

        double distanceMatrix[][] = new double[data.length][data.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (i == j)
                    distanceMatrix[i][j] = Double.POSITIVE_INFINITY;
                else
                    distanceMatrix[i][j] = euclideanDistance(data[i], data[j]);
            }
        }

        // int steps = 0;
        System.out.println(clusters);
        while (clusters.size() > 1) {
            double minDistance = Double.POSITIVE_INFINITY;
            int merge[] = { 0, 0 };

            for (int k = 0; k < clusters.size(); k++) {
                for (int k2 = k + 1; k2 < clusters.size(); k2++) {
                    double distance = singlelinkageDistance(clusters.get(k), clusters.get(k2), distanceMatrix);
                    if (distance < minDistance) {
                        minDistance = distance;
                        merge[0] = k;
                        merge[1] = k2;
                    }

                }
            }
            // Remember Outside for loop
            int i = merge[0], j = merge[1];
            clusters.get(i).addAll(clusters.get(j));
            clusters.remove(j);
            System.out.println("Clusters: " + clusters);
        }
        System.out.println("Final clusters: " + clusters);
    }
}
