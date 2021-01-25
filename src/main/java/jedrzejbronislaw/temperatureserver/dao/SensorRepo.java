package jedrzejbronislaw.temperatureserver.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jedrzejbronislaw.temperatureserver.dao.entity.Sensor;

@Repository
public class SensorRepo {

	private List<Sensor> sensors = new ArrayList<>();
	
	public SensorRepo() {
		sensors.add(new Sensor("first", "First", "My window"));
		sensors.add(new Sensor("kitchen", "Kitchen", "My kitchen"));
		sensors.add(new Sensor("room", "Room", "My living room"));
	}
	
	public List<Sensor> getAll() {
		return Collections.unmodifiableList(sensors);
	}
	
	public Optional<Sensor> get(String login) {
		return sensors.stream()
				.filter(sensor -> sensor.getLogin().equals(login))
				.findFirst();
	}
}
