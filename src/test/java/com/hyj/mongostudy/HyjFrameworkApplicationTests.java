package com.hyj.mongostudy;

import com.alibaba.fastjson.JSONArray;
import com.hyj.mongostudy.mapper.mongodb.mongo1.RemoveDollarOperation;
import com.hyj.mongostudy.model.domain.MgCompany;
import com.hyj.mongostudy.model.domain.MgDepartment;
import com.hyj.mongostudy.model.domain.MgEmployee;
import com.hyj.mongostudy.model.po.City;
import com.hyj.mongostudy.service.IDemoService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@MapperScan("com.hyj.mongostudy.mapper")
@EnableTransactionManagement
public class HyjFrameworkApplicationTests {


//	@Resource(name="mongoTemplate")
//	private MongoTemplate mongoTemplate;
//
//	@Resource(name="secondaryMongoTemplate")
//	private MongoTemplate secondaryMongoTemplate;

	@Resource
	private MongoTemplate mongoTemplate;

	@Resource
	private IDemoService demoService;
	@Resource
	private MongoClient client;

	@Test
	public void changeDB() {
		City city=new City();
		city.setCityName("test-city1135");
		System.out.println(mongoTemplate.getDb().getName());

		mongoTemplate.insert(city);
//		System.out.println(secondaryMongoTemplate.getDb().getName());
//		mongoTemplate.insert(city);

		//MongoClient操作数据库
//		MongoDatabase db=client.getDatabase("hyj3");
//		MongoCollection collection=db.getCollection("test1");
////		System.out.println(mongoTemplate2.getDb().getName());
////		mongoTemplate2.insert(city);
//		collection.insertOne(city);


	}

	@Test
	public void testAdd(){
		City city=new City();

		city.setCityName("20190513-1");
		city.setDescription("xxx2");

		demoService.testAdd(city);

	}

	@Test
	public void testUpdate(){
		City city=mongoTemplate.findOne(new Query(Criteria.where("cityName").is("20190513-2")),City.class);
		city.setCityName("20190513-3");
		demoService.testUpdate2(city);
	}

	@Test
	public void testDelete(){
		City city=mongoTemplate.findOne(new Query(Criteria.where("cityName").is("20190513-1")).limit(1),City.class);
		System.out.println(city);
		demoService.testDelete2(city);
	}


	@Test
	public void initData() {
		// 公司
		MgCompany company = new MgCompany();
		company.setCompanyName("XXX公司");
		company.setMobile("023-66668888");
		mongoTemplate.save(company);
		log.info("save company ok");

		// 部门
		MgDepartment department = new MgDepartment();
		department.setDepartmentName("XXX信息开发系统");
		department.setCompany(company);
		department.setEmployeeList(Collections.emptyList());
		mongoTemplate.save(department);
		log.info("save department ok");

		// 员工
		List<MgEmployee> employeeList = new ArrayList<>();
		MgEmployee employee1 = new MgEmployee();
		employee1.setEmployeeName("张一");
		employee1.setPhone("159228359xx");
		employee1.setDepartment(department);
		employeeList.add(employee1);

		MgEmployee employee2 = new MgEmployee();
		employee2.setEmployeeName("张二");
		employee2.setPhone("159228358xx");
		employee2.setDepartment(department);
		employeeList.add(employee2);
		mongoTemplate.insert(employeeList, MgEmployee.class);
		log.info("insert employeeList ok");

		department.setEmployeeList(employeeList);
		mongoTemplate.save(department);
		log.info("setEmployeeList-save department ok");
	}

