package guiclient;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import utils.Connectinfor;
import utils.XuLy;
import utils.SerialFile;

@SuppressWarnings("all")
public class Client {
	
	private static DatagramSocket client;	
	private static String sourceFilePath;
	private static InetAddress  inetAddress;
	private static int port;
	private Connectinfor mConnect;
	
	public Client(Connectinfor mConnect){
		this.mConnect = mConnect;
	}
	
	public void Running(){
		sourceFilePath = mConnect.getSourceFilePath();
		try {
			inetAddress = InetAddress.getByName(mConnect.getHost());
		} catch (UnknownHostException e1) {
			System.out.println(e1.getMessage());
		}
		port = mConnect.getPort();
		boolean cont = true;
		byte[] receivex = new byte[65507]; 
		try{
			client = new DatagramSocket();
			SerialFile iFile = new SerialFile();
			String fileName = sourceFilePath.substring(sourceFilePath.lastIndexOf("/")+1,sourceFilePath.length());		
			iFile.setFileName(new String(fileName));
			iFile.setSourceDirectory(sourceFilePath);		
			File file = new File(sourceFilePath);		
			if(file.isFile()){
				try {
					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int sizeFile = (int)file.length();
					if(sizeFile > 65508){
						System.out.println("Dung lượng truyền vào: "+sizeFile+" > 64KB");
						new XuLy().splitFile(new File(sourceFilePath),client,inetAddress,port);
					}else{
						byte[] dataFile = new byte[(int)sizeFile];
						int read = 0;
						int numRead = 0;
						while(read < dataFile.length && (numRead = in.read(dataFile,read,dataFile.length-read)) >= 0){
							read = read + numRead;
						}
						iFile.setSizeFile(sizeFile);
						iFile.setFileData(dataFile);
						iFile.setStatus("Success");
						ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
						new ObjectOutputStream(outputStream).writeObject(iFile);
						client.send(new DatagramPacket(outputStream.toByteArray(), outputStream.toByteArray().length,inetAddress,port));
						System.out.println("Gửi file lên server thành công!");
					}
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
					iFile.setStatus("Error");
				} catch (IOException e) {
					System.out.println(e.getMessage());
					iFile.setStatus("Error");
				}
			}else{
				iFile.setStatus("Error");
			}
		} catch (SocketException e){
			System.out.println(e.getMessage());
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
