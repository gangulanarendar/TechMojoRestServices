package com.techmojo.service;

import java.util.List;

import com.techmojo.model.BankRecord;

public interface IBankRecordService {
	List<BankRecord> getAllBankRecords();

	BankRecord getBankRecordById(long id);

	boolean addBankRecord(BankRecord bankrecord);

	void updateBankRecord(BankRecord bankrecord);

	void deleteBankRecord(long id);
}