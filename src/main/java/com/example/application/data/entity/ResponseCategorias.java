package com.example.application.data.entity;

import java.util.List;

public class ResponseCategorias {
private List<Categoria> items;
private boolean hasMore;
private int limit;
private int offset;
private int count;


public List<Categoria>getItems() {
	return items;
}
public boolean isHasMore() {
	return hasMore;
}
public int getLimit() {
	return limit;
}
public int getOffset() {
	return offset;
}
public int getCount() {
	return count;
}
public void setItems(List<Categoria> items) {
	this.items = items;
}
public void setHasMore(boolean hasMore) {
	this.hasMore = hasMore;
}
public void setLimit(int limit) {
	this.limit = limit;
}
public void setOffset(int offset) {
	this.offset = offset;
}
public void setCount(int count) {
	this.count = count;
}


}
