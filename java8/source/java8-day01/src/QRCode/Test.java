package QRCode;

public class Test {
	public static void main(String[] args)  throws Exception{

		String imgPath = "src/123.png";
		String content =  "http://www.baidu.com";

		QRCodeUtil qrUtil = new QRCodeUtil();
		qrUtil.encoderQRCode(content, imgPath, "png", 17);
		
//		   TwoDimensionCode handler = new TwoDimensionCode();  
//		   handler.encoderQRCode(content, imgPath, "png", 7);
		
		
//		String imgContent = qrUtil.decoderQRCode(imgPath) ;
//		System.out.println(imgContent);
		   
		
	}
}
