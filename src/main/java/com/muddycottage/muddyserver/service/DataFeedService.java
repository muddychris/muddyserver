package com.muddycottage.muddyserver.service;

import java.util.List;

import javax.transaction.Transactional;

import com.muddycottage.muddyserver.dto.DataFeedDto;
import com.muddycottage.muddyserver.model.DataItem;
import com.muddycottage.muddyserver.repository.DataItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DataFeedService {

	private final Logger logger = LoggerFactory.getLogger(DataFeedService.class) ;

	@Autowired
	private DataItemRepository dataItemRepository ;

	public void processDataFeed(DataFeedDto dataFeedDto) {

		if (dataFeedDto.isPing()) {
			logger.info("PING {}", dataFeedDto.getPingInfo()); 
		}
		else {
			// convert the list of data items into model items to write to the DB

			List<DataItem> dataItemList = dataFeedDto.toModel();
			if (dataItemList != null) {
				for (DataItem dataItem : dataItemList) {
					// / now write via the repo
					logger.info("DATA : {}", dataItem);

					if (dataItemRepository != null)
					{
						logger.info("SAVE : {}", dataItem);
						dataItemRepository.save(dataItem) ;
					}
				}

				dataItemRepository.flush() ;
			}
		}
	}

}
