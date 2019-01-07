package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StoreSortDao;
import com.example.model.StoreSort;
import com.example.service.StoreSortService;

@Service("storeSortService")
public class StoreSortServiceImpl implements StoreSortService {
	@Autowired
	private StoreSortDao storeSortDao;

	@Override
	public List<StoreSort> findStoreSortList() {
		// TODO Auto-generated method stub
		return storeSortDao.findAll();
	}

	@Override
	public StoreSort addStoreSort(StoreSort storeSort) {
		// TODO Auto-generated method stub
		return storeSortDao.save(storeSort);
	}

	@Override
	public StoreSort ModifyStoreSort(StoreSort storeSort, int id) {
		StoreSort findStore = storeSortDao.findById(id);
		storeSortDao.findById(id);
		if (findStore == null) {
			//抛异常
			return null;
		} else {
			storeSort.setId(id);
			return storeSortDao.save(storeSort);
		}
	}

	@Override
	public void deleteStoreSort(int id) {
		storeSortDao.deleteById(id);
	}

}
