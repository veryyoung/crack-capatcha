package com.young.crash;

import java.awt.image.BufferedImage;

public class getModel {

	public static String compare() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			BufferedImage testImage = Tools
					.getImage("model" + "/" + i + ".bmp");
			if (testImage == null) {
				continue;
			}
			for (int x = 0; x < testImage.getWidth(); x++) {
				System.out.print("{");
				for (int y = 0; y < testImage.getHeight() - 1; y++) {
					int cmpRGB = Tools.pixelConvert(testImage.getRGB(x, y));
					System.out.print(cmpRGB + ",");
				}
				System.out.print(Tools.pixelConvert(testImage.getRGB(x, 11)));
				System.out.println("},");
			}

		}
		return "0";
	}
}
