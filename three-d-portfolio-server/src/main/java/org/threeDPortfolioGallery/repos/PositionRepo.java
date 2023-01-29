package org.threeDPortfolioGallery.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.threeDPortfolioGallery.workloads.Position;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PositionRepo implements PanacheRepository<Position> { }
