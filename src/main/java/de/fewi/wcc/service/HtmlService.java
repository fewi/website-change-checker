package de.fewi.wcc.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.text.html.HTML;
import java.io.IOException;

@Service
public class HtmlService {
    private static final Logger logger = LoggerFactory.getLogger(HtmlService.class);

    @Value("${website.url}")
    String url;

    @Value("${website.searchString}")
    String searchString;

    private String fetchHtml(){
        try {
            Document doc = Jsoup.connect(url).get();

            return doc.body().toString();
        } catch (IOException e) {
            logger.error("Connection to "+url+ " failed.");
        }
        return null;
    }

    public boolean isSearchString(){
        logger.info("Checking "+url+" for '"+searchString+"'");
        String html = fetchHtml();
        if(html == null){
            logger.warn("No HTML received.");
            return true;
        } else if (html != null && html.contains(searchString)){
            return true;
        }

        return false;
    }
}
