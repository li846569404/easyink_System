package com.easywecom.wecom.domain.dto.transfer;

import com.easywecom.common.utils.spring.SpringUtils;
import com.easywecom.wecom.client.WeExternalContactClient;
import com.easywecom.wecom.domain.dto.WePageBaseReq;
import com.easywecom.wecom.domain.dto.WePageBaseResp;
import lombok.Data;

/**
 * 类名: 获取待分配的离职成员列表请求实体
 *
 * @author : silver_chariot
 * @date : 2021/12/6 15:20
 */
@Data

public class GetUnassignedListReq extends WePageBaseReq<GetUnassignedListResp.UnassignedInfo> {
    /**
     * 每次返回的最大记录数，默认为1000，最大值为1000
     */
    private final Integer page_size = 100;

    @Override
    public WePageBaseResp<GetUnassignedListResp.UnassignedInfo> execute(String corpId) {
        WeExternalContactClient client = SpringUtils.getBean(WeExternalContactClient.class);
        return client.getUnassignedList(this, corpId);
    }
}
