import java.net.*;  
import java.io.*;  
class Server{  
public static void main(String args[])throws Exception{  
ServerSocket ss=new ServerSocket(3333);  
Socket s=ss.accept();  
DataInputStream din=new DataInputStream(s.getInputStream());  
DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
  
String str="",str2="";String num;  
while(!str.equals("stop")){  
num=din.readUTF();  
//System.out.println("client says: "+str);  
System.out.println("Number given by client: "+num); 
int n = Integer.parseInt(num);
int square = n*n;
System.out.println("Square of Number given by client: "+square); 
//str2=br.readLine();  
dout.writeUTF("Square of Number given by you: "+square);  
dout.flush();  
}  
din.close();  
s.close();  
ss.close();  
}}  