package hu.letscode.billing.domain.factory;

import hu.letscode.billing.domain.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Factory to create {@link BillingRequest}'s.
 */
public class BillingRequestFactory {

    private final ItemFactory itemFactory;

    /**
     * Default constructor.
     * @param itemFactory an item factory to create Item elements.
     */
    public BillingRequestFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    /**
     * Creates a new billing request to be sent with the client.
     * @param buyer the buyer
     * @param billableItems the items to be billed
     * @param header the header element
     * @return the created request
     */
    public BillingRequest create(Buyer buyer, List<RawItem> billableItems, Header header) {
        List<Item> items = billableItems.stream().map(itemFactory::create).collect(Collectors.toList());
        BillingRequest billingRequest = new BillingRequest();
        billingRequest.setHeader(header);
        billingRequest.setItems(items);
        billingRequest.setBuyer(buyer);
        return billingRequest;
    }
}
