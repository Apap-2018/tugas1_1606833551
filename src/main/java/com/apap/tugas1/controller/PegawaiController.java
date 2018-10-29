package com.apap.tugas1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
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
		double gajiPegawai = pegawai.getGajiPokokPegawai();
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("jabatanPegawai", jabatanPegawai);
		model.addAttribute("gajiPegawai", gajiPegawai);
		return "view-pegawai";
	}

	/**
	 * Fitur 2
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String tambahPegawai(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setJabatanPegawaiList(new ArrayList<JabatanModel>());
		pegawai.getJabatanPegawaiList().add(new JabatanModel());
		List<JabatanModel> jabatan = jabatanService.findAllJabatan();
		List<ProvinsiModel> provinsi = provinsiService.findAllProvinsi();
		List<InstansiModel> instansi = instansiService.findAllInstansi();
		model.addAttribute("JabatanList", jabatan);
		model.addAttribute("ProvinsiList", provinsi);
		model.addAttribute("InstansiList", instansi);
		model.addAttribute("pegawai", pegawai);
		return "add-pegawai";
	}

	@PostMapping(value = "/pegawai/tambah", params = { "addRow" })
	public String addRowJabatan(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
		if (pegawai.getJabatanPegawaiList() == null) {
			pegawai.setJabatanPegawaiList(new ArrayList<JabatanModel>());
		}
		pegawai.getJabatanPegawaiList().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("JabatanList", jabatanService.findAllJabatan());
		model.addAttribute("InstansiList", instansiService.findAllInstansi());
		model.addAttribute("ProvinsiList", provinsiService.findAllProvinsi());
		return "add-pegawai";
	}

	@PostMapping(value = "/pegawai/tambah", params = { "deleteRow" })
	private String deleteRowJabatan(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model,
			HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
		pegawai.getJabatanPegawaiList().remove(rowId.intValue());
		// pegawai.getJabatan().remove(rowId.intValue());
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("JabatanList", jabatanService.findAllJabatan());
		model.addAttribute("InstansiList", instansiService.findAllInstansi());
		model.addAttribute("ProvinsiList", provinsiService.findAllProvinsi());
		return "add-pegawai";
	}

	@RequestMapping(value = "/pegawai/tambah", params = { "submitPegawai" }, method = RequestMethod.POST)
	public String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model, BindingResult result,
			RedirectAttributes redirectAttrs) {
		pegawaiService.generateNip(pegawai);
		pegawaiService.addPegawai(pegawai);
		System.out.print(pegawai.getNip());
		String message = "Pegawai bernama " + pegawai.getNama() + " dengan NIP: " + pegawai.getNip();
		model.addAttribute("msg", message);
		return "add";
	}

	/**
	 * Fitur 3
	 * 
	 * @param nip
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
	private String ubahPegawai(@RequestParam(value = "nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip);

		List<JabatanModel> jabatan = jabatanService.findAllJabatan();
		List<ProvinsiModel> provinsi = provinsiService.findAllProvinsi();
		List<InstansiModel> instansi = instansiService.findAllInstansi();
		model.addAttribute("JabatanList", jabatan);
		model.addAttribute("ProvinsiList", provinsi);
		model.addAttribute("InstansiList", instansi);
		model.addAttribute("pegawai", pegawai);
		return "ubah-pegawai";

	}

	@PostMapping(value = "/pegawai/ubah", params = { "addRow" })
	public String addRowJabatanUbah(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
		if (pegawai.getJabatanPegawaiList() == null) {
			pegawai.setJabatanPegawaiList(new ArrayList<JabatanModel>());
		}
		pegawai.getJabatanPegawaiList().add(new JabatanModel());
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("JabatanList", jabatanService.findAllJabatan());
		model.addAttribute("InstansiList", instansiService.findAllInstansi());
		model.addAttribute("ProvinsiList", provinsiService.findAllProvinsi());
		return "ubah-pegawai";
	}

	@PostMapping(value = "/pegawai/ubah", params = { "deleteRow" })
	private String deleteRowJabatanUbah(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model,
			HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("deleteRow"));
		pegawai.getJabatanPegawaiList().remove(rowId.intValue());
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("JabatanList", jabatanService.findAllJabatan());
		model.addAttribute("InstansiList", instansiService.findAllInstansi());
		model.addAttribute("ProvinsiList", provinsiService.findAllProvinsi());
		return "ubah-pegawai";
	}

	@RequestMapping(value = "/pegawai/ubah", params = { "submitPegawai" }, method = RequestMethod.POST)
	public String ubahPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model, BindingResult result,
			RedirectAttributes redirectAttrs) {
		pegawaiService.generateNip(pegawai);
		pegawaiService.addPegawai(pegawai);
		String message = "Pegawai bernama " + pegawai.getNama() + " dengan NIP: " + pegawai.getNip()
				+ " berhasil diubah";
		model.addAttribute("msg", message);
		return "add";
	}

	@RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String terMudaTua(@RequestParam("idInstansi") long id, Model model) {
		InstansiModel instansi = instansiService.getInstansiDetailById(id).get();
		List<PegawaiModel> pegawaiInstansi = instansi.getPegawaiInstansiList();
		PegawaiModel termuda = pegawaiInstansi.get(0);
		PegawaiModel tertua = pegawaiInstansi.get(0);
		for (int i = 0; i < pegawaiInstansi.size(); i++) {
			if (pegawaiInstansi.get(i).getUmur() < termuda.getUmur()) {
				termuda = pegawaiInstansi.get(i);
			} else if (pegawaiInstansi.get(i).getUmur() > tertua.getUmur()) {
				tertua = pegawaiInstansi.get(i);
			}
		}
		double gajiTermuda = termuda.getGajiPokokPegawai();
		double gajiTertua = tertua.getGajiPokokPegawai();
		List<JabatanModel> jabatanTermuda = termuda.getJabatanPegawaiList();
		List<JabatanModel> jabatanTertua = tertua.getJabatanPegawaiList();
		model.addAttribute("termuda", termuda);
		model.addAttribute("tertua", tertua);
		model.addAttribute("jabatanTermuda", jabatanTermuda);
		model.addAttribute("jabatanTertua", jabatanTertua);
		model.addAttribute("gajiTermuda", gajiTermuda);
		model.addAttribute("gajiTertua", gajiTertua);
		return "view-tertua-termuda";

	}

	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
	private String cariPegawai(@RequestParam(value = "idProvinsi", required = false) String idProvinsi,
			@RequestParam(value = "idInstansi", required = false) String idInstansi,
			@RequestParam(value = "idJabatan", required = false) String idJabatan, Model model) {
		model.addAttribute("listProvinsi", provinsiService.findAllProvinsi());
		model.addAttribute("listInstansi", instansiService.findAllInstansi());
		model.addAttribute("listJabatan", jabatanService.findAllJabatan());

		List<PegawaiModel> listPegawai = pegawaiService.findAllPegawai();

		if ((idProvinsi == null || idProvinsi.equals("")) && (idInstansi == null || idInstansi.equals(""))
				&& (idJabatan == null || idJabatan.equals(""))) {
		} else {
			if (idProvinsi != null && !idProvinsi.equals("")) {
				List<PegawaiModel> temp = new ArrayList<PegawaiModel>();
				for (PegawaiModel pegawai : listPegawai) {
					if (((Long) pegawai.getInstansi().getProvinsi().getId()).toString().equals(idProvinsi)) {
						temp.add(pegawai);
					}
				}
				listPegawai = temp;
				model.addAttribute("idProvinsi", idProvinsi);
			} else {
				model.addAttribute("idProvinsi", "");
			}
		}
		if (idInstansi != null && !idInstansi.equals("")) {
			List<PegawaiModel> temp = new ArrayList<PegawaiModel>();
			for (PegawaiModel pegawai : listPegawai) {
				if (((Long) pegawai.getInstansi().getId()).toString().equals(idInstansi)) {
					temp.add(pegawai);
				}
			}
			listPegawai = temp;
			model.addAttribute("idInstansi", idInstansi);
		} else {
			model.addAttribute("idInstansi", "");
		}
		if (idJabatan != null && !idJabatan.equals("")) {
			List<PegawaiModel> temp = new ArrayList<PegawaiModel>();
			for (PegawaiModel pegawai : listPegawai) {
				for (JabatanModel jabatan : pegawai.getJabatanPegawaiList()) {
					if (((Long) jabatan.getId()).toString().equals(idJabatan)) {
						temp.add(pegawai);
						break;
					}
				}

			}
			listPegawai = temp;
			model.addAttribute("idJabatan", idJabatan);
		} else {
			model.addAttribute("idJabatan", "");
		}
		model.addAttribute("listPegawai", listPegawai);
		return "cari-pegawai";
	}

}
