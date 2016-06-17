package guiserver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import utils.Connectinfor;
import utils.SerialFile;

@SuppressWarnings("all")
public class Server{
	
	private static DatagramSocket server;
	private static int port;
	private static Connectinfor mConnect;
	
	public Server(Connectinfor mConnect){
		this.mConnect = mConnect;
	}
	
	public void RunningServer(){
		port = mConnect.getPort();
		byte[] receivex = new byte[65508];
		int i = 1;
		try {
			server = new DatagramSocket(port);
			System.out.println("Server ready!");
				while(true){
					DatagramPacket dp = new DatagramPacket(receivex, receivex.length);
					server.receive(dp);
					ByteArrayInputStream inputStream = new ByteArrayInputStream(dp.getData());
					SerialFile xFile = (SerialFile)new ObjectInputStream(inputStream).readObject();
					xFile.setDestinationDirectory(mConnect.getDestinationFilePath());
	
					String destinationFile = xFile.getDestinationDirectory()+xFile.getFileName();
					if(!new File(xFile.getDestinationDirectory()).exists()){
						new File(xFile.getDestinationDirectory()).mkdirs();
					}
					
					File yFile = new File(destinationFile);
					FileOutputStream outputStream = null;
					try {
						outputStream = new FileOutputStream(yFile);
						outputStream.write(xFile.getFileData());
						outputStream.flush();
						outputStream.close();
						System.out.println("Nhận gói tin thứ "+(i++)+" thành công");
						if(xFile.getSizeFile() < 65000){
							System.exit(0);
						}
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
					if(xFile.getStatus().equals("Error")){
						System.out.println("Có lỗi trong quá trình nhận file!");
						System.exit(0);
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
			} catch (SocketException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
	}
}
