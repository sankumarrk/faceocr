package com.signature;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
 
import javax.imageio.ImageIO;
 
public class SignatureCrap {
    public static void main(String[] args)
    {
        try
        {
                String s="";
               
//            BufferedImage img = ImageIO.read(new File("C:/Data/photoproject/pic/22.jpg") );
//            BufferedImage gray = new BufferedImage(img.getWidth(), img.getHeight(),
//                    BufferedImage.TYPE_BYTE_BINARY);
////            Graphics2D g = gray.createGraphics();
////            g.drawImage(gray, 0, 0, null);
//           
//            Graphics2D g=gray.createGraphics();
//            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//            g.clearRect(0, 0, img.getWidth(), img.getHeight());
//            g.drawImage(img, 0, 0, img.getWidth(),img.getHeight(), null);
//            g.dispose();
            //ImageIO.write(outputImage,"jpg",destFile);
           
         //   ImageIO.write(gray, "jpg", "C:/Data/photoproject/pic/222.jpg");
           // ImageIO.write(gray, "jpg", "C:/Data/photoproject/pic/222.jpg");
//
//            Graphics2D g = gray.createGraphics();
//            g.drawImage(img, 0, 0, null);
//
//            HashSet<Integer> colors = new HashSet<>();
//            int color = 0;
//            for (int y = 0; y < gray.getHeight(); y++)
//            {
//                for (int x = 0; x < gray.getWidth(); x++)
//                {
//                    color = gray.getRGB(x, y);
//                   System.out.print(color);
//                    s = s+color+" ";
//                    colors.add(color);
//                }
//                System.out.println();
//                s = s+"\n";
//            }
//
//            System.out.println(colors.size() );
//            PrintWriter out = new PrintWriter("C:/Data/photoproject/pic/22.txt");
//            out.write(s);
           
            
           /* set the width and height here */
            BufferedImage inputImage=ImageIO.read(new File("C:/Project/pic/9.jpg"));
            int width=inputImage.getWidth();
            int height=inputImage.getHeight();
            BufferedImage outputImage=new BufferedImage(width, height,
                BufferedImage.TYPE_BYTE_GRAY);
            Graphics2D g=outputImage.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.clearRect(0, 0, width, height);
            g.drawImage(inputImage, 0, 0, width, height, null);
            g.dispose();
            ImageIO.write(outputImage,"jpg",new File("C:/Project/pic/99.jpg"));
       }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    
    
}
 