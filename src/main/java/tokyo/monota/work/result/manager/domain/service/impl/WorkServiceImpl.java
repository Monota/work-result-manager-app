package tokyo.monota.work.result.manager.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tokyo.monota.work.result.manager.domain.entity.WorkItemEntity;
import tokyo.monota.work.result.manager.domain.mapper.WorkItemMapper;
import tokyo.monota.work.result.manager.domain.resource.WorkResource;
import tokyo.monota.work.result.manager.domain.service.WorkService;

@Service
public class WorkServiceImpl implements WorkService {

	@Autowired
	WorkItemMapper workItemMapper;

	@Override
	public List<WorkResource> getAllWorkItem() {

		List<WorkItemEntity> workItemEntityList = workItemMapper.selectAllWorkItems();

		List<WorkResource> workResourceList = new ArrayList<>();
		for (WorkItemEntity entity : workItemEntityList) {
			WorkResource resource = new WorkResource();
			BeanUtils.copyProperties(entity, resource);
			workResourceList.add(resource);
		}

		return workResourceList;
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
