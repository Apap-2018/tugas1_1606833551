package com.apap.tugas1.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;


@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping("/")
	private String home(Model model) {
		List<JabatanModel> jabatan = jabatanService.findAllJabatan();
		List<InstansiModel> instansi = instansiService.findAllInstansi();
		model.addAttribute("JabatanList", jabatan);
		model.addAttribute("InstansiList", instansi);
		model.addAttribute("title", "Home");
		return "home";
	}
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String viewPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip);
		List<JabatanModel> jabatanPegawai = pegawai.getJabatanPegawaiList();
		double gajiPokokTerbesar = 0;
		for(JabatanModel jabatan : jabatanPegawai) {
			if(jabatan.getGajiPokok() > gajiPokokTerbesar) {
				gajiPokokTerbesar = jabatan.getGajiPokok();
			}
		}
		// gaji pokok + (%tunjangan x gaji pokok)
		int gajiPegawai = (int) ((int)gajiPokokTerbesar + (gajiPokokTerbesar * pegawai.getInstansi().getProvinsi().getPresentaseTunjangan()/100)); ;
	
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("jabatanPegawai", jabatanPegawai);
		model.addAttribute("gajiPegawai", gajiPegawai);
		return "view-pegawai";
	}
	@RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String terMudaTua(@RequestParam("idInstansi") long id, Model model) {
		InstansiModel instansi = instansiService.getInstansiDetailById(id).get();
		List<PegawaiModel> pegawaiInstansi = instansi.getPegawaiInstansiList();
		
		PegawaiModel termuda = pegawaiInstansi.get(0);
		PegawaiModel tertua = pegawaiInstansi.get(0);
		
	
		
		return null;
		
	}
	

}
