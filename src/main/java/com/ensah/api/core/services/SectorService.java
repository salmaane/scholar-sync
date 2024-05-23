package com.ensah.api.core.services;

import com.ensah.api.core.dao.SectorDAO;
import com.ensah.api.core.models.Sector;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class SectorService extends GenericServiceImpl<Sector> {
    private final SectorDAO sectorDAO;
    public SectorService(SectorDAO dao) {
        super(dao);
        this.sectorDAO = dao;
    }

    @Override
    public Sector update(Long id, Sector newSector) {
        Optional<Sector> sector = findById(id);
        if(sector.isPresent()) {
            newSector.setId(id);
            return sectorDAO.save(newSector);
        }
        return null;
    }
}
