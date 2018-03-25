package tokyo.monota.work.result.manager.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemMasterMapper {

	@Select("SELECT DISTINCT item_type_name FROM item_master ORDER BY item_type_name")
	public List<String> selectAllItemTypeNames();
}
