package com.easyink.wecom.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lipengyuan
 * @date 2024/4/9
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TextCardMessageDTO {
    private String title;
    private String description;
    private String url;
    private String btntxt;

}
