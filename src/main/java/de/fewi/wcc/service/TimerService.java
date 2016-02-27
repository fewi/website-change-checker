package de.fewi.wcc.service;

import de.fewi.wcc.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TimerService {
    private static final Logger logger = LoggerFactory.getLogger(TimerService.class);

    @Autowired
    HtmlService htmlService;

    @Autowired
    MailService mailService;

    @Autowired
    FileUtil fileUtil;

    @Value("${filename}")
    String filename;

    @Scheduled(fixedRate = 600000, initialDelay = 10000)
    public void timerTick(){
        if (!htmlService.isSearchString() && !fileUtil.fileExists(filename)) {
            logger.info("Search string not found on website!");
            boolean mailSent = mailService.sendMail();
            if (mailSent)
                fileUtil.createFile(filename);
        }
    }
}
