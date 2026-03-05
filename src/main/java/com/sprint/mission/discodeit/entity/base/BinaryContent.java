package com.sprint.mission.discodeit.entity.base;

import jakarta.persistence.Column;
import java.util.Arrays;

public class BinaryContent extends BaseEntity{

  @Column(name = "file_name", nullable = false)
  private String fileName;

  @Column(nullable = false)
  private Long size;

  @Column(name = "content-type", nullable = false, length = 100)
  private String contentType;

  // byte 배열은 크기가 클 수 있으므로 PostgreSQL 의 bytea 타입과 매핑되도록 처리합니다.
  @Column(nullable = false)
  private byte[] bytes;

  public BinaryContent(String fileName, Long size, String contentType, byte[] bytes) {
    this.fileName = fileName;
    this.size = size;
    this.contentType = contentType;
    this.bytes = bytes;
  }

}
