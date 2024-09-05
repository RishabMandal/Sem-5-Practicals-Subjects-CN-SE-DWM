import java.util.Scanner;

public class GoBackNMyOwn {

    public static void gobackn(int frames[], int senderWindow) {
        Scanner sc = new Scanner(System.in);
        // Using two pointers for start and end of window,
        int start = 0, end = senderWindow - 1;
        for (int i = 0; i < senderWindow; i++) {
            System.out.println("Sent frame: " + frames[i]);
        }
        while (end < frames.length) {
            if (end == frames.length - 1) {
                System.out.println("All frames transmitted successfully");
                break;
            }
            int ackn = getAckn(sc);
            int remaining = ackn - start;
            start = ackn;
            if (end + remaining < frames.length)
                end += remaining;
            else
                end = frames.length - 1;
            for (int i = start; i <= end; i++) {
                System.out.println("Sent frame: " + frames[i]);
            }
        }
    }

    public static int getAckn(Scanner sc) {
        System.out.println("Enter receiver's acknowledgement: ");
        int ackn = sc.nextInt();
        return ackn;
    }

    public static void main(String[] args) {
        // int m = 2;
        int senderWindow = 3; // 2^m-1
        // int receiverWindow = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of frames to be transmitted: ");
        int totalFrames = sc.nextInt();
        int arr[] = new int[totalFrames];
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Enter frame " + i + ": ");
            arr[i] = sc.nextInt();
        }
        gobackn(arr, senderWindow);
        // Try implementing random ackn
        sc.close();
    }
}
