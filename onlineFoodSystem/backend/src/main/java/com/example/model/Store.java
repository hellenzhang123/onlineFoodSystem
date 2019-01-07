package com.example.model;

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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "stores")
public class Store {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(name="small_img")
	private String smallImg; 
	@Column(name="big_img")
	private String bigImg;
	private String phone;
	private String address;
	private Integer score;
	private String keyword;
	private String description;
	@ManyToOne(cascade= {CascadeType. PERSIST, CascadeType. REFRESH})
    @JoinColumn(name = "sort_id", referencedColumnName = "id")
	private StoreSort sort;
	@Column(name="delivery_time")
	private Integer deliveryTime;
	@Column(name="delivery_price")
	private Double deliveryPrice;
	@Column(name="start_price")
	private Double startPrice;
	@Column(name="sell_count")
	private Integer sellCount;
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "store_id")
	private List<Food> food;
//	@OneToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name="meterial_id")
//	private Meterial meterial;
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "sto_id")
	private List<Rating> rating;
	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "s_id")
	private List<Sort> msort;
	
	public Store() {
		// TODO Auto-generated constructor stub
	}

	public Store(Integer id, String name, String smallImg, String bigImg, String phone, String address, Integer score,
			String keyword, String description, StoreSort sort, Integer deliveryTime, Double deliveryPrice,
			Double startPrice, Integer sellCount, List<Food> food, List<Rating> rating,
			 List<Sort> msort) {
		super();
		this.id = id;
		this.name = name;
		this.smallImg = smallImg;
		this.bigImg = bigImg;
		this.phone = phone;
		this.address = address;
		this.score = score;
		this.keyword = keyword;
		this.description = description;
		this.sort = sort;
		this.deliveryTime = deliveryTime;
		this.deliveryPrice = deliveryPrice;
		this.startPrice = startPrice;
		this.sellCount = sellCount;
		this.food = food;
		this.rating = rating;
		this.msort = msort;
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

	public String getSmallImg() {
		return smallImg;
	}

	public void setSmallImg(String smallImg) {
		this.smallImg = smallImg;
	}

	public String getBigImg() {
		return bigImg;
	}

	public void setBigImg(String bigImg) {
		this.bigImg = bigImg;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StoreSort getSort() {
		return sort;
	}

	@JsonBackReference
	public void setSort(StoreSort sort) {
		this.sort = sort;
	}

	public Integer getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Double getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(Double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}

	public List<Food> getFood() {
		return food;
	}

	public void setFood(List<Food> food) {
		this.food = food;
	}
	public List<Rating> getRating() {
		return rating;
	}

	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}

	public List<Sort> getMsort() {
		return msort;
	}

	public void setMsort(List<Sort> msort) {
		this.msort = msort;
	}
	
}
