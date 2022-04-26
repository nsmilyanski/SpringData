package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PictureSeedDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    private static final String PICTURE_PATH = "src/main/resources/files/pictures.json";

    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validationUtils;

    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, ModelMapper modelMapper, ValidatorUtil validationUtils) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
    }


    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(PICTURE_PATH));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();

        PictureSeedDto[] pictureSeedDtos = gson.fromJson(this.readFromFileContent(), PictureSeedDto[].class);

        Arrays.stream(pictureSeedDtos)
                .filter(picture -> {
                    boolean isValid = validationUtils.isValid(picture)
                            && !pathIsExist(picture.getPath());
                    sb.append(isValid ? String.format("Successfully imported Picture, with size %.2f",
                                    picture.getSize()) : "Invalid Picture")
                            .append(System.lineSeparator());

                    return isValid;
                }).map(pictureSeedDto -> modelMapper.map(pictureSeedDto, Picture.class))
                .forEach(pictureRepository::save);

        return sb.toString();
    }

    @Override
    public boolean pathIsExist(String path) {
        return pictureRepository.existsPictureByPath(path);
    }

    @Override
    public String exportPictures() {
        StringBuilder sb = new StringBuilder();
        List<Picture> pictures = pictureRepository.findAllBySizeGreaterThanOrderBySizeAsc(30000);
        pictures.stream()
                .forEach(p -> {
                    sb.append(String.format("%.2f – %s", p.getSize(), p.getPath()))
                            .append(System.lineSeparator());
                });

        return sb.toString();
    }

    @Override
    public Picture findPicture(String profilePicture) {
        return pictureRepository.findPictureByPath(profilePicture);
    }

}
