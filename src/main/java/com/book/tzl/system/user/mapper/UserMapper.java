package com.book.tzl.system.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.tzl.system.user.domain.UserPojo;

/**
 * 
 * @author tianzhili
 *
 */
@Mapper
public interface UserMapper {
	List<UserPojo> findAll();

	UserPojo findByUsername(String userName);
}
