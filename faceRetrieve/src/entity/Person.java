package entity;

/**
 * êl
 * 
 * @author XinZhi
 *
 */
public class Person {
	private String personid;
	private String name;
	private String persontag;
	private String gender;
	private String groupid;
	private int age;

	public Person() {
		super();
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersontag() {
		return persontag;
	}

	public void setPersontag(String persontag) {
		this.persontag = persontag;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [personid=" + personid + ", name=" + name + ", persontag=" + persontag + ", gender=" + gender
				+ ", groupid=" + groupid + ", age=" + age + "]";
	}

}
