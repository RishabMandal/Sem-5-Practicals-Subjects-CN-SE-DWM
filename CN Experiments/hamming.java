
// Online IDE - Code Editor, Compiler, Interpreter

import java.util.Scanner;

public class hamming {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        // Calculate the number of parity bits required (r)
        int r = 0;
        while (Math.pow(2, r) < n + r + 1) {
            r++;
        }

        System.out.println("Number of parity bits required: " + r);

        int[] data = new int[n + r];

        System.out.println("Enter the data bits (separated by spaces):");
        for (int i = 0; i < n; i++) {
            data[i] = scanner.nextInt();
        }

        // Calculate and insert parity bits
        for (int i = 0; i < r; i++) {
            int parityIndex = (int) Math.pow(2, i) - 1;
            data[parityIndex] = calculateParity(data, parityIndex);
        }

        System.out.print("Sent Codeword (Data + Parity bits): ");
        for (int bit : data) {
            System.out.print(bit + " ");
        }
        System.out.println();

        System.out.print("Enter the received Codeword (separated by spaces): ");
        int[] receivedData = new int[n + r];
        for (int i = 0; i < n + r; i++) {
            receivedData[i] = scanner.nextInt();
        }

        int errorBitIndex = checkErrorBit(receivedData, r);

        if (errorBitIndex > 0) {
            System.out.println("Error detected in bit: " + errorBitIndex);
            receivedData[errorBitIndex - 1] = (receivedData[errorBitIndex - 1] + 1) % 2;
            System.out.println("Corrected Codeword: ");
            for (int bit : receivedData) {
                System.out.print(bit + " ");
            }
        } else {
            System.out.println("No error detected.");
        }

        scanner.close();
    }

    // Calculate the parity bit value for a given position
    private static int calculateParity(int[] data, int parityIndex) {
        int xor = 0;
        for (int i = parityIndex; i < data.length; i++) {
            if (((i + 1) & (parityIndex + 1)) != 0) {
                xor ^= data[i];
            }
        }
        return xor;
    }

    // Check for errors and return the error bit index (0 if no error)
    private static int checkErrorBit(int[] receivedData, int r) {
        int errorBitIndex = 0;
        for (int i = 0; i < r; i++) {
            int parityIndex = (int) Math.pow(2, i) - 1;
            int calculatedParity = calculateParity(receivedData, parityIndex);
            if (calculatedParity != receivedData[parityIndex]) {
                errorBitIndex += parityIndex + 1;
            }
        }
        return errorBitIndex;
    }
}
