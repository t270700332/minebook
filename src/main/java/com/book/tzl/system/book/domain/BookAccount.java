package com.book.tzl.system.book.domain;

import java.util.List;

/**
 * @author 作者:tianzhili
 * @version 创建时间:2019年7月12日上午9:18:21 类说明:账户
 */
public class BookAccount {
	private int id;
	private String name;
	private List<BookAccountBranch> branchList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BookAccountBranch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<BookAccountBranch> branchList) {
		this.branchList = branchList;
	}

}
