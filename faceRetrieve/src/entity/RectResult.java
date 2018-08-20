package entity;

import java.util.ArrayList;

public class RectResult {
	private Rect face_rect;
	private int id;
	private int errorcode;
	private Candidate[] candidates;

	public RectResult() {
		super();
	}

	public Rect getFace_rect() {
		return face_rect;
	}

	public void setFace_rect(Rect face_rect) {
		this.face_rect = face_rect;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}

	public Candidate[] getCandidates() {
		return candidates;
	}

	public void setCandidates(Candidate[] candidates) {
		this.candidates = candidates;
	}

	@Override
	public String toString() {
		return "RectResult [face_rect=" + face_rect + ", id=" + id + ", errorcode=" + errorcode + ", candidates="
				+ candidates + "]";
	}

}
