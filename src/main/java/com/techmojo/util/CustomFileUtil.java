package com.techmojo.util;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;

public class CustomFileUtil {
	
	public static void cleanFiles(String directoryPath)
	{
		  try {
			FileUtils.cleanDirectory(new File(directoryPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
 
}
