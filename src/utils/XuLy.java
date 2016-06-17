package utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class XuLy {

	public void splitFile(File xFile,DatagramSocket client,InetAddress inetAddress,int port){
		
		int MAX_PART_SIZE = 65000;
		String FILE_NAME = xFile.getName();
		FileInputStream inputStreamRoot;
		FileOutputStream outputStreamPart;
		
		String nameofFilePart = null;
		 
		int sizeFile = (int) xFile.length();
		int nChunks = 0, read = 0, readLength = MAX_PART_SIZE;
		byte[] byteChunkPart;
		int count = 1;
		
		try {
			inputStreamRoot = new FileInputStream(xFile);
			while (sizeFile > 0) 
			{
				if (sizeFile <= MAX_PART_SIZE) {
					readLength = sizeFile;
				}
				byteChunkPart = new byte[readLength];
				read = inputStreamRoot.read(byteChunkPart, 0, readLength);
				sizeFile -= read;
				assert (read == byteChunkPart.length);
				nChunks++;
				nameofFilePart = FILE_NAME + ".part" + Integer.toString(nChunks - 1);
				/* ======================== GỬI XUỐNG CLIENT ======================== */
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				new ObjectOutputStream(outputStream).writeObject(new SerialFile(nameofFilePart, MAX_PART_SIZE, byteChunkPart, "Sucess"));
				outputStreamPart = new FileOutputStream(new File(nameofFilePart));
				client.send(new DatagramPacket(outputStream.toByteArray(), outputStream.toByteArray().length,inetAddress,port));
				System.out.println("Gửi gói tin thứ "+count+" lên server");
				byteChunkPart = null;
				outputStreamPart = null;
				count++;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
			inputStreamRoot.close();
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}
	public void joinFile(File xFile,int countFile,String destinationFilePath){
			String FILE_NAME = xFile.getName();
			File ofile = new File(destinationFilePath+FILE_NAME);
			FileOutputStream fos;
			FileInputStream fis;
			byte[] fileBytes;
			int bytesRead = 0;
			List<File> list = new ArrayList<File>();
			for(int i = 0;i < countFile; i++){
				list.add(new File(destinationFilePath+FILE_NAME+".part"+i));
			}
			try {
			    fos = new FileOutputStream(ofile,true);
			    for (File file : list) {
			        fis = new FileInputStream(file);
			        fileBytes = new byte[(int) file.length()];
			        bytesRead = fis.read(fileBytes, 0,(int)  file.length());
			        assert(bytesRead == fileBytes.length);
			        assert(bytesRead == (int) file.length());
			        fos.write(fileBytes);
			        fos.flush();
			        fileBytes = null;
			        fis.close();
			        fis = null;
			    }
			    fos.close();
			    fos = null;
			}catch (Exception exception){
				exception.printStackTrace();
			}
			for(int i = 0;i < countFile; i++){
				new File(destinationFilePath+FILE_NAME+".part"+i).delete();
			}
	}
	public static void main(String[]args){
		String sourceFilePath = "C:/Users/vuongluis/Desktop/Client/test2.mp3";
		String destinationFilePath = "C:/Users/vuongluis/Desktop/Server/";
		int countFile = new File(destinationFilePath).listFiles().length;
		System.out.println("Số file: "+countFile);
		new XuLy().joinFile(new File(sourceFilePath),countFile,destinationFilePath);
	}
}
