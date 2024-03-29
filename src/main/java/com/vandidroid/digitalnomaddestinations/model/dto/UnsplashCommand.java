package com.vandidroid.digitalnomaddestinations.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnsplashCommand {
    private String data;
    private Long locationId;
}
