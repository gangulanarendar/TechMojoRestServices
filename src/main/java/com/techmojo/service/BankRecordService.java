package com.techmojo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmojo.model.BankRecord;

@Service
public class BankRecordService implements IBankRecordService {
	@Autowired
	private BankRecordRepository bankRecordRepository;

	@Override
	public BankRecord getBankRecordById(long id) {
		BankRecord obj = bankRecordRepository.findById(id).get();
		return obj;
	}

	@Override
	public List<BankRecord> getAllBankRecords() {
		List<BankRecord> list = new ArrayList<>();
		bankRecordRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public synchronized boolean addBankRecord(BankRecord bankrecord) {
		List<BankRecord> list = bankRecordRepository.fetchBankInfo(bankrecord.getBank(), bankrecord.getIfsc());
		if (list.size() > 0) {
			return false;
		} else {
			bankRecordRepository.save(bankrecord);
			return true;
		}
	}

	@Override
	public void updateBankRecord(BankRecord bankrecord) {
		bankRecordRepository.save(bankrecord);
	}

	@Override
	public void deleteBankRecord(long id) {
		bankRecordRepository.delete(getBankRecordById(id));
	}

	@Override
	public void deleteAllRecords() {
		bankRecordRepository.deleteAll();
		
	}
}