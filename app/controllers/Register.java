/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import models.UserInfo;
import play.cache.Cache;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.libs.Codec;
import play.libs.Images;
import play.mvc.Controller;
import util.UserType;

/**
 *
 * @author lin
 */
public class Register extends Controller{
    public static void index(){
        String randomID = Codec.UUID();
        UserInfo userInfo = new UserInfo();
        render(randomID,userInfo);
    }

    public static void captcha(String id){
        Images.Captcha captcha = Images.captcha();
        String code = captcha.getText();
        Cache.set(id, code,"10mn");
        renderBinary(captcha);
    }

    public static void register(UserInfo userInfo,
            @Required(message="Please type code correctly")String captcha,
            String randomID){
            validation.equals(captcha,Cache.get(randomID)).message("Invalid code.");
            validation.required(userInfo.email);
            validation.required(userInfo.password);
            if(Validation.hasErrors()){
               render("Register/index.html");
            }
            Cache.delete(randomID);
            userInfo.userType = UserType.User;
            userInfo.save();

            models.Customer cust = new models.Customer();
            cust.name = userInfo.name;
            cust.info = userInfo;
            cust.creditPoint = 0 ;
            cust.save();
            
            Application.index();
    }
}
