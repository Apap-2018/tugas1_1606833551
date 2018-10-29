package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.InstansiModel;

import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;

import com.apap.tugas1.service.ProvinsiService;
@Controller
public class InstansiController {
	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private ProvinsiService provinsiService;

	@RequestMapping(value = "/instansi/getInstansiByProvinsi", method = RequestMethod.GET)
	@ResponseBody
	public List<InstansiModel> getInstansi(@RequestParam (value = "idProvinsi", required = true) long idProvinsi, Model model) {
		System.out.println("masuk fungsi list");
		ProvinsiModel provinsi = provinsiService.getProvinsiById(idProvinsi);
		System.out.println(provinsi.getNama());
	    return instansiService.getInstansiByProvinsi(provinsi);
	}
	

}
