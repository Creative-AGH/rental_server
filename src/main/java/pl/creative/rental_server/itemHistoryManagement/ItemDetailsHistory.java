package pl.creative.rental_server.itemHistoryManagement;

public class ItemDetailsHistory {
    private String itemId;
    private String itemDescription;
    private String itemStatusOfItem;
    private String itemDateOfCreation;
    private String itemPlaceId;
    private String itemPlaceName;
    private String itemPlaceDescription;

    public ItemDetailsHistory(String itemId, String itemDescription, String itemStatusOfItem,
                              String itemDateOfCreation, String itemPlaceId, String itemPlaceName,
                              String itemPlaceDescription) {
        this.itemId = itemId;
        this.itemDescription = itemDescription;
        this.itemStatusOfItem = itemStatusOfItem;
        this.itemDateOfCreation = itemDateOfCreation;
        this.itemPlaceId = itemPlaceId;
        this.itemPlaceName = itemPlaceName;
        this.itemPlaceDescription = itemPlaceDescription;
    }

    @Override
    public String toString() {
        return itemId + ';' +
                itemDescription + ';' +
                itemStatusOfItem + ';' +
                itemDateOfCreation + ';' +
                itemPlaceId + ';' +
                itemPlaceName + ';' +
                itemPlaceDescription + ';';
    }
}
