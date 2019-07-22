package top.show.upload.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Administrator
 * @date 2019-7-22
 */

@Controller
@Slf4j
public class ImageVerificationController {
    @Resource
    private DefaultKaptcha captchaProducer;

    @GetMapping("/imgcode")
    public void getImgVerificationCode(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String producerText = captchaProducer.createText();
        log.info("producerText:{}",producerText);

        BufferedImage image = captchaProducer.createImage(producerText);
        session.setAttribute("imgcode",producerText);
        ServletOutputStream outputStream = null;
        try {
             outputStream = response.getOutputStream();
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            log.error(e.toString());
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/checkImageCode")
    public String checkImageCode(HttpServletRequest request, HttpServletResponse response, Model model){
        String parameter = request.getParameter("code");
        String imgcode = request.getSession().getAttribute("imgcode").toString();
        if (!parameter.equals(imgcode)) {
            return "fail";
        }
        return "success";
    }

    @GetMapping("/testImg")
    public String testImg(){
        return "testImg";
    }



}
