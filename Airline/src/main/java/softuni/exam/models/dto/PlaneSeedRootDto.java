package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneSeedRootDto {

    @XmlElement(name = "plane")
    private Set<PlaneSeedDto> planeSeedDtos;

    public Set<PlaneSeedDto> getPlaneSeedDtos() {
        return planeSeedDtos;
    }

    public void setPlaneSeedDtos(Set<PlaneSeedDto> planeSeedDtos) {
        this.planeSeedDtos = planeSeedDtos;
    }
}
