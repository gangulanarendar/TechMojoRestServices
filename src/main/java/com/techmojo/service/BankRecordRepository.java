package com.techmojo.service;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.techmojo.model.BankRecord;

@RepositoryRestResource(collectionResourceRel = "bankinfo", path = "banks")
public interface BankRecordRepository  extends CrudRepository<BankRecord, Long> {

	List<BankRecord> findByBank(@Param("bankname") String name);
	List<BankRecord> findByIfsc(@Param("ifsc") String ifsc);
	List<BankRecord> findByCity(@Param("city") String city);
	List<BankRecord> findByState(@Param("state") String state);
	
    @Query("SELECT b FROM BankRecord b WHERE b.bank=:bank and b.ifsc=:ifsc")
    List<BankRecord> fetchBankInfo(@Param("bank") String bank, @Param("ifsc") String ifsc);
  
	
    
}






	