package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {

    _12_increaseTeCopiesOffAllBooks();

    }

    private void _12_increaseTeCopiesOffAllBooks(){

        String date = "12 Oct 2005";

        int amount = 100;
        bookService.addCopiesYoBookAfter(date, amount);
    }

    private void _11_reducedBook(){
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        bookService.findAllBooksWhereTitlesIs(title)
                .forEach(b -> System.out.printf("%s %s %s %s%n", b.getTitle(),b.getEditionType()
                        ,b.getAgeRestriction(), b.getPrice()));
    }

    private void _10_findAllBooksCopiesByAuthor(){
        authorService.getAllBooksCopiesByAuthor()
                .forEach(a -> System.out.printf("%s %s - %d%n", a.getFirstName(),
                        a.getLastName(), a.getTotalCopies()));
    }

    private void _09_findCountOfBooks(){
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        int count = bookService.findCountOfBooksWithLengthLongerThan(length);

        System.out.println(count);
    }
    private void _08_printAllBookTitlesWhereAuthorsNameStartWith(){
        Scanner scanner = new Scanner(System.in);

        String authorName = scanner.nextLine();
        bookService.getAllBooksTitleWhereAuthorNameStart(authorName)
                .forEach(System.out::println);
    }

    private void _07_printAllBooksTitleWithContains(){
        Scanner scanner = new Scanner(System.in);

        String word = scanner.nextLine();

        bookService.findAllBooksContains(word)
                .forEach(System.out::println);
    }

    private void _06_findAllAuthorsNamesEndingWith(){
        Scanner scanner = new Scanner(System.in);

        String endingWord = scanner.nextLine();

        authorService.findAllNamesEndingWith(endingWord)
                .forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));

    }

    private  void _05_findAllBooksWithIsReleaseBefore(){
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, df);

        bookService.findAllBooksWithDateBefore(localDate)
                .forEach( b -> System.out.printf("%s %s %s%n", b.getTitle(), b.getEditionType(),
                        b.getPrice()));
    }
    private void _04_printAllBooksWhereYearIsNot(){
        LocalDate dateBefore = LocalDate.of(1998, 1, 1);
        LocalDate dateAfter = LocalDate.of(1998, 12, 31);
       List<Book> titles = bookService.getAllBooksTitleWhereYearIsNot(dateBefore, dateAfter);

       titles.forEach(b -> System.out.println(b.getTitle()));
    }
    private void _03_printTitleWherePriceIsNotBetween5and40(){
        BigDecimal lowerBound = new BigDecimal("5");
        BigDecimal upperBound = new BigDecimal("40");

        bookService.findAllBooksWherePriceIsNotBetween5and40(lowerBound, upperBound)
                .forEach(b -> System.out.printf("%s %s%n", b.getTitle(), b.getPrice()));
    }

    private void printTheTitleOfAllBooksWithEditionTypeGold(){
        EditionType editionType = EditionType.GOLD;
        bookService.findAllBooksWithEditionTypeGoldAndLessThan5000copies(editionType, 5000)
                .forEach(System.out::println);


    }
    private void printTheTitleOfAllBooks(){
        AgeRestriction ageRestriction = AgeRestriction.MINOR;
        List<String> allBooks = bookService.findAllBooksTitleByAgeRestriction(ageRestriction);

        allBooks.forEach(System.out::println);

    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
