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
		System.out.println("���person"+p);
		return id;
	}

	public void addComingRecord(ComingRecord record) {
		// TODO ȥ�����
		System.out.println("��Ӽ�¼"+record);
	}
	
}
