package com.kyzer.service;

import org.json.JSONObject;

public interface EmpService {

	public JSONObject addEmp(JSONObject requestData);

	public JSONObject getEmpById(JSONObject requestData);

	public JSONObject getAllEmloyees(JSONObject requestData);

	public JSONObject delete(JSONObject requestData);

	public JSONObject update(JSONObject requestData);

//	public JSONObject refresh(JSONObject requestData);

}
