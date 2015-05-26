import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerClient extends Thread {
	private Socket cSocket;
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss -> ");
	String uname = "Client";
	BufferedReader in = null;
	PrintWriter out = null;

	public ServerClient(Socket s) {
		cSocket = s;
		try {
			in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			out = new PrintWriter(cSocket.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		Calendar cal = Calendar.getInstance();
		cal.getTime();
		String msg = "";
		try {
			while (true) {
				if ((msg = in.readLine()) != null) {
					if (msg.startsWith("#USER-NAME#")) {
						uname = msg.substring("#USER-NAME#".length());
						msg = "Logged in.";
					}
					for (int i = 0; i < ServerMain.chatList.size(); i++) {
						ServerClient std = (ServerClient) ServerMain.chatList.get(i);
						std.sendResponse(uname + ": " + msg);
					}
					cal = Calendar.getInstance();
					System.out.println(sdf.format(cal.getTime()) + uname + ": " + msg);
					cal = Calendar.getInstance();
				}
			}
		} catch (Exception e) {
			System.out.println(sdf.format(cal.getTime()) + uname);
		}
	}

	synchronized void sendResponse(String data) {
		out.println(data);
	}

}