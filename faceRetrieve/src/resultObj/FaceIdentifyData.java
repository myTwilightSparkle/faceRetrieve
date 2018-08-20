package resultObj;

import java.util.ArrayList;

import entity.Candidate;

public class FaceIdentifyData {
		private ArrayList<Candidate> candidates;
		private int time_ms;
		private int group_size;
		public FaceIdentifyData() {
			super();
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
		public ArrayList<Candidate> getCandidates() {
			return candidates;
		}
		public void setCandidates(ArrayList<Candidate> candidates) {
			this.candidates = candidates;
		}
		@Override
		public String toString() {
			return "FaceIdentifyData [candidates=" + candidates + ", time_ms="
					+ time_ms + ", group_size=" + group_size + "]";
		}
		

}
