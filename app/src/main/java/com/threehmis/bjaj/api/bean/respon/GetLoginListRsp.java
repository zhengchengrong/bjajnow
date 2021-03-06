package com.threehmis.bjaj.api.bean.respon;

import java.io.Serializable;

public class GetLoginListRsp implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * projectID : P000002903
	 * projectGpsX : 0
	 * projectGpsY : 0
	 * "projectName": "广州市公共卫生综合大楼",
	 * ,"address":"天河区"
	 "userId": "B671EF90-F272-4A8D-AFF2-70251CBC76F0"
	 */

	public String projectID;
	public String projectGpsX;
	public String projectGpsY;
	public String projectName;
	public String userId;
	public String customerId;
	public String address;
	private String projectCode;
	private String projectNum;

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

	public String getSgxkzh() {
		return sgxkzh;
	}

	public void setSgxkzh(String sgxkzh) {
		this.sgxkzh = sgxkzh;
	}

	private String userType;

	private String personId;

	private String sgxkzh;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectGpsY() {
		return projectGpsY;
	}

	public void setProjectGpsY(String projectGpsY) {
		this.projectGpsY = projectGpsY;
	}




	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getProjectGpsX() {
		return projectGpsX;
	}

	public void setProjectGpsX(String projectGpsX) {
		this.projectGpsX = projectGpsX;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
