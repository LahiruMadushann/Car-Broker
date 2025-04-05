package com.example.seller.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmbeddedAppraisalId implements Serializable {
    @Column(name = "appraisal_id")
    private Long appraisalId;

    @Column(name = "shop_id")
    private Integer shopId;
}
