package com.young.crash;

import java.awt.image.BufferedImage;

public class CrashCode {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			BufferedImage image = Tools
					.getImage("checkCode/code_" + i + ".jpg");
			Filter.dotFilter(image);
			Filter.blackAndWhiteFilter(image);
			BufferedImage img = Tools.getSingleCode(image);
			System.out.print("code_" + i + ".jpg == ");
			compare(img);
			System.out.println("");
		}
	}

	public static void compare(BufferedImage image) {
		BufferedImage checkCode[] = Tools.getCheckCodes(image);
		for (int t = 0; t < 4; t++) {
			int[] result = new int[10];
			boolean ckFlg = false;
			int num = -1;
			for (int i = 0; i < 10; i++) {
				num = -1;
				ckFlg = true;
				BufferedImage testImage = Tools.getImage("model" + "/" + i
						+ ".bmp");
				if (testImage == null) {
					continue;
				}

				for (int y = 0; y < checkCode[t].getHeight(); ++y) {
					for (int x = 0; x < checkCode[t].getWidth(); ++x) {
						int expRGB = Tools.pixelConvert(checkCode[t].getRGB(x,
								y));
						int cmpRGB = Tools.pixelConvert(testImage.getRGB(x, y));
						if (expRGB == cmpRGB) {
							result[i]++;
						}
					}
				}

				if (result[i] > 90) {
					ckFlg = true;
					num = i;
					break;
				}
			}
			if (ckFlg) {
				System.out.print(num);
				ckFlg = false;
			} else {
				ckFlg = false;
				System.out.print("x");
				Tools.writeImageToFile("E:/studyImg-" + t + ".bmp",
						checkCode[t]);
			}
		}
	}
}
