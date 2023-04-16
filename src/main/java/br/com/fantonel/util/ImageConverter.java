package br.com.fantonel.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import br.com.fantonel.excepts.LojaVirtualExceptions;

public class ImageConverter {

	public ImageConverter() {
	}
	
	public static String toPngBase64Converter(final String imageCode) throws LojaVirtualExceptions{
		String base64Image = !imageCode.contains("data:image") ? imageCode : imageCode.split(",")[1];
		try {
		
			byte[] imageBytes =  DatatypeConverter.parseBase64Binary(base64Image);		
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));		
			if (bufferedImage != null) {				
				int type    = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
				int largura = Integer.parseInt("800");
				int altura  = Integer.parseInt("600");
				
				BufferedImage resizedImage = new BufferedImage(largura, altura, type);
				Graphics2D g = resizedImage.createGraphics();
				g.drawImage(bufferedImage, 0, 0, largura, altura, null);
				g.dispose();
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();			
				ImageIO.write(resizedImage, "png", baos);
				
				bufferedImage.flush();
				resizedImage.flush();
				baos.flush();
				baos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return base64Image;
	}
}