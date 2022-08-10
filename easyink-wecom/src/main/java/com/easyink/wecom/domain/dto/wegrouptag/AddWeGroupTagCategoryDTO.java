package com.easyink.wecom.domain.dto.wegrouptag;

import com.easyink.wecom.domain.WeGroupTag;
import com.easyink.wecom.domain.WeGroupTagCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 类名：AddWeGroupTagCategoryDTO
 *
 * @author Society my sister Li
 * @date 2021-11-12 15:51
 */
@Data
@ApiModel("添加群标签组")
public class AddWeGroupTagCategoryDTO extends WeGroupTagCategory {

    @ApiModelProperty("新增的标签列表")
    private List<WeGroupTag> tagList;
}
