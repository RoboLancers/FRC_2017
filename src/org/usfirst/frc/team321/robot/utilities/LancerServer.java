package org.usfirst.frc.team321.robot.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class LancerServer {
	
	private ServerSocket serverSocket;
	private Socket socket;
	
	public LancerServer(int port) throws IOException{
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(1000);
	}
	
	public void startServer() throws IOException, SocketTimeoutException{
		socket = serverSocket.accept();
	}
	
	public String readFromClient(BufferedReader read) throws IOException{
		return read.readLine();
	}
	
	public void writeToClient(PrintWriter write, String message){
		write.println(message);
	}
	
	public Socket getSocket(){
		return socket;
	}
}
