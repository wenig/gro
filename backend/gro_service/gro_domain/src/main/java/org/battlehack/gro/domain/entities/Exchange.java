package org.battlehack.gro.domain.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 22/06/14.
 */
@XmlRootElement
@Entity
@Table
public class Exchange extends AbstractPersistable<Long> {
    @OneToOne
    private Item sourceItem;
    @OneToOne
    private Item destinationItem;

    private int diffAmount;
    private String comment;

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

    public int getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(int diffAmount) {
        this.diffAmount = diffAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
}
