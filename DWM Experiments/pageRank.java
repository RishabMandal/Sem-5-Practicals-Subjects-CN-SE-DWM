import java.util.Arrays;

public class pageRank {
    public static double sum(int[] matrix) {
        double sum = 0;
        for (int i : matrix) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int matrix[][] = { { 0, 0, 1, 0 }, { 1, 0, 0, 1 }, { 0, 1, 0, 0 }, { 0, 0, 1, 1 } };

        int numOfPages = matrix.length;
        double[] pageRank = new double[numOfPages];
        Arrays.fill(pageRank, 1.0 / numOfPages);

        double d = 0.85;
        int iterations = 20;

        for (int x = 0; x < iterations; x++) {
            double[] newPageRank = new double[numOfPages];
            Arrays.fill(pageRank, (1 - d) / numOfPages);
            for (int i = 0; i < numOfPages; i++) {
                for (int j = 0; j < numOfPages; j++) {
                    if (matrix[j][i] == 1) // Important
                        newPageRank[j] = d * (pageRank[j] / sum(matrix[j]));
                }
            }
            pageRank = newPageRank;
        }
        for (double e : pageRank) {
            System.out.println("page 1: " + e);
        }
    }
}
