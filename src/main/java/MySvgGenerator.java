
import entities.Vierhoek;

import org.jfree.graphics2d.svg.SVGGraphics2D;



import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.io.*;
import java.util.ArrayList;


public class MySvgGenerator {

    public static Color[] colors = {Color.RED, Color.GREEN,Color.BLUE};
    static boolean mirror=true;


    public static void makeSvg(ArrayList<Vierhoek> vierhoeken, int maxX, int maxY) throws IOException {


        // Create an instance of the SVG Generator.
        SVGGraphics2D svgGraphics2D = new SVGGraphics2D(0,0);
        for(int i=0;i<vierhoeken.size();i++){
            Vierhoek vierhoek=vierhoeken.get(i);
            Path2D path=new Path2D.Float();
            path.moveTo(vierhoek.coordinaten.get(0).x,vierhoek.coordinaten.get(0).y);

            for(int j=1;j<vierhoek.coordinaten.size();j++){
                path.lineTo(vierhoek.coordinaten.get(j).x,vierhoek.coordinaten.get(j).y);
            }
            path.closePath();
            svgGraphics2D.setColor(colors[i]);
            if(mirror){
                AffineTransform flip=AffineTransform.getScaleInstance(-1,1);
                flip.translate(-maxX,0);
                path.transform(flip);
            }

            svgGraphics2D.fill(path);
        }

        String viewBox=String.format("viewBox=\"0 0 %s %s\"",maxX,maxY);
        String result=svgGraphics2D.getSVGElement();
        result=result.replace("width=\"0\" height=\"0\"",viewBox);
        File file = new File("mysvg.svg");
        // creates the file
        file.createNewFile();
        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);

        // Writes the content to the file
        writer.write(result);
        writer.flush();
        writer.close();



    }
}
