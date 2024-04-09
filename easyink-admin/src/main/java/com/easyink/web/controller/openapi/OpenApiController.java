package com.easyink.web.controller.openapi;


import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Header;
import com.dtflys.forest.annotation.Query;
import com.easyink.common.core.domain.entity.WeCorpAccount;
import com.easyink.wecom.client.We3rdUserClient;
import com.easyink.wecom.client.WeAccessTokenClient;
import com.easyink.wecom.client.WeUserClient;
import com.easyink.wecom.domain.dto.WeAccessUserInfo3rdDTO;
import com.easyink.wecom.domain.dto.WeLoginUserInfoDTO;
import com.easyink.wecom.domain.dto.WeUserInfoDTO;
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

    private final WeAccessTokenClient weAccessTokenClient;

    private final WeUserClient weUserClient;
    /**
     * 发送应用消息（文本）
     *
     * @param userIds  员工id列表
     * @param corpId   企业id
     * @param msg      消息模板 例如 “员工姓名：{0},年龄：{1}”
     * @param paramMsg 替换占位符{}消息
     */
    @GetMapping("/send/app")
    public void sendAppMessage(String userIds, String corpId,String title,String url,String btntxt, String msg, String... paramMsg) {
        if (StringUtils.isBlank(corpId)){
            WeCorpAccount validWeCorpAccount = ixCorpAccountService.findValidWeCorpAccount();
            corpId = validWeCorpAccount.getCorpId();
        }
        applicationMessageUtil.sendTxtCardAppMessage(Arrays.asList(userIds.split(",")),corpId,title,url,btntxt,msg,paramMsg);
    }

    @GetMapping("/user/getuserinfo")
    public WeUserInfoDTO getuserinfo3rd(@Query("code") String code){
        WeCorpAccount validWeCorpAccount = ixCorpAccountService.findValidWeCorpAccount();
        WeUserInfoDTO qrCodeLoginUserInfo = weUserClient.getQrCodeLoginUserInfo(code, validWeCorpAccount.getCorpId());
        return  qrCodeLoginUserInfo;
    }


}
