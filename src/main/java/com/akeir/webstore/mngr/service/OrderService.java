package com.akeir.webstore.mngr.service;


import com.akeir.webstore.mngr.model.Order;
import com.akeir.webstore.mngr.model.Product;
import com.akeir.webstore.mngr.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {

	private final Random random = new Random();
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderRepository orderRepository;
    
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplateOrder;

    public Order createOrder(Order order) 
    {
        order.setTotal(calculateTotal(order.getItems()));
        order.setDeliveryRequiredDate(calculateDeliveryDate(order.getRegisterDate()));
        return orderRepository.save(order);
    }

	public List<Order> getAllOrders() 
    {
        return orderRepository.findAll();
    }
	
    private double calculateTotal(List<Product> items) 
    {
        double total = 0.00;
    	
    	for(Product product : items)
        {
        	System.out.println(product.toString());
    		total += product.getUnitPrice() * product.getOrderedQuantity();
        }
    	
    	System.out.println("TOTAL " + total);
    	return total;
    }

    private LocalDateTime calculateDeliveryDate(LocalDateTime registerDate) 
    {
    	return registerDate.plusDays(7);
    }
    
    public void sendOrder(Order order)
    {
    	int partition = random.nextInt(3);
    	System.out.println("Message sent to partition: " + partition);
    	System.out.println("Sending order: " + order.toString());
    	kafkaTemplateOrder.send("new-orders", partition, null, order);
    }
    
    public void createProducts(Order order)
    {
    	productService.saveProducts(order);
    }
    
    public void updateProductsRefIds(Order order)
    {
    	productService.setOrderRefIds(order);
    	productService.saveProducts(order);
    }
}
