package com.example.service;

import java.util.List;

import com.example.model.Sort;

public interface SortService {
	public List<Sort> findSortList();
	public Sort addSort(Sort sort, int storeId);
	public Sort ModifySort(Sort sort, int id);
	public void deleteSort(int id);
	public Sort findOne(int id);
}
