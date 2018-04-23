package by.ilka.TestTaskForDevadminCrawler.util;

import by.ilka.TestTaskForDevadminCrawler.exception.UtilException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Here could be your advertisement +375 29 3880490
 */
@Service
public class ImageUtil {
    public byte[] convertBufferedImageToByte(BufferedImage bufferedImage) throws UtilException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byte[] imageInByte = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return imageInByte;
        } catch (IOException e) {
            throw new UtilException(e);
        }
    }
}
