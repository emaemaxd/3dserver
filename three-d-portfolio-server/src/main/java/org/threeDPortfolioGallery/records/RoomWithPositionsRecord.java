package org.threeDPortfolioGallery.records;

import org.threeDPortfolioGallery.workloads.Position;

import java.util.Collection;
import java.util.List;

public record RoomWithPositionsRecord(Long id, String name, String room_wall_url, String wall_mat_url, String room_floor_url, String floor_mat_url, Collection<Position> positions) {
}
