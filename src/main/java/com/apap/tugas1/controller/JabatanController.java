package com.apap.tugas1.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.JabatanService;




@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
		private String addJabatan(Model model) {
		model.addAttribute("jabatan", new JabatanModel());
		return "add-jabatan";

	}

	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("msg","Jabatan berhasil ditambahkan!");
		return "add";

	}
	@RequestMapping(value = "/jabatan/view",  method = RequestMethod.GET)
	private String viewJabatan(@RequestParam(value="idJabatan") long id, Model model) {
			JabatanModel jabatan = jabatanService.getJabatanDetailById(id).get();
			int jumlahPegawai = jabatan.getJumlahPegawai();
			model.addAttribute("jabatan", jabatan);
			model.addAttribute("jumlahPegawai", jumlahPegawai);
			model.addAttribute("title", "View Jabatan");
			return "view-jabatan";	
	}
	
	
	@RequestMapping(value="/jabatan/ubah", method = RequestMethod.GET)
	private String ubahJabatan(@RequestParam(value = "idJabatan") long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(id).get();
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("title", "Ubah Jabatan");
		return "ubah-jabatan";
	}
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	private String ubahJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("title", "Ubah Jabatan");
		model.addAttribute("msg","Jabatan berhasil diubah!");
		return "add";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	public String hapusJabatan(@RequestParam(value="idJabatan", required=true) long id, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanById(id);
		System.out.println(jabatan.getPegawaiJabatanList().size());

		if(jabatan.getPegawaiJabatanList().size()==0) {
			jabatanService.deleteJabatanById(id);
			model.addAttribute("msg", "Jabatan " + jabatan.getNama()+ " Berhasil dihapus");
			return "add";
		}
		model.addAttribute(jabatan);
		return "hapus-failed";
		
	}
	@RequestMapping(value ="/jabatan/viewall", method = RequestMethod.GET)
	private String viewAll(Model model) {
		List<JabatanModel> listJabatan =  jabatanService.findAllJabatan();
		model.addAttribute("listJabatan", listJabatan);
		return "viewAll-jabatan";		
	}

}
