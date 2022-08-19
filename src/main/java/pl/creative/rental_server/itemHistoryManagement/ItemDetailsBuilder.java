package pl.creative.rental_server.itemHistoryManagement;

public class ItemDetailsBuilder {
    private String itemId = "-";
    private String itemDescription = "-";
    private String itemStatusOfItem = "-";
    private String itemDateOfCreation = "-";
    private String itemPlaceId = "-";
    private String itemPlaceName = "-";
    private String itemPlaceDescription = "-";
    private String itemCategoryIds = "-";
    private String itemCategoryNames = "-";

    public ItemDetailsBuilder addItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public ItemDetailsBuilder addItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public ItemDetailsBuilder addItemStatusOfItem(String itemStatusOfItem) {
        this.itemStatusOfItem = itemStatusOfItem;
        return this;
    }

    public ItemDetailsBuilder addItemDateOfCreation(String itemDateOfCreation) {
        this.itemDateOfCreation = itemDateOfCreation;
        return this;
    }

    public ItemDetailsBuilder addItemPlaceId(String itemPlaceId) {
        this.itemPlaceId = itemPlaceId;
        return this;
    }

    public ItemDetailsBuilder addItemPlaceName(String itemPlaceName) {
        this.itemPlaceName = itemPlaceName;
        return this;
    }

    public ItemDetailsBuilder addItemPlaceDescription(String itemPlaceDescription) {
        this.itemPlaceDescription = itemPlaceDescription;
        return this;
    }

    public ItemDetailsBuilder addItemCategoryIds(String itemCategoryIds) {
        this.itemCategoryIds = itemCategoryIds;
        return this;
    }

    public ItemDetailsBuilder addItemCategoryNames(String itemCategoryNames) {
        this.itemCategoryNames = itemCategoryNames;
        return this;
    }

    public ItemDetailsHistory build() {
        return new ItemDetailsHistory(itemId, itemDescription, itemStatusOfItem,
                itemDateOfCreation, itemPlaceId, itemPlaceName,
                itemPlaceDescription, itemCategoryIds, itemCategoryNames);
    }
}
