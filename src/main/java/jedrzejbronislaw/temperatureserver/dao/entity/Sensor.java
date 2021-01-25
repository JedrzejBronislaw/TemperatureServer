package jedrzejbronislaw.temperatureserver.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Sensor {

	private String login;
	private String name;
	private String desc;
	
}
