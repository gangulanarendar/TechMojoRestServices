package com.techmojo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.techmojo.model.BankRecord;
import com.techmojo.service.IBankRecordService;

@Controller
@RequestMapping("techmojo")
public class BankRecordController {
	@Autowired
	private IBankRecordService bankRecordService;

	@GetMapping("banks/{id}")
	public ResponseEntity<BankRecord> getBankRecordById(@PathVariable("id") Integer id) {
		BankRecord bankrecord = bankRecordService.getBankRecordById(id);
		return new ResponseEntity<BankRecord>(bankrecord, HttpStatus.OK);
	}

	@GetMapping("banks")
	public ResponseEntity<List<BankRecord>> getAllBankRecords() {
		List<BankRecord> list = bankRecordService.getAllBankRecords();
		return new ResponseEntity<List<BankRecord>>(list, HttpStatus.OK);
	}

	@PostMapping("banks")
	public ResponseEntity<Void> addBankRecord(@RequestBody BankRecord bankrecord, UriComponentsBuilder builder) {
		boolean flag = bankRecordService.addBankRecord(bankrecord);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/bankrecord/{id}").buildAndExpand(bankrecord.getBank()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("banks")
	public ResponseEntity<BankRecord> updateBankRecord(@RequestBody BankRecord bankrecord) {
		bankRecordService.updateBankRecord(bankrecord);
		return new ResponseEntity<BankRecord>(bankrecord, HttpStatus.OK);
	}

	@DeleteMapping("banks/{id}")
	public ResponseEntity<Void> deleteBankRecord(@PathVariable("id") Integer id) {
		bankRecordService.deleteBankRecord(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}