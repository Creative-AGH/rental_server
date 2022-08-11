package pl.creative.rental_server.itemHistoryManagement;


public class ItemBuilder {
    private String itemId ="-";
    private String itemDescription ="-";
    private String itemStatusOfItem ="-";
    private String itemDateOfCreation ="-";
    private String itemPlaceId ="-";
    private String itemPlaceName ="-";
    private String itemPlaceDescription ="-";
//    private String itemCategoriesId;


    public ItemBuilder addItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public ItemBuilder addItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    public ItemBuilder addItemStatusOfItem(String itemStatusOfItem) {
        this.itemStatusOfItem = itemStatusOfItem;
        return this;
    }

    public ItemBuilder addItemDateOfCreation(String itemDateOfCreation) {
        this.itemDateOfCreation = itemDateOfCreation;
        return this;
    }

    public ItemBuilder addItemPlaceId(String itemPlaceId) {
        this.itemPlaceId = itemPlaceId;
        return this;
    }

    public ItemBuilder addItemPlaceName(String itemPlaceName) {
        this.itemPlaceName = itemPlaceName;
        return this;
    }

    public ItemBuilder addItemPlaceDescription(String itemPlaceDescription) {
        this.itemPlaceDescription = itemPlaceDescription;
        return this;
    }

    public ItemDetailsHistory build() {
        return new ItemDetailsHistory(itemId, itemDescription, itemStatusOfItem,
                itemDateOfCreation, itemPlaceId, itemPlaceName,
                itemPlaceDescription);
    }
}
