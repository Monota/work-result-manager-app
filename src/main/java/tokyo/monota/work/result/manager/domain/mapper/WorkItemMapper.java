package tokyo.monota.work.result.manager.domain.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tokyo.monota.work.result.manager.domain.entity.WorkItemEntity;

@Mapper
public interface WorkItemMapper {

	@Select("SELECT * FROM work_item")
	public List<WorkItemEntity> selectAllWorkItems();

	@Select("SELECT * FROM work_item WHERE user_id = #{userId} AND work_date = #{workDate} AND item_type_name = #{itemTypeName} AND item_is_new = #{itemIsNew} FOR UPDATE")
	public WorkItemEntity selectByPkForUpdate(
			@Param("userId") String userId,
			@Param("workDate") Date workDate,
			@Param("itemTypeName") String itemTypeName,
			@Param("itemIsNew") Boolean itemIsNew);

	@Update("UPDATE work_item SET item_quantity = #{itemQuantity} WHERE user_id = #{userId} AND work_date = #{workDate} AND item_type_name = #{itemTypeName} AND item_is_new = #{itemIsNew}")
	public int updateWorkItem(WorkItemEntity entity);
}
