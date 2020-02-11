package de.novatec.showcase.order.ejb.session;

import java.util.List;

import de.novatec.showcase.order.ejb.entity.Item;

public interface ItemService {
	void setBatchSize(int Size);

	int getBatchSize();

	List<Item> getItems(String itemIds);

	List<Item> browseForward();

	List<Item> browseReverse();

	long getTotalItems();

	String getCurrentMax();

	String getCurrentMin();

	Item createItem(Item item);

}
