package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDb pegawaiDb;
	
	@Override
	public PegawaiModel getPegawaiByNip(String nip) {
		return pegawaiDb.findByNip(nip);
	}

	@Override
	public List<PegawaiModel> findAllPegawai() {
		// TODO Auto-generated method stub
		return pegawaiDb.findAll();
	}

	@Override
	public PegawaiModel getPegawaiById(long id) {
		// TODO Auto-generated method stub
		return pegawaiDb.getOne(id);
	}

}
