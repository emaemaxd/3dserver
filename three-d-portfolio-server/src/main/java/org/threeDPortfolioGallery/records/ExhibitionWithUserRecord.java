package org.threeDPortfolioGallery.records;

import org.threeDPortfolioGallery.workloads.Exhibition;

public record ExhibitionWithUserRecord(Exhibition exhibition, String user_name, String user_icon_url) {
}
