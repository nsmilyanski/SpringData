package softuni.exam.instagraphlite.service;

import softuni.exam.instagraphlite.models.entities.User;

import java.io.IOException;

public interface UserService {
    boolean areImported();
    String readFromFileContent() throws IOException;
    String importUsers() throws IOException;

    boolean userNameExist(String username);

    String exportUsersWithTheirPosts();

    User findUserByName(String username);
}
