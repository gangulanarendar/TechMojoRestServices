package com.techmojo.schedular;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.techmojo.model.BankRecord;
import com.techmojo.service.BankRecordService;
import com.techmojo.util.ExcelReader;
import com.techmojo.util.HttpDownloadClient;

@Component
public class ScheduledTasks {
	@Autowired
	private BankRecordService bankRecordService;
	
	

    public BankRecordService getBankRecordService() {
		return bankRecordService;
	}

	public void setBankRecordService(BankRecordService bankRecordService) {
		this.bankRecordService = bankRecordService;
	}

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron="*/60 * * * * *")
    public void reportCurrentTime() {
    	bankRecordService.deleteAllRecords();
    	HttpDownloadClient downloadClient=new HttpDownloadClient();
    	downloadClient.downloadFileFromServer("https://rbidocs.rbi.org.in/rdocs/Content/DOCs/IFCB2009_01.xls");
    	ExcelReader excelReader=new ExcelReader();
    	try {
    		ArrayList<BankRecord> bankRecordsDtos=excelReader.processRequest("./ExternalResource/IFCB2009_01.xls");
    		for(BankRecord record:bankRecordsDtos)
    		{
    			bankRecordService.addBankRecord(record);
    		}
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
        log.info("The time is now {}", dateFormat.format(new Date()));
        
        
    }
}