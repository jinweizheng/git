package com.igeek.student.pojo;

public class Dict {
	
    private Integer did;

    private String dname;

    private Integer parentId;

    private String dtype;
    
    private Boolean parent;


    
    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname == null ? null : dname.trim();
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
        this.dtype = dtype == null ? null : dtype.trim();
    }

	public Boolean getParent() {
		return parent;
	}

	public void setParent(Boolean parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "Dict [did=" + did + ", dname=" + dname + ", parentId=" + parentId + ", dtype=" + dtype + ", parent="
				+ parent + "]";
	}

	

	
}