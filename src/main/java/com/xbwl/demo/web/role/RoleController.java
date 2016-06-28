package com.xbwl.demo.web.role;

import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.xbwl.demo.entity.role.Role;
import com.xbwl.demo.service.account.AccountService;
import com.xbwl.demo.service.account.ShiroDbRealm.ShiroUser;
import com.xbwl.demo.service.permission.PermissionService;
import com.xbwl.demo.service.role.RoleService;
import com.xbwl.demo.utils.ShiroUserUtils;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    private static final String PAGE_SIZE = "10";
    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("title", "名字");
    }
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PermissionService permissionService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        String name = (String)searchParams.get("LIKE_roleName");
        sortTypes.put("name", name);
        PageInfo<Role> roles = roleService.findRole(pageNumber, pageSize, sortTypes);
        model.addAttribute("roles", roles);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "role/roleList";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("action", "create");
        return "role/roleForm";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Role role, RedirectAttributes redirectAttributes) {
        ShiroUser user = ShiroUserUtils.getShiroUser();
        role.setCreateId(user.id);
        role.setCreateDate(new Date());
        role.setState(new Long(1));
        roleService.save(role);
        redirectAttributes.addFlashAttribute("message", "创建角色成功");
        return "redirect:/role/";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("role", roleService.findById(id));
        model.addAttribute("action", "update");
        return "role/roleForm";
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("role") Role role, RedirectAttributes redirectAttributes) {
        ShiroUser user = ShiroUserUtils.getShiroUser();
        role.setUpdateId(user.id);
        role.setUpdateDate(new Date());
        roleService.update(role);
        redirectAttributes.addFlashAttribute("message", "更新角色成功");
        return "redirect:/role/";
    }
    
    @RequestMapping(value = "delete/{ids}")
    public String delete(@PathVariable("ids") List<Long> ids, RedirectAttributes redirectAttributes) {
        roleService.delete(ids);
        permissionService.deleteByRoleIds(ids);
        redirectAttributes.addFlashAttribute("message", "删除角色成功");
        return "redirect:/role/";
    }
    
    @RequestMapping(value = "recover/{id}/{state}")
    public String recover(@PathVariable("id") String id, @PathVariable("state") String state, RedirectAttributes redirectAttributes) {
        Map<String, Object> map = Maps.newLinkedHashMap();
        map.put("id", id);
        map.put("state", state);
        roleService.recover(map);
        String message = "";
        if("0".equals(state)){
            message = "冻结";
        }
        if("1".equals(state)){
            message = "解冻";
        }
        redirectAttributes.addFlashAttribute("message", message+"角色成功");
        return "redirect:/role/";
    }
    
}
