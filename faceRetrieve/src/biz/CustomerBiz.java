package biz;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import dao.LocalDao;
import dao.QcloudDao;
import entity.Candidate;
import entity.ComingRecord;
import entity.Person;
import entity.RectResult;
import resultObj.MultiFacesIdentifyResult;
import util.Util;

public class CustomerBiz {

	float CONFIDENCE_THRESHOLD = 95;

	private LocalDao ld;
	private QcloudDao qd;
	private List<Person> personList;

	public CustomerBiz() {
		ld = new LocalDao();
		qd = new QcloudDao();
		List<Person> personList = new ArrayList<Person>();
	}

	/*
	 * ����ͼ�� ���ͼ������� �õ�Candidates ѡ��Candidate��ת��Person �������person��¼�����ھʹ���һ��person
	 */
	public int findComingPerson(File[] images) {
		if (personList == null) {
			personList = new ArrayList<Person>();
		}
		for (File img : images) {// ��������ͼƬ
			MultiFacesIdentifyResult result = qd.multiFacesIdentify("customer", img);
			if (result.getMessage().equals("OK")) {
				List<RectResult> results = result.getData().getResults();
				if (result != null && results.size() >= 0) {
					for (RectResult rs : results) {// ����ͼƬ��ÿ����������
						System.out.println(rs);
						Candidate[] cands = rs.getCandidates();
						Candidate cand = judgeCandidates(cands);
						Person person = new Person();
						if (cand != null) {
							person = ld.getPersonById(cand.getPerson_id());
							if (person != null) {
								personList.add(person);
							}
						} else {// û�ҵ����candidate �����person�����غ�bucket
							String personid = ld.addPerson();
							try {
								// ��ȡ��person����
								File faceimg = Util.getFaceimg(personid, img, rs.getFace_rect());
								qd.addPerson(personid, "customer", faceimg, "new person", "customer");
							} catch (IOException e) {
								e.printStackTrace();
							}
							person = ld.getPersonById(personid);
							personList.add(person);
						}
					}
				}
			} else {
				System.out.println("ʶ�����");
				System.out.println("code: " + result.getCode());
				System.out.println("message: " + result.getMessage());
				return result.getCode();
			}
		}
		return 0;
	}

	private Candidate judgeCandidates(Candidate[] carray) {
		Arrays.sort(carray, new Comparator<Candidate>() {
			@Override
			public int compare(Candidate o1, Candidate o2) {
				return (int) ((o1.getConfidence() - o2.getConfidence()) * 1000);
			}
		});
		float bestConfidence = carray[0].getConfidence();
		if (bestConfidence >= CONFIDENCE_THRESHOLD) {
			System.out.println("best candidate: "+carray[0]);
			return carray[0];
		}
		System.out.println("no best candidate");
		return null;
	}

	/*
	 * ����ComingRecord
	 */
	public void addComingRecord(Date comingTime) {
		for (Person p:personList) {
			ComingRecord record = new ComingRecord();
			record.setPerson(p);
			record.setComingTime(comingTime);
			ld.addComingRecord(record);
		}
		personList = new ArrayList<Person>();
	}

}
