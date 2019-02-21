package com.igeek.student.common;

public class DictTree  {
	
	 private Integer id;

	 private String name;
	    
	 private String title;

	 private Integer parentId;

	 private String dtype;
	    
	 private Boolean isParent; 
	 
	 private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDtype() {
		return dtype;
	}

	
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
