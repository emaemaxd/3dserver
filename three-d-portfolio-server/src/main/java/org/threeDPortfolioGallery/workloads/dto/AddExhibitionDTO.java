package org.threeDPortfolioGallery.workloads.dto;

import lombok.Data;
import org.threeDPortfolioGallery.workloads.Exhibit;
import org.threeDPortfolioGallery.workloads.Theme;

import java.util.List;

@Data
public class AddExhibitionDTO {
    String thumbnail_url;
    String title;

    // Theme theme;

    Long user_id;
    Long[] category_ids;

    AddExhibitDTO[] exhibits;
}
