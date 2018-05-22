package de.htw.ai.kbe.songServlet;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "song"
})
@XmlRootElement(name = "songs")
public class Songs {

    @XmlElement(required = true)
    protected List<Object> song;

    public List<Object> getSong() {
        if (song == null) {
            song = new ArrayList<Object>();
        }
        return this.song;
    }    
}
