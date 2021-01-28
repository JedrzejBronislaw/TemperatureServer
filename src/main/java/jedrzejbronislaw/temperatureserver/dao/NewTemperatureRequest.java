package jedrzejbronislaw.temperatureserver.dao;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewTemperatureRequest {

	private String sensorLogin;
	private float value;
}
