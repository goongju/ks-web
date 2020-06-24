package kr.ac.ks.app.controller;

import kr.ac.ks.app.domain.Course;
import kr.ac.ks.app.domain.Lesson;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.repository.CourseRepository;
import kr.ac.ks.app.repository.LessonRepository;
import kr.ac.ks.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class CourseController {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public CourseController(StudentRepository studentRepository, CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    @GetMapping("/course")
    public String showCourseForm(Model model) {
        List<Student> students = studentRepository.findAll();
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);
        return "courses/courseForm";
    }
    @PostMapping("/course")
    public String createCourse(@RequestParam("studentId") Long studentId,
                               @RequestParam("lessonId") Long lessonId
                               ) {
        Student student = studentRepository.findById(studentId).get();
        Lesson lesson = lessonRepository.findById(lessonId).get();
        lesson.setQuota(lesson.getQuota()-1);
        if (lesson.getQuota()<1){
            return "courses/error";
        }
        else{
        Course course = Course.createCourse(student,lesson);

        Course savedCourse = courseRepository.save(course);
        return "redirect:/courses";}}


    @GetMapping(value = "/courses/{id}")
    public String updateShowCourse(Model model,@PathVariable ("id") Long id){
        Course course = courseRepository.findById(id).orElseThrow(()->new IllegalArgumentException("error"));
        List<Student> students = studentRepository.findAll();
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);
        CourseForm courseForm= new CourseForm();
        courseForm.setStudentId(course.getStudent());
        courseForm.setLessonId(course.getLesson());
        return "courses/courseForm";
    }
    @PostMapping(value = "/courses/{id}")
    public String updateCourses(CourseForm form,@PathVariable ("id") Long id,Model model) {
        Course course = courseRepository.findById(id).orElseThrow(()->new IllegalArgumentException("error"));
        Lesson lesson=course.getLesson();
        lesson.setQuota(lesson.getQuota()+1);
        Lesson lesson1=lessonRepository.findById(form.getLessonId().getId()).orElseThrow(()->new IllegalArgumentException("error"));
        lesson1.setQuota(lesson1.getQuota()-1);
        course.setStudent(form.getStudentId());
        course.setLesson(form.getLessonId());
        courseRepository.save(course);
        model.addAttribute("courses",courseRepository.findAll());
        return "redirect:/courses";
    }

    @GetMapping(value = "/courses/delete/{id}")
    public String deleteCourses (@PathVariable ("id") Long id,Model model){
        Course course=courseRepository.findById(id).orElseThrow(()->new IllegalArgumentException("invalid lesson id "+ id));
        Lesson lesson=course.getLesson();
        lesson.setQuota(lesson.getQuota()+1);
        courseRepository.delete(course);
        model.addAttribute("courses",courseRepository.findAll());
        return "redirect:/courses";
    }
    @GetMapping("/courses")
    public String courseList(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "courses/courseList";
    }

}