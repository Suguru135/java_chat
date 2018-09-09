import java.io.*;
import java.net.*;

public class ServerApp {

	public static final int PORT = 10000;

	public static void main(String[] args) {
	
		try {
			// ポートを開ける
			ServerSocket ss = new ServerSocket(PORT);

			// 受け取り(一回)
			System.out.println("受け取り待ち……");
			Socket socket = ss.accept();

			// Buffer用意
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter( socket.getOutputStream() )));

			// 受信
			String line;
			while((line = br.readLine()) != null && ! line.equals("<end>")) {
				System.out.println("->" + line);
			}
			
			System.out.println("読み込み完了");

			// 送信
			pw.println("ServerApp: success!");
			pw.println("<end>");
			pw.flush();

			System.out.println("送信完了");

			// close処理
			br.close();
			pw.close();
			socket.close();

			System.out.println("終わり");

		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}
