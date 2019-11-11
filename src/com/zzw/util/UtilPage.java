package com.zzw.util;

public class UtilPage {

	private int pageSize=7;//ÿҳҳ���С
	private int currentPageNo=1;//��ǰҳ��
	private int totalCount;//������
	private int totalPageCount;//��ҳ��
	
	public UtilPage() {
		super();
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		if(currentPageNo>0)
			this.currentPageNo = currentPageNo;
		else
			this.currentPageNo = 1;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		if(totalCount>0){
			this.totalCount = totalCount;
			this.totalPageCount = (totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);
		}else{
			this.totalCount = 0;
			this.totalPageCount = 1;
		}
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	@Override
	public String toString() {
		return "UtilPage [pageSize=" + pageSize + ", currentPageNo=" + currentPageNo + ", totalCount=" + totalCount
				+ ", totalPageCount=" + totalPageCount + "]";
	}
	
}
