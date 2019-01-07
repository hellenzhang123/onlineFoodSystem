package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.dao.MeterialDao;
import com.example.dao.StoreDao;
import com.example.model.Meterial;
import com.example.model.Store;
import com.example.service.MeterialService;

@Service("MeterialService")
public class MeterialServiceImpl implements MeterialService {
	@Autowired
	private MeterialDao meterialDao;
	@Autowired
	private StoreDao storeDao;

	@Override
	public Meterial addMeterial(Meterial meterial, int storeId) {
		// TODO Auto-generated method stub
		Store store = storeDao.findById(storeId);
		meterial.setStore(store);
		return meterialDao.save(meterial);
	}

	@Override
	public Meterial ModifyMeterial(Meterial meterial, int id) {
		Meterial findMeterial = meterialDao.findById(id);
		meterialDao.findById(id);
		if (findMeterial == null) {
			//抛异常
			return null;
		} else {
			meterial.setId(id);
			return meterialDao.save(meterial);
		}
	}

	@Override
	public void deleteMeterial(int id) {
		meterialDao.deleteById(id);
	}

	@Override
	public Page<Meterial> findMeterialList(Pageable pageable, Integer storeId) {
		return meterialDao.findByStoreId(pageable, storeId);
	}

}
