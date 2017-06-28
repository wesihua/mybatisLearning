package com.wei.manager.bean;

public class Page {

	private int pageNow = 1;
	private int pageSize = 15;
	private int pageCount = 0;
	private int rowCount = 0;
	private int offset = 0;
	
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
		this.offset = this.pageNow == 0 ? 0 : (this.pageNow - 1) * this.pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		if(rowCount % pageSize == 0){
			pageCount = rowCount / pageSize;
		}
		else{
			pageCount = rowCount / pageSize + 1;
		}
//		this.offset = this.pageNow == 0 ? 0 : (this.pageNow - 1) * this.pageSize;
	}
}
