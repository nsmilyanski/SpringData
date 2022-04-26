package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllBooksTitleByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllBooksWithEditionTypeGoldAndLessThan5000copies(EditionType editionType, int copies);

    List<Book> findAllBooksWherePriceIsNotBetween5and40(BigDecimal lowerBound, BigDecimal upperBound);

    List<Book> getAllBooksTitleWhereYearIsNot(LocalDate dateBefore, LocalDate dateAfter);

    List<Book> findAllBooksWithDateBefore(LocalDate localDate);

    List<String> findAllBooksContains(String word);

    List<String> getAllBooksTitleWhereAuthorNameStart(String authorName);

    int findCountOfBooksWithLengthLongerThan(int length);

    List<Book> findAllBooksWhereTitlesIs(String title);

    int addCopiesYoBookAfter(String date, int amount);
}
