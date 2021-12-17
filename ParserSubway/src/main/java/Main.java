import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ToString
public class Main {
    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
            List lines = getListLines(document);
            List stations = getListStations(document);
            Map metro = getMetro(lines, stations);
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            String json = gson.toJson(metro);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Station> getListStations(Document document) {
        String lineNumber;
        Elements elementsNameStations = document.select(".js-metro-stations");
        List<Station> stations = new ArrayList<>();
        for (Element elementsNameStation : elementsNameStations) {
            lineNumber = elementsNameStation.attr("data-line");
            Elements elementsObjects = elementsNameStation.select("span.name");
            for (Element elementsObject : elementsObjects) {
                stations.add(new Station(elementsObject.text(), lineNumber));
            }
        }
        return stations;
    }

    public static List<Line> getListLines(Document document) {
        Elements elementsNameLines = document.select(".js-metro-line");
        List<Line> lines = new ArrayList<>();
        for (Element elementsNameLine : elementsNameLines) {
            lines.add(new Line(elementsNameLine.attr("data-line"), elementsNameLine.text()));
        }
        return lines;
    }

    public static Map<String, List<String>> getMetro(List lines, List stations) {
        Map<String, List<String>> metro = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            List<String> stationToLine = new ArrayList<>();
            Line line = (Line) lines.get(i);
            for (int j = 0; j < stations.size(); j++) {
                Station station = (Station) stations.get(j);
                if (line.getNum().equals(station.getLine())) {
                    stationToLine.add(station.getName());
                }
            }
            metro.put(line.getName(), stationToLine);
        }
        return metro;
    }
}
