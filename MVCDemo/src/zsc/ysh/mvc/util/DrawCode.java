package zsc.ysh.mvc.util;
/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��7��17�� ����8:49:30 
* ��˵�� :�����ר��������֤��
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawCode {
	private int width;
	private int height;
	private int num;  //��֤��ĳ���
	private String code;
	private static final Random random = new Random();
	
	//ʹ�õ���ģʽ
	private static DrawCode drCode;
	private DrawCode() {
		code = "0123456789ABCDGH";
		num = 4;
	}
	public static DrawCode getInstance() {
		if(drCode == null) {
			drCode = new DrawCode();
		}
		return drCode;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public static DrawCode getDrCode() {
		return drCode;
	}
	public static void setDrCode(DrawCode drCode) {
		DrawCode.drCode = drCode;
	}
	public static Random getRandom() {
		return random;
	}
	
	//��ȡ�ַ����������
	public String generateDrawCode() {
		StringBuffer cc = new StringBuffer();
		for(int i = 0;i < num;i++) {
			cc.append(code.charAt(random.nextInt(code.length())));
		}
		return cc.toString();
	}
	
	public BufferedImage generateCheckImg(String checkCode) {
		//����һ��ͼƬ����
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//��ȡͼƬ����Ļ���
		Graphics2D graphic = img.createGraphics();
		//ѡ�񱳾�����ɫ
		graphic.setColor(Color.WHITE);
		graphic.fillRect(0, 0, width, height);
		//�����ʻ��ɺ�ɫ���߿�
		graphic.setColor(Color.BLACK);
		graphic.drawRect(0, 0, width - 1, height - 1);
		Font font = new Font("����",Font.BOLD + Font.ITALIC,(int)(height * 0.8));
		graphic.setFont(font);
		for(int i = 0;i < num;i++) {
			graphic.setColor(new Color(random.nextInt(155),random.nextInt(255),random.nextInt(255)));
			graphic.drawString(String.valueOf(checkCode.charAt(i)), i * (width / num) + 4, (int)(height * 0.8));
		}
		//��һЩ��
		for(int i = 0;i < (width + height);i++) {
			graphic.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			graphic.drawOval(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		
		//����
		for(int i = 0;i < 2;i++) {
			graphic.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			graphic.drawLine(0, random.nextInt(height), width, random.nextInt(height));
		}
		return img;
	}

}
