package com.xbwl.demo.web.client;

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
import com.xbwl.demo.entity.client.Client;
import com.xbwl.demo.service.client.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

    private static final String PAGE_SIZE = "10";
    private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
    static {
        sortTypes.put("auto", "自动");
        sortTypes.put("title", "名字");
    }
    
    @Autowired
    private ClientService clientService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model, ServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        String name = (String)searchParams.get("LIKE_roleName");
        sortTypes.put("name", name);
        PageInfo<Client> clients = clientService.findClient(pageNumber, pageSize, sortTypes);
        model.addAttribute("clients", clients);
        model.addAttribute("sortType", sortType);
        model.addAttribute("sortTypes", sortTypes);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        return "client/clientList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("action", "create");
        return "client/clientForm";
    }
    
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid Client client, RedirectAttributes redirectAttributes) {
        clientService.createClient(client);
        redirectAttributes.addFlashAttribute("message", "创建客户端成功");
        return "redirect:/client/";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("client", clientService.findOne(id));
        model.addAttribute("action", "update");
        return "client/clientForm";
    }
    
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("client") Client client, RedirectAttributes redirectAttributes) {
        clientService.updateClient(client);
        redirectAttributes.addFlashAttribute("message", "更新客户端成功");
        return "redirect:/client/";
    }

    @RequestMapping(value = "delete/{ids}")
    public String delete(@PathVariable("ids") List<Long> ids, RedirectAttributes redirectAttributes) {
        clientService.deleteClient(ids);
        redirectAttributes.addFlashAttribute("message", "删除客户端成功");
        return "redirect:/client/";
    }

}
