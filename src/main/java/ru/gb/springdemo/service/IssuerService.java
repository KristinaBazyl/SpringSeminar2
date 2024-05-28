package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IssuerService {

    @Value("${application.max-allowed-books}")
    private int limitBooks;

    // спринг это все заинжектит
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    @Autowired
    public IssuerService(BookRepository bookRepository, ReaderRepository readerRepository, IssueRepository issueRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
        this.issueRepository = issueRepository;
    }

    public Issue saveIssue(IssueRequest request) {
        if (bookRepository.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerRepository.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)

        if (issueRepository.getAllIssueByReaderId(request.getReaderId()).size() >= limitBooks) {
            throw new IllegalArgumentException("пользователь взял уже книгу");
        }

        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        issueRepository.save(issue);
        return issue;
    }

    // показать список всех заявок
    public List<Issue> getIssues() {
        return issueRepository.getIssues();
    }

    // получить информацию о запросе по id
    public Issue getIssueById(Long id) {
        return issueRepository.getIssueById(id);
    }

    // возврат книги
    public Issue returnBooks(Long id) {
        return issueRepository.returnBooks(id);
    }


}
