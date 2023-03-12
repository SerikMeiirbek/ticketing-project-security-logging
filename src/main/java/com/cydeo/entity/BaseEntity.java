package com.cydeo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class BaseEntity {  //break till 8:35

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public LocalDateTime insertDateTime;
    public Long insertUserId;
    public LocalDateTime lastUpdateDateTime;
    public Long lastUpdateUserId;
    private Boolean isDeleted=false;








}
