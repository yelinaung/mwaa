/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.util.List;
import models.PrepaidCode;
import models.QuestionAnswer;
import models.QuestionType;
import models.UserInfo;
import play.mvc.Controller;
import play.mvc.With;

/**
 *
 * @author lin
 */
@With(Secure.class)
@Check("Customer")
public class Customer extends Controller{
    public static void index(){
        UserInfo userInfo = UserInfo.find("email = ?", Security.connected()).first();
				        models.Customer customer = models.Customer.find("info = ?", userInfo).first();
				        List<QuestionAnswer> questions = QuestionAnswer.find("user = ?", customer).fetch();
				        QuestionAnswer qa = new QuestionAnswer();

				        UserInfo uinfo = UserInfo.find("email = ?", Security.connected()).first();
				        models.Customer cust = models.Customer.find("info = ?", uinfo).first();
				        List<models.Astrologer> astrologers  = models.Astrologer.findAll();

				        QuestionType[] types = QuestionType.values();
				        render(questions,qa,cust,astrologers,types);
				
    }

    public static void showAnswer(Long id){
        QuestionAnswer qa = QuestionAnswer.findById(id);
        render(qa);
    }

    public static void addQuestion(QuestionAnswer qa){
        Long id  = Long.parseLong(params.get("astrologer.id"));
        models.Astrologer astrologer = models.Astrologer.findById(id);
        if(astrologer == null){
            renderText("Error in Retrieving Astrologer");
        }
        qa.astrologer = astrologer;

        UserInfo uinfo = UserInfo.find("email = ?", Security.connected()).first();
        models.Customer customer = models.Customer.find("info = ?", uinfo).first();
        qa.user = customer;

        String type = params.get("type");
        qa.type = QuestionType.valueOf(type);
        if(customer.creditPoint < astrologer.price){
				            renderText("Not Enough Credit");
				        }else
				        {
				            customer.creditPoint -= astrologer.price;
				            astrologer.receivePoint += astrologer.price;
										customer.save();
										astrologer.save();
				            qa.save();
				        }
				        Customer.index();
				
    }

    public static void usePrepaidCode(String code){
        PrepaidCode prepaid =  PrepaidCode.find("codeNo = ?", code).first();
        if(prepaid == null){
            renderText("Invalid");
        }
        if(prepaid.usedEmail != null){
            renderText("Already Used");
        }

        String email = Security.connected();
        prepaid.usedEmail = email;
        prepaid.save();
        UserInfo uinfo = UserInfo.find("email = ?", email).first();
        models.Customer cust = models.Customer.find("info = ?", uinfo).first();
        cust.creditPoint += 500;
        cust.save();

        Customer.index();
    }

    public static void showProfile(){
        UserInfo uinfo = UserInfo.find("email = ?", Security.connected()).first();
        models.Customer cust = models.Customer.find("info = ?", uinfo).first();
        if(cust ==null){
            renderText("Error on Loading Data");
        }
        render(cust);
    }

    public static void editProfile(){
        UserInfo uinfo = UserInfo.find("email = ?", Security.connected()).first();
        models.Customer cust = models.Customer.find("info = ?", uinfo).first();
        render(cust);
    }


    public static void updateProfile(Long id){
        models.Customer cust = models.Customer.findById(id);
        cust.name = params.get("name");
        cust.info.password = params.get("password");
        cust.info.address = params.get("address");
        cust.save();
        showProfile();
    }
}
