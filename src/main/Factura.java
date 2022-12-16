package main;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.zip.*;

public class Factura {
	
	private String name;
	
	private String url;
	
	//Getters & Setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	//Methods
	
	
	//Download
    public void downloadFile() throws IOException{;
        URL url = new URL(this.getUrl());
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(System.getenv("USERPROFILE") + "\\Downloads\\" + this.getName());
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
    
    //Delete file
    public void deleteFile() {
    	
    	File file = new File(System.getenv("USERPROFILE") + "\\Downloads\\" + this.getName());
    	
    	try {
			
    		file.delete();
    		
    		System.out.println("Archivo: " + this.getName() + " eliminado.");
    		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
    		
    }
	
    //Decompress
    
    public void decompress() throws Exception {
        ZipFile zf = new ZipFile(new File(System.getenv("USERPROFILE") + "\\Downloads\\" + this.getName()));
        try (zf) {
            Enumeration<? extends ZipEntry> entries = zf.entries();
            for (ZipEntry ze : Collections.list(entries)) {
                System.out.printf("Inflating %s (compressed: %s, size: %s, ratio: %.2f)%n", ze.getName(), ze.getCompressedSize(), ze.getSize(), (double) ze.getSize() / ze.getCompressedSize());
                InputStream is = zf.getInputStream(ze);
                FileOutputStream fos = new FileOutputStream(new File(System.getenv("USERPROFILE") + "\\Downloads\\", ze.getName()));
                try (is; fos) {
                    fos.write(is.readAllBytes());
                }
            }
        }
    }

}
