package util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


import resultObj.FaceIdentifyResult;
import test.TestQcloud;

import com.alibaba.fastjson.JSON;
import com.qcloud.image.ImageClient;
import com.qcloud.image.request.FaceAddFaceRequest;
import com.qcloud.image.request.FaceAddGroupIdsRequest;
import com.qcloud.image.request.FaceDetectRequest;
import com.qcloud.image.request.FaceIdentifyRequest;
import com.qcloud.image.request.FaceNewPersonRequest;

public class FaceUtil {
	static String appid;
	static String secretid;
	static String secretkey;
	static String bucketName;
	static ImageClient imageClient;
	
	static {
		String filename = "qcloud.properties";

		Properties prop = new Properties();

		try {
			prop.load(TestQcloud.class.getClassLoader().getResourceAsStream(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}

		appid = prop.getProperty("appid");
		secretid = prop.getProperty("secretid");
		secretkey = prop.getProperty("secretkey");
		bucketName = prop.getProperty("bucketName");
		imageClient = new ImageClient(appid, secretid, secretkey);
		
	}
	
	
	/*
	譽�豬倶ｺｺ閼ｸ
	 */
	public String faceDetect(File image){
		String name = image.getName();
		FaceDetectRequest faceDetectRequest = new FaceDetectRequest(bucketName, name, image, 0);
		String result = imageClient.faceDetect(faceDetectRequest);
		return result;
	}
	
	/*
	譁ｰ蠅樔ｺｺ
	 */
	public String addPerson(String personid, String groupid, File image){
		String[] groupids = new String[1];
		groupids[0] = groupid;
		FaceNewPersonRequest faceNewPersonRequest = new FaceNewPersonRequest(bucketName, personid, groupids, image, "messi", "footballplayer");
		String result = imageClient.faceNewPerson(faceNewPersonRequest);
		return result;
	}
	
	/*
	謚贋ｺｺ豺ｻ�ｿｽ?蛻ｰ�ｿｽ?
	 */
	public String personSetGroup(String personid, String groupid){
		FaceAddGroupIdsRequest faceAddGroupIdsRequest = new FaceAddGroupIdsRequest(bucketName, personid, groupid);
		String result = imageClient.faceAddGroupIds(faceAddGroupIdsRequest, false);
		return result;
	}
	
	/*
	譁ｰ蠅樔ｺｺ閼ｸ
	 */
	public String addFace(String personid, String persontag, File image){
		File[] addFaceImageList = new File[1];
		addFaceImageList[0] = image;
		FaceAddFaceRequest faceAddFaceRequest = new FaceAddFaceRequest(bucketName, addFaceImageList, personid, persontag);
		String result = imageClient.faceAddFace(faceAddFaceRequest);
		return result;
	}
	
	/*
	莠ｺ閼ｸ�ｿｽ?邏｢
	 */
	public FaceIdentifyResult faceIdentify(String groupid, File image){
		FaceIdentifyRequest faceIdentifyRequest = new FaceIdentifyRequest(bucketName, groupid, image);
		String result = imageClient.faceIdentify(faceIdentifyRequest);
		FaceIdentifyResult faceIdentifyResult = JSON.parseObject(result, FaceIdentifyResult.class);
		return faceIdentifyResult;
	}
}
