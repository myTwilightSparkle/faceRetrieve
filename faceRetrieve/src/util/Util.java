package util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import entity.Rect;

public class Util {
	/**
	 * 创建15位字母加数字ID bl 为true是为暂停时间
	 *
	 * @return string 15位随机ID
	 */
	public synchronized static String generateStrID(String str) {
		int count = 0;
		String time = Long.toString(System.currentTimeMillis());
		time = time.substring(time.length() - 9, time.length());
		if (count > 99) {
			count = 0;
		} else {
			count++;
		}
		if (count < 10) {
			str += time + "0" + count;
		} else {
			str += time + count;
		}
		return str;
	}

	/*
	 * 剪裁人脸部分
	 */
	public static File getFaceimg(String id, File img, Rect face_rect) throws IOException {
		File dir = new File("new_faces");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File faceimg = new File("new_faces/"+id);
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(img.getPath());
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(face_rect.getX(), face_rect.getY(), face_rect.getWidth(), face_rect.getHeight());
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", faceimg);
		iis.close();
		in.close();
		return faceimg;
	}
}
