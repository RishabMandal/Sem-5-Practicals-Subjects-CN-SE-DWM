import java.util.*;

public class kmeansMyOwnTry {
    public static double euclideanDistance(int x1, int y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        int points[][] = { { 2, 10 }, { 2, 5 }, { 8, 4 }, { 5, 8 }, { 7, 5 }, { 1, 2 }, { 6, 4 }, { 4, 9 } };
        double centroids[][] = { { 2, 10 }, { 5, 8 }, { 1, 2 } };

        double distance1[] = new double[points.length];
        double distance2[] = new double[points.length];
        double distance3[] = new double[points.length];

        // List<int[]> cluster1 = new ArrayList<int[]>();
        // List<int[]> cluster2 = new ArrayList<int[]>();
        // List<int[]> cluster3 = new ArrayList<int[]>();

        int steps = 1;
        while (steps++ < 10) {

            List<int[]> cluster1 = new ArrayList<int[]>();
            List<int[]> cluster2 = new ArrayList<int[]>();
            List<int[]> cluster3 = new ArrayList<int[]>();

            for (int i = 0; i < points.length; i++) {
                distance1[i] = euclideanDistance(points[i][0], points[i][1], centroids[0][0], centroids[0][1]);
                distance2[i] = euclideanDistance(points[i][0], points[i][1], centroids[1][0], centroids[1][1]);
                distance3[i] = euclideanDistance(points[i][0], points[i][1], centroids[2][0], centroids[2][1]);
            }
            for (double d : distance1) {
                System.out.print(d + " ");
            }
            System.out.println();
            for (double d : distance2) {
                System.out.print(d + " ");
            }
            System.out.println();
            for (double d : distance3) {
                System.out.print(d + " ");
            }

            for (int i = 0; i < points.length; i++) {
                int pointy[] = new int[2];
                pointy[0] = points[i][0];
                pointy[1] = points[i][1];
                if (distance1[i] < distance2[i]) {
                    if (distance1[i] < distance3[i]) {
                        cluster1.add(pointy);
                    } else {
                        cluster3.add(pointy);
                    }
                } else {
                    if (distance2[i] < distance3[i]) {
                        cluster2.add(pointy);
                    } else {
                        cluster3.add(pointy);
                    }
                }
            }
            System.out.print("\nClusters: ");
            for (int[] is : cluster1) {
                System.out.print("[" + is[0] + "," + is[1] + "]");
            }
            System.out.print("\nClusters: ");
            for (int[] is : cluster2) {
                System.out.print("[" + is[0] + "," + is[1] + "]");
            }
            System.out.print("\nClusters: ");
            for (int[] is : cluster3) {
                System.out.print("[" + is[0] + "," + is[1] + "]");
            }
            int sum1x = 0, sum1y = 0, sum2x = 0, sum2y = 0, sum3x = 0, sum3y = 0;
            for (int[] is : cluster1) {
                sum1x += is[0];
                sum1y += is[1];
            }
            centroids[0][0] = (double) sum1x / cluster1.size();
            centroids[0][1] = (double) sum1y / cluster1.size();
            for (int[] is : cluster2) {
                sum2x += is[0];
                sum2y += is[1];
            }
            centroids[1][0] = (double) sum2x / cluster2.size();
            centroids[1][1] = (double) sum2y / cluster2.size();
            for (int[] is : cluster3) {
                sum3x += is[0];
                sum3y += is[1];
            }
            centroids[2][0] = (double) sum3x / cluster3.size();
            centroids[2][1] = (double) sum3y / cluster3.size();
            System.out.println("\nCentroid: " + centroids[0][0] + "," + centroids[0][1]);
            System.out.println("Centroid: " + centroids[1][0] + "," + centroids[1][1]);
            System.out.println("Centroid: " + centroids[2][0] + "," + centroids[2][1]);
            System.out.println();
        }
    }
}
