package jedrzejbronislaw.temperatureserver.dao;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jedrzejbronislaw.temperatureserver.dao.entity.Temperature;

@Component
public class TemperatureConverter {

	@Autowired SensorRepo sensorRepo;
	
	
	public Temperature createTemperature(NewTemperatureRequest newTemperatureRequest) {
		Temperature temperature = new Temperature();
		
		temperature.setTime(LocalDateTime.now());
		temperature.setValue(newTemperatureRequest.getValue());
		sensorRepo.get(newTemperatureRequest.getSensorLogin()).ifPresent(temperature::setSensor);
		
		return temperature;
	}
}
