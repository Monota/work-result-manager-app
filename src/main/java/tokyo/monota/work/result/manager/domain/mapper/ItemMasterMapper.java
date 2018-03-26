package tokyo.monota.work.result.manager.domain.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemMasterMapper {

	@Select("SELECT DISTINCT item_type_name FROM item_master ORDER BY item_type_name")
	public List<String> selectAllItemTypeNames();

	@Select("SELECT item_unit_price FROM item_master WHERE item_type_name = #{itemTypeName} AND item_is_new = #{itemIsNew}")
	public BigDecimal selectUnitPrice(@Param("itemTypeName") String itemTypeName, @Param("itemIsNew") Boolean itemIsNew);
}
