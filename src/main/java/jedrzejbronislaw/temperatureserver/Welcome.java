package jedrzejbronislaw.temperatureserver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jedrzejbronislaw.temperatureserver.dao.entity.Sensor;
import jedrzejbronislaw.temperatureserver.dao.entity.Temperature;

@RestController
public class Welcome {

	private List<Sensor> sensorList = new ArrayList<>();
	private List<Temperature> tempList = new ArrayList<>();
	
	public Welcome() {
		Sensor sensor1 = new Sensor("first", "First", "My window");
		Sensor sensor2 = new Sensor("kitchen", "Kitchen", "My kitchen");
		Sensor sensor3 = new Sensor("room", "Room", "My living room");
		sensorList.add(sensor1);
		sensorList.add(sensor2);
		sensorList.add(sensor3);
		
		tempList.add(new Temperature(LocalDateTime.now().minusMinutes(30),  1.7f, sensor1));
		tempList.add(new Temperature(LocalDateTime.now().minusMinutes(30),  2.1f, sensor2));
		tempList.add(new Temperature(LocalDateTime.now().minusMinutes(30), 20.2f, sensor3));
		tempList.add(new Temperature(LocalDateTime.now().minusMinutes(15), 20.4f, sensor3));
		tempList.add(new Temperature(LocalDateTime.now(),                  20.5f, sensor3));
	}
	
	@GetMapping("/")
	public String welcome() {
		return "Temperature server";
	}
	
	@GetMapping("/sensors")
	public List<Sensor> printSensors() {
		return sensorList;
	}
	
	@GetMapping("/temperatures")
	public List<Temperature> printTemperatures() {
		return tempList;
	}
	
	@GetMapping("/sensor")
	public List<Temperature> printTemperatures(String login) {
		Optional<Sensor> sensor = sensorList.stream().filter(s -> s.getLogin().equals(login)).findFirst();
		
		if (sensor.isEmpty()) return null;
		
		return tempList.stream()
				.filter(temp -> temp.getSensor() == sensor.get())
				.collect(Collectors.toList());
	}
}
