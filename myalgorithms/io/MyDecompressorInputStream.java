package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
	
	private InputStream input;
	
	public MyDecompressorInputStream(InputStream input) {
		this.input = input;
	}

	@Override
	public int read() throws IOException {
		return 0;
	}
	
	@Override
	public int read(byte[] b) throws IOException
	{
		for (int i=0; i<9; i++)
			b[i]=(byte)input.read();
		
		int amount,	place = 9;
        while((amount=input.read())!=-1)
        { 	
        	int WhatToWrite=input.read();
        	for (int j = place; j < place + amount; j++)
        	{
        		b[j]=(byte)WhatToWrite;
        	}
    		place+=amount;  		
        }
        
		return 0;	
	}

}
