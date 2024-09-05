import java.util.*;

public class gobackn {
    static Random random = new Random();

    public static void main(String[] args) {
        int windowSize = getUserInput("Enter sender's window size:");
        int totalFrames = getUserInput("Enter total number of frames to be transmitted:");

        int[] frames = new int[totalFrames];
        for (int i = 0; i < totalFrames; i++) {
            frames[i] = i;
        }

        System.out.println("Sender");
        for (int i = 0; i < windowSize && i < totalFrames; i++) {
            System.out.print(frames[i] + ", ");
        }
        System.out.println();

        System.out.println("Receiver");
        System.out.println(frames[0]);

        int totalFramesSent = sender(frames, windowSize);

        System.out.println("Total number of frames sent (including retransmissions): " + totalFramesSent);
    }

    static int getUserInput(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    static int sender(int[] frames, int windowSize) {
        int point = 0;
        int totalFramesSent = 0;

        while (point < frames.length) {
            for (int i = point; i < Math.min(point + windowSize, frames.length); i++) {
                System.out.println("Sent Frame " + frames[i]);
                totalFramesSent++;
            }

            int ackFrame = receiverAck(point, windowSize);
            if (ackFrame >= 0) {
                point = ackFrame + 1;
            } else if (ackFrame == -2) {
                return -1;
            } else {
                // Correct this part
                point = Math.max(0, point);
            }
        }

        return totalFramesSent;
    }

    static int receiverAck(int expectedSeqNum, int windowSize) {
        boolean ackReceived = random.nextDouble() > 0.1;

        //

        if (ackReceived) {
            int ackFrame = expectedSeqNum + random.nextInt(windowSize);
            //
            // Scanner scanner = new Scanner(System.in);
            // System.out.print("Enter received acknowledge frame number: ");
            // int ackFrame = scanner.nextInt();
            System.out.println("Receiver: Ack for Frame " + ackFrame);
            return ackFrame;
        } else {
            System.out.println("Receiver: Ack not received");
            return -1;
        }
        // Scanner scanner = new Scanner(System.in);
        // System.out.print("Enter received acknowledge frame number (Enter -1 for no
        // acknowledgment received): ");
        // int ackFrame = scanner.nextInt();
        // System.out.println("Receiver: Ack for Frame " + ackFrame);
        // return ackFrame;
    }
}