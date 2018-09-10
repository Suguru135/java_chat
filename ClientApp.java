import java.io.*;
import java.net.*;

public class ClientApp {

	public static final String HOST = "localhost";
	public static final int PORT = 10000;

	public static void main(String[] args){
		
		try {

			// 接続
			Socket socket = new Socket(HOST, PORT);

			// Buffer用意
			BufferedReader br = new BufferedReader(new InputStreamReader( socket.getInputStream() ));
			PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter( socket.getOutputStream() )));

			// 送信
			pw.println("こんにちは");
			pw.println("成功した？");
			pw.println("<fin>");
			pw.flush();

			System.out.println("送信完了");

			// 受信
			System.out.println("受信待ち……");

			connect:
			while(true) {
				String line;

				receive:
				while(true) {
					line = br.readLine();

					if(line == null || line.equals("<fin>")) {

						System.out.println("読み込み完了 && 切断");
						break connect;

					} else if(line.equals("<end>")) {

						System.out.println("読み込み完了");
						break receive;

					} else {

						System.out.println("-> " + line);

					}
				}
			}

			System.out.println("切断");

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
