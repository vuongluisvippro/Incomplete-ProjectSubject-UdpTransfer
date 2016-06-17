package guiserver;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import utils.Connectinfor;
import utils.SerialFile;

@SuppressWarnings("all")
public class GUIServerx {

	private JFrame frame;
	private JTextField xtext1;
	private JTextField xtext2;
	private JTextField xtext3;
	
	private Connectinfor mConnect = new Connectinfor();
	private DatagramSocket server;
	private byte[] sendx = new byte[65508];
	private byte[] receivex = new byte[65508];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIServerx window = new GUIServerx();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIServerx() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 372);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("udptransfer");
		frame.setIconImage(new ImageIcon("D:/ONE/WorkspaceStatic/udptransfer/image/icon.ico").getImage());
		
		JLabel xtitle = new JLabel("Truy\u1EC1n File Java S\u1EED D\u1EE5ng Giao Th\u1EE9c UDP");
		xtitle.setForeground(Color.RED);
		xtitle.setFont(new Font("Times New Roman", Font.BOLD, 14));
		xtitle.setBounds(87, 37, 265, 19);
		frame.getContentPane().add(xtitle);
		
		JLabel xtitle2 = new JLabel("\u0110\u1ED3 \u00C1n C\u01A1 S\u1EDF Ng\u00E0nh M\u1EA1ng");
		xtitle2.setForeground(Color.MAGENTA);
		xtitle2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		xtitle2.setBounds(140, 67, 172, 26);
		frame.getContentPane().add(xtitle2);
		
		JLabel xtitle3 = new JLabel("SVTH: Nguy\u1EC5n V\u0103n V\u01B0\u01A1ng - L\u1EDBp 12T2 - Khoa C\u00F4ng Ngh\u1EC7 Th\u00F4ng Tin");
		xtitle3.setForeground(Color.GREEN);
		xtitle3.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		xtitle3.setBounds(49, 106, 355, 14);
		frame.getContentPane().add(xtitle3);
		
		JLabel xtitle4 = new JLabel("M\u00E1y ch\u1EE7");
		xtitle4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		xtitle4.setForeground(Color.RED);
		xtitle4.setBounds(69, 131, 59, 19);
		frame.getContentPane().add(xtitle4);
		
		JLabel xtitle5 = new JLabel("");
		xtitle5.setBounds(141, 134, 249, 14);
		frame.getContentPane().add(xtitle5);
		try {
			xtitle5.setText(InetAddress.getLocalHost().toString());
		} catch (UnknownHostException e1) {
			System.out.println(e1.getMessage());
		}
		
		JLabel xtitle6 = new JLabel("Th\u00F4ng Tin Server");
		xtitle6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		xtitle6.setForeground(Color.BLUE);
		xtitle6.setBounds(69, 166, 118, 19);
		frame.getContentPane().add(xtitle6);
		
		JLabel xtitle7 = new JLabel("T\u00EAn Server/IP");
		xtitle7.setFont(new Font("Times New Roman", Font.BOLD, 14));
		xtitle7.setForeground(Color.PINK);
		xtitle7.setBounds(97, 196, 98, 19);
		frame.getContentPane().add(xtitle7);
		
		JLabel xtitle8 = new JLabel("Port:");
		xtitle8.setForeground(Color.PINK);
		xtitle8.setFont(new Font("Times New Roman", Font.BOLD, 14));
		xtitle8.setBounds(95, 220, 46, 19);
		frame.getContentPane().add(xtitle8);
		
		JButton xbutton1 = new JButton("Nh\u1EADn ");
		xbutton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		xbutton1.setBounds(98, 281, 89, 23);
		frame.getContentPane().add(xbutton1);
		
		JLabel xtitle9 = new JLabel("N\u01A1i l\u01B0u");
		xtitle9.setFont(new Font("Times New Roman", Font.BOLD, 14));
		xtitle9.setForeground(Color.BLUE);
		xtitle9.setBounds(69, 250, 59, 20);
		frame.getContentPane().add(xtitle9);
		
		xtext1 = new JTextField();
		xtext1.setEditable(false);
		xtext1.setBounds(200, 196, 152, 20);
		frame.getContentPane().add(xtext1);
		xtext1.setColumns(10);
		try {
			xtext1.setText(InetAddress.getLocalHost().toString());
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
		
		xtext2 = new JTextField();
		xtext2.setEditable(false);
		xtext2.setBounds(200, 220, 152, 20);
		frame.getContentPane().add(xtext2);
		xtext2.setColumns(10);
		xtext2.setText(String.valueOf(4000).toString());
		
		xtext3 = new JTextField();
		xtext3.setEditable(false);
		xtext3.setBounds(140, 250, 212, 20);
		frame.getContentPane().add(xtext3);
		xtext3.setColumns(10);
		
		JButton xbutton2 = new JButton("H\u1EE7y");
		xbutton2.setBounds(246, 281, 89, 23);
		xbutton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().add(xbutton2);
		/**-------- LOAD SERVER
		 * Server tự động khởi động lên
		 */
//		int i = 1;
//		try{
//			server = new DatagramSocket(4000);
//			while(true){
//				DatagramPacket dp = new DatagramPacket(receivex, receivex.length);
//				server.receive(dp);
//				ByteArrayInputStream inputStream = new ByteArrayInputStream(dp.getData());
//				SerialFile xFile = (SerialFile)new ObjectInputStream(inputStream).readObject();
//				xFile.setDestinationDirectory(mConnect.getDestinationFilePath());
//	
//				String destinationFile = xFile.getDestinationDirectory()+xFile.getFileName();
//				if(!new File(xFile.getDestinationDirectory()).exists()){
//					new File(xFile.getDestinationDirectory()).mkdirs();
//				}
//				
//				File yFile = new File(destinationFile);
//				FileOutputStream outputStream = null;
//				try {
//					outputStream = new FileOutputStream(yFile);
//					outputStream.write(xFile.getFileData());
//					outputStream.flush();
//					outputStream.close();
//					if(xFile.getSizeFile() < 65000){
//						System.exit(0);
//					}
//				} catch (FileNotFoundException e) {
//				} catch (IOException e) {
//				}
//				if(xFile.getStatus().equals("Error")){
//					System.exit(0);
//				}
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//				}
//			}
//		} catch(IOException e){
//			System.out.println(e.getMessage());
//		} catch (ClassNotFoundException e1) {
//			System.out.println(e1.getMessage());
//		}
		try
		{
 			server=new DatagramSocket(9999);
			while(true)
			{
            	DatagramPacket datapack=new DatagramPacket(receivex,receivex.length);
				server.receive(datapack);
				String msg=new String(datapack.getData()).substring(0,datapack.getLength());
				//txtarea.append("\nClient:"+msg);
			}
		}catch(Exception e){
			System.out.println("Error "+e.getMessage());
		}
	}
}
