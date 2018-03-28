package tokyo.monota.work.result.manager.domain.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tokyo.monota.work.result.manager.domain.entity.WorkItemEntity;
import tokyo.monota.work.result.manager.domain.mapper.ItemMasterMapper;
import tokyo.monota.work.result.manager.domain.mapper.WorkItemMapper;
import tokyo.monota.work.result.manager.domain.resource.MonthListResource;
import tokyo.monota.work.result.manager.domain.resource.MonthSelectionResource;
import tokyo.monota.work.result.manager.domain.resource.WorkResource;
import tokyo.monota.work.result.manager.domain.service.WorkService;

@Service
public class WorkServiceImpl implements WorkService {

	@Autowired
	WorkItemMapper workItemMapper;

	@Autowired
	ItemMasterMapper itemMasterMapper;

	@Override
	public List<WorkResource> getWorkItemByWorkMonth(String workMonth) {

		List<WorkItemEntity> workItemEntityList = workItemMapper.selectWorkItemsByWorkMonth(workMonth);

		List<WorkResource> workResourceList = new ArrayList<>();
		for (WorkItemEntity entity : workItemEntityList) {
			WorkResource resource = new WorkResource();
			BeanUtils.copyProperties(entity, resource);
			workResourceList.add(resource);
		}

		return workResourceList;
	}

	@Override
	public MonthListResource getAllWorkMonths() {

		List<String> workMonthList = workItemMapper.selectWorkMonths();

		MonthListResource monthList = new MonthListResource();
		for (String workMonth : workMonthList) {
			monthList.addValue(new MonthSelectionResource(workMonth, workMonth));
		}

		return monthList;
	}

	@Override
	public List<String> getAllItemTypeNames() {

		return itemMasterMapper.selectAllItemTypeNames();
	}

	@Override
	public String getItemUnitPrice(String itemTypeName, Boolean itemIsNew) {

		BigDecimal unitPrice = itemMasterMapper.selectUnitPrice(itemTypeName, itemIsNew);

		if (unitPrice == null) {
			return "";
		}

		unitPrice = unitPrice.setScale(2, RoundingMode.FLOOR);

		return unitPrice.toString();
	}

	@Override
	public String getTotalPrice() {

		BigDecimal totalPrice = workItemMapper.sumupUnitPrice();

		if (totalPrice == null) {
			return "";
		}

		totalPrice = totalPrice.setScale(2, RoundingMode.FLOOR);

		return totalPrice.toString();
	}

	@Override
	@Transactional
	public void updateWorkItem(WorkResource resource) {

		WorkItemEntity entity = workItemMapper.selectByPkForUpdate("user", resource.getWorkDate(), resource.getItemTypeName(), resource.getItemIsNew());

		if (entity == null) {
			throw new IllegalStateException("entity is not found.");
		}

		entity.setItemQuantity(resource.getItemQuantity());
		workItemMapper.updateWorkItem(entity);
	}

	@Override
	public void deleteWorkItem(WorkResource resource) {

		workItemMapper.deleteWorkItem("user", resource.getWorkDate(), resource.getItemTypeName(), resource.getItemIsNew());
	}

	@Override
	public void createWorkItem(WorkResource resource) {

		WorkItemEntity entity = new WorkItemEntity();
		entity.setUserId("user");
		entity.setWorkDate(resource.getWorkDate());
		entity.setItemIsNew(resource.getItemIsNew());
		entity.setItemTypeName(resource.getItemTypeName());
		entity.setItemUnitPrice(resource.getItemUnitPrice());
		entity.setItemQuantity(resource.getItemQuantity());
		workItemMapper.insertWorkItem(entity);
	}
}
