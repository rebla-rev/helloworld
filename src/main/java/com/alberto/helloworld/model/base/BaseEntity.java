package com.alberto.helloworld.model.base;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Version
  private Long version;

  @CreationTimestamp
  @EqualsAndHashCode.Exclude
  private OffsetDateTime createdAt;

  @UpdateTimestamp
  @EqualsAndHashCode.Exclude
  private OffsetDateTime modifiedAt;

}