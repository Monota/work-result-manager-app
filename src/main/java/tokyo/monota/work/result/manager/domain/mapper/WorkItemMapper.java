package tokyo.monota.work.result.manager.domain.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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


	@Select("SELECT SUM(item_unit_price * item_quantity) FROM work_item")
	public BigDecimal sumupUnitPrice();

	@Update("UPDATE work_item SET item_quantity = #{itemQuantity} WHERE user_id = #{userId} AND work_date = #{workDate} AND item_type_name = #{itemTypeName} AND item_is_new = #{itemIsNew}")
	public int updateWorkItem(WorkItemEntity entity);

	@Delete("DELETE FROM work_item WHERE user_id = #{userId} AND work_date = #{workDate} AND item_type_name = #{itemTypeName} AND item_is_new = #{itemIsNew}")
	public int deleteWorkItem(
			@Param("userId") String userId,
			@Param("workDate") Date workDate,
			@Param("itemTypeName") String itemTypeName,
			@Param("itemIsNew") Boolean itemIsNew);

	@Insert("INSERT INTO work_item VALUES(#{userId}, #{workDate}, #{itemTypeName}, #{itemIsNew}, #{itemUnitPrice}, #{itemQuantity})")
	public int insertWorkItem(WorkItemEntity entity);
}
