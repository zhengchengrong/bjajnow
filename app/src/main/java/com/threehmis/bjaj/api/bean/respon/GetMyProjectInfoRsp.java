package com.threehmis.bjaj.api.bean.respon;

import java.io.Serializable;
import java.util.List;

public class GetMyProjectInfoRsp implements Serializable {

	private List<?> projectList;
	private String result;
	private Boolean verification;

	/**
	 * 是否显示提交按钮：0-显示，1-不显示
	 */
	private String showSubmit = "0";

	public List<?> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<?> projectList) {
		this.projectList = projectList;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Boolean getVerification() {
		return verification;
	}

	public void setVerification(Boolean verification) {
		this.verification = verification;
	}

	public String getShowSubmit() {
		return showSubmit;
	}

	public void setShowSubmit(String showSubmit) {
		this.showSubmit = showSubmit;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
