package org.khai.learning.api.controller;

import org.khai.learning.service.browsing.BrowsingService;
import org.khai.learning.service.model.ThemeListModel;
import org.khai.learning.service.model.ThemeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrowsingController {

    private final BrowsingService browsingService;

    @Autowired
    BrowsingController(BrowsingService browsingService) {
        this.browsingService = browsingService;
    }

    @GetMapping("/theme")
    public ThemeListModel getThemeList() {
        return new ThemeListModel(browsingService.getThemeList());
    }

}
