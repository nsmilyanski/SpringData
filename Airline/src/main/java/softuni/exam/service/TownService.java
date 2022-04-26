package softuni.exam.service;


import softuni.exam.models.entities.Town;

import java.io.IOException;

public interface TownService {

    boolean areImported();

    String readTownsFileContent() throws IOException;
	
	String importTowns() throws IOException;

    Town findTownByName(String town);
}
