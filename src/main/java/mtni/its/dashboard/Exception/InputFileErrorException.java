package mtni.its.dashboard.Exception;

import org.springframework.web.multipart.MultipartFile;

public class InputFileErrorException extends RuntimeException {
    public InputFileErrorException(MultipartFile file) {
        super("Could not open file to read " + file.getOriginalFilename());
    }
}
