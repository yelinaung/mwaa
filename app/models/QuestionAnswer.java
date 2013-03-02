/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import javax.persistence.Entity;
import play.db.jpa.Model;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

/**
 *
 * @author lin
 */
@Entity
public class QuestionAnswer extends Model{
		@ManyToOne
    public Customer user;
    public String question;
    public String answer;

		@ManyToOne
    public Astrologer astrologer;
		@Column(name="QType")
    public QuestionType type;

		public String showUser(){
			return user.name;
		}
}
