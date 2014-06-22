package org.battlehack.gro.tos;

import org.battlehack.gro.domain.entities.Item;

/**
 * Created by aakhmerov on 22/06/14.
 */
public class CreateOfferTO {
    private Item sourceItem;
    private Item destinationItem;
    private int diff;

    public Item getSourceItem() {
        return sourceItem;
    }

    public void setSourceItem(Item sourceItem) {
        this.sourceItem = sourceItem;
    }

    public Item getDestinationItem() {
        return destinationItem;
    }

    public void setDestinationItem(Item destinationItem) {
        this.destinationItem = destinationItem;
    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }
}
