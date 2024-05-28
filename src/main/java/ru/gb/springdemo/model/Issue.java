package ru.gb.springdemo.model;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */

// @Entity
public class Issue {

  private Long id;
  private Long bookId;
  private Long readerId;

  /**
   * Дата выдачи
   */
  private final LocalDateTime issuedAt;
  /**
   * Дата возврата
   */
  private LocalDateTime timeReturn = null;

  public Issue(Long bookId, Long readerId) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.issuedAt = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public Long getBookId() {
    return bookId;
  }

  public Long getReaderId() {
    return readerId;
  }

  public LocalDateTime getIssuedAt() {
    return issuedAt;
  }

  public LocalDateTime getTimeReturn() {
    return timeReturn;
  }
  public void setReaderId(Long readerId) {
    this.readerId = readerId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTimeReturn(LocalDateTime timeReturn) {
    this.timeReturn = timeReturn;
  }
}
