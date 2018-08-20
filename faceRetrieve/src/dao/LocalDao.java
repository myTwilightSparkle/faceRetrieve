package dao;


import entity.ComingRecord;
import entity.Person;
import util.Util;

public class LocalDao {

	public Person getPersonById(String personid) {
		// TODO
		Person p = new Person();
		p.setPersonid(personid);
		return p;
	}

	public String addPerson() {
		// TODO Auto-generated method stub
		Person p = new Person();
		String id = "person";
		id = Util.generateStrID(id);
		p.setPersonid(id);
		p.setName("new person");
		p.setGroupid("customer");
		p.setPersontag("customer");
		System.out.println("添加person"+p);
		return id;
	}

	public void addComingRecord(ComingRecord record) {
		// TODO 去重添加
		System.out.println("添加记录"+record);
	}
	
}
