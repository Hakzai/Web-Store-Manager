package com.akeir.webstore.mngr.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@Column(nullable = false)
	private String customerName;
	
	@ElementCollection
	@Column(nullable = false)
	private List<Product> items;
	
	private double total;

	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime registerDate;

	private LocalDateTime deliveryRequiredDate;
	
	@Column(nullable = false)
	private String status;
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getCustomerName() 
	{
		return customerName;
	}

	public void setCustomerName(String customerName) {
		
		this.customerName = customerName;
	}

	public List<Product> getItems() 
	{
		return items;
	}

	public void setItems(List<Product> items) 
	{
		this.items = items;
	}

	public double getTotal() 
	{
		return total;
	}

	public void setTotal(double total) 
	{
		this.total = total;
	}
	
	public LocalDateTime getRegisterDate()
	{
		return registerDate;
	}
	
	public void setRegisterDate(LocalDateTime registerDate)
	{
		this.registerDate = registerDate;
	}
	
	public LocalDateTime getDeliveryRequiredDate()
	{
		return deliveryRequiredDate;
	}
	
	public void setDeliveryRequiredDate(LocalDateTime deliveryRequiredDate)
	{
		this.deliveryRequiredDate = deliveryRequiredDate;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String toString()
	{
		return "Order__" + this.id + "__" + this.customerName + "__" + this.getRegisterDate();
	}
}
