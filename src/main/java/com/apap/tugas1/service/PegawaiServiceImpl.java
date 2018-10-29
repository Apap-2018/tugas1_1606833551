package com.apap.tugas1.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDb pegawaiDb;
	
	@Override
	public void addPegawai(PegawaiModel pegawai) {
		// TODO Auto-generated method stub
		pegawaiDb.save(pegawai);		
	}
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
	@Override
	public void generateNip(PegawaiModel pegawai) {
		String nip = "";
		nip += pegawai.getInstansi().getId();
		
		SimpleDateFormat date = new SimpleDateFormat("ddMMyy");
		String tglLahir = date.format(pegawai.getTanggalLahir());
		nip += tglLahir;
		
		nip += pegawai.getTahunMasuk();

		int urutan = 1;
		//Lisr<PegawaiModel> pegawaiList = pegawaiDb.findByInstansi(instansi)
		List<PegawaiModel> pegawaiInstansiList = pegawai.getInstansi().getPegawaiInstansiList();
		for (PegawaiModel pegawaiInstansi: pegawaiInstansiList) {
			if (pegawaiInstansi.getTahunMasuk().equals(pegawai.getTahunMasuk()) && pegawaiInstansi.getTanggalLahir().equals(pegawai.getTanggalLahir())) {
				urutan += 1;
			}	
		}
		if (urutan<10) {
			nip += "0" + urutan;
		}
		else {
			nip += urutan;
		}

		//for (JabatanModel jabatan:pegawai.getJabatanPegawaiList()) {
		//	System.out.println(jabatan.getNama());
		//}
		pegawai.setNip(nip);

		/**
		//int urutan = 1;
		List<PegawaiModel> listPegawai = pegawaiDb.findByInstansi(pegawai.getInstansi());
		for (PegawaiModel oPegawai : listPegawai) {
			if (pegawai.getTanggalLahir().equals(oPegawai.getTanggalLahir()))
				urutan++;
		}
		
		String strUrutan = "";
		if (urutan<10) {
			strUrutan = "0" + urutan;
		}
		
		String nip = pegawai.getInstansi().getId()+""+newDateFormat+
					pegawai.getTahunMasuk()+""+strUrutan;
		pegawai.setNip(nip);
		*/
	}
	/**
	 * 
	 
	String nip = "";
	
	nip += pegawai.getInstansi().getId();
	
	String[] tglLahir = pegawai.getTanggalLahir().toString().split("-");
	String tglLahirString = tglLahir[2] + tglLahir[1] + tglLahir[0].substring(2, 4);
	nip += tglLahirString;

	nip += pegawai.getTahunMasuk();

	int counterSama = 1;
	for (PegawaiModel pegawaiInstansi:pegawai.getInstansi().getPegawaiInstansi()) {
		if (pegawaiInstansi.getTahunMasuk().equals(pegawai.getTahunMasuk()) && pegawaiInstansi.getTanggalLahir().equals(pegawai.getTanggalLahir())) {
			counterSama += 1;
		}	
	}
	nip += "0" + counterSama;

	for (JabatanModel jabatan:pegawai.getJabatanList()) {
		System.out.println(jabatan.getNama());
	}
	pegawai.setNip(nip);
	*/

}
