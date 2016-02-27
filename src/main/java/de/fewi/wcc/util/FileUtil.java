package de.fewi.wcc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public void createFile(String filename){
        try {
            File file = new File(filename);
            file.createNewFile();
        } catch (IOException e) {
            logger.error("Can't create file",e);
        }
    }

    public boolean fileExists(String filename){
        File file = new File(filename);
        if (file.exists())
            return true;
        else
            return false;
    }
}
