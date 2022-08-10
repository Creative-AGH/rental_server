package pl.creative.rental_server.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.creative.rental_server.entities.Image;
import pl.creative.rental_server.entities.Item;
import pl.creative.rental_server.repository.ImageRepository;
import pl.creative.rental_server.repository.ItemRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final FileUploader fileUploader;
    private final RandomIdHandler randomIdHandler;
    private final ImageRepository imageRepository;
    private final ItemRepository itemRepository;

    private static String buildMinioLinkForItemImage(String itemId, String imageId) {

        return new StringBuilder()
                .append("/Item_")
                .append(itemId)
                .append("/Image_")
                .append(imageId)
                .append(".jpg").toString();
    }

    @Transactional
    public void addImages(String itemId, List<String> images) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if (itemOptional.isPresent())
            for (String image : images) {
                String uniqueUrlId = randomIdHandler.generateUniqueIdFromTable(imageRepository);
                String urlToItemInMinio = ImageService.buildMinioLinkForItemImage(itemId, uniqueUrlId);
                Optional<Image> urlToItem = fileUploader.uploadFile(image, urlToItemInMinio, itemOptional.get(), uniqueUrlId);


                urlToItem.ifPresentOrElse((toItem) -> {
                            imageRepository.save(toItem);
                            itemOptional.get().getImages().add(toItem);
                        },
                        () -> log.error("Unfortunately save image with Item id {} and image id {} was not finished successfully", itemId, uniqueUrlId));
            }

    }
}
