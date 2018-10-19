package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
	PegawaiModel getPegawaiByNip(String nip);
	List<PegawaiModel> findAllPegawai();
	PegawaiModel getPegawaiById(long id);


}
