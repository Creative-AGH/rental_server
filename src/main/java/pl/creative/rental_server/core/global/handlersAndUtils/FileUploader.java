package pl.creative.rental_server.core.global.handlersAndUtils;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.core.global.exception.NotSentException;
import pl.creative.rental_server.db.entities.Image;
import pl.creative.rental_server.db.entities.Item;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

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

    public void uploadImage(String stringToDecode) {
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

    public void removeImages(List<String> names) {
        List<DeleteObject> imagesToDelete = names.stream().map(DeleteObject::new).toList();

        Iterable<Result<DeleteError>> errors = minioClient.removeObjects(RemoveObjectsArgs.builder().bucket(bucketName).objects(imagesToDelete).build());
        StreamSupport.stream(errors.spliterator(), false)
                .map(this::unpackDeleteError)
                .forEach(e -> log.error("Error in deleting object " + e.objectName() + "; " + e.message()));

    }

    private DeleteError unpackDeleteError(Result<DeleteError> result) {
        try {
            return result.get();
        } catch (ServerException | InsufficientDataException | ErrorResponseException | IOException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            log.error(e.getMessage());
        }
        throw new RuntimeException("MINIO NOT FOUND EXCEPTION");
    }

    public void uploadBuildingPlan(String stringToDecode, String linkToBuildingPlan) {
        byte[] bytesFromEncodedFile = stringToDecode.getBytes();
        byte[] decodeBase64 = Base64.decodeBase64(bytesFromEncodedFile);
        File fileFromRest = new File(linkToBuildingPlan.split("/")[linkToBuildingPlan.split("/").length - 1]);
        try (
                FileOutputStream restOutput = new FileOutputStream(fileFromRest)
        ) {
            restOutput.write(decodeBase64);
            minioClient.uploadObject(
                    UploadObjectArgs
                            .builder()
                            .bucket(bucketName)
                            .object(linkToBuildingPlan)
                            .filename(fileFromRest.getName())
                            .build());

        } catch (MinioException e) {

            log.error("HTTP trace: {} ", e.httpTrace());
            log.error("Sth went wrong with sending image to MinIO");
            throw new NotSentException("Unfortunately the file was not sent");
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            log.error(e.getMessage());
            log.error("Sth went wrong with sending image to MinIO");
            throw new NotSentException("Unfortunately the file was not sent");
        } finally {
            fileFromRest.delete();
        }

    }

    public Optional<Image> uploadImage(String stringToDecode, String urlToFile, Item item, String randomImageId) {
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
            Image image = new Image(randomImageId, item.getId(), urlToFile);

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
