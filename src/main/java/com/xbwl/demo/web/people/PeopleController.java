package com.xbwl.demo.web.people;

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

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.xbwl.demo.entity.people.People;
import com.xbwl.demo.service.people.PeopleService;

@Controller
@RequestMapping(value = "/people")
public class PeopleController {
    
    private static final String PAGE_SIZE = "10";
    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("title", "名字");
    }
    
    @Autowired
    private PeopleService peopleService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
            @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
            @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
            ServletRequest request) {
//        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        PageInfo<People> peoples = peopleService.findPeople(pageNumber, pageSize);
        model.addAttribute("peoples", peoples);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);
        // 将搜索条件编码成字符串，用于排序，分页的URL
//        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "people/peopleList";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("people", new People());
        model.addAttribute("action", "create");
        return "people/peopleForm";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid People people, RedirectAttributes redirectAttributes) {
        peopleService.save(people);
        redirectAttributes.addFlashAttribute("message", "创建员工信息成功");
        return "redirect:/people/";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("people", peopleService.findById(id));
        model.addAttribute("action", "update");
        return "people/peopleForm";
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("people") People people, RedirectAttributes redirectAttributes) {
        peopleService.update(people);
        redirectAttributes.addFlashAttribute("message", "更新员工信息成功");
        return "redirect:/people/";
    }
    
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        peopleService.delete(id);
        redirectAttributes.addFlashAttribute("message", "删除员工信息成功");
        return "redirect:/people/";
    }
}
