package com.tobeyond.blog.controller.admin.pc;

import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.model.po.TagExample;
import com.tobeyond.blog.model.po.TagPo;
import com.tobeyond.blog.service.ITagService;
import com.tobeyond.blog.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("adminTagController")
@RequestMapping(value = "/admin/tag")
public class TagController {

    private ModelAndView modelAndView;

    @Autowired
    ITagService iTagService;

    @GetMapping(value = "/list")
    public ModelAndView list(@RequestParam(value = "page",required = false) Integer page){
        TagExample example = new TagExample();
        example.createCriteria().andDeletedAtIsNull();
        PageInfo<TagPo> pageInfo =  iTagService.listWithPager(example,page,10);
        ModelAndView modelAndView = new ModelAndView("/admin/tag/list");
        modelAndView.addObject("pageInfo",pageInfo);
        return  modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage(){
        return new ModelAndView("/admin/tag/add");
    }

    @PostMapping(value = "/add")
    @ResponseBody
    public ReturnJson addSave(TagPo po){
        if(po.getTitle() == null || po.getTitle().equals("")) return  ReturnJson.error("中文标签不能为空");
        if(po.getTag() == null || po.getTag().equals("")) return  ReturnJson.error("英文标签不能为空");

        try{
            iTagService.add(po);
        }catch (Exception e){
            return ReturnJson.error(e.getMessage());
        }
        return  ReturnJson.success("添加成功");
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable Long id){
        TagExample example = new TagExample();
        example.createCriteria().andIdEqualTo(id.intValue());
        List<TagPo> list = iTagService.selectByExample(example);
        CommonUtils.outputObject(list.get(0));

        modelAndView =  new ModelAndView("/admin/tag/edit");
        modelAndView.addObject("tag",list.get(0));
        return modelAndView;
    }

    @PostMapping(value = "/edit/{id}")
    @ResponseBody
    public ReturnJson editSave(TagPo po,@PathVariable Long id){
        if(po.getId() == null) return  ReturnJson.error("id不能为空");
        if(po.getTitle() == null || po.getTitle().equals("")) return  ReturnJson.error("中文标签不能为空");
        if(po.getTag() == null || po.getTag().equals("")) return  ReturnJson.error("英文标签不能为空");

        try{
            iTagService.update(po);
        }catch (Exception e){
            return ReturnJson.error(e.getMessage());
        }
        return  ReturnJson.success("修改成功");
    }

    @PostMapping(value = "/del")
    @ResponseBody
    public ReturnJson del(@RequestParam(value = "id",required = true) Integer id,
                                 HttpServletRequest request){
        TagPo po = new TagPo();
        po.setId(id);
        try {
            iTagService.del(po);
        } catch (Exception e) {

        }

        return  ReturnJson.success("删除成功");
    }

}
