package com.kyzer.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.kyzer.constants.Constants;
import com.kyzer.dao.EmpDao;
import com.kyzer.model.Emp;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpDao empDao;


	
	@Override
	public JSONObject getAllEmloyees(JSONObject requestData) {
		JSONObject responseJSON			= new JSONObject();
		List<Emp> empList = new ArrayList<Emp>();

		List<JSONObject> empListJson = new ArrayList<JSONObject>();
		if (empList.isEmpty()) 
			{
			System.out.println(":::: This function is hit");
			empList = empDao.getAllEmpList();
			for (Emp employee : empList) {
				JSONObject empJson = new JSONObject();
				empJson.put("empId", employee.getEmpId());
				empJson.put("firstName", employee.getFirstName());
				empJson.put("lastName", employee.getLastName());
				empJson.put("age", employee.getAge());
				empListJson.add(empJson);
			}
			responseJSON.put("empList", empListJson);
		} 

		return responseJSON;
	}
	
//	@Override
//	public JSONObject refresh(JSONObject requestData) {
//		JSONObject responseJSON = new JSONObject();
//		
//		if(!Constants.EMP_CACHE_LIST.isEmpty() && !Constants.EMP_LIST_JSON.isEmpty() ) {
//			Constants.EMP_CACHE_LIST.clear();
//			Constants.EMP_LIST_JSON.clear();
//			responseJSON.put("Status","Refreshed");	
//		}
//		
//		return responseJSON;
//	}
//	

	@Override
	public JSONObject addEmp(JSONObject requestData) {
		JSONObject responseDataObj = new JSONObject();

		int empId = requestData.getInt("empId");
		String firstName = requestData.getString("firstName");
		String lastName = requestData.getString("lastName");
		int age = requestData.getInt("age");

		Emp emp = new Emp();
		emp.setEmpId(empId);
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setAge(age);

		empDao.addEmp(emp);
		responseDataObj.put("Success", emp);
		System.out.println("::" + responseDataObj);

		return responseDataObj;
	}

	@Override
	public JSONObject getEmpById(JSONObject requestData) {
		JSONObject responseJSONObj = new JSONObject();
		int empId = requestData.getInt("empId");
		Emp empdata = empDao.getEmpById(empId);
		JSONObject empDetailsJson = new JSONObject();
		
		try {
			
		empDetailsJson.put("empId", empdata.getEmpId());
		empDetailsJson.put("firstName", empdata.getFirstName());
		empDetailsJson.put("lastName", empdata.getLastName());
		empDetailsJson.put("age", empdata.getAge());
		
		}catch (Exception e) {
			
			System.out.println("no such user in list");
			return null;
		}
		responseJSONObj.put("empDeatilsJson", empDetailsJson);

		System.out.println("::" + responseJSONObj);
		return responseJSONObj;
	}

	@Override
	public JSONObject delete(JSONObject requestData) {
		JSONObject response = new JSONObject();
		int id = requestData.getInt("empId");
		try {
			Emp employee = empDao.getEmpById(id);
			int delete = empDao.delete(employee);
			if (delete > 0) {
				response.put("Success", employee);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("deleted response::>>>"+response);
		return response;
	}

	@Override
	public JSONObject update(JSONObject requestData) {
		JSONObject response = new JSONObject();
		int id = requestData.getInt("empId");
		String firstName = requestData.getString("firstName");
		String lastName = requestData.getString("lastName");
		int age = requestData.getInt("age");
		try {
			Emp employee = empDao.getEmpById(id);
			employee.setEmpId(id);
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setAge(age);
			empDao.update(employee);
			response.put("Success", employee);
			System.out.println("::" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
