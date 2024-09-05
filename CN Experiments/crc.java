
import java.util.*;

public class crc {

    public static int[] CRC(int[] data, int[] poly) {
        int[] extendedData = Arrays.copyOf(data, data.length + poly.length - 1);

        for (int i = 0; i <= extendedData.length - poly.length; i++) {
            if (extendedData[i] == 1) {
                for (int j = 0; j < poly.length; j++) {
                    extendedData[i + j] = extendedData[i + j] ^ poly[j];
                }
            }
        }

        int[] crc = Arrays.copyOfRange(extendedData, extendedData.length - poly.length + 1, extendedData.length);
        return crc;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the data bits:\n");
        String dataInput = scanner.nextLine();
        int[] data = new int[dataInput.length()];
        for (int i = 0; i < dataInput.length(); i++) {
            data[i] = Character.getNumericValue(dataInput.charAt(i));
        }

        int[] dupData = Arrays.copyOf(data, data.length);

        System.out.print("Enter the generator polynomial in binary form:\n");
        String polyInput = scanner.nextLine();
        int[] poly = new int[polyInput.length()];
        for (int i = 0; i < polyInput.length(); i++) {
            poly[i] = Character.getNumericValue(polyInput.charAt(i));
        }
        int numofzeroes = polyInput.length() - 1;
        System.out.println("Number of zeroes to be appended to data: " + numofzeroes);
        for (int i = 0; i < numofzeroes; i++)
            dataInput += "0";
        System.out.println("After appending " + numofzeroes + " zeroes to the data bits, the dividend is " + dataInput);

        int[] crcCode = CRC(data, poly);
        int[] extendedDataWithCRC = Arrays.copyOf(dupData, dupData.length + crcCode.length);
        System.arraycopy(crcCode, 0, extendedDataWithCRC, dupData.length, crcCode.length);

        System.out.print("Now, after performing Modulo-2 division(XOR),\nRemainder: ");
        for (int i = extendedDataWithCRC.length - numofzeroes; i < extendedDataWithCRC.length; i++) {
            System.out.print(extendedDataWithCRC[i]);
        }
        System.out.println();
        System.out.print("CRC Code: ");
        for (int value : extendedDataWithCRC) {
            System.out.print(value);
        }
        System.out.println();

        check();
    }

    public static void check() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the data word received by you:\n");
        String dataInput = scanner.nextLine();
        int[] data1 = new int[dataInput.length()];
        for (int i = 0; i < dataInput.length(); i++) {
            data1[i] = Character.getNumericValue(dataInput.charAt(i));
        }

        int[] data1Dup = Arrays.copyOf(data1, data1.length);

        System.out.print("Enter your generator polynomial in binary form:\n");
        String polyInput = scanner.nextLine();
        int[] poly1 = new int[polyInput.length()];
        for (int i = 0; i < polyInput.length(); i++) {
            poly1[i] = Character.getNumericValue(polyInput.charAt(i));
        }

        for (int i = 0; i <= data1.length - poly1.length; i++) {
            if (data1[i] == 1) {
                for (int j = 0; j < poly1.length; j++) {
                    data1[i + j] = data1[i + j] ^ poly1[j];
                }
            }
        }

        int[] crc = Arrays.copyOfRange(data1, data1.length - poly1.length + 1, data1.length);

        int count = 0;
        for (int value : crc) {
            if (value != 0) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("The codeword received has no error, hence accepted");
            System.out.print("The correct data bits are = ");
            for (int i = 0; i < data1Dup.length - poly1.length + 1; i++) {
                System.out.print(data1Dup[i]);
            }
            System.out.println();
        } else {
            System.out.println("The received code word is wrong, hence rejected");
        }
    }
}
