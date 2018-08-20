package dao;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


import resultObj.FaceIdentifyResult;
import resultObj.MultiFacesIdentifyResult;
import test.TestQcloud;

import com.alibaba.fastjson.JSON;
import com.qcloud.image.ImageClient;
import com.qcloud.image.request.FaceAddFaceRequest;
import com.qcloud.image.request.FaceAddGroupIdsRequest;
import com.qcloud.image.request.FaceDelPersonRequest;
import com.qcloud.image.request.FaceDetectRequest;
import com.qcloud.image.request.FaceIdentifyRequest;
import com.qcloud.image.request.FaceMultiIdentifyRequest;
import com.qcloud.image.request.FaceNewPersonRequest;

public class QcloudDao {
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
	 * 人脸�?�?
	 */
	public String faceDetect(File image){
		String name = image.getName();
		FaceDetectRequest faceDetectRequest = new FaceDetectRequest(bucketName, name, image, 0);
		String result = imageClient.faceDetect(faceDetectRequest);
		return result;
	}
	
	/*
	 * 添加�?
	 */
	public String addPerson(String personid, String groupid, File image, String name, String persontag){
		String[] groupids = new String[1];
		groupids[0] = groupid;
		FaceNewPersonRequest faceNewPersonRequest = new FaceNewPersonRequest(bucketName, personid, groupids, image, name, persontag);
		String resultStr = imageClient.faceNewPerson(faceNewPersonRequest);
		return resultStr;
	}
	
	/*
	 * 删除�?
	 * @Param personid
	 */
	public String deletePerson(String personid) {
		FaceDelPersonRequest request = new FaceDelPersonRequest(bucketName, personid);
		String resultStr = imageClient.faceDelPerson(request );
		return resultStr;
	}
	
	/*
	 * 添加group
	 * @Param personid
	 * @Param groupid
	 * 如果bucket上不存在groupid会自动创建group
	 */
	public String personSetGroup(String personid, String groupid){
		FaceAddGroupIdsRequest faceAddGroupIdsRequest = new FaceAddGroupIdsRequest(bucketName, personid, groupid);
		String result = imageClient.faceAddGroupIds(faceAddGroupIdsRequest, false);
		return result;
	}
	
	/*
	 * 添加人脸
	 */
	public String addFace(String personid, String persontag, File image){
		File[] addFaceImageList = new File[1];
		addFaceImageList[0] = image;
		FaceAddFaceRequest faceAddFaceRequest = new FaceAddFaceRequest(bucketName, addFaceImageList, personid, persontag);
		String result = imageClient.faceAddFace(faceAddFaceRequest);
		return result;
	}
	
	/*
	 * 人脸识别
	 * @Param groupid
	 * @Param image
	 */
	public FaceIdentifyResult faceIdentify(String groupid, File image){
		FaceIdentifyRequest faceIdentifyRequest = new FaceIdentifyRequest(bucketName, groupid, image);
		String resultStr = imageClient.faceIdentify(faceIdentifyRequest);
		FaceIdentifyResult result = JSON.parseObject(resultStr, FaceIdentifyResult.class);
		return result;
	}
	
	/*
	 * 多脸识别
	 * @Param groupid
	 * @Param image
	 */
	public MultiFacesIdentifyResult multiFacesIdentify(String groupid, File image) {
		FaceMultiIdentifyRequest request = new FaceMultiIdentifyRequest(bucketName, image, groupid);
		String resultStr = imageClient.faceMultiIdentify(request, false);
		MultiFacesIdentifyResult result = JSON.parseObject(resultStr, MultiFacesIdentifyResult.class);
		return result;
	}
}
