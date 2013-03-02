/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import java.util.List;
import models.QuestionAnswer;

/**
 *
 * @author lin
 */
public class QAService {
    
    public void addQuestion(QuestionAnswer question){
        question.save();
    }

    public List<QuestionAnswer> getAll(){
        return QuestionAnswer.findAll();
    }

    public void addAnswer(QuestionAnswer questionAnswer,String answer){
        questionAnswer.answer = answer;
    }   
}
