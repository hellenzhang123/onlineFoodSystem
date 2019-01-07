package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.model.Meterial;

public interface MeterialService {
	public Page<Meterial> findMeterialList(Pageable pageable, Integer storeId);
	public Meterial addMeterial(Meterial meterial, int storeId);
	public Meterial ModifyMeterial(Meterial Meterial, int id);
	public void deleteMeterial(int id);
}
