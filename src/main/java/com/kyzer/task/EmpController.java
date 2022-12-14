package com.kyzer.task;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kyzer.service.EmpService;

@Controller
public class EmpController {

	@Autowired
	EmpService empService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/add", produces = { "application/json" },method = RequestMethod.POST)
	public ResponseEntity add(@RequestBody String requestData) {
		JSONObject requestJson = new JSONObject(requestData);
		JSONObject responseJson = empService.addEmp(requestJson);
		return new ResponseEntity(responseJson.toString(), HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getId", method = RequestMethod.POST)
	public ResponseEntity getId(@RequestBody String requestData) {
		JSONObject RequestJson = new JSONObject(requestData);
		JSONObject reponseJson = empService.getEmpById(RequestJson);
		System.out.println("Responce JSON GOT it"+reponseJson);
		if(reponseJson==null) {
			
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(reponseJson.toString(), HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ResponseEntity get(@RequestBody String requestData) {

		JSONObject requestJson = new JSONObject(requestData);
		JSONObject responseJson = empService.getAllEmloyees(requestJson);
		if(responseJson==null) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(responseJson.toString(),HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity delete(@RequestBody String request) {
		JSONObject requestJson = new JSONObject(request);
		JSONObject response = empService.delete(requestJson);
		System.out.println("inside controller checking response delet value::>>"+response);
		return new ResponseEntity(response.toString(), HttpStatus.OK);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity update(@RequestBody String requestData) {
		JSONObject requestJson = new JSONObject(requestData);
		JSONObject response = empService.update(requestJson);
		return new ResponseEntity(response.toString(), HttpStatus.OK);

	}

//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
//	ResponseEntity refresh(@RequestBody String requestData) {
//		JSONObject requestJson = new JSONObject(requestData);
//		JSONObject response = empService.refresh(requestJson);
//		return new ResponseEntity(response.toString(), HttpStatus.OK);
//
//	}

}
