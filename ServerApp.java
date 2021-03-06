import java.io.*;
import java.net.*;

public class ServerApp {

	public static final int PORT = 10000;

	public static void main(String[] args) {
	
		try {
			// ポートを開ける
			ServerSocket ss = new ServerSocket(PORT);

			// 受け取り
			
			while(true) {

				System.out.println("受け取り待ち……");
				Socket socket = ss.accept();

				Thread connect = new Thread(new Connect(socket));
				connect.start();
			}


		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}


class Connect implements Runnable {
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;

	public Connect(Socket socket) {
		this.socket = socket;
		try {
			// Buffer用意
			this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter( this.socket.getOutputStream() )));
		
		} catch(IOException e) {
			e.printStackTrace();
		}
	}


	public void run() {
		try {
			connect:
			while(true) {
				// 受信
				String line;

				receive:
				while(true) {
					line = this.br.readLine();

					if(line == null || line.equals("<fin>")) {
						System.out.println("読み込み完了");

						// 送信
						this.pw.println("ServerApp: disconnected!");
						this.pw.println("<fin>");
						this.pw.flush();

						System.out.println("disconnected:送信完了");

						// 切断
						break connect;

					} else if(line.equals("<end>")) {
						System.out.println("読み込み完了");

						// 送信
						this.pw.println("ServerApp: received!");
						this.pw.println("<end>");
						this.pw.flush();

						System.out.println("reveived:送信完了");

						// メッセージの終了
						break receive;

					} else {

						System.out.println("->" + line);
					}
				}

			}

		} catch(Exception e) {

			e.printStackTrace();

		} finally {
			
			try{
				// close処理
				this.br.close();
				this.pw.close();
				this.socket.close();

			} catch(IOException e) {

				e.printStackTrace();

			}

			System.out.println("終わり");
		}
	}

}

