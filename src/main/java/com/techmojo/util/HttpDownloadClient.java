package com.techmojo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpDownloadClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpDownloadClient.class);

	public static void main(String[] args) throws Exception {
		HttpDownloadClient client = new HttpDownloadClient();
		System.out.println("Http GET Request Example\n");
		client.downloadFileFromServer("https://rbidocs.rbi.org.in/rdocs/Content/DOCs/IFCB2009_01.xls");
		// System.out.println("\nHttp POST Request Example\n");
		// client.post();
	}

	public void downloadFileFromServer(String location) {
		LOGGER.info("downloadFileFromServer ", location);

		CustomFileUtil.cleanFiles("./ExternalResource");
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(location);
			request.setHeader("accepts", "application/vnd.ms-excel");
			HttpResponse response = client.execute(request);

			int responseCode = response.getStatusLine().getStatusCode();
			LOGGER.info("response.getEntity().getContentLength() " + response.getEntity().getContentLength());
			LOGGER.info("**GET** request Url: " + request.getURI());
			LOGGER.info("Response Code: " + responseCode);
			
			
			HttpEntity entity = response.getEntity();
			

			String filePath = "./ExternalResource/IFCB2009_01.xls";
			LOGGER.info("filePath ", filePath);

			BufferedInputStream bis = new BufferedInputStream(entity.getContent());

			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			int inByte;
			while ((inByte = bis.read()) != -1)
				bos.write(inByte);
			bos.flush();
			bis.close();
			bos.close();

		}

		catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}