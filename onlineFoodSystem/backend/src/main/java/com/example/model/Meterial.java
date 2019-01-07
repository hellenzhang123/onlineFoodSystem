package com.example.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "meterial")
public class Meterial {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date orderDate;
	private Integer number;
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date sellDate;
	private String saveMethod; 
	private String img;
	@ManyToOne(cascade= {CascadeType. PERSIST, CascadeType. REFRESH})
    @JoinColumn(name = "store_id", referencedColumnName = "id")
	private Store store;

	public Meterial() {
		// TODO Auto-generated constructor stub
	}

	public Meterial(Integer id, String name, Date orderDate, Integer number, Date sellDate, String saveMethod) {
		super();
		this.id = id;
		this.name = name;
		this.orderDate = orderDate;
		this.number = number;
		this.sellDate = sellDate;
		this.saveMethod = saveMethod;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getSellDate() {
		return sellDate;
	}

	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}

	public String getSaveMethod() {
		return saveMethod;
	}

	public void setSaveMethod(String saveMethod) {
		this.saveMethod = saveMethod;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	
}
