package app.car.cap07.web.controller;


import app.car.cap07.web.domain.CarRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor(onConstructor_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IndexController {

    CarRepository carRepository;


    @RequestMapping("/")
    public String index(Model model){

        model.addAttribute("cars", carRepository.findAll(PageRequest.of(0, 9)));

        return "index";
    }
}
