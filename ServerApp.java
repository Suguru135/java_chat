import java.io.*;
import java.net.*;

public class ServerApp {

	public static final int PORT = 10000;

	public static void main(String[] args) {
	
		try {
			// �|�[�g���J����
			ServerSocket ss = new ServerSocket(PORT);

			// �󂯎��(���)
			System.out.println("�󂯎��҂��c�c");
			Socket socket = ss.accept();

			// Buffer�p��
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter( socket.getOutputStream() )));

			// ��M
			String line;
			while((line = br.readLine()) != null && ! line.equals("<end>")) {
				System.out.println("->" + line);
			}
			
			System.out.println("�ǂݍ��݊���");

			// ���M
			pw.println("ServerApp: success!");
			pw.println("<end>");
			pw.flush();

			System.out.println("���M����");

			// close����
			br.close();
			pw.close();
			socket.close();

			System.out.println("�I���");

		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}
