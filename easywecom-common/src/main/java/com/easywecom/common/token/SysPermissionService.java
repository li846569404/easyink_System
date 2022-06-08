package com.easywecom.common.token;

import cn.hutool.core.util.ObjectUtil;
import com.easywecom.common.constant.Constants;
import com.easywecom.common.core.domain.entity.SysRole;
import com.easywecom.common.core.domain.entity.SysUser;
import com.easywecom.common.core.domain.model.LoginUser;
import com.easywecom.common.core.domain.wecom.WeUser;
import com.easywecom.common.enums.DataScopeEnum;
import com.easywecom.common.mapper.SysRoleDeptMapper;
import com.easywecom.common.mapper.SysRoleMapper;
import com.easywecom.common.service.ISysMenuService;
import com.easywecom.common.service.ISysRoleService;
import com.easywecom.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户权限处理
 *
 * @author admin
 */
@Component
@Slf4j
public class SysPermissionService {
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRoleDeptMapper sysRoleDeptMapper;


    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(LoginUser user) {
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (user.isSuperAdmin()) {
            roles.add(Constants.SUPER_ADMIN);
        } else if (user.getWeUser() != null) {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getCorpId(), user.getWeUser().getUserId()));
        }
        return roles;
    }

    /**
     * 设置登录用户 可以查看的部门 数据权限范围和角色
     *
     * @param user 登录用户实体
     * @return 部门id ,隔开
     */
    public void setRoleAndDepartmentDataScope(LoginUser user) {
        if (ObjectUtil.isNull(user)) {
            return;
        }
        //如果系统管理员第一次登录后,不存在corpId则不设置数据权限范围
        if (ObjectUtil.isNotNull(user.getUser()) && StringUtils.isBlank(user.getUser().getCorpId())) {
            return;
        }
        String corpId = user.getCorpId();
        // 部门范围(,隔开)
        String departmentScope = null;
        if (user.isSuperAdmin()) {
            user.setDepartmentDataScope(sysRoleDeptMapper.getAllDept(corpId));
            return;
        }
        if (ObjectUtil.isNull(user.getWeUser())) {
            return;
        }
        //设置权限
        SysRole role = sysRoleMapper.selectRoleByWeUserId(corpId, user.getWeUser().getUserId());
        if (role == null && org.apache.commons.lang3.StringUtils.isNotBlank(user.getWeUser().getExternalCorpId())) {
            role = sysRoleMapper.selectRoleByWeUserId(user.getWeUser().getExternalCorpId(), user.getWeUser().getUserId());
        }
        if(ObjectUtil.isNull(role)) {
            return;
        }
        user.setRole(role);
        //根据权限获取可查看部门
        DataScopeEnum dataScopeEnum = DataScopeEnum.getDataScope(role.getDataScope());
        switch (dataScopeEnum) {
            case ALL:
                departmentScope = sysRoleDeptMapper.getAllDept(corpId);
                break;
            case SELF_DEPT:
            case SELF:
                departmentScope = String.valueOf(user.getWeUser().getMainDepartment());
                break;
            case CUSTOM:
                departmentScope = StringUtils.join(sysRoleDeptMapper.getDeptByRoleId(corpId, role.getRoleId()), ",");
                break;
            case DEPT_AND_CHILD:
                departmentScope = sysRoleDeptMapper.getDeptAndChildDept(corpId, user.getWeUser().getMainDepartment());
                break;
            default:
                break;
        }
        // 如果为空字符串 设置为-1 缺省值,防止后续SQL报错
        if(StringUtils.isBlank(departmentScope)){
            departmentScope =  "-1";
        }
        log.info("corpId:{},name:{},type:{},设置可见部门：{}", corpId, user.getUsername(), dataScopeEnum, departmentScope);
        user.setDepartmentDataScope(departmentScope);
    }


    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (user != null && user.isAdmin()) {
            perms.add(Constants.ALL_PERMISSION);
        }
        return perms;
    }

    public Set<String> getMenuPermission(WeUser weUser) {
        Set<String> perms = new HashSet<>();
        if (weUser != null) {
            Set<String> menuSet = menuService.selectMenuPermsByWeUserId(weUser.getCorpId(), weUser.getUserId());
            if (CollectionUtils.isEmpty(menuSet) && org.apache.commons.lang3.StringUtils.isNotBlank(weUser.getExternalCorpId())) {
                menuSet = menuService.selectMenuPermsByWeUserId(weUser.getExternalCorpId(), weUser.getUserId());
            }
            perms.addAll(menuSet);
        }
        return perms;
    }


    public Set<String> getMenuPermission(LoginUser loginUser) {
        if (loginUser.getWeUser() != null) {
            return getMenuPermission(loginUser.getWeUser());
        } else if (loginUser.getUser() != null) {
            return getMenuPermission(loginUser.getUser());
        }
        return Collections.emptySet();
    }
}
