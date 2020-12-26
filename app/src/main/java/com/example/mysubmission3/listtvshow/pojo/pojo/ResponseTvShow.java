package com.example.mysubmission3.listtvshow.pojo.pojo;

import java.util.List;

import com.androidnetworking.error.ANError;
import com.google.gson.annotations.SerializedName;

public class ResponseTvShow{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<ResultsItem> results;

	@SerializedName("total_results")
	private int totalResults;
	private Throwable anError;

	public ResponseTvShow(ANError anError) {

	}

	public List<ResultsItem> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"ResponseTvShow{" + 
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}


	public Throwable getAnError() {
		return anError;
	}

	public void setAnError(Throwable anError) {
		this.anError = anError;
	}
}