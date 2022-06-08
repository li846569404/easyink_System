package com.easywecom.wecom.domain.entity.autotag;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 客户标签命中记录(WeAutoTagRuleHitKeywordRecordTagRel)表实体类
 *
 * @author tigger
 * @since 2022-03-02 14:51:28
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WeAutoTagRuleHitKeywordRecordTagRel {
    /**
     * 规则id
     */
    private Long ruleId;
    /**
     * 标签id,去重用
     */
    private String tagId;
    /**
     * 标签名
     */
    private String tagName;
    /**
     * 客户id
     */
    private String customerId;
    /**
     * 员工id
     */
    private String userId;

}

