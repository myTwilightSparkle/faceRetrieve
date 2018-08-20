package resultObj;

import java.util.ArrayList;

import entity.RectResult;

public class MultiFacesIdentifyData {
	private ArrayList<RectResult> results;
	private String session_id;
	private int time_ms;
	private int group_size;

	public MultiFacesIdentifyData() {
		super();
	}

	public ArrayList<RectResult> getResults() {
		return results;
	}

	public void setResults(ArrayList<RectResult> results) {
		this.results = results;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public int getTime_ms() {
		return time_ms;
	}

	public void setTime_ms(int time_ms) {
		this.time_ms = time_ms;
	}

	public int getGroup_size() {
		return group_size;
	}

	public void setGroup_size(int group_size) {
		this.group_size = group_size;
	}

	@Override
	public String toString() {
		return "MultiFacesIdentifyData [results=" + results + ", session_id=" + session_id + ", time_ms=" + time_ms
				+ ", group_size=" + group_size + "]";
	}

}
