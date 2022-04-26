package softuni.exam.instagraphlite.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PictureSeedDto {

    @Expose
    @NotBlank
    private String path;


    @Expose
    @Min(500)
    @Max(60000)
    @NotNull
    private double size;

    public PictureSeedDto() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
