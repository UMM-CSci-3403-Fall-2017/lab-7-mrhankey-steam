package echoserver;

import java.io.*;
import java.net.Socket;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;
	//private Thread keyThread = null;
	//private Thread serverThread = null;
	private boolean closed = false;
	private static Socket socket;
	private static InputStream socketInputStream;
	private static OutputStream socketOutputStream;
	private static int readByte;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
	}

	public static class usrInThread extends Thread {
		public void run() {
			try {
				//System.out.println("AYYYYYyyyeeeee");
				socket = new Socket("localhost", PORT_NUMBER);
				socketInputStream = socket.getInputStream();
				socketOutputStream = socket.getOutputStream();
				while((readByte = System.in.read()) != -1){
					socketOutputStream.write(readByte);
					System.out.println("first While");
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
				//System.out.println("234235");
				int socketByte;
				while((socketByte = socketInputStream.read()) != -1){
					System.out.write(socketByte);
					System.out.println("second While");
				}
			} catch(IOException ioE2){
				System.err.println(ioE2);
			}
		}
	}

	private void start() throws IOException {
		new usrInThread().start();
		new serverThread().start();

//		socketOutputStream.close();
//		socketInputStream.close();
//		socket.close();
		System.out.flush();

//		Socket socket = new Socket("localhost", PORT_NUMBER);
//		InputStream socketInputStream = socket.getInputStream();
//		InputStream stdIn = System.in;
//		BufferedReader in = new BufferedReader(new InputStreamReader(stdIn));
//
//		OutputStream socketOutputStream = socket.getOutputStream();
//		int readByte;
//
//		//keyThread.start();
//		while ((readByte = System.in.read()) != -1) {
//			socketOutputStream.write(readByte);
//		}
//		int readByte;
//		while ((readByte = System.in.read()) != -1) {
//			socketOutputStream.write(readByte);
//			int socketByte = socketInputStream.read();
//			System.out.write(socketByte);
//		}
	}
//	private void run() throws IOException {
//
//		int socketByte = socketInputStream.read();
////			System.out.write(socketByte);
//
//		System.out.flush();
//	}
}