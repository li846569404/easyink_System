package com.easyink.web.controller.openapi;


import com.easyink.common.core.domain.entity.WeCorpAccount;
import com.easyink.wecom.service.WeCorpAccountService;
import com.easyink.wecom.utils.ApplicationMessageUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;

/**
 * 类名: 对外开放的api接口
 *
 * @author : silver_chariot
 * @date : 2022/3/14 15:44
 */
@RestController
@RequestMapping("/open_api")
@RequiredArgsConstructor
@Api(tags = {"对外开放的api接口"})
public class OpenApiController {

    private final ApplicationMessageUtil applicationMessageUtil;

    private final WeCorpAccountService ixCorpAccountService;

    /**
     * 发送应用消息（文本）
     *
     * @param userIds  员工id列表
     * @param corpId   企业id
     * @param msg      消息模板 例如 “员工姓名：{0},年龄：{1}”
     * @param paramMsg 替换占位符{}消息
     */
    @GetMapping("/send/app")
    public void sendAppMessage(String userIds, String corpId, String msg, String... paramMsg) {
        if (StringUtils.isBlank(corpId)){
            WeCorpAccount validWeCorpAccount = ixCorpAccountService.findValidWeCorpAccount();
            corpId = validWeCorpAccount.getCorpId();
        }
        applicationMessageUtil.sendAppMessage(Arrays.asList(userIds.split(",")),corpId,msg,paramMsg);
    }
}
