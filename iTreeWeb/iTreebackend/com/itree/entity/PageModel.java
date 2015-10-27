package com.itree.entity;

public class PageModel {
	@SuppressWarnings("unused")
	private int totalPage;
	private int allRecords;
	private int pageNo = 1;
	private int pageSize = 5;
	@SuppressWarnings("unused")
	private int startIndex;

	public int getTotalPage() {
		
		return this.totalPage =(allRecords%pageSize==0)?(allRecords/pageSize):(allRecords/pageSize+1) ;
	}

	public int getAllRecords() {
		return allRecords;
	}

	public void setAllRecords(int allRecords) {
		this.allRecords = allRecords;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getStartIndex() {
		return (pageNo-1)*pageSize;
		}
}
