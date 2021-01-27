package service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * @author 玉竹
 */
public class PictureIn {

	/**
	 *
	 * @param path 图片路径
	 * @return  修改好尺寸的图片
	 */
	 public ImageIcon drawImage(String path) {
	     //通过给定的路径创建一个icon对象
		 ImageIcon imageIcon = new ImageIcon(path);
	     //设置imageIcon对象内的image属性
	     imageIcon.setImage(imageIcon.getImage().getScaledInstance(280, 230,Image.SCALE_DEFAULT));
	     //将设置好的imageIcon对象返回
	     return imageIcon;
	 }
}
