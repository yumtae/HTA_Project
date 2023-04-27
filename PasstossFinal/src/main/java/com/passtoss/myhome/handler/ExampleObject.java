package com.passtoss.myhome.handler;

import java.io.File;

public class ExampleObject {
	
	private File file; 
	private byte[] bytes;

	public ExampleObject(File file, byte[] bytes) {
		this.bytes = bytes;
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	

}
