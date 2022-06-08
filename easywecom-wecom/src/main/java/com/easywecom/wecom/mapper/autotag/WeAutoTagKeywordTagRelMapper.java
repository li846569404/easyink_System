package com.easywecom.wecom.mapper.autotag;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easywecom.wecom.domain.entity.autotag.WeAutoTagKeywordTagRel;
import com.easywecom.wecom.domain.vo.autotag.TagInfoVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关键词与标签关系表(WeAutoTagKeywordTagRel)表数据库访问层
 *
 * @author tigger
 * @since 2022-02-27 15:52:38
 */
@Repository
public interface WeAutoTagKeywordTagRelMapper extends BaseMapper<WeAutoTagKeywordTagRel> {

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<WeAutoTagKeywordTagRel> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<WeAutoTagKeywordTagRel> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<WeAutoTagKeywordTagRel> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<WeAutoTagKeywordTagRel> entities);

    /**
     * 查询标签列表通过规则id
     * select抓取接口
     *
     * @param ruleId
     * @return
     */
    List<TagInfoVO> listTagListByRuleId(@Param("ruleId") Long ruleId, @Param("corpId") String corpId);
    /**
     * 查询标签名称列表通过规则id
     * select抓取接口
     *
     * @param ruleId
     * @return
     */
    List<String> listTagNameListByRuleId(@Param("ruleId") Long ruleId, @Param("corpId") String corpId);

}

