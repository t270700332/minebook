package com.book.tzl.system.book.domain;

/**
 * @author 作者:tianzhili
 * @version 创建时间:2019年7月12日上午9:19:42 类说明:minebook
 */
public class BookAccountBranch {
	private int id;
	private int account_id;
	private String name;
	private String account_balance;// 余额
	private String account_inflows;// 流入
	private String account_outflow;// 流出
	private String remarks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(String account_balance) {
		this.account_balance = account_balance;
	}

	public String getAccount_inflows() {
		return account_inflows;
	}

	public void setAccount_inflows(String account_inflows) {
		this.account_inflows = account_inflows;
	}

	public String getAccount_outflow() {
		return account_outflow;
	}

	public void setAccount_outflow(String account_outflow) {
		this.account_outflow = account_outflow;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
