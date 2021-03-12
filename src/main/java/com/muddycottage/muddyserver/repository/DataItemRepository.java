package com.muddycottage.muddyserver.repository;

import java.util.List;

import com.muddycottage.muddyserver.model.DataItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataItemRepository extends CrudRepository<DataItem, Long>{

	List<DataItem> findAll () ;
	void flush() ;
}
