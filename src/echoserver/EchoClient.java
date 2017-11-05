package echoserver;

import java.io.*;
import java.net.Socket;

public class EchoClient {
	public static final int PORT_NUMBER = 1337;
	private boolean closed = false;
	private static volatile Socket socket;
	private static volatile InputStream socketInputStream;
	private static volatile OutputStream socketOutputStream;
	private static int readByte;

	public static void main(String[] args) throws IOException {
		socket = new Socket("localhost", PORT_NUMBER);
		socketInputStream = socket.getInputStream();
		socketOutputStream = socket.getOutputStream();
		new usrInThread().start();
		new serverThread().start();

	}

	public static class usrInThread extends Thread {
		public void run() {
			try {
				while((readByte = System.in.read()) != -1){
					socketOutputStream.write((byte)readByte);
				}
			}
			catch(IOException ioE){
				System.err.println(ioE);
			}
		}
	}

	public static class serverThread extends Thread {
		public void run() {
			try {
				int socketByte;
					while ((socketByte = socketInputStream.read()) != -1) {
						System.out.write((byte)socketByte);
					}
				System.out.flush();
				socketOutputStream.flush();
			} catch(IOException ioE2){
				System.err.println(ioE2);
			}
		}
	}
}