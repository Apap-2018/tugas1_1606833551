package com.apap.tugas1.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * 
 * JabatanModel
 *
 */
@Entity
@Table(name = "jabatan_pegawai")
public class JabatanPegawaiModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
