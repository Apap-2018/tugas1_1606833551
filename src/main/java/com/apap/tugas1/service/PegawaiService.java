package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
	public void addPegawai(PegawaiModel pegawai);
	PegawaiModel getPegawaiByNip(String nip);
	List<PegawaiModel> findAllPegawai();
	PegawaiModel getPegawaiById(long id);
	public void generateNip(PegawaiModel pegawai);

}
