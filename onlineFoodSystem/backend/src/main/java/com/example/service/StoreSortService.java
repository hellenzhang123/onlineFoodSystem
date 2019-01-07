package com.example.service;

import java.util.List;

import com.example.model.StoreSort;

public interface StoreSortService {
	public List<StoreSort> findStoreSortList();
	public StoreSort addStoreSort(StoreSort storeSort);
	public StoreSort ModifyStoreSort(StoreSort storeSort, int id);
	public void deleteStoreSort(int id);
}
