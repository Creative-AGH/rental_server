package pl.creative.rental_server.handlers;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Image;
import pl.creative.rental_server.entities.Item;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileUploader {
    @Value("${minio.username}")
    private String login;
    @Value("${minio.password}")
    private String password;
    @Value("${minio.url}")
    private String minioURL;
    @Value("${minio.bucketName}")
    private String bucketName;

    private MinioClient minioClient;


    @PostConstruct
    private void createMinioInstanceAndClient() {
        try {
            minioClient = MinioClient.builder().endpoint(minioURL)
                    .credentials(login, password).build();

            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            } else {
                log.info("There was not necessary to create {} bucket in MinIO", bucketName);
            }

        } catch (MinioException e) {
            log.error("Sth went wrong");
            log.error("HTTP trace: {} ", e.httpTrace());
            log.info(bucketName);
            log.info(minioURL);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            log.info(bucketName);
            log.info(minioURL);
            log.error(e.getMessage());
            log.error("Sth went wrong with initialize MinIO connection ");
        }

    }

    public void uploadFile(String stringToDecode) {
//https://pastebin.com/HCX3cASb
        byte[] bytesFromEncodedFile = stringToDecode.getBytes();
        byte[] decodeBase64 = Base64.decodeBase64(bytesFromEncodedFile);
        File fileFromRest = new File("REST.log");
        try (
                FileOutputStream restOutput = new FileOutputStream(fileFromRest);

        ) {

            restOutput.write(decodeBase64);
            minioClient.uploadObject(
                    UploadObjectArgs
                            .builder()
                            .bucket(bucketName)
                            .object("/testowyKatalog/test.log")//TO TRZEBA ZAPAMIETAC !!!
                            .filename("REST.log")
                            .build());
            log.info("Success!");
        } catch (MinioException e) {
            log.error("Sth went wrong");
            log.error("HTTP trace: {} ", e.httpTrace());
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            log.error(e.getMessage());
            log.error("Sth went wrong");
        } finally {
            fileFromRest.delete();
        }
    }

    public Optional<Image> uploadFile(String stringToDecode, String urlToFile, Item item, String randomImageId) {
//https://pastebin.com/HCX3cASb
        byte[] bytesFromEncodedFile = stringToDecode.getBytes();
        byte[] decodeBase64 = Base64.decodeBase64(bytesFromEncodedFile);
        File fileFromRest = new File(urlToFile.split("/")[urlToFile.split("/").length - 1]);
        try (
                FileOutputStream restOutput = new FileOutputStream(fileFromRest)
        ) {
            restOutput.write(decodeBase64);
            minioClient.uploadObject(
                    UploadObjectArgs
                            .builder()
                            .bucket(bucketName)
                            .object(urlToFile)
                            .filename(fileFromRest.getName())
                            .build());

            log.info("Success!");
            Image image = new Image(randomImageId, item, urlToFile);

            return Optional.of(image);
        } catch (MinioException e) {

            log.error("HTTP trace: {} ", e.httpTrace());
            log.error("Sth went wrong with sending image to MinIO");
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            log.error(e.getMessage());
            log.error("Sth went wrong with sending image to MinIO");
        } finally {
            fileFromRest.delete();
        }
        return Optional.empty();
    }
}
