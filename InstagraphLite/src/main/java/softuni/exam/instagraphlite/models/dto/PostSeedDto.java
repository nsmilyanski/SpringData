package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedDto {

    @XmlElement
    @NotBlank
    @Size(min = 21)
    private String caption;

    @XmlElement
    private UserNameDto user;

    @XmlElement
    private PicturePathDto picture;

    public PostSeedDto() {
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public UserNameDto getUser() {
        return user;
    }

    public void setUser(UserNameDto user) {
        this.user = user;
    }

    public PicturePathDto getPicture() {
        return picture;
    }

    public void setPicture(PicturePathDto picture) {
        this.picture = picture;
    }
}
