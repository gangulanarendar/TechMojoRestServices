package com.techmojo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

 
public class HttpDownloadClient {
 
    public static void main(String[] args) throws Exception {
        HttpDownloadClient client = new HttpDownloadClient();
        System.out.println("Http GET Request Example\n");
        client.downloadFileFromServer("https://rbidocs.rbi.org.in/rdocs/Content/DOCs/IFCB2009_01.xls");
       // System.out.println("\nHttp POST Request Example\n");
       // client.post();
    }
 
    public void downloadFileFromServer(String location) {
    	
    	CustomFileUtil.cleanFiles("./ExternalResource");
        try{
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(location);
            request.setHeader("accepts", "application/vnd.ms-excel");
            HttpResponse response = client.execute(request);
 
            int responseCode = response.getStatusLine().getStatusCode();
            System.out.println(response.getEntity().getContentLength());
            System.out.println("**GET** request Url: " + request.getURI());
            System.out.println("Response Code: " + responseCode);
            System.out.println("Content:-\n");
           // File myFile=new File("data.xls");           
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();
            String filePath = "./ExternalResource/IFCB2009_01.xls";
           // FileOutputStream fos = new FileOutputStream(myFile);
            
            BufferedInputStream bis = new BufferedInputStream(entity.getContent());
          
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            int inByte;
            while((inByte = bis.read()) != -1)
            	bos.write(inByte);
            bos.flush();
            bis.close();
            bos.close();
/*
            int inByte;
            while((inByte = is.read()) != -1)
                 fos.write(inByte);
            is.close();
            fos.close();*/
            
           /*
            if (entity != null) {
                try (FileOutputStream outstream = new FileOutputStream(myFile)) {
                    entity.writeTo(outstream);
                    outstream.flush();
                }
            }
            
           */ 
                       
          
            
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