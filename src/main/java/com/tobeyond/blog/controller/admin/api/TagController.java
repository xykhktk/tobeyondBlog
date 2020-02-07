package com.tobeyond.blog.controller.admin.api;

import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.annotation.AdminLoginToken;
import com.tobeyond.blog.model.dto.ListWithPager;
import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.model.po.TagExample;
import com.tobeyond.blog.model.po.TagPo;
import com.tobeyond.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@AdminLoginToken
@RestController("apiAdminTagController")
@RequestMapping(value = "/api/admin/tag")
public class TagController extends BaseController{


    @Autowired
    ITagService iTagService;

    @PostMapping(value = "/list")
    public ReturnJson list(@RequestParam(value = "page",required = false) Integer page){
        ListWithPager listWithPager=  iTagService.listWithPager(page,10);
        returnData.put("data",listWithPager);
        return  ReturnJson.success("获取列表成功",returnData);
    }

    @PostMapping(value = "/add")
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

    @PostMapping(value = "/detail")
    public ReturnJson editPage(@RequestParam(value = "id") Integer id){
        TagPo tagPo = iTagService.selectByPrimaryKey(id);
        returnData.put("data",tagPo);
        return ReturnJson.success("获取数据成功",returnData);
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public ReturnJson editSave(TagPo po){
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
