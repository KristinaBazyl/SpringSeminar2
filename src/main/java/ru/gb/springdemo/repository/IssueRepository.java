package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class IssueRepository {

    private final AtomicLong counter =new AtomicLong();
    private final Map<Long, Issue> issues = new ConcurrentHashMap<>();


    public void save(Issue issue) {
        issue.setId(counter.incrementAndGet());
        issues.put(issue.getId(), issue);
    }
    // показать список всех заявок
    public List<Issue> getIssues (){ return new ArrayList<>(issues.values());}

    // получить информацию о запросе по id
    public Issue getIssueById(Long id) {
        return issues.get(id);
    }
//    получить запрос по id читателя
//    public Issue getIssueByReaderId(Long id){
//        return issues.values().stream().filter(issue -> issue.getReaderId().equals(id)).findFirst().orElse(null);
//    }
    //получить список выдач по id читателя
    public List<Issue> getAllIssueByReaderId(Long id){
        return issues.values().stream()
                .filter(issue -> issue.getReaderId().equals(id))
                .filter(issue -> issue.getTimeReturn()==null)
                .toList();
    }

    // возврат книги
    public Issue returnBooks(Long id){
        Issue updateIssue = issues.get(id);
        if(updateIssue!= null){
            updateIssue.setTimeReturn(LocalDateTime.now());
        }
        return updateIssue;
    }

}
