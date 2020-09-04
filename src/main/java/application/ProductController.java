package application;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @PostMapping("/productPage")
    public Product getProduct(@RequestBody Product product){

    return product;

}
}
