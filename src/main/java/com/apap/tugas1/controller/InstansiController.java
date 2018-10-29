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

	@RequestMapping(value = "/instansi/getFromProvinsi", method = RequestMethod.GET)
	@ResponseBody
	public List<InstansiModel> getInstansiByProvinsi(@RequestParam (value = "idProvinsi", required = true) long idProvinsi, Model model) {
		ProvinsiModel provinsi = provinsiService.getProvinsiById(idProvinsi);
		List<InstansiModel> listInstansi = provinsi.getInstansiProvinsiList();
	    return instansiService.getInstansiByProvinsi(provinsi);
	}
	

}
