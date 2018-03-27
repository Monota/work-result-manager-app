package tokyo.monota.work.result.manager.domain.resource;

public class MonthSelectionResource {

	private String name;

	private String value;

	public MonthSelectionResource(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
