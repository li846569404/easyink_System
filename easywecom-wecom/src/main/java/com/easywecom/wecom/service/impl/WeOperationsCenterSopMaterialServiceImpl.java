package com.easywecom.wecom.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easywecom.common.enums.ResultTip;
import com.easywecom.common.exception.CustomException;
import com.easywecom.wecom.domain.WeOperationsCenterSopMaterialEntity;
import com.easywecom.wecom.mapper.WeOperationsCenterSopMaterialMapper;
import com.easywecom.wecom.service.WeOperationsCenterSopMaterialService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class WeOperationsCenterSopMaterialServiceImpl extends ServiceImpl<WeOperationsCenterSopMaterialMapper, WeOperationsCenterSopMaterialEntity> implements WeOperationsCenterSopMaterialService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delSopByCorpIdAndSopIdList(String corpId, List<Long> sopIdList) {
        if (StringUtils.isBlank(corpId) || CollectionUtils.isEmpty(sopIdList)) {
            throw new CustomException(ResultTip.TIP_GENERAL_BAD_REQUEST);
        }
        LambdaQueryWrapper<WeOperationsCenterSopMaterialEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(WeOperationsCenterSopMaterialEntity::getCorpId, corpId)
                .in(WeOperationsCenterSopMaterialEntity::getSopId, sopIdList);
        baseMapper.delete(wrapper);
    }
}