public class singlelinkage {

    public static int[] findMinEle(int matrix[][]) {
        int min = 100, row = -1, col = -1; int arr[] = new int[3];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (min > matrix[i][j]) {
                    min = matrix[i][j];
                    row = i;
                    col = j;
                }
            }
        }
        arr[0] = min; arr[1] = row; arr[2] = col;
        return arr;
    }

    public static void singleLinkage(int matrix[][]) {
        for (int i = matrix.length; i >= 1; i--) {
            int arr[] = findMinEle(matrix);
        }
    }

    public static void main(String[] args) {
        int matrix[][] = {
                { 0, 99, 99, 99, 99 }, { 9, 0, 99, 99, 99 }, { 3, 7, 0, 99, 99 }, { 6, 5, 9, 0, 99 },
                { 11, 10, 2, 8, 0 }
        };
        singleLinkage(matrix);
    }
}
