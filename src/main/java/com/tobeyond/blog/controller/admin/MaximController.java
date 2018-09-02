package com.tobeyond.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.Dto.ReturnJson;
import com.tobeyond.blog.model.po.MaximExample;
import com.tobeyond.blog.model.po.MaximPo;
import com.tobeyond.blog.service.IMaximsService;
import com.tobeyond.blog.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("adminMaximController")
@RequestMapping(value = "/admin/maxim")
public class MaximController {

    private ModelAndView modelAndView;

    @Autowired
    IMaximsService iMaximsService;

    @GetMapping(value = "/list")
    public ModelAndView maximList(@RequestParam(value = "page",required = false) Integer page){
        MaximExample maximExample = new MaximExample();
        maximExample.createCriteria().andDeletedAtIsNull();
        PageInfo<MaximPo> pageInfo =  iMaximsService.listWithPager(maximExample,page,10);
        ModelAndView modelAndView = new ModelAndView("/admin/maxim/list");
        modelAndView.addObject("pageInfo",pageInfo);
        return  modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage(){
        return new ModelAndView("/admin/maxim/add");
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ReturnJson addSave(MaximPo maximPo){
        if(maximPo.getIsShow() == null) maximPo.setIsShow(false);
        if(maximPo.getAuthor() == null || maximPo.getAuthor().equals("")) return  ReturnJson.error("作者不能为空");
        if(maximPo.getContent() == null || maximPo.getContent().equals("")) return  ReturnJson.error("内容不能为空");

        try{
            iMaximsService.add(maximPo);
        }catch (Exception e){
            return ReturnJson.error(e.getMessage());
        }
        return  ReturnJson.success("添加成功");
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable Long id){
        MaximExample maximExample = new MaximExample();
        maximExample.createCriteria().andIdEqualTo(id.intValue());
        List<MaximPo> list = iMaximsService.list(maximExample);
        CommonUtils.outputObject(list.get(0));

        modelAndView =  new ModelAndView("/admin/maxim/edit");
        modelAndView.addObject("maxim",list.get(0));
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public ReturnJson editSave(MaximPo maximPo){
        if(maximPo.getIsShow() == null) maximPo.setIsShow(false);
        if(maximPo.getId() == null) return  ReturnJson.error("id不能为空");
        if(maximPo.getAuthor() == null || maximPo.getAuthor().equals("")) return  ReturnJson.error("作者不能为空");
        if(maximPo.getContent() == null || maximPo.getContent().equals("")) return  ReturnJson.error("内容不能为空");

        try{
            iMaximsService.update(maximPo);
        }catch (Exception e){
            return ReturnJson.error(e.getMessage());
        }
        return  ReturnJson.success("修改成功");
    }

    @PostMapping(value = "/changeShow")
    @ResponseBody
    public ReturnJson changeShow(@RequestParam(value = "id",required = true) Integer id,
                                 @RequestParam(value = "isShow",required = true) Boolean isShow,
                                 HttpServletRequest request){
        MaximPo maximPo = new MaximPo();
        maximPo.setId(id);
        maximPo.setIsShow(isShow);
        try {
            iMaximsService.update(maximPo);
        } catch (Exception e) {
        }
        return  ReturnJson.success("修改成功");
    }

    @PostMapping(value = "/del")
    @ResponseBody
    public ReturnJson del(@RequestParam(value = "id",required = true) Integer id,
                                 HttpServletRequest request){
        MaximPo maximPo = new MaximPo();
        maximPo.setId(id);
        try {
            iMaximsService.del(maximPo);
        } catch (Exception e) {

        }

        return  ReturnJson.success("删除成功");
    }

}
