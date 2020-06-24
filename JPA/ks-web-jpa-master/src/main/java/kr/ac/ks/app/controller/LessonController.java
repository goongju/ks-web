package kr.ac.ks.app.controller;

import kr.ac.ks.app.domain.Lesson;
import kr.ac.ks.app.repository.LessonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LessonController {

    private final LessonRepository lessonRepository;

    public LessonController(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @GetMapping(value = "/lessons/new")
    public String createForm(Model model) {
        model.addAttribute("lessonForm", new LessonForm());
        return "lessons/lessonForm";
    }

    @PostMapping(value = "/lessons/new")
    public String create(LessonForm form) {
        Lesson lesson = new Lesson();
        lesson.setName(form.getName());
        lesson.setQuota(form.getQuota());
        lessonRepository.save(lesson);
        return "redirect:/lessons";
    }

    @GetMapping(value = "/lessons/{id}")
    public String updateShowLesson(Model model,@PathVariable ("id") Long id){
        Lesson lesson = lessonRepository.findById(id).orElseThrow(()->new IllegalArgumentException("error"));
        LessonForm lessonForm= new LessonForm();
        lessonForm.setName(lesson.getName());
        lessonForm.setQuota(lesson.getQuota());
        model.addAttribute("lessonForm", lessonForm);
        return "lessons/lessonForm";
    }
    @PostMapping(value = "/lessons/{id}")
    public String updateLesson(LessonForm form,@PathVariable ("id") Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(()->new IllegalArgumentException("error"));
        lesson.setName(form.getName());
        lesson.setQuota(form.getQuota());
        lessonRepository.save(lesson);
        return "redirect:/lessons";
    }


    @GetMapping(value = "/lessons/delete/{id}")
    public String deleteLesson(@PathVariable ("id") long id){
        Lesson lesson=lessonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid lesson id "+ id));
        lessonRepository.delete(lesson);
        return "redirect:/lessons";
    }

    @GetMapping(value = "/lessons")
    public String list(Model model) {
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return "lessons/lessonList";
    }
}
