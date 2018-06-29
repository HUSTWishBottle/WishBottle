//个人主页控制类
package com.wishbottle.wishbottle.controller;

import com.wishbottle.wishbottle.bean.Wish;
import com.wishbottle.wishbottle.repository.WishRepository;
import com.wishbottle.wishbottle.service.AccountInfoService;
import com.wishbottle.wishbottle.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PersonController {
    @Autowired
    private AccountInfoService accountInfoService;
    @Autowired
    private WishService wishService;
    //树洞
    @GetMapping("/tree")
    public String tree(Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            model.addAttribute("presentAccount",AccountInfoController.presentAccount);
            return "treePage";
        }
        else
            return "loginPage";
    }
    //发布心愿
    @PostMapping("/addWish")
    public String addWish(@RequestParam("wishTitle") String wishTitle,
                          @RequestParam("wishContent") String wishContent,
                          @RequestParam("visibility") String visibility,
                          Model model){
        if(AccountInfoController.presentAccount.getEmail()!=null){
            boolean  Permision=visibility.equals("all");
            Wish awish=new Wish(AccountInfoController.presentAccount,wishTitle,wishContent,Permision);
            wishService.addWish(awish);
            System.out.println(wishTitle+"\n"+wishContent+"\n"+visibility);
            model.addAttribute("presentAccount",AccountInfoController.presentAccount);
            return "treePage";
        }
        else
            return "loginPage";

    }

}
