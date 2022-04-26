package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.PostSeedRootDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidatorUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private static final String POST_PATH = "src/main/resources/files/posts.xml";

    private  final PostRepository postRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validatorUtil;
    private final UserService userService;
    private final PictureService pictureService;

    public PostServiceImpl(PostRepository postRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidatorUtil validatorUtil, UserService userService, PictureService pictureService) {
        this.postRepository = postRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.userService = userService;

        this.pictureService = pictureService;
    }

    @Override
    public boolean areImported() {
        return postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return Files.readString(Path.of(POST_PATH));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {

        StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(POST_PATH, PostSeedRootDto.class)
                .getPostSeedDtos()
                .stream()
                .filter(post -> {
                    boolean isValid = validatorUtil.isValid(post)
                            && userService.userNameExist(post.getUser().getUsername())
                            && pictureService.pathIsExist(post.getPicture().getPath());
                    sb.append(isValid ? String.format("Successfully imported Post, made by %s",
                            post.getUser().getUsername()) : "Invalid Post");
                    return isValid;
                }).map(post -> {
                    Post map = modelMapper.map(post, Post.class);

                    User userByName = userService.findUserByName(post.getUser().getUsername());
                    Picture picture = pictureService.findPicture(post.getPicture().getPath());
                    map.setUser(userByName);
                    map.setPicture(picture);
                    return map;
                })
                .forEach(postRepository::save);

        return sb.toString();
    }
}
