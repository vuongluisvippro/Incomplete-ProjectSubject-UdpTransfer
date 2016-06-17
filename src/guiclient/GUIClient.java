package guiclient;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.SWTResourceManager;

import utils.Connectinfor;

@SuppressWarnings("all")
public class GUIClient{

	protected Shell xudptransfer;
	private Text xtext;
	private Display display;
	private File selectedFile;
	private Text xtext1;
	private Text xtext2;
	private Text xtext3;
	private Text xtext4;
	
	private Connectinfor mConnect = new Connectinfor();

	public static void main(String[] args) {
		try {
			GUIClient window = new GUIClient();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
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
	protected void createContents() {
		xudptransfer = new Shell();
		xudptransfer.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		xudptransfer.setText("udptransfer");
		xudptransfer.setSize(450, 397);
		xudptransfer.setImage(new Image(display, "D:/ONE/WorkspaceStatic/udptransfer/image/icon.ico"));
		
		
		Composite composite = new Composite(xudptransfer, SWT.NONE);
		composite.setBounds(0, 0, 434, 360);
		composite.setLayout(null);
		
		Label xtitle1 = new Label(composite, SWT.NONE);
		xtitle1.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		xtitle1.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.BOLD));
		xtitle1.setBounds(33, 135, 69, 18);
		xtitle1.setText("Chọn File");
		
		xtext = new Text(composite, SWT.BORDER);
		xtext.setBackground(SWTResourceManager.getColor(255, 255, 255));
		xtext.setEnabled(false);
		xtext.setBounds(103, 134, 222, 21);
		
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
				xtext.setText(chooser.getCurrentDirectory().toString());
				xtext1.setText(selectedFile.getName());
				xtext2.setText(String.valueOf(selectedFile.length()));
				/**
				 * Xử lý dấu \ này tý nhé.
				 * Biến dấu \ thành dấu / 
				 */
				String sourceFilePath = new String(chooser.getCurrentDirectory().toString()+"\\"+selectedFile.getName().toString()).replace("\\", "/");
				mConnect.setSourceFilePath(sourceFilePath);
			}
		});
		xButton.setBounds(331, 132, 75, 25);
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
		
		Label xtitle4 = new Label(composite, SWT.NONE);
		xtitle4.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.BOLD));
		xtitle4.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		xtitle4.setBounds(31, 160, 96, 18);
		xtitle4.setText("Thông tin file");
		
		Label xtitle5 = new Label(composite, SWT.NONE);
		xtitle5.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		xtitle5.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		xtitle5.setBounds(62, 184, 96, 18);
		xtitle5.setText("Tên file");
		
		xtext1 = new Text(composite, SWT.BORDER);
		xtext1.setEnabled(false);
		xtext1.setBounds(164, 182, 161, 21);
		
		Label xtitle6 = new Label(composite, SWT.NONE);
		xtitle6.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		xtitle6.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		xtitle6.setBounds(62, 208, 96, 18);
		xtitle6.setText("Dung lượng file");
		
		xtext2 = new Text(composite, SWT.BORDER);
		xtext2.setEnabled(false);
		xtext2.setBounds(164, 209, 161, 21);
		
		Label xtitle7 = new Label(composite, SWT.NONE);
		xtitle7.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.BOLD));
		xtitle7.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		xtitle7.setBounds(31, 232, 141, 18);
		xtitle7.setText("Thông tin máy chủ");
		
		xtext3 = new Text(composite, SWT.BORDER);
		xtext3.setBounds(164, 256, 161, 21);
		
		Label xtitle8 = new Label(composite, SWT.NONE);
		xtitle8.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		xtitle8.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		xtitle8.setText("Tên Client/IP");
		xtitle8.setBounds(62, 258, 83, 18);
		
		Label xtitle9 = new Label(composite, SWT.NONE);
		xtitle9.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.NORMAL));
		xtitle9.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		xtitle9.setBounds(62, 282, 40, 18);
		xtitle9.setText("Port");
		
		xtext4 = new Text(composite, SWT.BORDER);
		xtext4.setBounds(164, 283, 160, 21);
		
		Button xButton1 = new Button(composite, SWT.FLAT);
		xButton1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				mConnect.setHost(xtext3.getText().toString().trim());
				mConnect.setPort(Integer.parseInt(xtext4.getText().toString().trim()));
				new Client(mConnect).Running();
				/**
				 * Hiển thị MessageBox thông báo
				 */
				int style = SWT.ICON_INFORMATION | SWT.OK;
				MessageBox dialogBox = new MessageBox(xudptransfer,style);
				dialogBox.setText("Thông báo");
				dialogBox.setMessage("Gửi file đến server thành công!");
				dialogBox.open();
			}
		});
		xButton1.setBounds(98, 319, 105, 25);
		xButton1.setText("Gửi");
		
		Button xButton2 = new Button(composite, SWT.FLAT);
		xButton2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		xButton2.setBounds(234, 319, 105, 25);
		xButton2.setText("Thoát");
		
		Label xtitle10 = new Label(composite, SWT.NONE);
		xtitle10.setFont(SWTResourceManager.getFont("Times New Roman", 10, SWT.BOLD));
		xtitle10.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		xtitle10.setBounds(62, 105, 65, 15);
		xtitle10.setText("Máy khách:");
		
		Label xtitle11 = new Label(composite, SWT.NONE);
		xtitle11.setBounds(133, 105, 141, 15);
		try {
			xtitle11.setText(InetAddress.getLocalHost().toString());
		} catch (UnknownHostException e1) {
			System.out.println(e1.getMessage());
		}
	}
}
