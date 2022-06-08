package com.easywecom.wecom.domain.dto.transfer;

import com.easywecom.common.utils.spring.SpringUtils;
import com.easywecom.wecom.client.WeExternalContactClient;
import com.easywecom.wecom.domain.dto.WePageBaseReq;
import com.easywecom.wecom.domain.dto.WePageBaseResp;
import lombok.Builder;
import lombok.Data;

/**
 * 类名: 查询客户接替状态请求实体
 *
 * @author : silver_chariot
 * @date : 2021/11/30 18:20
 */
@Data
@Builder
public class TransferResultReq extends WePageBaseReq<TransferResultResp.ResultDetail> {
    /**
     * 原添加成员的userid
     */
    private String handover_userid;
    /**
     * 接替成员的userid
     */
    private String takeover_userid;

    @Override
    public WePageBaseResp<TransferResultResp.ResultDetail> execute(String corpId) {
        WeExternalContactClient client = SpringUtils.getBean(WeExternalContactClient.class);
        return client.transferResult(this, corpId);
    }
}
