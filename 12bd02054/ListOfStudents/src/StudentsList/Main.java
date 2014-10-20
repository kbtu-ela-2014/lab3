package StudentsList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.hibernate.Session;


import org.hibernate.SessionFactory;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
  HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
	     
	     server.createContext("/", new Handler());
	        
	     server.setExecutor(null); // creates a default executor
	     server.start(); 
        
	}

	public static Vector<String> readUserInput(HttpExchange t) throws IOException {
		 InputStream is = t.getRequestBody();
		 BufferedReader br = new BufferedReader(new InputStreamReader(is));
		 
		 Vector<String> data = new Vector<>();
		 String s;
		 s = br.readLine();
		 
		 s = URLDecoder.decode(s);
		// System.out.println(s);
		 StringTokenizer st = new StringTokenizer(s, "=&");
	     while (st.hasMoreTokens()) {
	    	 data.add(st.nextToken());
	     }
	     return data;
		}
		public static String readFromFile(String fileName) throws IOException{
			String response = null;
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			while (line != null) {
	            response+=line+"\n";
	            line = br.readLine();
	      }
			br.close();
			return response;
		}
   public static int convertToInt(String s){
	   char a[];
	   int result = 0;
	   int h=1;
	   a = s.toCharArray();
	   int n = s.length();
	   for(int i=n-1; i>=0; i--){
		   result += (a[i]-48)*h;
		   h*=10;
	   }
	   return result;
   }
		
		
		
	static class Handler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
        	
        	
        	
        	URI uri = t.getRequestURI();
        	String path = uri.toString();
            String response = new String();
            
            String templ = readFromFile("main.html");
            String view = new String();
           
        
            System.out.println(100);
            
            
            if(path.equals("/update")){
            	view = ListManage.viewOfList();
            	response = templ.replace("%content%", view);
            	
            	Vector<String> data = readUserInput(t);
            	int id = convertToInt(data.get(1));
            	String firstName = data.get(1);
            	String lastName = data.get(3);
            	
            	Student st = new Student();
            	st.setId(id);
            	ListManage.editStudent(st, lastName, firstName);
           }
            else if(path.equals("/delete")){
            	view = ListManage.viewOfList();
            	response = templ.replace("%content%", view);
            	Vector<String> data = readUserInput(t);
            	int id = convertToInt(data.get(1));
            	System.out.println(data.get(1));
            	Student st = new Student();
            	st.setId(id);
            	ListManage.deleteStudent(st);
           }
            else if(path.equals("/reg")){
            	view = ListManage.viewOfList();
            	response = templ.replace("%content%", view);
            	Vector<String> data = readUserInput(t);
            	
            	String firstName = data.get(1);
            	String lastName = data.get(3);
            	
            	
            	Student st = new Student();
            	//st.setId(id);
            	st.setFirstName(firstName);
            	st.setLastName(lastName);
            	
            	ListManage.addStudent(st);
           }
            else if(!path.equals("/")){
            	view = ListManage.viewOfList();
            	response = templ.replace("%content%", view);
            	
        		path=path.substring(1);
        		String sub=readFromFile(path);
 
            }
            
              else if(path.equals("/")){
            	  view = ListManage.viewOfList();
              	response = templ.replace("%content%", view);
            	
            	
           
            }
       
            
			
			Headers header = t.getResponseHeaders();	//adding header
			header.add("Content-Type", "text/html; charset=UTF-8");
			System.out.println("Sheeeeeeeest'");
			path = "";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        
    }
	

	}


}
