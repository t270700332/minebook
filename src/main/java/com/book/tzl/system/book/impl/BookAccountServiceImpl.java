package com.book.tzl.system.book.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.tzl.system.book.domain.BookAccount;
import com.book.tzl.system.book.mapper.BookAccountMapper;
import com.book.tzl.system.book.service.BookAccountService;

@Service
public class BookAccountServiceImpl implements BookAccountService {

	@Autowired
	private BookAccountMapper bookAccountMapper;

	@Override
	public List<BookAccount> findAll() {
		return bookAccountMapper.findAll();
	}

}
