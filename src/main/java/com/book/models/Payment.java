package com.book.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Payment 
{
	@Id
	@GeneratedValue(generator = "payment_seq")
	@SequenceGenerator(name = "payment_seq",initialValue = 111111)
	private int pid;
	private String userid;
	private String card;
	private long totalPayment;
	private String date;
	public Payment() {}
	public Payment(String userid, String card, long totalPayment, String date) 
	{
		this.userid = userid;
		this.card = card;
		this.totalPayment = totalPayment;
		this.date = date;
	}

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public long getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(long totalPayment) {
		this.totalPayment = totalPayment;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
