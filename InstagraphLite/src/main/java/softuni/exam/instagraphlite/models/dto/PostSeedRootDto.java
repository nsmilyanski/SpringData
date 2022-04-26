package softuni.exam.instagraphlite.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedRootDto {

    @XmlElement(name = "post")
    Set<PostSeedDto> postSeedDtos;

    public Set<PostSeedDto> getPostSeedDtos() {
        return postSeedDtos;
    }

    public void setPostSeedDtos(Set<PostSeedDto> postSeedDtos) {
        this.postSeedDtos = postSeedDtos;
    }
}
