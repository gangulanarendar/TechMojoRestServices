package com.techmojo.util;

import java.io.File;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomFileUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomFileUtil.class);
	public static void cleanFiles(String directoryPath)
	{
		LOGGER.info("cleanFiles ",directoryPath);
		  try {
			FileUtils.cleanDirectory(new File(directoryPath));
		} catch (IOException e) {
			LOGGER.error("cleanFiles ",e.getMessage());
			e.printStackTrace();
		}
	}
	
 
}
