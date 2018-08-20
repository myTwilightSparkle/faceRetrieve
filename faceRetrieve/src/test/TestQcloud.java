package test;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import resultObj.FaceIdentifyResult;

import com.alibaba.fastjson.JSON;
import com.qcloud.image.ImageClient;
import com.qcloud.image.request.FaceAddFaceRequest;
import com.qcloud.image.request.FaceAddGroupIdsRequest;
import com.qcloud.image.request.FaceDetectRequest;
import com.qcloud.image.request.FaceIdentifyRequest;
import com.qcloud.image.request.FaceNewPersonRequest;

public class TestQcloud {

	
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
	
	String personid;
	String groupid;
	File image;
	
	@Before
	public void doBeforeRequest(){
		personid = "person1";
		groupid = "customer";
	}
	
	@Test
	public void faceDetect(){
		//url隸ｷ豎よ婿�ｿｽ?
		//String imageUrl = "http://i3.sinaimg.cn/ty/g/2013-11-30/U5244P6T12D6910497F44DT20131130112919.jpg";
		String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534480544241&di=99d764b745d26ad1bdc5e841a585e9d7&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Fbmiddle%2F64143be5jw1dmj4ce3ki9j.jpg";
		int mode = 0;//0譏ｯ�ｿｽ?譛我ｺｺ閼ｸ?�ｿｽ?1譏ｯ�ｿｽ?螟ｧ莠ｺ閼ｸ
		String result1 = imageClient.faceDetect(new FaceDetectRequest(bucketName, imageUrl, mode));
		System.out.println(result1);
		
		//image隸ｷ豎よ婿�ｿｽ?
		File image = new File("D:/images/img4.jpg");
		String name = image.getName();
		FaceDetectRequest faceDetectRequest = new FaceDetectRequest(bucketName, name, image, 0);
		String result2 = imageClient.faceDetect(faceDetectRequest);
		System.out.println(result2);
	}
	
	/*
	譁ｰ蠅樔ｺｺ
	 */
	@Test
	public void addPerson(){
		String[] groupids = new String[1];
		groupids[0] = groupid;
		image = new File("D:/images/messi.jpg");
		FaceNewPersonRequest faceNewPersonRequest = new FaceNewPersonRequest(bucketName, personid, groupids, image, "messi", "footballplayer");
		String result = imageClient.faceNewPerson(faceNewPersonRequest);
		System.out.println("譁ｰ蠅樔ｺｺ?�ｿｽ? "+result);
	}
	
	/*
	謚贋ｺｺ豺ｻ�ｿｽ?蛻ｰ�ｿｽ?
	 */
	@Test
	public void personSetGroup(){
		FaceAddGroupIdsRequest faceAddGroupIdsRequest = new FaceAddGroupIdsRequest(bucketName, personid, groupid);
		String result = imageClient.faceAddGroupIds(faceAddGroupIdsRequest, false);
		System.out.println("豺ｻ�ｿｽ?蛻ｰ�ｿｽ?�ｿｽ? "+result);
	}
	
	/*
	譁ｰ蠅樔ｺｺ閼ｸ
	 */
	@Test
	public void addFace(){
		File[] addFaceImageList = new File[1];
		addFaceImageList[0] = image;
		FaceAddFaceRequest faceAddFaceRequest = new FaceAddFaceRequest(bucketName, addFaceImageList, personid, "");
		String result = imageClient.faceAddFace(faceAddFaceRequest);
		System.out.println("譁ｰ蠅樔ｺｺ閼ｸ?�ｿｽ? "+result);
	}
	
	/*
	莠ｺ閼ｸ�ｿｽ?邏｢
	 */
	@Test
	public void faceIdentify(){
		//逵滂ｿｽ?隘ｿconfidence100
		image = new File("D:/images/img4.jpg");
		//莨頑恬�ｿｽ?隘ｿconfidence75
		//image = new File("D:/images/iranmessi.png");
		//鮴壽￡荵議onfidence23
		//image = new File("D:/images/img2.jpg");
		FaceIdentifyRequest faceIdentifyRequest = new FaceIdentifyRequest(bucketName, groupid, image);
		String result = imageClient.faceIdentify(faceIdentifyRequest);
		System.out.println("莠ｺ閼ｸ�ｿｽ?邏｢?�ｿｽ? "+result);
	}
	
	@Test
	public void testJSON(){
		String result = "{\"code\":0,\"message\":\"OK\",\"data\":{\"session_id\":\"\",\"candidates\":[{\"person_id\":\"person1\",\"face_id\":\"2715873977920651864\",\"confidence\":100.0,\"tag\":\"footballplayer\"}],\"time_ms\":206,\"group_size\":1}}";
		FaceIdentifyResult faceIdentifyResult = JSON.parseObject(result, FaceIdentifyResult.class);
		System.out.println(faceIdentifyResult);
	}

}
