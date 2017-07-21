package cn.tax.core.action;

import cn.tax.core.page.PageResult;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	protected String[] selectedRow;
	protected PageResult pageResult;
	private int pageNo;
	private int pageSize;
	//默认页大小
	public static int DEFAULT_PAGE=3;
	

	public String[] getSelectedRow() {
		return selectedRow;
	}
	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}
	public PageResult getPageResult() {
		return pageResult;
	}
	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		if(pageSize<1)pageSize=DEFAULT_PAGE;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
