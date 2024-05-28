package ru.gb.springdemo.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.IssuerService;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issues")
public class IssuerController {

    @Autowired
    private IssuerService service;

    //  @PutMapping
    //  public void returnBook(long issueId) {
    //    // найти в репозитории выдачу и проставить ей returned_at
    //  }

    // создание запроса
    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.saveIssue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    // показать список всех заявок
    @GetMapping
    public ResponseEntity<List<Issue>> getIssues() {
        return ResponseEntity.ok(service.getIssues());
    }

    // получить информацию о запросе по id
    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getIssueById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Issue> returnBooks(@PathVariable Long id) {
        Issue updateIssue = service.returnBooks(id);
        if (updateIssue != null) {
            return ResponseEntity.ok().body(updateIssue);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
