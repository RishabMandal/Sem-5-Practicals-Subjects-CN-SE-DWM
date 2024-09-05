
// Online IDE - Code Editor, Compiler, Interpreter
import java.util.*;

public class exp7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an IP Address");
        String IP = sc.next();
        // String arr[] = IP.split(".");
        if (!IP.contains(".")) {
            System.out.println("IP address " + IP + " is invalid");
            sc.close();
            return;
        }
        int IPClass;
        if (IP.charAt(1) == '.')
            IPClass = Integer.parseInt(IP.substring(0, 1));
        else if (IP.charAt(2) == '.')
            IPClass = Integer.parseInt(IP.substring(0, 2));
        else
            IPClass = Integer.parseInt(IP.substring(0, 3));
        if (IPClass >= 1 && IPClass <= 126)
            System.out.println("The IP address " + IP + " belongs to class A\nNet ID: " + IP
                    + "\nTotal no. of IP addresses possible: 256*256*256\nNetwork mask: 255.0.0.0");
        else if (IPClass >= 128 && IPClass <= 191)
            System.out.println("The IP address " + IP + " belongs to class B\nNet ID: " + IP
                    + "\nTotal no. of IP addresses possible: 256*256\nNetwork mask: 255.255.0.0");
        else if (IPClass >= 192 && IPClass <= 223)
            System.out.println("The IP address " + IP + " belongs to class C\nNet ID: " + IP
                    + "\nTotal no. of IP addresses possible: 256\nNetwork mask: 255.255.255.0");
        else if (IPClass >= 224 && IPClass <= 239)
            System.out.println("The IP address " + IP + " belongs to class D\nNet ID: " + IP
                    + "\nTotal no. of IP addresses possible: 256\nNetwork mask: 255.255.255.0");
        else if (IPClass >= 240 && IPClass <= 255)
            System.out.println("The IP address " + IP + " belongs to class E\nNet ID: " + IP
                    + "\nTotal no. of IP addresses possible: 256\nNetwork mask: 255.255.255.0");
        else {
            System.out.println("IP address " + IP + " is invalid");
            sc.close();
            return;
        }
        System.out.println("Now enter the number of subnets(power of 2)");
        int subnets = sc.nextInt();
        if ((subnets & 1) == 1)
            System.out.println("Number of subnets is not in the power of 2");
        String binary = Integer.toBinaryString(subnets);
        System.out.println("Number of subnets: " + subnets);
        System.out.println("Number of bits in subnets ID: " + (binary.length() - 1));
        int noOfSubnetAddress = ((int) Math.pow(2, 8 - (binary.length() - 1)));
        System.out.println(
                "Total no of IP addresses possible in each subnet: " + ((int) Math.pow(2, 8 - (binary.length() - 1))));
        int temp = -1;
        for (int i = 0; i < subnets; i++) {
            System.out.println("\nSubnet " + i + ": -");
            System.out.println("Subnet address - " + IP.substring(0, 12) + (temp + 1));
            temp += noOfSubnetAddress;
            System.out.println("Broadcast address - " + IP.substring(0, 12) + temp);
            System.out.println(
                    "Valid range of host IP address - " + IP.substring(0, 12) + (temp - noOfSubnetAddress + 2) + " - "
                            + IP.substring(0, 13) + (temp - 1));
        }
        sc.close();

    }
}
