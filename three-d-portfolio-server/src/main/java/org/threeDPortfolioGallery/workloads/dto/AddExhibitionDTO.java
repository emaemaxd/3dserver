package org.threeDPortfolioGallery.workloads.dto;

import lombok.Data;
import org.threeDPortfolioGallery.workloads.Exhibit;
import org.threeDPortfolioGallery.workloads.Theme;

import java.util.List;

@Data
public class AddExhibitionDTO {
    /*
    {
      "thumbnail_url": "string",
      "title": "string",
      "user_id": -1,
      "category_ids": [
        -1, -2
      ]
    }
     */
    String thumbnail_url;
    String title;

    // Theme theme;

    Long user_id;
    Long[] category_ids;

    AddExhibitDTO[] exhibits;

    /*
    {
  "exhibits": [
    {
      "url": "string",
      "data_type": "string",
      "title": "string",
      "description": "string",
      "theme": {
        "name": "string",
        "mat_object": "string",
        "mat_inside": "string",
        "light_intensity": 0,
        "model_path": "string",
        "is_exhibit": true,
        "exhibitions": [
          "string"
        ]
      },
      "position": {
        "id": 0,
        "x": 0,
        "y": 0,
        "is_wall": true,
        "room": "string",
        "exhibit": "string"
      }
    }
  ],
  "theme": {
    "id": 0,
    "name": "string",
    "mat_object": "string",
    "mat_inside": "string",
    "light_intensity": 0,
    "model_path": "string",
    "is_exhibit": true,
    "exhibitions": [
      "string"
    ]
  },
  "rooms": [
    {
      "id": 0,
      "name": "string",
      "positions_amount": 0,
      "room_url": "string",
      "exhibition": "string",
      "positions": [
        {
          "id": 0,
          "x": 0,
          "y": 0,
          "is_wall": true,
          "room": "string",
          "exhibit": "string"
        }
      ]
    }
  ]
}*/
}
