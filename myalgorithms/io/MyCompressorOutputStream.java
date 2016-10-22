package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream{
	
	private OutputStream out;
	
	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}

	@Override
	public void write(byte[] b) throws IOException {
		
		for (int i=0; i<9; i++)
			out.write(b[i]);
		
		int count=1;
		
		for (int i=10; i<b.length; i++)
		{
			if (count == 255 || b[i] != b[i-1])
			{
				out.write((byte)count);
				out.write(b[i-1]);
				count = 1;
			}
			else
				count++;
			
		}
	}

	@Override
	public void write(int b) throws IOException {
	}
}