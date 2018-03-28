package tokyo.monota.work.result.manager.domain.resource;

public class MonthSelectionResource {

	private String name;

	private String value;

	private boolean selected;

	public MonthSelectionResource(String name, String value, boolean selected) {
		this.name = name;
		this.value = value;
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public boolean isSelected() {
		return selected;
	}
}
