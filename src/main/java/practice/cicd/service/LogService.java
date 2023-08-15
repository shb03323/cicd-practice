package practice.cicd.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    private static final Logger logger = LogManager.getLogger(LogService.class);

    public void logging() {
        logger.info("INFO SUCCESS");
        logger.debug("DEBUG SUCCESS");
        logger.error("ERROR SUCCESS");
    }
}
