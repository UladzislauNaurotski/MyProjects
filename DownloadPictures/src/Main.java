import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) {
        try {
            String directory = "images/";
            Document doc = Jsoup.connect("https://lenta.ru").get();
            Elements elements = doc.select("img");
            for (Element element : elements) {
                String NameElement = element.attr("abs:src");
                Pattern pattern = Pattern.compile("(:?[^\\/])(\\w)+(:?.jpg|jpeg)");
                Matcher matcher = pattern.matcher(NameElement);
                String namePicture = "";
                while (matcher.find()) {
                    namePicture = NameElement.substring(matcher.start(), matcher.end());
                }
                if (NameElement.contains("jpg")) {
                    File file = new File(directory + namePicture );
                    URL url = new URL(NameElement);
                    BufferedImage bufferedImage = ImageIO.read(url);
                    ImageIO.write(bufferedImage, "JPG", file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
