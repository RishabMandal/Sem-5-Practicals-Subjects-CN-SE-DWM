import java.util.*;

public class CRCMyOwn {
    public static int[] xor(int[] dividend, int divisor[]) {
        int m = divisor.length, n = dividend.length;
        // int i = 0, j = m - 1;
        int dummyDividend[] = Arrays.copyOf(dividend, n);
        for (int k = 0; k <= n - m; k++) {
            if (dummyDividend[k] == 1) {
                for (int k2 = 0; k2 < m; k2++) {
                    dummyDividend[k + k2] = dummyDividend[k + k2] ^ divisor[k2];
                }
            }
        }
        return dummyDividend;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of bits in generator polynomial: ");
        int n = sc.nextInt();
        int[] polynomial = new int[n];
        System.out.println("Enter generator polynomial in binary: ");
        for (int i = 0; i < polynomial.length; i++) {
            polynomial[i] = sc.nextInt();
        }
        System.out.println("Enter no. of bits in data: ");
        int databitslength = sc.nextInt();
        int[] dataBits = new int[databitslength + n - 1];
        Arrays.fill(dataBits, 0);
        System.out.println("Enter data in binary: ");
        for (int i = 0; i < databitslength; i++) {
            dataBits[i] = sc.nextInt();
        }
        System.out.println("No. of zeroes to be appended to data: " + (n - 1));
        System.out.println("Modified data is: ");
        for (int i = 0; i < dataBits.length; i++) {
            System.out.print(dataBits[i]);
        }
        System.out.println();

        // Sender side
        System.out.println("Remainder is");
        int calc_xor[] = xor(dataBits, polynomial);
        int senderRemainder[] = Arrays.copyOfRange(calc_xor, databitslength, databitslength + n - 1);
        for (int i : senderRemainder) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println("Sender XOR is");
        for (int i = 0; i < senderRemainder.length; i++) {
            dataBits[i+databitslength] = senderRemainder[i];
        }
        for (int i = 0; i < dataBits.length; i++) {
            System.out.print(dataBits[i]);
        }
        System.out.println();

        // Receiver side
        System.out.println("Receiver side");
        System.out.println("CRC is");
        int receiver_calc_xor[] = xor(dataBits, polynomial);
        for (int i : receiver_calc_xor) {
            System.out.print(i);
        }
        sc.close();
    }
}
