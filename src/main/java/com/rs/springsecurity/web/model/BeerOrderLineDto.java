package com.rs.springsecurity.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * created by rs 3/6/2022.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderLineDto extends BaseItem{

    @Builder
    public BeerOrderLineDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                            UUID beerId, Integer orderQuantity) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerId = beerId;
        this.orderQuantity = orderQuantity;
    }

    private UUID beerId;
    private Integer orderQuantity = 0;
}
