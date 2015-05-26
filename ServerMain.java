import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ServerMain {

	static ArrayList<ServerClient> chatList;
	static Socket cSock;
	static int count;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		cSock = null;
		chatList = new ArrayList<ServerClient>();
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss -> ");

		try {
			final int PORT = 6677;
			ServerSocket server = new ServerSocket(PORT);
			cal = Calendar.getInstance();
			System.out.println(sdf.format(cal.getTime()) + "Server active.");

			while (true) {
				cSock = server.accept();

				cal = Calendar.getInstance();
				System.out.println(sdf.format(cal.getTime()) + "Client connected from " + cSock.getLocalAddress().getHostName() + ".");

				ServerClient t = new ServerClient(cSock);
				t.setName("T" + count);
				chatList.add(t);
				t.start();
				count++;
			}
		} catch (Exception e) {
			cal = Calendar.getInstance();
			System.out.println(sdf.format(cal.getTime()) + "An error occured.");
			e.printStackTrace();
		}
	}

}