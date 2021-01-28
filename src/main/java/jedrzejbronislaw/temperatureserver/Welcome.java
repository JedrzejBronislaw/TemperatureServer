package jedrzejbronislaw.temperatureserver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jedrzejbronislaw.temperatureserver.dao.NewTemperatureRequest;
import jedrzejbronislaw.temperatureserver.dao.SensorRepo;
import jedrzejbronislaw.temperatureserver.dao.TemperatureConverter;
import jedrzejbronislaw.temperatureserver.dao.TemperatureRepo;
import jedrzejbronislaw.temperatureserver.dao.entity.Sensor;
import jedrzejbronislaw.temperatureserver.dao.entity.Temperature;

@RestController
public class Welcome {

	@Autowired SensorRepo sensors;
	@Autowired TemperatureRepo temperatures;
	
	@Autowired TemperatureConverter temperatureConverter;
	
	
	@GetMapping("/")
	public String welcome() {
		return "Temperature server";
	}
	
	@GetMapping("/sensors")
	public List<Sensor> printSensors() {
		return sensors.getAll();
	}
	
	@GetMapping("/temperatures")
	public List<Temperature> printTemperatures() {
		return temperatures.getAll();
	}
	
	@GetMapping("/sensor")
	public List<Temperature> printTemperatures(String login) {
		return temperatures.filterLogin(login);
	}
	
	@PostMapping("/temperature")
	public Temperature saveTemperature(NewTemperatureRequest temperatureRequest) {
		Temperature temperature = temperatureConverter.createTemperature(temperatureRequest);
		return temperatures.save(temperature);
	}
}
