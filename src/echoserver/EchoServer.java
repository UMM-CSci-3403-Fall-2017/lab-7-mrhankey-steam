package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
	public static ExecutorService threadPool;
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException, InterruptedException {

		try {
			ServerSocket sock = new ServerSocket(PORT_NUMBER);
			threadPool = Executors.newFixedThreadPool(64);


			while (true) {
				Socket clientSock = sock.accept();
				ServerThread serverThread = new ServerThread(clientSock);
				threadPool.execute(serverThread);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public static class ServerThread extends Thread {
		Socket clientSock;
		public ServerThread(Socket clientSock){
			this.clientSock = clientSock;
		}

		public void run() {
			try {
				InputStream inputStream = clientSock.getInputStream();
				OutputStream outputStream = clientSock.getOutputStream();
				int b;
			while ((b = inputStream.read()) != -1) {
				System.out.println((byte)b);
				outputStream.write((byte)b);
		}
			System.out.flush();
			outputStream.flush();
			clientSock.close();

			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}


}
