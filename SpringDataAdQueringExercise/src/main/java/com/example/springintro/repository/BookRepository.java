package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    @Query("select b.title from Book b where b.ageRestriction = :ageRestriction")
    List<String> findByAgeRestriction(AgeRestriction ageRestriction);

    @Query("select b.title from Book b where b.editionType = :editionType " +
            "and b.copies < :copies")
    List<String> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal upperBound);


    List<Book>  findByReleaseDateBeforeOrReleaseDateAfter(LocalDate dateBefore, LocalDate dateAfter);

    List<Book> findByReleaseDateBefore(LocalDate releaseDate);


    List<Book> findByTitleContaining(String search);

    List<Book> findByAuthorLastNameStartingWith(String search);

    @Query("select count(b) from Book b where  length(b.title) > :length")
    int countBooksWithTitleLongerThan(int length);

    List<Book> findAllByTitle(String title);

    @Modifying
    @Transactional
    @Query("update Book b set b.copies = b.copies + :amount " +
            "where b.releaseDate > :after")
    int addCopiesToBooksAfter(LocalDate after, int amount);
}
