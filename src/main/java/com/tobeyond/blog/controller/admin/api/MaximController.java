package com.tobeyond.blog.controller.admin.api;

import com.github.pagehelper.PageInfo;
import com.tobeyond.blog.annotation.AdminLoginToken;
import com.tobeyond.blog.model.dto.ReturnJson;
import com.tobeyond.blog.model.po.MaximExample;
import com.tobeyond.blog.model.po.MaximPo;
import com.tobeyond.blog.service.IMaximsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AdminLoginToken
@RestController("apiAdminMaximController")
@RequestMapping(value = "/api/admin/maxim")
public class MaximController extends BaseController{

    @Autowired
    IMaximsService iMaximsService;

    @PostMapping(value = "/list")
    public ReturnJson maximList(@RequestParam(value = "page",required = false) Integer page){
        PageInfo<MaximPo> pageInfo =  iMaximsService.listWithPager(page,10);
        returnData.put("data",pageInfo);
        return  ReturnJson.success("获取列表成功",returnData);
    }

    @PostMapping(value = "/add")
    public ReturnJson addSave(MaximPo maximPo){
        if(maximPo.getIsShow() == null) maximPo.setIsShow(Byte.valueOf("0"));
        if(maximPo.getAuthor() == null || maximPo.getAuthor().equals("")) return  ReturnJson.error("作者不能为空");
        if(maximPo.getContent() == null || maximPo.getContent().equals("")) return  ReturnJson.error("内容不能为空");

        try{
            iMaximsService.add(maximPo);
        }catch (Exception e){
            return ReturnJson.error(e.getMessage());
        }
        return  ReturnJson.success("添加成功");
    }

    @PostMapping(value = "/detail")
    public ReturnJson editPage(@RequestParam(value = "id") Integer id){
        MaximPo maximPo = iMaximsService.selectByPrimaryKey(id);
        returnData.put("data",maximPo);
        return ReturnJson.success("获取数据成功",returnData);
    }

    @PostMapping(value = "/edit")
    @ResponseBody
    public ReturnJson editSave(MaximPo maximPo){
        if(maximPo.getIsShow() == null) maximPo.setIsShow(Byte.valueOf("0"));
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
                                 @RequestParam(value = "isShow",required = true) Byte isShow,
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
