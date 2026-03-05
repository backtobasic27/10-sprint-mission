package com.sprint.mission.discodeit.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


  @Getter
  @MappedSuperclass
  @EntityListeners(AuditingEntityListener.class) // 생성/ 수정 시간 자동화를 활성화함.
  public abstract class BaseEntity {

    @Id // PK 지정
    @GeneratedValue(strategy = GenerationType.UUID) // UUID 자동 생성 전략 (Spring Boot 3 기준)
    private UUID id;


    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

  }

