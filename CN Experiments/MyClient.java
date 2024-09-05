

import java.net.*;  
import java.io.*;
import java.util.*;  
class MyClient{  
public static void main(String args[])throws Exception{  
Socket s=new Socket("localhost",3333);  
DataInputStream din=new DataInputStream(s.getInputStream());  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
  
String str="",str2="";  
while(!str.equals("stop")){  
System.out.print("Enter number: ");
//str=br.readLine();  
Scanner sc = new Scanner(System.in);
int n = 10;
n = sc.nextInt();
//dout.writeUTF(str);  
//dout.writeUTF("Hello guys Chai Peelo");
dout.writeUTF(Integer.toString(n));
dout.flush();  
str2=din.readUTF();  
System.out.println("Server says: "+str2);  
}  
  
dout.close();  
s.close();  
}}  


