package jedrzejbronislaw.temperatureserver.dao.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Temperature {
	
	private LocalDateTime time;
	private float value;
	private Sensor sensor;
	
}
