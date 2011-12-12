/**
 * 
 */
package com.github.airqs.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author Eric
 *
 */
public class Page<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private int totalCount;
	private int limit;
	private int offset;
	private List<T> items;
	
	public Page(List<T> items, int offset, int limit, int totalCount) {
		super();
		this.items = items;
		this.offset = offset;
		this.limit = limit;
		this.totalCount = totalCount;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	
	public int getNextPageOffset(){
		int nextPageOffset = offset + limit;
		if(nextPageOffset > totalCount){
			return totalCount-1;
		}
		return nextPageOffset;
	}
	
	public int getPrevPageOffset(){
		int prevPageOffset = offset - limit;
		return prevPageOffset;
	}
	
	public int getFirstPageOffset(){
		return 0;
	}
	
	public int getLastPageOffset(){
		return totalCount-limit;
	}
	
	public boolean isFirstPage(){
		return getPrevPageOffset() < 0;
	}
	
	public boolean isLastPage(){
		return (offset + limit) >= (totalCount-1); 
	}

	public int getCurrentPage(){
		return offset / limit + 1;
	}
	
	public int getTotalPage(){
		return totalCount / limit + 1;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [totalCount=");
		builder.append(totalCount);
		builder.append(", offset=");
		builder.append(offset);
		builder.append(", limit=");
		builder.append(limit);
		builder.append(", items=");
		builder.append(items);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
