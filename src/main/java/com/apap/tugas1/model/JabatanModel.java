package com.apap.tugas1.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 
 * JabatanModel
 *
 */
@Entity
@Table(name = "jabatan")
public class JabatanModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long Id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "deskripsi", nullable=false)
	private String deskripsi;
	
	@NotNull
	@Column(name = "gaji_pokok", nullable=false)
	private double gajiPokok;
	
	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = {CascadeType.PERSIST,CascadeType.MERGE}, 
			mappedBy = "jabatanPegawaiList",targetEntity = PegawaiModel.class)
	private List<PegawaiModel> pegawaiJabatanList;
	
	public List<PegawaiModel> getPegawai(){
		return pegawaiJabatanList;
		
	}
	public Integer getJumlahPegawai() {
		return getPegawai().size();
	}
	
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public double getGajiPokok() {
		return gajiPokok;
	}

	public void setGajiPokok(double gajiPokok) {
		this.gajiPokok = gajiPokok;
	}

	public List<PegawaiModel> getPegawaiJabatanList() {
		return pegawaiJabatanList;
	}

	public void setPegawaiJabatanList(List<PegawaiModel> pegawaiJabatanList) {
		this.pegawaiJabatanList = pegawaiJabatanList;
	}
	

}
