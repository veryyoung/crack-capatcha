package com.young.crash;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cutter {
	public static void main(String[] args) throws IOException {

		for (int k = 0; k < 100; k++) {
			BufferedImage image = null;
			BufferedImage checkCode[] = new BufferedImage[4];

			try {
				image = ImageIO.read(new File("checkCode/code_" + k + ".jpg"));
			} catch (IOException e) {
				System.out.println("can't open checkCode");
			}
			Filter.dotFilter(image);
			Filter.blackAndWhiteFilter(image);

			BufferedImage img = Tools.getSingleCode(image);
			checkCode = Tools.getCheckCodes(img);

			for (int i = 0; i < checkCode.length; i++) {
				try {
					ImageIO.write(checkCode[i], "BMP", new File("check"
							+ (i + 1) + "\\test" + k + ".bmp"));
				} catch (IOException e) {
					System.out.println("can't open checkCode");
				}
			}
		}
	}
}
