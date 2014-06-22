package org.battlehack.gro.tos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 21/06/14.
 */
@XmlRootElement
public class Status {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
