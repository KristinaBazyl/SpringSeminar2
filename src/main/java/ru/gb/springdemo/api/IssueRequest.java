package ru.gb.springdemo.api;

import lombok.Data;

/**
 * Запрос на выдачу
 */

public class IssueRequest {

  /**
   * Идентификатор читателя
   */
  private Long readerId;

  /**
   * Идентификатор книги
   */
  private Long bookId;

  public IssueRequest(Long readerId, Long bookId) {
    this.readerId = readerId;
    this.bookId = bookId;
  }

  public Long getReaderId() {
    return readerId;
  }

  public void setReaderId(long readerId) {
    this.readerId = readerId;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }
}
