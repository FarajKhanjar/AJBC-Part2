package ajbc.webservice.rest.api_demo.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.google.gson.Gson;

import ajbc.webservice.rest.api_demo.DBservice.StudentDBservice;
import ajbc.webservice.rest.api_demo.models.Student;

public class ServerSocketThread implements Runnable {

	private Socket clientSocket;
	private StudentDBservice studentDB;
	public ServerSocketThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
		studentDB = new StudentDBservice();
	}

	@Override
	public void run() {

		try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);) {
							
			Student student1 = new Student();
			studentDB.addStudent(student1);
			System.out.println("added student with id "+student1.getID());
			String line = bufferReader.readLine();

			System.out.println("Thing says: " + line);
			Gson gson = new Gson();
			Student student = gson.fromJson(line, Student.class);
			StudentDBservice dbService = new StudentDBservice();
			dbService.addStudent(student);

		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

}
