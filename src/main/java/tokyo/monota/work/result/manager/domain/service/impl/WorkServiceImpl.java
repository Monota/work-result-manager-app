package tokyo.monota.work.result.manager.domain.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tokyo.monota.work.result.manager.core.helper.ServiceUserHelper;
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

	@Autowired
	ServiceUserHelper serviceUserHelper;

	@Override
	public List<WorkResource> getWorkItemByWorkMonth(String currentWorkMonth) {
		String userId = serviceUserHelper.getCurrentUserId();
		List<WorkItemEntity> workItemEntityList = workItemMapper.selectWorkItemsByWorkMonth(userId, currentWorkMonth);
		List<WorkResource> workResourceList = new ArrayList<>();
		for (WorkItemEntity entity : workItemEntityList) {
			WorkResource resource = new WorkResource();
			BeanUtils.copyProperties(entity, resource);
			workResourceList.add(resource);
		}
		return workResourceList;
	}

	@Override
	public MonthListResource getAllWorkMonths(String currentWorkMonth) {
		String userId = serviceUserHelper.getCurrentUserId();
		List<String> workMonthList = workItemMapper.selectWorkMonths(userId);
		MonthListResource monthList = new MonthListResource();
		for (String workMonth : workMonthList) {
			monthList.addValue(new MonthSelectionResource(workMonth, workMonth, workMonth.equals(currentWorkMonth)));
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
			return "0";
		}
		unitPrice = unitPrice.setScale(2, RoundingMode.FLOOR);
		return unitPrice.toString();
	}

	@Override
	public String getTotalPrice(String currentWorkMonth) {
		String userId = serviceUserHelper.getCurrentUserId();
		BigDecimal totalPrice = workItemMapper.sumupUnitPrice(userId, currentWorkMonth);
		if (totalPrice == null) {
			return "0";
		}
		totalPrice = totalPrice.setScale(2, RoundingMode.FLOOR);
		return totalPrice.toString();
	}

	@Override
	@Transactional
	public void updateWorkItem(WorkResource resource) {
		String userId = serviceUserHelper.getCurrentUserId();
		WorkItemEntity entity = workItemMapper.selectByPkForUpdate(userId, resource.getWorkDate(), resource.getItemTypeName(), resource.getItemIsNew());
		if (entity == null) {
			throw new IllegalStateException("entity is not found.");
		}
		entity.setItemQuantity(resource.getItemQuantity());
		workItemMapper.updateWorkItem(entity);
	}

	@Override
	public void deleteWorkItem(WorkResource resource) {
		String userId = serviceUserHelper.getCurrentUserId();
		workItemMapper.deleteWorkItem(userId, resource.getWorkDate(), resource.getItemTypeName(), resource.getItemIsNew());
	}

	@Override
	public void createWorkItem(WorkResource resource) {
		String userId = serviceUserHelper.getCurrentUserId();
		WorkItemEntity entity = new WorkItemEntity();
		entity.setUserId(userId);
		entity.setWorkDate(resource.getWorkDate());
		entity.setItemIsNew(resource.getItemIsNew());
		entity.setItemTypeName(resource.getItemTypeName());
		entity.setItemUnitPrice(resource.getItemUnitPrice());
		entity.setItemQuantity(resource.getItemQuantity());
		workItemMapper.insertWorkItem(entity);
	}

	@Override
	public boolean isExceedMonthlyLimit() {
		String userId = serviceUserHelper.getCurrentUserId();
		String currentMonth = new SimpleDateFormat("yyyy/MM").format(new Date());
		return workItemMapper.countWorkItemMonthly(userId, currentMonth) >= 100;
	}

	@Override
	public boolean isExceedTotalLimit() {
		String userId = serviceUserHelper.getCurrentUserId();
		return workItemMapper.countWorkItemTotal(userId) >= 10000;
	}
}
