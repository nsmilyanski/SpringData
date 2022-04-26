package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.UserSeedDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidatorUtil;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_PATH = "src/main/resources/files/users.json";

    private final UserRepository userRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final PictureService pictureService;

    public UserServiceImpl(UserRepository userRepository, Gson gson, ModelMapper modelMapper, ValidatorUtil validatorUtil, PictureService pictureService) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.pictureService = pictureService;
    }

    @Override
    public boolean areImported() {
        return userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(USER_PATH));
    }

    @Override
    public String importUsers() throws IOException {

        StringBuilder sb = new StringBuilder();
        UserSeedDto[] userSeedDtos = gson.fromJson(readFromFileContent(), UserSeedDto[].class);

       Arrays.stream(userSeedDtos)
                .filter(user -> {

                    boolean isValid = validatorUtil.isValid(user)
                            && !userNameExist(user.getUsername())
                            && pictureService.pathIsExist(user.getProfilePicture());
                    sb.append(isValid ? String.format("Successfully imported User: %s", user.getUsername())
                            : "Invalid User")
                            .append(System.lineSeparator());

                    return isValid;
                }).map(user -> {
                    User map = modelMapper.map(user, User.class);

                    Picture picture = pictureService.findPicture(user.getProfilePicture());
                    map.setProfilePicture(picture);
                    return map;
                }).forEach(userRepository::save);

        return sb.toString();
    }

    @Override
    public boolean userNameExist(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    @Transactional
    public String exportUsersWithTheirPosts() {
        StringBuilder sb = new StringBuilder();
        List<User> users = userRepository.fidAllUsersOrderByCountPostDescThenByUserIdAcs();
        for (User u : users) {
            sb.append(String.format("""
                    "User: %s
                    Post count: %d            
                    """, u.getUsername(), u.getPosts().size()));
            u.getPosts().stream()
                    .sorted((e1, e2) -> (int)(e1.getPicture().getSize() - e2.getPicture().getSize()))
                    .forEach(p -> {
                        sb.append(String.format("""
                                 ==Post Details:
                    ----Caption: %s
                    ----Picture Size: %.2f
                    …""", p.getCaption(), p.getPicture().getSize()));
                    });

            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public User findUserByName(String username) {
        return userRepository.findUserByUsername(username);
    }


}
