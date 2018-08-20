package resultObj;

import java.util.ArrayList;
import java.util.Arrays;

import com.alibaba.fastjson.JSON;

import entity.Candidate;

public class FaceIdentifyResult {
	private int code;
	private String message;
	private FaceIdentifyData data;
	public FaceIdentifyResult() {
		super();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public FaceIdentifyData getData() {
		return data;
	}
	public void setData(FaceIdentifyData data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "FaceIdentifyResult [code=" + code + ", message=" + message
				+ ", data=" + data + "]";
	}
	
	
	
}