	/**
	 * 一对一：两表关联查询
	 * 员工表关联部门表
	 */
	@Test
	public void twoTableQuery() {
		// 1、消除@DBRef引用对象中的"$id"的"$"符号
		RemoveDollarOperation removeDollarOperation = new RemoveDollarOperation("newDepartmentFieldName", "department");

		// 2、使用mongodb $lookup实现左连接部门表
		LookupOperation lookupOperation = LookupOperation.newLookup().from("t_department")
				.localField("newDepartmentFieldName.id").foreignField("_id").as("newDepartment");

		// $match条件筛选
		// MatchOperation matchOperation = new MatchOperation(Criteria.where("newDepartment.departmentName").is("信息开发系统"));

		// 3、Aggregation管道操作（还可以加入$match、$project等其他管道操作，但是得注意先后顺序）
		TypedAggregation aggregation = Aggregation.newAggregation(MgEmployee.class, removeDollarOperation, lookupOperation);
		// TypedAggregation aggregation = Aggregation.newAggregation(Employee.class, removeDollarOperation, lookupOperation, matchOperation);
		AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, Document.class);

		System.out.println(JSONArray.toJSONString(results.getMappedResults()));
	}

	/**
	 * 一对一：多表关联查询
	 * 员工表关联部门表，部门表关联公司表
	 */
	@Test
	public void threeTableQuery() {
		// 1、消除@DBRef引用对象中的"$id"的"$"符号
		RemoveDollarOperation removeDollarOperation1 = new RemoveDollarOperation("newDepartmentFieldName", "department");

		// 2、使用mongodb $lookup实现左连接部门表
		LookupOperation lookupOperation1 = LookupOperation.newLookup().from("t_department")
				.localField("newDepartmentFieldName.id").foreignField("_id").as("newDepartment");

		// 3、使用$unwind展平步骤二中的左连接的department表的"newDepartment"
		UnwindOperation unwindOperation = new UnwindOperation(Fields.field("$newDepartment"));

		// 4、消除@DBRef引用对象中的"$id"的"$"符号
		RemoveDollarOperation removeDollarOperation2 = new RemoveDollarOperation("newCompanyFieldName", "newDepartment.company");

		// 5、使用mongodb $lookup实现左连接公司表
		LookupOperation lookupOperation2 = LookupOperation.newLookup().from("t_company")
				.localField("newCompanyFieldName.id").foreignField("_id").as("newCompany");

		MatchOperation matchOperation = new MatchOperation(Criteria.where("newCompany.companyName").is("XXX公司"));

		// 4、Aggregation管道操作（还可以加入$match、$project等其他管道操作，但是得注意先后顺序）
		TypedAggregation aggregation = Aggregation.newAggregation(MgEmployee.class,
				removeDollarOperation1, lookupOperation1,
				unwindOperation,
				removeDollarOperation2, lookupOperation2,
				matchOperation);

		AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, Document.class);

		System.out.println(JSONArray.toJSONString(results.getMappedResults()));
	}

	/**
	 * 一对多：关联查询
	 * 查询部门中的所有员工，部门关联多个员工
	 */
	@Test
	public void oneToManyTableQuery() {
		// 1、展平“多”的一方
		UnwindOperation unwindOperation = new UnwindOperation(Fields.field("employeeList"));

		// 2、消除@DBRef引用对象中的"$id"的"$"符号
		RemoveDollarOperation removeDollarOperation1 = new RemoveDollarOperation("newEmployeeFieldName", "employeeList");

		// 3、使用mongodb $lookup实现左连接员工表
		LookupOperation lookupOperation1 = LookupOperation.newLookup().from("t_department")
				.localField("newEmployeeFieldName.id").foreignField("_id").as("newEmployee");

		// 筛选条件（非必须，看自己是否需要筛选）
		MatchOperation matchOperation = new MatchOperation(Criteria.where("newEmployee.employeeName").is("张一"));

		// 4、Aggregation管道操作（还可以加入$match、$project等其他管道操作，但是得注意先后顺序）
		TypedAggregation aggregation = Aggregation.newAggregation(MgDepartment.class,
				unwindOperation,
				removeDollarOperation1, lookupOperation1,
				matchOperation);

		AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, Document.class);

		System.out.println(JSONArray.toJSONString(results.getMappedResults()));
	}

}
