package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.SortDao;
import com.example.dao.StoreDao;
import com.example.model.Sort;
import com.example.model.Store;
import com.example.service.SortService;

@Service("sortService")
public class SortServiceImpl implements SortService {
	@Autowired
	private SortDao sortDao;
	@Autowired
	private StoreDao storeDao;

	@Override
	public List<Sort> findSortList() {
		// TODO Auto-generated method stub
		return sortDao.findAll();
	}

	@Override
	public Sort addSort(Sort sort, int storeId) {
		Store store = storeDao.findById(storeId);
		
		Sort newSort = sortDao.save(sort);
		List<Sort> newSorts = store.getMsort();
		newSorts.add(newSort);
		store.setMsort(newSorts);
		storeDao.save(store);
		return newSort;
	}

	@Override
	public Sort ModifySort(Sort sort, int id) {
		Sort findSort = sortDao.findById(id);
		sortDao.findById(id);
		if (findSort == null) {
			//抛异常
			return null;
		} else {
			sort.setId(id);
			return sortDao.save(sort);
		}
	}

	@Override
	public void deleteSort(int id) {
		sortDao.deleteById(id);
	}

	@Override
	public Sort findOne(int id) {
		return sortDao.findById(id);
	}

}
