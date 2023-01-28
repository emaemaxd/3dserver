package org.threeDPortfolioGallery.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.threeDPortfolioGallery.workloads.Room;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RoomRepo implements PanacheRepository<Room> {
}
