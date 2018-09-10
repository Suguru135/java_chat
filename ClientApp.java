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
			pw.println("<fin>");
			pw.flush();

			System.out.println("���M����");

			// ��M
			System.out.println("��M�҂��c�c");

			connect:
			while(true) {
				String line;

				receive:
				while(true) {
					line = br.readLine();

					if(line == null || line.equals("<fin>")) {

						System.out.println("�ǂݍ��݊��� && �ؒf");
						break connect;

					} else if(line.equals("<end>")) {

						System.out.println("�ǂݍ��݊���");
						break receive;

					} else {

						System.out.println("-> " + line);

					}
				}
			}

			System.out.println("�ؒf");

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
