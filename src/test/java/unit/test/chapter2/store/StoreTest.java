package unit.test.chapter2.store;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

class StoreTest {

	@Test
	void 구매_성공_고전_테스트() {
		Store store = new Store();
		store.addInventory(Product.shampoo, 10);

		Customer customer = new Customer();
		boolean purchase = customer.purchase(store, Product.shampoo, 5);

		Assertions.assertThat(purchase).isTrue();
		Assertions.assertThat(store.getInventory(Product.shampoo)).isEqualTo(5);
	}

	@Test
	void 구매_실패_고전_테스트() {
		Store store = new Store();
		store.addInventory(Product.shampoo, 10);

		Customer customer = new Customer();
		boolean purchase = customer.purchase(store, Product.shampoo, 15);

		Assertions.assertThat(purchase).isFalse();
		Assertions.assertThat(store.getInventory(Product.shampoo)).isEqualTo(10);
	}

	@Test
	void 구매_성공_런던_테스트() {
		Store storeMock = Mockito.mock(Store.class);
		when(storeMock.hasEnoughInventory(Product.shampoo, 5)).thenReturn(true);
		Customer customer = new Customer();

		boolean success = customer.purchase(storeMock, Product.shampoo, 5);

		assertThat(success).isTrue();
		verify(storeMock, times(1)).removeInventory(Product.shampoo, 5);

	}

	@Test
	void 구매_실패_런던_테스트() {
		Store storeMock = Mockito.mock(Store.class);
		given(storeMock.hasEnoughInventory(Product.shampoo, 5)).willReturn(false);
		Customer customer = new Customer();

		boolean success = customer.purchase(storeMock, Product.shampoo, 5);

		assertThat(success).isFalse();
		then(storeMock).should(never()).removeInventory(Product.shampoo, 5);
	}
}
