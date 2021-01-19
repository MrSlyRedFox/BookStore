package ru.mrSlyRedFox.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import ru.mrSlyRedFox.persist.model.Book;
import ru.mrSlyRedFox.service.PictureService;


@Controller
@RequestMapping("/picture")
public class PictureController {

    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/{pictureId}")
    public void downloadBookPicture(@PathVariable("pictureId") Long pictureId, HttpServletResponse resp) throws IOException {
        logger.info("Downloading picture with id: {}", pictureId);

        Optional<String> opt = pictureService.getPictureContentTypeById(pictureId);
        if(opt.isPresent()){
            resp.setContentType(opt.get());
            resp.getOutputStream().write(pictureService.getPictureDataById(pictureId).get());
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping("/{pictureId}")
    public String deletePicture(@PathVariable("pictureId") Long pictureId) {
        Book book = pictureService.getBookByPictureId(pictureId).get();
        pictureService.removePicture(pictureId);
        return "redirect:/book/" + book.getBook_id() + "/edit";
    }


}
