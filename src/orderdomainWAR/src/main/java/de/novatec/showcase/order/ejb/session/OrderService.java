package de.novatec.showcase.order.ejb.session;

import java.util.List;

import de.novatec.showcase.order.client.manufacture.RestcallException;
import de.novatec.showcase.order.dto.ShoppingCart;
import de.novatec.showcase.order.ejb.entity.Order;
import de.novatec.showcase.order.ejb.session.exception.CustomerNotFoundException;
import de.novatec.showcase.order.ejb.session.exception.InsufficientCreditException;
import de.novatec.showcase.order.ejb.session.exception.ItemNotFoundException;
import de.novatec.showcase.order.ejb.session.exception.OrderNotFoundException;
import de.novatec.showcase.order.ejb.session.exception.PriceException;
import de.novatec.showcase.order.ejb.session.exception.SpecificationException;

public interface OrderService {
	Order getOrder(Integer id) throws OrderNotFoundException;

	Long getOrderCount(Integer customerId);

	List<Order> getOpenOrders(Integer customerId);

	Order newOrder(Integer customerId, ShoppingCart cart) throws InsufficientCreditException, PriceException, SpecificationException, RestcallException, CustomerNotFoundException, ItemNotFoundException;

	Order cancelOrder(Integer orderId) throws OrderNotFoundException;
}
