package entity;

import java.util.Date;

/**
 * 
 * 顾客光临记录
 * 
 * @author XinZhi
 *
 */
public class ComingRecord {
	private Person person;
	private Date comingTime;

	public Person getPerson() {
		return person;
	}

	public ComingRecord() {
		super();
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Date getComingTime() {
		return comingTime;
	}

	public void setComingTime(Date comingTime) {
		this.comingTime = comingTime;
	}

	@Override
	public String toString() {
		return "ComingRecord [person=" + person + ", comingTime=" + comingTime + "]";
	}

}
