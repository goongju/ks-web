package kr.ac.ks.app.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Setter
@Getter
public class LessonForm {
    private Long id;
    private String name;
    private int quota;
}
