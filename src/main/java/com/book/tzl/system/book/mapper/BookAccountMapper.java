package com.book.tzl.system.book.mapper;

import java.util.List;

import com.book.tzl.system.book.domain.BookAccount;

/**
 * 
 * @author SEELE 账户接口
 */
public interface BookAccountMapper {

	List<BookAccount> findAll();
}
