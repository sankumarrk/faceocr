package com.signature;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

import javax.imageio.ImageIO;
 
public class SignaturePosition {
               
               
                   public static void pixel() {
                      try {
                                  BufferedImage image;
                                   int width;
                                   int height;
                                   
                         File input = new File("C:/Project/pic/99.jpg");
                         image = ImageIO.read(input);
                         width = image.getWidth();
                         height = image.getHeight();
                         ColorUtils util = new ColorUtils();
                         int innercount=0;
                        
                         String ss="";
                        
                         int count = 0;
                         boolean flag=false;
                         for(int i=height-1; i>0; i--){
                        
                            for(int j=width-1; j>0; j--){
                           
                               count++;
                               Color c = new Color(image.getRGB(j, i));
                              // System.out.println("S.No: " + count + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());
                              
                               //String s = getNameReflection(c);
                              
                               String s = util.getColorNameFromColor(c);
                               
                               if(i > 892 && i < 888){
                            	   System.out.print("Pixel position: x: " + j + " y:"+ i +"--" +s +" ");
                               }
                                                    
                               
                               if(i < 705) {
//                           if(!"No matched color name.".equals(s) && !"Gainsboro".equals(s) && !"LightGray".equals(s) && !"Silver".equals(s) && !"WhiteSmoke".equals(s)
//                                               && !"Lavender".equals(s)){
//                                   
                                  if(s.equals("DarkGray")){
                                   innercount++;
                                   flag=true;
                                   ss= ss+"Pixel position: x: " + j + " y:"+ i +"--" +s +" ";
                                               //System.out.print("Pixel position: x: " + j + " y:"+ i +"--" +s +" ");
//                                           if(innercount > 50) {
//                                               System.out.println("i:" + i);
//                                               break;
//                                           }
                                  // break;
                               }
                               }
                                   
                            }
//                        if(innercount > 50){
//                            break;
//                        }
//                        else{
//                            innercount =0;
//                        }
                            if(flag){
                                ss= ss+"\n";
                                          // System.out.println();
                                            flag= false;
                            }
                          
                         }
                        
                         PrintWriter out = new PrintWriter(new File("C:/Project/pic/99.txt"));
                         out.write(ss);
                        
                      } catch (Exception e) { e.printStackTrace();}
                   }
 
                   public static String getNameReflection(Color colorParam) {
                        try {
                            //first read all fields in array
                            Field[] field = Class.forName("java.awt.Color").getDeclaredFields();
                           for (Field f : field) {
                                String colorName = f.getName();
                                Class<?> t = f.getType();
                                if (t == java.awt.Color.class) {
                                    Color defined = (Color) f.get(null);
                                    if (defined.equals(colorParam)) {
                                        System.out.println(colorName);
                                        return colorName.toUpperCase();
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error... " + e.toString());
                        }
                        return "NO_MATCH";
                    }
                  
                   
 
   public static void main(String[] args) throws IOException {
                  
                   
                   pixel();
 
     // BufferedImage hugeImage = ImageIO.read(new FileInputStream("C:\\Data\\photoproject\\pic\\2.jpg"));
 
//      System.out.println("Testing convertTo2DUsingGetRGB:");
//      //for (int i = 0; i < 10; i++) {
//         long startTime = System.nanoTime();
//         int[][] result = convertTo2DUsingGetRGB(hugeImage);        
//         long endTime = System.nanoTime();
//         //System.out.println(String.format("%-2d: %s", (i + 1), toString(endTime - startTime)));
//      //}
//
//      System.out.println("");
//
//      System.out.println("Testing convertTo2DWithoutUsingGetRGB:");
//     // for (int i = 0; i < 10; i++) {
//         startTime = System.nanoTime();
//         result = convertTo2DWithoutUsingGetRGB(hugeImage);
//         endTime = System.nanoTime();
//        // System.out.println(String.format("%-2d: %s", (i + 1), toString(endTime - startTime)));
//      //}
   }
 
   private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      int[][] result = new int[height][width];
 
      for (int row = 0; row < height; row++) {
         for (int col = 0; col < width; col++) {
            result[row][col] = image.getRGB(col, row);
           // image.get
         }
      }
 
      return result;
   }
 
   private static int[][] convertTo2DWithoutUsingGetRGB(BufferedImage image) {
 
      final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
      final int width = image.getWidth();
      final int height = image.getHeight();
      final boolean hasAlphaChannel = image.getAlphaRaster() != null;
 
      int[][] result = new int[height][width];
      if (hasAlphaChannel) {
         final int pixelLength = 4;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
            argb += ((int) pixels[pixel + 1] & 0xff); // blue
            argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
            result[row][col] = argb;
            col++;
            if (col == width) {
               col = 0;
               row++;
            }
         }
      } else {
         final int pixelLength = 3;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            argb += -16777216; // 255 alpha
            argb += ((int) pixels[pixel] & 0xff); // blue
            argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
            argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
            result[row][col] = argb;
            col++;
            if (col == width) {
               col = 0;
               row++;
            }
         }
      }
 
      return result;
   }
 
   private static String toString(long nanoSecs) {
      int minutes    = (int) (nanoSecs / 60000000000.0);
      int seconds    = (int) (nanoSecs / 1000000000.0)  - (minutes * 60);
      int millisecs  = (int) ( ((nanoSecs / 1000000000.0) - (seconds + minutes * 60)) * 1000);
 
 
      if (minutes == 0 && seconds == 0)
         return millisecs + "ms";
      else if (minutes == 0 && millisecs == 0)
         return seconds + "s";
      else if (seconds == 0 && millisecs == 0)
         return minutes + "min";
      else if (minutes == 0)
         return seconds + "s " + millisecs + "ms";
      else if (seconds == 0)
         return minutes + "min " + millisecs + "ms";
      else if (millisecs == 0)
         return minutes + "min " + seconds + "s";
 
      return minutes + "min " + seconds + "s " + millisecs + "ms";
   }
}
 