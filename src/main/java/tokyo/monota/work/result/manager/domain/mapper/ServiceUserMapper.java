package tokyo.monota.work.result.manager.domain.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tokyo.monota.work.result.manager.domain.entity.ServiceUserEntity;

@Mapper
public interface ServiceUserMapper {

	@Select("SELECT * FROM service_user WHERE user_id = #{userId}")
	public ServiceUserEntity selectServiceUserById(String userId);
}
