package com.xbwl.demo.web.resource;

import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.xbwl.demo.entity.resource.Resource;
import com.xbwl.demo.service.account.AccountService;
import com.xbwl.demo.service.account.ShiroDbRealm.ShiroUser;
import com.xbwl.demo.service.permission.PermissionService;
import com.xbwl.demo.service.resource.ResourceService;
import com.xbwl.demo.utils.ShiroUserUtils;

@Controller
@RequestMapping(value = "/resource")
public class ResourceController {

    private static final String PAGE_SIZE = "10";
    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("title", "名字");
    }
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private ResourceService resourceService;
    
    @Autowired
    private PermissionService permissionService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        String name = (String)searchParams.get("LIKE_resName");
        sortTypes.put("name", name);
        PageInfo<Resource> resources = resourceService.findResource(pageNumber, pageSize, sortTypes);
        model.addAttribute("resources", resources);
        model.addAttribute("perms", "create");
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "resource/resourceList";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        Resource resource = new Resource();
        resource.setParentId(0L);
        model.addAttribute("resource", resource);
        model.addAttribute("action", "create");
        return "resource/resourceForm";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Resource resource, RedirectAttributes redirectAttributes) {
        ShiroUser user = ShiroUserUtils.getShiroUser();
        resource.setCreateId(user.id);
        resource.setCreateDate(new Date());
        resource.setState(new Long(1));
        resourceService.save(resource);
        redirectAttributes.addFlashAttribute("message", "创建资源成功");
        return "redirect:/resource/";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("resource", resourceService.findById(id));
        model.addAttribute("action", "update");
        return "resource/resourceForm";
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("resource") Resource resource, RedirectAttributes redirectAttributes) {
        ShiroUser user = ShiroUserUtils.getShiroUser();
        resource.setUpdateId(user.id);
        resource.setUpdateDate(new Date());
        resource.setParentId(resource.getParentId());
        resourceService.update(resource);
        redirectAttributes.addFlashAttribute("message", "更新资源成功");
        return "redirect:/resource/";
    }
    
    @RequestMapping(value = "delete/{ids}")
    public String delete(@PathVariable("ids") List<Long> ids, RedirectAttributes redirectAttributes) {
        resourceService.delete(ids);
        permissionService.deleteByResIds(ids);
        redirectAttributes.addFlashAttribute("message", "删除资源成功");
        return "redirect:/resource/";
    }
    
    @RequestMapping(value = "recover/{id}/{state}")
    public String recover(@PathVariable("id") String id, @PathVariable("state") String state, RedirectAttributes redirectAttributes) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("state", state);
        resourceService.recover(map);
        String message = "";
        if("0".equals(state)){
            message = "冻结";
        }
        if("1".equals(state)){
            message = "解冻";
        }
        redirectAttributes.addFlashAttribute("message", message+"资源成功");
        return "redirect:/resource/";
    }
    
}
