package app.car.cap07;

import app.car.cap07.web.domain.Car;
import app.car.cap07.web.domain.CarRepository;
import app.car.cap07.web.domain.Manufacturer;
import app.car.cap07.web.domain.Model;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class Cap07ClientAppApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Cap07ClientAppApplication.class, args);
		CarRepository carRepository = ctx.getBean(CarRepository.class);


		Car hb20 = new Car();
		hb20.setModel(new Model(null, "HB20", new Manufacturer(null, "Hyundai"), "1.0 UNIQUE 12V FLEX 4P", 2019));
		hb20.setPrice(new BigDecimal("30000"));
		hb20.setThumbnail("HYUNDAI_HB20_1.0_UNIQUE_12V_FLEX_4P_MANUAL_34768609355073890.png");


		Car civic = new Car();
		civic.setModel(new Model(null, "Civic", new Manufacturer(null, "Honda"), "2.0 16V FLEX ONE LX 4P CVT", 2020));
		civic.setPrice(new BigDecimal("100000"));
		civic.setThumbnail("HONDA_CIVIC_2.0_16V_FLEXONE_LX_4P_CVT_34810015303079953.png");

		Car x4 = new Car();
		x4.setModel(new Model(null, "X4", new Manufacturer(null, "BMW"), "2.0 16V GASOLINA XDRIVE30I M SPORT STEPTRONIC", 2019));
		x4.setPrice(new BigDecimal("334950"));
		x4.setThumbnail("BMW_X4_2.0_16V_GASOLINA_XDRIVE30I_M_SPORT_STEPTRONIC_34785911080687951.png");

		Car focus = new Car();
		focus.setModel(new Model(null, "Focus", new Manufacturer(null, "Ford"), "1.6 SE 16V FLEX 4P Manual", 2019));
		focus.setPrice(new BigDecimal("70000"));
		focus.setThumbnail("FORD_FOCUS_1.6_SE_16V_FLEX_4P_MANUAL_3452671419485343.png");

		Car camaro = new Car();
		camaro.setModel(new Model(null, "Camaro", new Manufacturer(null, "Chevrolet"), "6.2 V8 GASOLINA SS AUTOMÁTICO", 2019));
		camaro.setPrice(new BigDecimal("350000"));
		camaro.setThumbnail("CHEVROLET_CAMARO_6.2_V8_GASOLINA_SS_AUTOMATICO_34224711212554032.png");


		carRepository.save(hb20);
		carRepository.save(civic);
		carRepository.save(x4);
		carRepository.save(focus);
		carRepository.save(camaro);
	}

}
