package org.threeDPortfolioGallery.records;

import org.threeDPortfolioGallery.workloads.Exhibition;

//TODO records
public record ExhibitionWithUserRecord(Exhibition exhibition, String user_name, String user_icon_url) {
    // Long id, String title, String thumbnail_url, Long user_id, String user_name,, Category category
}
