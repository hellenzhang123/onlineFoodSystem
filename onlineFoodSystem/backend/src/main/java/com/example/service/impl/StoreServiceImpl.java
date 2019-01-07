package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dao.StoreDao;
import com.example.dao.StoreSortDao;
import com.example.model.Store;
import com.example.model.StoreSort;
import com.example.service.StoreService;

@Service("storeService")
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreDao storeDao;
	@Autowired
	private StoreSortDao storeSortDao;

	@Override
	public Page<Store> findStoreList(Integer id, String name, Pageable pageable) throws Exception {
		if (id == null && name == null) {
			return storeDao.findAll(pageable);
		} else {
			if (id == null) {
				return storeDao.findByNameLike(name, pageable);
			} else if (name == null) {
				return storeDao.findBySortId(id, pageable);
			} else {
				return storeDao.findBySortIdAndName(id, name, pageable);
			}
		}
		
	}

	@Override
	public Store addStore(Store store, int sortId) {
		StoreSort sort = storeSortDao.findById(sortId);
		store.setSort(sort);
		return storeDao.save(store);
	}

	@Override
	public Store ModifyStore(Store store, int id) {
		Store findStore = storeDao.findById(id);
		storeDao.findById(id);
		if (findStore == null) {
			//抛异常
			return null;
		} else {
			store.setId(id);
			return storeDao.save(store);
		}
	}

	@Override
	public Store findOneStore(int id) {
		if (storeDao.findById(id) == null) {
			//抛异常
		}
		return storeDao.findById(id);
	}

	@Override
	public void deleteStore(int id) {
		storeDao.deleteById(id);
	}

}
