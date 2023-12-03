package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Ad;

import java.util.List;

@Data
public class AdsDTO {
    private Integer count;
    private List<AdDTO> results;
}
