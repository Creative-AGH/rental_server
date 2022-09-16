package pl.creative.rental_server.core.features.moderator.item.history;

public class ItemDetailsHistory {
    private final String itemId;
    private final String itemDescription;
    private final String itemStatusOfItem;
    private final String itemPlaceId;
    private final String itemPlaceName;
    private final String itemPlaceDescription;
    private final String itemCategoryIds;
    private final String itemCategoryNames;

    public ItemDetailsHistory(String itemId, String itemDescription, String itemStatusOfItem, String itemPlaceId, String itemPlaceName,
                              String itemPlaceDescription, String itemCategoryIds, String itemCategoryNames) {
        this.itemId = itemId;
        this.itemDescription = itemDescription;
        this.itemStatusOfItem = itemStatusOfItem;
        this.itemPlaceId = itemPlaceId;
        this.itemPlaceName = itemPlaceName;
        this.itemPlaceDescription = itemPlaceDescription;
        this.itemCategoryIds = itemCategoryIds;
        this.itemCategoryNames = itemCategoryNames;
    }

    @Override
    public String toString() {
        return itemId + ';' +
                itemDescription + ';' +
                itemStatusOfItem + ';' +
                itemPlaceId + ';' +
                itemPlaceName + ';' +
                itemPlaceDescription + ';' +
                itemCategoryIds + ';' +
                itemCategoryNames + ';';
    }
}
