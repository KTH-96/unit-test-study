package unit.test.chapter2.store;

import java.util.HashMap;
import java.util.Map;

public class Store {

	private final Map<Product, Integer> inventory = new HashMap();

	public boolean hasEnoughInventory(Product product, int quantity) {
		return getInventory(product) >= quantity;
	}

	public void removeInventory(Product product, int quantity) {
		if (!hasEnoughInventory(product, quantity)) {
			throw new IllegalStateException("Not enough inventory");
		}
		inventory.put(product, inventory.get(product) - quantity);
	}

	public void addInventory(Product product, int quantity) {
		if (inventory.containsKey(product)) {
			inventory.put(product, inventory.get(product) + quantity);
			return;
		}
		inventory.put(product, quantity);
	}

	public int getInventory(Product product) {
		return inventory.getOrDefault(product, 0);
	}
}
