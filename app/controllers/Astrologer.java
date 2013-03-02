/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import java.util.List;
import models.QuestionAnswer;
import models.UserInfo;
import play.mvc.Controller;
import play.mvc.With;

/**
 *
 * @author lin
 */
@With(Secure.class)
@Check("Astrologer")
public class Astrologer extends Controller{
    public static void index(){
        UserInfo userInfo = UserInfo.find("email = ?", Security.connected()).first();
        models.Astrologer astrologer =  models.Astrologer.find("userInfo = ?", userInfo).first();
        List<QuestionAnswer> questions = QuestionAnswer.find("astrologer = ?", astrologer).fetch();
        render(questions);
    }

     public static void showQuestion(Long id){
        QuestionAnswer qa=QuestionAnswer.findById(id);
        render(qa);
    }

    public static void saveAnswer(Long id,String answer){
        QuestionAnswer qa =QuestionAnswer.findById(id);
        qa.answer = answer;
        qa.save();
        System.out.println(qa.answer);
        Astrologer.index();
    }    
}
