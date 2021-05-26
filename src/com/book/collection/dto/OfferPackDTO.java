package com.book.collection.dto;

import java.util.Date;

/**
 * @author hadoop
 *
 */
public class OfferPackDTO {

	private int id;
	private String detail;
	private String duration;
	private Date offerEndDate;
	private Date offerStartDate;
	private String packageTitle;
	private Double price;
	private String code;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Date getOfferEndDate() {
		return offerEndDate;
	}
	public void setOfferEndDate(Date offerEndDate) {
		this.offerEndDate = offerEndDate;
	}
	public Date getOfferStartDate() {
		return offerStartDate;
	}
	public void setOfferStartDate(Date offerStartDate) {
		this.offerStartDate = offerStartDate;
	}
	public String getPackageTitle() {
		return packageTitle;
	}
	public void setPackageTitle(String packageTitle) {
		this.packageTitle = packageTitle;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
