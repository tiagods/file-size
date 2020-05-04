package com.tiagods.scanner.model;

import com.tiagods.scanner.validation.PathLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PathJob {
    @NotNull
    @PathLocation
    private String path;
    @NotNull
    @Min(0)
    private int size;
}
