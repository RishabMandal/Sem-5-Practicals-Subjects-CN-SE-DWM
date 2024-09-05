import java.util.Scanner;

public class IPMyOwn {
    static String getIPclass(int n) {
        if (n < 127) {
            return "A";
        } else if (n > 127 && n < 192) {
            return "B";
        } else if (n > 191 && n < 224) {
            return "C";
        } else if (n > 223 && n < 240) {
            return "D";
        } else if (n > 239 && n < 256) {
            return "E";
        } else {
            return "127 is reserved for loopback";
        }
    }

    static String getNetworkID(int a, int b, int c, int d, String IPclass) {
        if (IPclass.equals("A")) {
            return String.valueOf(a) + ".0.0.0";
        } else if (IPclass.equals("B")) {
            return String.valueOf(a) + "." + String.valueOf(b) + ".0.0";
        } else if (IPclass.equals("C")) {
            return String.valueOf(a) + "." + String.valueOf(b) + "." + String.valueOf(c) + ".0";
        } else {
            return String.valueOf(a) + "." + String.valueOf(b) + "." + String.valueOf(c) + "." + String.valueOf(d);
        }
    }

    static int getTotalIP(String IPclass) {
        if (IPclass.equals("A")) {
            return 256 * 256 * 256;
        } else if (IPclass.equals("B")) {
            return 256 * 256;
        } else if (IPclass.equals("C")) {
            return 256;
        } else {
            return 0;
        }
    }

    static String getMask(String IPclass) {
        if (IPclass.equals("A")) {
            return "255.0.0.0";
        } else if (IPclass.equals("B")) {
            return "255.255.0.0";
        } else if (IPclass.equals("C")) {
            return "255.255.255.0";
        } else {
            return "255.255.255.255";
        }
    }

    static int getSubnetTotal(String IPclass, int n) {
        int rem = 8 - n;

        if (IPclass.equals("A")) {
            return (int) Math.pow(2, (16 + rem));
        } else if (IPclass.equals("B")) {
            return (int) Math.pow(2, (8 + rem));
        } else if (IPclass.equals("C")) {
            return (int) Math.pow(2, rem);
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the IP address");
        String IP = sc.next();
        // String arr[] = IP.split("\\.");
        String arr[] = IP.split("[.]");
        int a = Integer.parseInt(arr[0]);
        int b = Integer.parseInt(arr[1]);
        int c = Integer.parseInt(arr[2]);
        int d = Integer.parseInt(arr[3]);

        String IPclass = getIPclass(a);
        System.out.println(IP + " is of Class " + IPclass);

        String network_id = getNetworkID(a, b, c, d, IPclass);
        System.out.println("Network ID is " + network_id);

        int total = getTotalIP(IPclass);
        System.out.println("No of possible IP addresses are " + total);

        String mask = getMask(IPclass);
        System.out.println("Network mask is " + mask);

        System.out.println("\nEnter the number of subnets");
        int subnets = sc.nextInt();
        int subnetBits = (int) Math.ceil(Math.log(subnets) / Math.log(2));
        System.out.println("The number of bits required for subnet: " + subnetBits);

        int subnet_total = getSubnetTotal(IPclass, subnetBits);
        System.out.println("Total number of IP addresses possible for each subnet: " + subnet_total);

        int count = -1;
        for (int i = 0; i < subnets; i++) {
            System.out.println("Subnet " + i + ":");
            System.out.println("Network Id: " + a + "." + b + "." + c + "." + (count + 1));
            System.out.println("Broadcast Id: " + a + "." + b + "." + c + "." + (count + subnet_total));
            System.out.println("Valid range of subnet IP addresses: " + a + "." + b + "." + c + "." + (count + 2)
                    + " - " + a + "." + b + "." + c + "." + (count + subnet_total - 1));
            count += subnet_total;
        }

        sc.close();
    }
}
