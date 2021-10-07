package example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;

import example.classloader.SteganographyClassLoader;
import example.encoder.SteganographyFactory;

public class Scene {

    public static void main(String[] args) throws Exception {

        Line line = new Line(7);
        line.put(Gourd.ONE, 6);
        line.put(Gourd.TWO, 3);
        line.put(Gourd.THREE, 1);
        line.put(Gourd.FOUR, 5);
        line.put(Gourd.FIVE, 2);
        line.put(Gourd.SIX, 4);
        line.put(Gourd.SEVEN, 0);

        Geezer theGeezer = Geezer.getTheGeezer();

        String className = args[0];
        String fullName = "example."+className;
        String url = "file:"+fullName+".png";

        SteganographyClassLoader loader = new SteganographyClassLoader(
                new URL(url));

        Class c = loader.loadClass(fullName);
        System.out.println(c.getClassLoader());
        Sorter sorter = (Sorter) c.newInstance();
        theGeezer.setSorter(sorter);

        String log = theGeezer.lineUp(line);

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("result.txt"));
        writer.write(log);
        writer.flush();
        writer.close();

    }

}