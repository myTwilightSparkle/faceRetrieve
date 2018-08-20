package entity;
/**
 * 人脸检索候选人
 * @author XinZhi
 *
 */
public class Candidate {
	private float confidence;//置信度0-100
	private long face_id;
	private String tag;
	private String person_id;
	public Candidate() {
		super();
	}
	public float getConfidence() {
		return confidence;
	}
	public void setConfidence(float confidence) {
		this.confidence = confidence;
	}
	public long getFace_id() {
		return face_id;
	}
	public void setFace_id(long face_id) {
		this.face_id = face_id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getPerson_id() {
		return person_id;
	}
	public void setPerson_id(String person_id) {
		this.person_id = person_id;
	}
	@Override
	public String toString() {
		return "Candidate [confidence=" + confidence + ", face_id=" + face_id
				+ ", tag=" + tag + ", person_id=" + person_id + "]";
	}
	
	
}
