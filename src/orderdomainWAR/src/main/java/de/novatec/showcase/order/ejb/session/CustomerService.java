package de.novatec.showcase.order.ejb.session;

import java.math.BigDecimal;
import java.util.List;

import de.novatec.showcase.order.ejb.entity.Customer;
import de.novatec.showcase.order.ejb.entity.CustomerInventory;
import de.novatec.showcase.order.ejb.entity.Order;
import de.novatec.showcase.order.ejb.entity.OrderLine;
import de.novatec.showcase.order.ejb.session.exception.CustomerNotFoundException;
import de.novatec.showcase.order.ejb.session.exception.ItemNotFoundException;
import de.novatec.showcase.order.ejb.session.exception.OrderNotFoundException;

public interface CustomerService {
	Customer getCustomer(Integer customerId) throws CustomerNotFoundException;

	long countCustomer();

	List<CustomerInventory> getInventories(Integer customerId) throws CustomerNotFoundException;

	List<Customer> selectCustomerWithGoodCredit();

	boolean checkCustomerCredit(Integer customerId, BigDecimal costs);

	boolean sellInventory(Integer customerId, String itemId, int quantity) throws ItemNotFoundException, CustomerNotFoundException;

	void addInventory(Order order);

	void addInventory(OrderLine orderLine);

	void addInventory(Integer orderId) throws OrderNotFoundException;

	Customer createCustomer(Customer customer);

}
