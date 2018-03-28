package tokyo.monota.work.result.manager.domain.service;

import java.util.List;

import tokyo.monota.work.result.manager.domain.resource.MonthListResource;
import tokyo.monota.work.result.manager.domain.resource.WorkResource;

public interface WorkService {

	public List<WorkResource> getAllWorkItem();

	public MonthListResource getAllWorkMonths();

	public List<String> getAllItemTypeNames();

	public String getItemUnitPrice(String itemTypeName, Boolean itemIsNew);

	public String getTotalPrice();

	public void updateWorkItem(WorkResource resource);

	public void deleteWorkItem(WorkResource resource);

	public void createWorkItem(WorkResource resource);
}
