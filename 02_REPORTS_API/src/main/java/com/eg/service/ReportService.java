package com.eg.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.eg.request.SearchRequest;
import com.eg.response.SearchResponse;

public interface ReportService {
	public List<String> getUniquePlanNames();
	public List<String> getUniquePlanStatuses();
	public List<SearchResponse> search(SearchRequest request);
	public void  generateExcel(HttpServletResponse response) throws Exception;
	//public HttpServletResponse  generateExcel(); we can do in this way also
	public void  generatePdf(HttpServletResponse response) throws Exception;
	
	

}
