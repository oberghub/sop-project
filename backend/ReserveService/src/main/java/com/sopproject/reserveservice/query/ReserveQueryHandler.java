package com.sopproject.reserveservice.query;

import com.sopproject.reserveservice.core.ReserveEntity;
import com.sopproject.reserveservice.core.data.ReserveRepository;
import com.sopproject.reserveservice.query.rest.FindReserveQuery;
import com.sopproject.reserveservice.query.rest.ReserveRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReserveQueryHandler {

    private final ReserveRepository reserveRepository;
    public ReserveQueryHandler(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    @QueryHandler
    List<ReserveRestModel> findReserves(FindReserveQuery query){
        List<ReserveRestModel> reserveRestModels = new ArrayList<>();
        List<ReserveEntity> storedReserves = reserveRepository.findAll();
        for (ReserveEntity reserveEntity: storedReserves){
            ReserveRestModel reserveRestModel = new ReserveRestModel();
            BeanUtils.copyProperties(reserveEntity, reserveRestModel);
            reserveRestModels.add(reserveRestModel);
        }
        return reserveRestModels;
    }
}
