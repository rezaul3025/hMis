package org.hmis.web.controller.result;

import java.util.List;

public class ControllerResult<T> {
	
	private Integer count;
	
	private List<T> results;
	
	public ControllerResult(Integer count, List<T> results){
		this.count= count;
		this.results = results;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
	
	

}
