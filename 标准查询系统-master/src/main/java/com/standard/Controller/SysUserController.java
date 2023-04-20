package com.standard.Controller;

import com.standard.Jpa.LibUserJpa;
import com.standard.Service.SysUserService;
import com.standard.entity.ReaderInfo;
import com.standard.untils.Res;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.*;





@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;



    @Autowired
    LibUserJpa libUserJpa;

    @RequestMapping("/getList")
    public Res getList(@RequestBody Map<String,String> map){
        List<ReaderInfo> data =  sysUserService.List();

        return Res.ok(data);
    }

    @RequestMapping("/currentUser")
    @ResponseBody
    public String currentUser(HttpSession session, HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        System.out.println("User:"+user);
        Map<String, String> usermap = new LinkedHashMap<>();
        //if (user != null && !user.getUsername().equals("")) {
            //用户信息项目逐渐补充
//            log.info("射门L:",user.toString());
            usermap.put("success","1");
            usermap.put("name", "aaaa");
            usermap.put("userid","111");
            usermap.put("currentAuthority", "admin");

            return "{\"success\":true,"  +
                    "\"data\":" +
                    "{\"name\": "+"\"" +"admin"+ "\"" + ","+
                    "\"avatar\":\"https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png\"," +
                    "\"userid\":\"00000001\"," +
                    "\"email\":\"antdesign@alipay.com\"," +
                    "\"signature\":\"海纳百川，有容乃大\"," +
                    "\"title\":\"交互专家\",\"group\":\"蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED\"," +
                    "\"tags\":[{\"key\":\"0\",\"label\":\"很有想法的\"},{\"key\":\"1\",\"label\":\"专注设计\"}," +
                    "{\"key\":\"2\",\"label\":\"辣~\"},{\"key\":\"3\",\"label\":\"大长腿\"},{\"key\":\"4\",\"label\":\"川妹子\"}," +
                    "{\"key\":\"5\",\"label\":\"海纳百川\"}]," +
                    "\"notifyCount\":12,\"unreadCount\":11,\"country\":\"China\"," +
                    "\"access\":\"admin\",\"geographic\":{\"province\":{\"label\":\"浙江省\",\"key\":\"330000\"}," +
                    "\"city\":{\"label\":\"杭州市\",\"key\":\"330100\"}}," +
                    "\"address\":\"西湖区工专路 77 号\",\"phone\":\"0752-268888888\"}}" ;
        //}else{
            //先假定有个账户，以完成免登录
//            return "{\"success\":true,\"data\":{\"name\":\"Serati Ma\",\"avatar\":\"https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png\",\"userid\":\"00000001\",\"email\":\"antdesign@alipay.com\",\"signature\":\"海纳百川，有容乃大\",\"title\":\"交互专家\",\"group\":\"蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED\",\"tags\":[{\"key\":\"0\",\"label\":\"很有想法的\"},{\"key\":\"1\",\"label\":\"专注设计\"},{\"key\":\"2\",\"label\":\"辣~\"},{\"key\":\"3\",\"label\":\"大长腿\"},{\"key\":\"4\",\"label\":\"川妹子\"},{\"key\":\"5\",\"label\":\"海纳百川\"}],\"notifyCount\":12,\"unreadCount\":11,\"country\":\"China\",\"access\":\"admin\",\"geographic\":{\"province\":{\"label\":\"浙江省\",\"key\":\"330000\"},\"city\":{\"label\":\"杭州市\",\"key\":\"330100\"}},\"address\":\"西湖区工专路 77 号\",\"phone\":\"0752-268888888\"}}" ;
            //return null;//前台未接收到用户信息，就跳转到登录页面

//          return "{\"data\":{\"isLogin\":false},\"errorCode\":\"401\",\"errorMessage\":\"请先登录！\",\"success\":true}";
       // }


    }


    @RequestMapping("/update")
    public Res updateLend(@RequestBody Map<String,String> map){
        Long readerId = Long.valueOf(map.get("readerId"));
        String name = map.get("name");
        String sex = map.get("sex");
        Date birth = Date.valueOf(map.get("birth"));
        String address = map.get("address");
        String  phone = map.get("address");
        sysUserService.update(name,sex,birth,address,phone,readerId);
        return Res.ok("修改成功！");
    }

    @RequestMapping("/add")
    public Res addLend(@RequestBody Map<String,String> map){
        Long readerId = Long.valueOf(map.get("readerId"));
        String name = map.get("name");
        String sex = map.get("sex");
        Date birth = Date.valueOf(map.get("birth"));
        String address = map.get("address");
        String  phone = map.get("address");
        sysUserService.add(readerId,name,sex,birth,address,phone);
        return Res.ok("新增成功！");
    }


    @RequestMapping("/delete")
    public Res deleteLend(@RequestBody Map<String,String> map){
        Long ser_num = Long.valueOf(map.get("readerId"));

        sysUserService.delete(ser_num);
        return Res.ok("删除成功！");
    }
}
