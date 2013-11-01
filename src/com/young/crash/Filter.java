package com.young.crash;

import java.awt.image.BufferedImage;

public class Filter {
	public static void blackAndWhiteFilter(BufferedImage image) {
		if (image == null) {
			return;
		}

		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				image.setRGB(j, i, Tools.pixelConvert(image.getRGB(j, i)));
			}
		}
	}

	public static void dotFilter(BufferedImage image) {
		if (image == null) {
			return;
		}

		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				if (i > 0 && j > 0 && i < (image.getHeight() - 1)
						&& j < (image.getWidth() - 1)) {
					if (image.getRGB(j, i) == 0xff000000) {
						if (image.getRGB(j - 1, i) == 0xffffffff
								&& image.getRGB(j - 1, i - 1) == 0xffffffff
								&& image.getRGB(j, i - 1) == 0xffffffff
								&& image.getRGB(j + 1, i) == 0xffffffff
								&& image.getRGB(j + 1, i + 1) == 0xffffffff
								&& image.getRGB(j, i + 1) == 0xffffffff) {
							image.setRGB(j, i, 0xffffffff);
						}
					}
				}
			}
		}
	}
}
