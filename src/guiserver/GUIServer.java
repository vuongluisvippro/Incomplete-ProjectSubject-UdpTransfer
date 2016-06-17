package guiserver;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;

import utils.Connectinfor;
import utils.SerialFile;

@SuppressWarnings("all")
public class GUIServer{

	protected Shell xudptransfer;
	private Text xtext;
	private Display display;
	private File selectedFile;
	
	private Connectinfor mConnect = new Connectinfor();
	private Text xtext1;
	private Text xtext2;
	
	private DatagramSocket server;
	private byte[] sendx = new byte[65508];
	private byte[] receivex = new byte[65508];

	/**
	 * Launch the application.
	 * @param args
	 */
	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		xudptransfer.open();
		xudptransfer.layout();
		while (!xudptransfer.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		xudptransfer = new Shell();
		xudptransfer.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		xudptransfer.setText("udptransfer");
		xudptransfer.setSize(450, 309);
		xudptransfer.setImage(new Image(display, "D:/ONE/WorkspaceStatic/udptransfer/image/icon.ico"));
		
		
		Composite composite = new Composite(xudptransfer, SWT.NONE);
		composite.setBounds(0, 0, 434, 270);
		composite.setLayout(null);
		
		Label xtitle1 = new Label(composite, SWT.NONE);
		xtitle1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		xtitle1.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.BOLD));
		xtitle1.setBounds(72, 209, 84, 18);
		xtitle1.setText("Chọn nơi lưu");
		
		xtext = new Text(composite, SWT.BORDER);
		xtext.setEditable(false);
		xtext.setBackground(SWTResourceManager.getColor(255, 255, 255));
		xtext.setBounds(178, 208, 144, 21);
		
		Button xButton = new Button(composite, SWT.FLAT);
		xButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		xButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int returnValue = chooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
			        selectedFile = chooser.getSelectedFile(); 
				}
				String destinationFilePath = new String(chooser.getCurrentDirectory().toString()+"\\").replace("\\", "/");
				mConnect.setDestinationFilePath(destinationFilePath);
				xtext.setText(mConnect.getDestinationFilePath());
			}
		});
		xButton.setBounds(328, 206, 75, 25);
		xButton.setText("Browse");
		
		Label xtitle = new Label(composite, SWT.NONE);
		xtitle.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		xtitle.setAlignment(SWT.CENTER);
		xtitle.setFont(SWTResourceManager.getFont("Times New Roman", 12, SWT.BOLD));
		xtitle.setBounds(42, 24, 351, 21);
		xtitle.setText("Truyền File Java Sử Dụng Giao Thức UDP");
		
		Label xtitle2 = new Label(composite, SWT.NONE);
		xtitle2.setForeground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		xtitle2.setAlignment(SWT.CENTER);
		xtitle2.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.BOLD));
		xtitle2.setBounds(140, 51, 179, 21);
		xtitle2.setText("Đồ Án Cơ Sở Ngành Mạng");
		
		Label xtitle3 = new Label(composite, SWT.NONE);
		xtitle3.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.ITALIC));
		xtitle3.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		xtitle3.setAlignment(SWT.CENTER);
		xtitle3.setBounds(28, 81, 376, 18);
		xtitle3.setText("SVTH: Nguyễn Văn Vương - Lớp 12T2 - Khoa Công Nghệ Thông Tin");
		
		Button xButton1 = new Button(composite, SWT.FLAT);
		xButton1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/**
				 * Khi Click nút nhận ta sẻ được file và ghép thành file hoàn chỉnh
				 */
			}
		});
		xButton1.setBounds(108, 235, 105, 25);
		xButton1.setText("Nhận");
		
		Button xButton2 = new Button(composite, SWT.FLAT);
		xButton2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(1);
			}
		});
		xButton2.setBounds(252, 235, 105, 25);
		xButton2.setText("Thoát");
		
		Label xtitle4 = new Label(composite, SWT.NONE);
		xtitle4.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.BOLD));
		xtitle4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		xtitle4.setBounds(72, 105, 55, 15);
		xtitle4.setText("Máy Chủ:");
		
		Label xtitle5 = new Label(composite, SWT.NONE);
		xtitle5.setBounds(133, 105, 141, 15);
		try {
			xtitle5.setText(InetAddress.getLocalHost().toString());
			
			Label xtitle6 = new Label(composite, SWT.NONE);
			xtitle6.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.BOLD));
			xtitle6.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
			xtitle6.setBounds(28, 126, 115, 18);
			xtitle6.setText("Thông tin server");
			
			Label xtitle7 = new Label(composite, SWT.NONE);
			xtitle7.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
			xtitle7.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.BOLD));
			xtitle7.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
			xtitle7.setBounds(72, 150, 93, 18);
			xtitle7.setText("Tên Server/IP");
			
			xtext1 = new Text(composite, SWT.BORDER);
			xtext1.setEditable(false);
			xtext1.setEnabled(false);
			xtext1.setBounds(178, 150, 141, 21);
			xtext1.setText(InetAddress.getLocalHost().toString());
			
			Label xtitle8 = new Label(composite, SWT.NONE);
			xtitle8.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
			xtitle8.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.BOLD));
			xtitle8.setBounds(72, 174, 33, 18);
			xtitle8.setText("Port:");
			
			xtext2 = new Text(composite, SWT.BORDER);
			xtext2.setEnabled(false);
			xtext2.setBounds(178, 177, 141, 21);
			xtext2.setText(String.valueOf(4000).toString());
		} catch (UnknownHostException e1) {
			System.out.println(e1.getMessage());
		}
		/**-------- LOAD SERVER
		 * Server tự động khởi động lên
		 */
		int i = 1;
		try{
			server = new DatagramSocket(4000);
			// System.out.println("Server start!");
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
					// System.out.println("Nhận gói tin thứ "+(i++)+" thành công");
					if(xFile.getSizeFile() < 65000){
						System.exit(0);
					}
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				if(xFile.getStatus().equals("Error")){
					// System.out.println("Có lỗi trong quá trình nhận file!");
					System.exit(0);
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		} catch(IOException e){
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		}
	}
	public static void main(String[] args) {
		GUIServer window = null;
		try {
			window = new GUIServer();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
