package com.xbwl.demo.web.permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.web.Servlets;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.xbwl.demo.entity.permission.Permission;
import com.xbwl.demo.entity.resource.Resource;
import com.xbwl.demo.entity.role.Role;
import com.xbwl.demo.service.account.ShiroDbRealm.ShiroUser;
import com.xbwl.demo.service.permission.PermissionService;
import com.xbwl.demo.service.resource.ResourceService;
import com.xbwl.demo.service.role.RoleService;
import com.xbwl.demo.utils.ShiroUserUtils;

@Controller
@RequestMapping(value = "/permission")
public class PermissionController {

    private static final String PAGE_SIZE = "10";
    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("title", "名字");
    }
    
    @Autowired
    private PermissionService permissionService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private ResourceService resourceService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        String name = (String)searchParams.get("LIKE_PermissionName");
        sortTypes.put("name", name);
        PageInfo<Permission> permissions = permissionService.findPermission(pageNumber, pageSize, sortTypes);
        model.addAttribute("permissions", permissions);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "permission/permissionList";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        List<Role> roles = roleService.findAllRoles("1");
        model.addAttribute("roles", roles);
        model.addAttribute("permission", new Permission());
        model.addAttribute("action", "create");
        return "permission/permissionForm";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Permission permission, RedirectAttributes redirectAttributes) {
        Long roleId = permission.getRoleId();
        List<Permission> permissions = permissionService.findAllByRoleId(roleId);
        if(permissions != null && permissions.size() > 0){
            permissionService.deleteByRoleId(roleId);
        }
        List<String> ids = permission.getSelectIds();
        if(ids != null && ids.size() > 0){
            for (int i = 0; i < ids.size(); i++) {
                Permission p = new Permission();
                p.setResId(new Long(ids.get(i)));
                p.setRoleId(permission.getRoleId());
                permissionService.save(p);
            }
        }
        redirectAttributes.addFlashAttribute("message", "分配权限成功");
        return "redirect:/permission/";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        List<Role> roles = roleService.findAllRoles("1");
        model.addAttribute("roles", roles);
        model.addAttribute("permission", permissionService.findAllByRoleId(id).get(0));
        model.addAttribute("action", "update");
        return "permission/permissionForm";
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("permission") Permission permission, RedirectAttributes redirectAttributes) {
        Long roleId = permission.getRoleId();
        List<Permission> permissions = permissionService.findAllByRoleId(roleId);
        if(permissions != null && permissions.size() > 0){
            permissionService.deleteByRoleId(roleId);
        }
        List<String> ids = permission.getSelectIds();
        if(ids != null && ids.size() > 0){
            for (int i = 0; i < ids.size(); i++) {
                Permission p = new Permission();
                p.setResId(new Long(ids.get(i)));
                p.setRoleId(permission.getRoleId());
                permissionService.save(p);
            }
        }
        redirectAttributes.addFlashAttribute("message", "分配权限成功");
        return "redirect:/permission/";
    }
    
    @RequestMapping(value = "delete/{ids}")
    public String delete(@PathVariable("ids") List<Long> ids, RedirectAttributes redirectAttributes) {
        permissionService.deleteByRoleIds(ids);
        redirectAttributes.addFlashAttribute("message", "删除权限成功");
        return "redirect:/permission/";
    }
    
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/getMenuAsJosnp", produces = "applicaiton/javascript")
    public String getTree(@RequestParam("roleId") Long roleId,  @RequestParam("flag") Long flag, @RequestParam("callback") String callbackname) throws Exception {
        List<Resource> menuList = new ArrayList<Resource>();
        if(flag != null && flag == 0){
            menuList = resourceService.findAllResources();
        }
        if(flag != null && flag == 1){
            menuList = resourceService.findAllResourcesNoBtn();
        }
        Map<String, Object> map = Maps.newLinkedHashMap();
        Resource resource = new Resource();
        resource.setId(0L);
        resource.setResName("功能菜单");
        menuList.add(resource);
        map.put("menuList", menuList);
        List<Long> menuIds = new ArrayList<Long>();
        if(roleId != null){
            List<Permission> permissions = permissionService.findByRoleIdAndResId(roleId);
            if(permissions != null && permissions.size() > 0){
                for(Permission permission:permissions){
                    Long menuId = permission.getResId();
                    menuIds.add(menuId);
                }
            }
            map.put("menuIds", menuIds);
        }
        JsonMapper mapper = new JsonMapper();
        return mapper.toJsonP(callbackname, map);
    }
    
    @ResponseBody
    @RequestMapping(value = "menusLoad")
    public Map<String, Object> menusLoad() {
        Map<String, Object> model = new HashMap<String, Object>();
        ShiroUser user = ShiroUserUtils.getShiroUser();
        String roleCode = user.roleCode;
        List<Resource> menusList = new ArrayList<Resource>();
        if("admin".equals(roleCode)){
            menusList = resourceService.findAllResourcesByAdmin();
        }else {
            menusList = resourceService.findAllResourcesByRole(user.roleId);
        }
        model.put("menusList", menusList);
        return model;
    }
    
}
