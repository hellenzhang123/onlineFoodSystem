package com.example.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.Store;

public interface StoreService {
	public Store addStore(Store store, int sortId);
	public Store ModifyStore(Store store, int id);
	public Store findOneStore(int id);
	public void deleteStore(int id);
	public Page<Store> findStoreList(Integer id, String name, Pageable pageable) throws Exception;
}
