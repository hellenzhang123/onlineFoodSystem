package com.example.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orderform")
public class Orderform {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double totalPrice;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createdAt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date orderTime;
	private String state;
	private String payWay;
	private String address;
	private String note;
	private String refuseMsg;
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "order_id")
	private List<Orderdetail> orderdetail;
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="rider_id")
	private Rider rider;
	@ManyToOne(cascade= {CascadeType. PERSIST, CascadeType. REFRESH})
    @JoinColumn(name = "storder_id")
	private Store store;
	@ManyToOne(cascade= {CascadeType. PERSIST, CascadeType. REFRESH})
    @JoinColumn(name = "user_id")
	private User user;
	private Double deliveryPrice;

	public Orderform() {
		// TODO Auto-generated constructor stub
	}

	public Orderform(Integer id, String name, Double totalPrice, Date createdAt, Date orderTime, String state,
			String payWay, String address, String note, List<Orderdetail> orderdetail, Rider rider, Store store) {
		super();
		this.id = id;
		this.name = name;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
		this.orderTime = orderTime;
		this.state = state;
		this.payWay = payWay;
		this.address = address;
		this.note = note;
		this.orderdetail = orderdetail;
		this.rider = rider;
		this.store = store;
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Orderdetail> getOrderdetail() {
		return orderdetail;
	}

	public void setOrderdetail(List<Orderdetail> orderdetail) {
		this.orderdetail = orderdetail;
	}

	public Rider getRider() {
		return rider;
	}

	public void setRider(Rider rider) {
		this.rider = rider;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Store getStore() {
		return store;
	}
	
	public void setStore(Store store) {
		this.store = store;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(Double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	
	public String getRefuseMsg() {
		return refuseMsg;
	}

	public void setRefuseMsg(String refuseMsg) {
		this.refuseMsg = refuseMsg;
	}

	@Override
	public String toString() {
		return "Orderform [id=" + id + ", name=" + name + ", totalPrice=" + totalPrice + ", createdAt=" + createdAt
				+ ", orderTime=" + orderTime + ", state=" + state + ", payWay=" + payWay + ", address=" + address
				+ ", note=" + note + ", orderdetail=" + orderdetail + ", rider=" + rider + ", store=" + store
				+ ", user=" + user + ", deliveryPrice=" + deliveryPrice + "]";
	}
	
	

}
