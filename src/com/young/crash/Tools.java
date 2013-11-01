package com.young.crash;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public class Tools {
	public static void writeImageToFile(String imgFile, BufferedImage bi) {
		Iterator<ImageWriter> writers = ImageIO
				.getImageWritersByFormatName(imgFile.substring(imgFile
						.lastIndexOf('.') + 1));
		ImageWriter writer = (ImageWriter) writers.next();
		File f = new File(imgFile);
		ImageOutputStream ios;

		try {
			ios = ImageIO.createImageOutputStream(f);
			writer.setOutput(ios);
			writer.write(bi);
			ios.close();
		} catch (Exception e) {
		}
	}

	public static int pixelConvert(int pixel) {
		int result = 0;

		int r = (pixel >> 16) & 0xff;
		int g = (pixel >> 8) & 0xff;
		int b = (pixel) & 0xff;

		result = 0xff000000;

		int tmp = r * r + g * g + b * b;
		if (tmp > 3 * 128 * 128) {
			result += 0x00ffffff;			
		}

		return result;
	}

	public static BufferedImage getImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
		}

		return image;
	}

	public static BufferedImage getSingleCode(BufferedImage image) {
		return image.getSubimage(6, 5, 36, 12);
	}

	public static BufferedImage[] getCheckCodes(BufferedImage image) {
		BufferedImage checkCode[] = new BufferedImage[4];
		int height = image.getHeight();
		int width = image.getWidth();
		int x = 0 * (width / checkCode.length);
		int y = 0;
		int w = width / checkCode.length;
		int h = height;
		checkCode[0] = image.getSubimage(x, y, w, h);
		checkCode[1] = image.getSubimage(1 * (width / checkCode.length), 0,
				width / checkCode.length, height);
		checkCode[2] = image.getSubimage(2 * (width / checkCode.length), 0,
				width / checkCode.length, height);
		checkCode[3] = image.getSubimage(3 * (width / checkCode.length), 0,
				width / checkCode.length, height);
		return checkCode;
	}
}
