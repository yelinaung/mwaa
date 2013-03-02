/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.imageio.ImageIO;
import models.PrepaidCode;
import models.UserInfo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.With;
import util.UserType;

/**
 *
 * @author lin
 */
@With(Secure.class)
@Check("Admin")
public class Admin extends Controller {

    public static void index() {
        render();
    }

    public static void astrologer() {
        UserInfo userInfo = new UserInfo();
        List astrologers = models.Astrologer.findAll();
        render(userInfo, astrologers);
    }

    public static void saveAstrologer(UserInfo userInfo) {
        validation.required(userInfo.email);
        validation.required(userInfo.password);
        if (Validation.hasErrors()) {
            render("Admin/astrologer.html");
        }

        userInfo.userType = UserType.Astrologer;
        userInfo.save();

        models.Astrologer astrologer = new models.Astrologer();
        astrologer.name = userInfo.email;
        astrologer.userInfo = userInfo;
				astrologer.price=100;
        astrologer.save();
        astrologer();
    }

    public static void user() {
        List users = UserInfo.find("userType = ?", UserType.User).fetch();
        render(users);
    }

    public static void code( ){
        PrepaidCode prepaidCode = new PrepaidCode();
        List<PrepaidCode> codes = PrepaidCode.findAll();
        render(prepaidCode, codes);
    }

    public static void saveCode(PrepaidCode prepaidCode) {
        prepaidCode.save();
        Admin.code();
    }

		public static void report() throws IOException{
			render("Admin/report.html");
		}

    public static void showAstrologer(Long id){
        models.Astrologer astrologer =  models.Astrologer.findById(id);
        render(astrologer);
    }

    public static void updateAstrologer(Long id){
        models.Astrologer astrologer = models.Astrologer.findById(id);
        astrologer.name = params.get("name");
        astrologer.biography = params.get("biography");
        astrologer.price = Integer.parseInt(params.get("price"));
        astrologer.receivePoint = Integer.parseInt(params.get("point"));
        astrologer.save();
        showAstrologer(id);
    }
}
