
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class hammingcode {

    public static int binaryToDecimal(List<Integer> num) {
        int decValue = 0;
        int base = 1;
        int len = num.size();
        for (int i = len - 1; i >= 0; i--, base = base * 2) {
            if (num.get(i) != 0)
                decValue += base;
        }
        return decValue;
    }

    public static int giveMeBit(int s, int pno, int len, List<Integer> h) {
        List<Integer> ans = new ArrayList<>();
        for (int i = s + 1; i <= len; i++) {
            if ((1 << pno & i) != 0)
                ans.add(i);
        }
        int cnt = 0;
        for (int i = 0; i < ans.size(); i++) {
            if (h.get(len - ans.get(i)) == 1)
                cnt++;
        }
        return cnt % 2;
    }

    public static boolean isPowerOfTwo(int n) {
        if (n == 0)
            return false;
        return (Math.ceil(Math.log(n) / Math.log(2)) == Math.floor(Math.log(n) / Math.log(2)));
    }

    public static void hammingCode(List<Integer> ham, boolean isEven) {
        int pno = 0;
        int h = ham.size();
        for (int i = 0; i < h; i++) {
            if (ham.get(h - i - 1) == -1) {
                int bit = isEven ? giveMeBit(i + 1, pno, h, ham) : (giveMeBit(i + 1, pno, h, ham) + 1) % 2;
                ham.set(h - i - 1, bit);
                pno++;
            }
        }
        System.out.print("\nHamming code: ");
        for (int i = 0; i < h; i++) {
            System.out.print(ham.get(i) + " ");
        }
    }

    public static void errorDetection() {
        boolean isEven = true;
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the received message: ");
        String recCode = scanner.nextLine();

        int r = (recCode.charAt(0) - '0');
        for (int i = 1; i < recCode.length(); i++) {
            r ^= (recCode.charAt(i) - '0');
            System.out.println(r);
        }

        List<Integer> rec = new ArrayList<>();
        List<Integer> correct = new ArrayList<>();
        for (char c : recCode.toCharArray()) {
            rec.add(c - '0');
        }

        int pno = 0;
        int h = recCode.length();
        for (int i = 0; i < h; i++) {
            if (isPowerOfTwo(i + 1)) {
                int bit = isEven ? giveMeBit(i, pno, h, rec) : (giveMeBit(i, pno, h, rec) + 1) % 2;
                correct.add(bit);
                pno++;
            }
        }

        Collections.reverse(correct);
        int erroPos = binaryToDecimal(correct);
        if (erroPos == 0 && r == 0) {
            System.out.println("No error found");
        } else if (erroPos != 0 && r == 0) {
            System.out.println("\n-----ERROR DETECTION-----");
            System.out.println("Two-bit error detected in " + recCode);
        } else {
            System.out.println("\n-----ERROR DETECTION-----");
            System.out.println("Error at position: " + (h - erroPos + 1));
            rec.set(h - erroPos, (rec.get(h - erroPos) + 1) % 2);
            System.out.print("Corrected received message: ");
            for (int i = 0; i < h; i++) {
                System.out.print(rec.get(i) + " ");
            }
            System.out.println();
        }
    }

    public static void encoding() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the number of bits: ");
        int n = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        System.out.print("Enter the message: ");
        String s = scanner.nextLine();
        int r = 0;
        while (Math.pow(2, r) < n + r + 1) {
            r++;
        }

        List<Integer> ham = new ArrayList<>(Collections.nCopies(r + n, -1));
        int k = s.length() - 1;
        int h = ham.size();
        for (int i = 0; i < h; i++) {
            if (!isPowerOfTwo(i + 1)) {
                ham.set(h - i - 1, s.charAt(k--) - '0');
            }
        }

        hammingCode(ham, true);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\nChoose your option:");
            System.out.println("1. Encoding the Message");
            System.out.println("2. Error detection and correction");
            System.out.println("3. Exit");
            System.out.print("Enter your option: ");
            int n = scanner.nextInt();
            if (n == 1) {
                encoding();
            } else if (n == 2) {
                errorDetection();
            } else {
                break;
            }
        }
    }
}