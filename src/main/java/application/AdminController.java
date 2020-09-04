package application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/adminPage")
    public Admin getAdmin(@RequestParam(value = "id")int id,
                          @RequestParam(value = "name")String name){
        Admin admin = new Admin();
        admin.setId(id);
        admin.setName(name);

        return admin;
    }

}
