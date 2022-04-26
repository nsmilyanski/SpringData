package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedRootDto {

    @XmlElement(name = "ticket")
    private Set<TicketSeedDto> ticketSeedDtos;

    public Set<TicketSeedDto> getTicketSeedDtos() {
        return ticketSeedDtos;
    }

    public void setTicketSeedDtos(Set<TicketSeedDto> ticketSeedDtos) {
        this.ticketSeedDtos = ticketSeedDtos;
    }
}
