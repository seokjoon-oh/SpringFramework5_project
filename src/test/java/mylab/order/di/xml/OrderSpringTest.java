
package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private OrderService orderService;

    @Test
    void testShoppingCart() {

        
        assertNotNull(shoppingCart);

        
        assertEquals(2, shoppingCart.getProducts().size());

        
        assertEquals("노트북",
                shoppingCart.getProducts().get(0).getName());

        
        assertEquals("스마트폰",
                shoppingCart.getProducts().get(1).getName());
    }

    @Test
    void testOrderService() {

        
        assertNotNull(orderService);

        
        assertNotNull(orderService.getShoppingCart());

        
        assertEquals(2300000.0,
                orderService.calculateOrderTotal());
    }
}
