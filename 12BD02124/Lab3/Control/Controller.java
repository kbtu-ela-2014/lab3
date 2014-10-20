package Control;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.StringTokenizer;

import Model.*;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Controller {
	public static void main(String[] args) throws IOException {
		ManageStudent.initialize();
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/menu", new MenuHandler());
		server.createContext("/add", new AddHandler());
		server.createContext("/view", new ViewHandler());
		server.createContext("/change", new UpdateHandler());
		server.createContext("/delete", new DeleteHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
	}
	
	abstract static class MyHandler implements HttpHandler {
		protected abstract String run(HttpExchange t) throws IOException;		
		public void handle(HttpExchange t) throws IOException {
			String response = run(t);
			t.getResponseHeaders().add("Content-type", "text/html; charset=UTF-8");
			t.sendResponseHeaders(200, response.getBytes().length);
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}		
	}
	
	static class MenuHandler extends MyHandler {
		protected String run(HttpExchange t) throws IOException {
			String template = readFromFile("temp");
			return template.replace("#content#", readFromFile("menu"));			
		}
	}
	
	static class AddHandler extends MyHandler {
		protected String run(HttpExchange t) throws IOException {
			String template = readFromFile("temp");			
			String fname, lname, fac;
			int yof;
			String vars = getVars(t);
			System.out.println(vars);
			if (vars == null) {
				return template.replace("#content#", readFromFile("add"));
			}
			
			template = template.replace("#content#", readFromFile("message"));
			StringTokenizer st = new StringTokenizer(vars, "=&");
			try {
				st.nextToken(); fname = st.nextToken();
				st.nextToken(); lname = st.nextToken();
				st.nextToken(); fac = st.nextToken();
				st.nextToken(); yof = Integer.parseInt(st.nextToken());
				ManageStudent.addStudent(fname, lname, fac, yof);
			} catch (Exception e) {		
				e.printStackTrace();
				String page = template.replace("#content#", readFromFile("message"));
				return page.replace("#message#", "Some feilds was filled wrong, try again");
			}
			return template.replace("#message#", "User was successfully added.");
		}
	}

	static class ViewHandler extends MyHandler {
		protected String run(HttpExchange t) throws IOException {
			String template = readFromFile("temp").replace("#content#", readFromFile("view"));
			return template.replace("#table#", ManageStudent.listStudents());			
		}
	}
	
	static class UpdateHandler extends MyHandler {
		protected String run(HttpExchange t) throws IOException {
			String template = readFromFile("temp");
			String vars = getVars(t);
			if (vars == null) {
				return template.replace("#content#", readFromFile("change"));
			}

			String fac;
			Integer studentID;
			template = template.replace("#content#", readFromFile("message"));
			StringTokenizer st = new StringTokenizer(vars, "=&");
			try {
				st.nextToken(); studentID = Integer.parseInt(st.nextToken());
				st.nextToken(); fac = st.nextToken();
				ManageStudent.updateStudent(studentID, fac);
			} catch (Exception e) {				
				String page = template.replace("#content#", readFromFile("message"));
				return page.replace("#message#", "Something was wrong, try again.");
			}
			return template.replace("#message#", "User was successfully updated.");
		}
	}

	
	static class DeleteHandler extends MyHandler {
		protected String run(HttpExchange t) throws IOException {
			String template = readFromFile("temp");
			String vars = getVars(t);
			if (vars == null) {
				return template.replace("#content#", readFromFile("delete"));
			}
			
			Integer studentID;
			template = template.replace("#content#", readFromFile("message"));
			StringTokenizer st = new StringTokenizer(vars, "=&");
			try {
				st.nextToken(); studentID = Integer.parseInt(st.nextToken());
				ManageStudent.deleteStudent(studentID);
			} catch (Exception e) {				
				String page = template.replace("#content#", readFromFile("message"));
				return page.replace("#message#", "No such user.");
			}
			return template.replace("#message#", "User was successfully deleted.");
		}
	}
	
	private static String getVars(HttpExchange t) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody()));
		String vars = br.readLine();
		br.close();
		return vars;
	}
	
	private static String readFromFile(String from) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Lab3/" + from));
		String s, res = "";
		while (true) {
			s = br.readLine();
			if (s == null) 
				break;
			res += s + "\n";
		}
		br.close();
		return res;
	} 
	
	
}
