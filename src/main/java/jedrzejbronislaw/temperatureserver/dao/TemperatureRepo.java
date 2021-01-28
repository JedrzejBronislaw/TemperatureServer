package jedrzejbronislaw.temperatureserver.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jedrzejbronislaw.temperatureserver.dao.entity.Sensor;
import jedrzejbronislaw.temperatureserver.dao.entity.Temperature;

@Repository
public class TemperatureRepo {

	private SensorRepo sensors;
	
	private List<Temperature> temperatures = new ArrayList<>();
	
	@Autowired
	public TemperatureRepo(SensorRepo sensors) {
		this.sensors = sensors;
		
		Sensor sensor1 = sensors.get("first").get();
		Sensor sensor2 = sensors.get("kitchen").get();
		Sensor sensor3 = sensors.get("room").get();
		
		temperatures.add(new Temperature(LocalDateTime.now().minusMinutes(30),  1.7f, sensor1));
		temperatures.add(new Temperature(LocalDateTime.now().minusMinutes(30),  2.1f, sensor2));
		temperatures.add(new Temperature(LocalDateTime.now().minusMinutes(30), 20.2f, sensor3));
		temperatures.add(new Temperature(LocalDateTime.now().minusMinutes(15), 20.4f, sensor3));
		temperatures.add(new Temperature(LocalDateTime.now(),                  20.5f, sensor3));
	}
	
	public List<Temperature> getAll() {
		return Collections.unmodifiableList(temperatures);
	}
	
	public Temperature save(Temperature temperature) {
		temperatures.add(temperature);
		return temperature;
	}
	
	public List<Temperature> filterLogin(String sensorLogin) {
		Optional<Sensor> sensor = sensors.get(sensorLogin);
		
		if (sensor.isEmpty()) return null;
		
		return temperatures.stream()
				.filter(temp -> temp.getSensor() == sensor.get())
				.collect(Collectors.toList());
	}
}
