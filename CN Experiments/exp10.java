// public class exp10 {

import java.net.*;
import java.io.*;
import java.util.*;

class MyClient {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 3333);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2 = "";
        while (!str.equals("stop")) {
            System.out.print("Enter number: ");
            // str=br.readLine();
            Scanner sc = new Scanner(System.in);
            int n = 10;
            n = sc.nextInt();
            // dout.writeUTF(str);
            // dout.writeUTF("Hello guys Chai Peelo");
            dout.writeUTF(Integer.toString(n));
            dout.flush();
            str2 = din.readUTF();
            System.out.println("Server says: " + str2);
        }

        dout.close();
        s.close();
    }
}

import java.net.*;
import java.io.*;

class Server {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(3333);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2 = "";
        String num;
        while (!str.equals("stop")) {
            num = din.readUTF();
            // System.out.println("client says: "+str);
            System.out.println("Number given by client: " + num);
            int n = Integer.parseInt(num);
            int square = n * n;
            System.out.println("Square of Number given by client: " + square);
            // str2=br.readLine();
            dout.writeUTF("Square of Number given by you: " + square);
            dout.flush();
        }
        din.close();
        s.close();
        ss.close();
    }
}
// }
