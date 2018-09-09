import java.io.*;
import java.net.*;

public class ClientApp {

	public static final String HOST = "localhost";
	public static final int PORT = 10000;

	public static void main(String[] args){
		
		try {

			// �ڑ�
			Socket socket = new Socket(HOST, PORT);

			// Buffer�p��
			BufferedReader br = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter( socket.getOutputStream() )));

			// ���M
			pw.println("����ɂ���");
			pw.println("���������H");
			pw.println("<end>");
			pw.flush();

			System.out.println("���M����");

			// ��M
			String line;
			System.out.println("��M�҂��c�c");

			while((line = br.readLine()) != null && ! line.equals("<end>")) {
				System.out.println("-> " + line);
			}

			System.out.println("��M����");

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