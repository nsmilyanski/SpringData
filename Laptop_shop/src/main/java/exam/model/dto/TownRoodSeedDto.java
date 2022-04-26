package exam.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownRoodSeedDto {

    @XmlElement(name = "town")
private Set<TownSeedDto> townSeedDtos;

    public Set<TownSeedDto> getTownSeedDtos() {
        return townSeedDtos;
    }

    public void setTownSeedDtos(Set<TownSeedDto> townSeedDtos) {
        this.townSeedDtos = townSeedDtos;
    }
}
