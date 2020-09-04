package application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @GetMapping("/cartPage")
    public Cart getCartData(@RequestParam(value = "cartNo")int cartNo,
                            @RequestParam(value = "totalPrice")double totalPrice,
                            @RequestParam(value = "status")String status){
        Cart cart = new Cart();
        cart.setCartNo(cartNo);
        cart.setStatus(status);
        cart.setTotalPrice(totalPrice);

        return cart;

    }


}
