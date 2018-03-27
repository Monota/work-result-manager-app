package tokyo.monota.work.result.manager.domain.resource;

import java.util.ArrayList;
import java.util.List;

public class MonthListResource {

	private List<MonthSelectionResource> values = new ArrayList<>();

	public List<MonthSelectionResource> getValues() {
		return values;
	}

	public void addValue(MonthSelectionResource value) {
		this.values.add(value);
	}
}
