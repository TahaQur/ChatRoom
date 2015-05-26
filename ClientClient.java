import java.net.Socket;

public class ClientClient extends Thread {
	@SuppressWarnings("unused")
	private Socket socket;
	ChatClientUI cuser;
	public ClientClient(Socket s) {
		socket = s;
	}
	
	public ClientClient(ChatClientUI cuser) {
		this.cuser = cuser;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		try {
			String input ="";
			while (true) {
			input = cuser.readInputData();
			cuser.jTextArea1.setText(cuser.jTextArea1.getText() + "\n"+ input);
			cuser.jTextArea1.setCaretPosition(cuser.jTextArea1.getText().length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}