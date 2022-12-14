package com.kyzer.dao;

import java.util.List;

import com.kyzer.model.Emp;

public interface EmpDao {

	public int addEmp(Emp emp);

	public Emp getEmpById(int empId);

	List<Emp> getAllEmpList();

	public int delete(Emp emp);

	public int update(Emp emp);

}
