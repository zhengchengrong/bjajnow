package com.threehmis.bjaj.api.bean.request;



public class ProjectInfoProgressReq {


	private String projectId="";

	private String branchName="";

	private String projectXxjd="";

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getProjectXxjd() {
		return projectXxjd;
	}

	public void setProjectXxjd(String projectXxjd) {
		this.projectXxjd = projectXxjd;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
}
