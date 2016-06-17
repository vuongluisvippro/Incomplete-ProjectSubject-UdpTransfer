package utils;

import java.io.Serializable;

@SuppressWarnings("all")
public class SerialFile implements Serializable{
	/**
	 * Cần lưu trữ đường dẫn destinationFilePath <=> new String
	 * Cần lưu trữ đường dẫn sourceFilePath <=> new String
	 * Cần lưu trữ tên File đã xử lý <=> new String
	 * Cần lưu trữ kích thước File <=> new File
	 * Cần lưu trữ kích thước Dữ liệu chứa trong File <=> new File
	 * Cần lưu trữ trạng thái của File <=> new String
	 */
	private String destinationDirectory;
	private String sourceDirectory;
	private String fileName;
	private int sizeFile;
	private byte[] fileData = new byte[65508];
	private String status;
	
	public SerialFile() {
		super();
	}

	public SerialFile(String destinationDirectory, String sourceDirectory, String fileName, int sizeFile,
			byte[] fileData, String status) {
		super();
		this.destinationDirectory = destinationDirectory;
		this.sourceDirectory = sourceDirectory;
		this.fileName = fileName;
		this.sizeFile = sizeFile;
		this.fileData = fileData;
		this.status = status;
	}
	
	public SerialFile(String fileName, int sizeFile, byte[] fileData, String status) {
		super();
		this.fileName = fileName;
		this.sizeFile = sizeFile;
		this.fileData = fileData;
		this.status = status;
	}
	

	public String getDestinationDirectory() {
		return destinationDirectory;
	}

	public void setDestinationDirectory(String destinationDirectory) {
		this.destinationDirectory = destinationDirectory;
	}

	public String getSourceDirectory() {
		return sourceDirectory;
	}

	public void setSourceDirectory(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSizeFile() {
		return sizeFile;
	}

	public void setSizeFile(int sizeFile) {
		this.sizeFile = sizeFile;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
