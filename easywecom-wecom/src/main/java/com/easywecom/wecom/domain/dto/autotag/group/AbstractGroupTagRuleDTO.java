package com.easywecom.wecom.domain.dto.autotag.group;

import com.easywecom.wecom.domain.dto.autotag.TagRuleDTO;
import com.easywecom.wecom.domain.entity.autotag.WeAutoTagGroupScene;
import com.easywecom.wecom.domain.entity.autotag.WeAutoTagGroupSceneGroupRel;
import com.easywecom.wecom.domain.entity.autotag.WeAutoTagGroupSceneTagRel;

import java.util.List;

/**
 * @author tigger
 * 2022/2/28 10:20
 **/
public abstract class AbstractGroupTagRuleDTO extends TagRuleDTO {


    public abstract List<WeAutoTagGroupScene> convertToWeAutoTagGroupSceneList();

    public abstract List<WeAutoTagGroupSceneGroupRel> convertToWeAutoTagGroupSceneGroupRelList(List<String> groupIdList);

    public abstract List<WeAutoTagGroupSceneTagRel> convertToWeAutoTagGroupSceneTagRelList(List<String> tagIdList);

}
