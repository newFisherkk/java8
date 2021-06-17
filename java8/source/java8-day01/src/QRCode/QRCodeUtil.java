package QRCode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;


public class QRCodeUtil {

	public void encoderQRCode(String content,String imgPath,String imgType,int size)  throws Exception{

		BufferedImage bufImg =   qRcodeCommon(content,imgType,size);
		
		File file = new File(imgPath);

		ImageIO.write(bufImg, imgType, file) ;
	}

	public  BufferedImage qRcodeCommon(String content ,String imgType,int size)  throws Exception{
		BufferedImage bufImg = null ;
		Qrcode qrCodeHandler = new Qrcode();
		qrCodeHandler.setQrcodeErrorCorrect('M');
		qrCodeHandler.setQrcodeEncodeMode('B');
		qrCodeHandler.setQrcodeVersion(size);
		
		byte[] contentBytes = content.getBytes("UTF-8") ;//"Hello world" -> byte[]"Hello world"
		//  -->boolean[][]
		boolean[][] codeOut = qrCodeHandler.calQrcode(contentBytes) ;
		
		int imgSize =  67 + 12*(size -1) ;
		
		bufImg = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB );//red green blue
		
		Graphics2D gs = bufImg.createGraphics() ;
		
		gs.setBackground(Color.WHITE);
		gs.clearRect( 0,0, imgSize,imgSize);
		gs.setColor(Color.BLACK);
		int pixoff = 2 ;
		
		for(int j=0;j<codeOut.length;j++) {
			for(int i=0;i<codeOut.length;i++) {
				if(codeOut[j][i]) {
					gs.fillRect(j*3+pixoff , i*3+pixoff, 3, 3);
				}
			}
		}
		Image logo =  ImageIO.read(new File("src/logo.png")  ) ;
		int maxHeight = bufImg.getHeight() ;
		int maxWdith = bufImg.getWidth() ;
		
		gs.drawImage(logo,imgSize/5*2,imgSize/5*2, maxWdith/5,maxHeight/5 ,null) ;
		
		gs.dispose();
		bufImg.flush();
		return bufImg ;
	}
	
	
//	public String decoderQRCode(String imgPath) throws Exception{
//
//		BufferedImage bufImg =  ImageIO.read( new File(imgPath) ) ;
//		QRCodeDecoder decoder = new QRCodeDecoder() ;
//
//		TwoDimensionCodeImage tdcImage = new TwoDimensionCodeImage(bufImg);
//		byte[] bs = decoder.decode(tdcImage) ;	//bufImg
//		//byte[] -->String
//		String content    = new String(bs,"UTF-8");
//		return content;
//
//	}
	
	
}
