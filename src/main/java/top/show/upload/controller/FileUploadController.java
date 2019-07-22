package top.show.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.show.upload.entity.User;
import top.show.upload.repository.UserRepository;
import top.show.upload.exception.StorageFileNotFoundException;
import top.show.upload.service.StorageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 吴启欢
 * @version 1.0
 * @date 19-7-21 下午9:20
 */
@Controller
@Slf4j
public class FileUploadController {
    @Autowired
    private UserRepository userRepository;

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }



    @GetMapping(value = {"/", "/index",""})
    public String index() {
        return "index";
    }
    @GetMapping(value = {"/show"})
    public String show() {
        return "show";
    }
    @PostMapping("/switch")
    public ModelAndView switchToIndex(@Param("username") String username,
                                @Param("password") String password,
                                @Param("verificationCode") String verificationCode,
                                HttpServletRequest request){
        //return "redirect:/index";
        ModelAndView modelAndView = new ModelAndView();
        String imgcode = request.getSession().getAttribute("imgcode").toString();
        if (!imgcode.equals(verificationCode)) {
            modelAndView.setViewName("index");
            modelAndView.addObject("message", "验证码错误");
            return modelAndView;
        }

        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            session.setAttribute("User",user);
            modelAndView.setViewName("redirect:/uploadForm");
            return modelAndView;
        }
        modelAndView.setViewName("index");
        modelAndView.addObject("message", "用户名或密码错误");
        return modelAndView;
    }
    @GetMapping("/login")
    public String  login() {
        return "index";
    }
    @GetMapping("/uploadForm")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    /**
     * storageService.store(file);
     * RequestParam("file") MultipartFile file,
     *
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/upload")
    public String handleFileUpload(MultipartHttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {
        Iterator<String> fileNames = request.getFileNames();
        StringBuffer sb = new StringBuffer();
        StringBuffer deleteCharAt = sb;
        log.info("deleteCharAt: {}", deleteCharAt);
        while (fileNames.hasNext()) {
            String next = fileNames.next();
            List<MultipartFile> files = request.getFiles(next);
            for (MultipartFile multipartFile : files) {
                storageService.store(multipartFile);
                sb.append(multipartFile.getOriginalFilename()).append(",");
                log.info("multipartFile.getOriginalFilename(): {}", multipartFile.getOriginalFilename());
            }
            log.info("sb: {}", sb);
            if (sb.length() > 0) {
                deleteCharAt = sb.deleteCharAt(sb.lastIndexOf(","));
                log.info("deleteCharAt: {}", deleteCharAt);
            }
        }


        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + deleteCharAt.toString() + "!");
        String contextPath = request.getContextPath();
        log.info("contextPath:{} ", contextPath);
        return "redirect:/uploadForm";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
