package com.kiki.route.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiki.route.model.PlanDto;
import com.kiki.route.model.PlanOrderDto;
import com.kiki.route.model.dao.PlanDao;
import com.kiki.tour.model.TourDto;
import com.kiki.tour.model.dao.TourDao;

@Service
public class PlanServiceImpl implements PlanService {
	public PlanDao planDao;
	public TourDao tourDao;

	@Autowired
	public PlanServiceImpl(PlanDao routeDao, TourDao tourDao) {
		this.planDao = routeDao;
		this.tourDao = tourDao;
	}

	@Override
	@Transactional
	public int insertRoute(PlanDto planDto, List<TourDto> tourList) {
		try {
			System.out.println("투어 리스트");
			System.out.println(tourList);
			System.out.println("입력할 계획");
			System.out.println(planDto);
			planDao.insertPlan(planDto);
			int seq = planDao.selectPlanSeq(planDto);
			System.out.println("플랜 번호 : "+seq );
			PlanOrderDto planOrder = new PlanOrderDto();
			planOrder.setPlanSeq(seq);
			for (int i = 0; i < tourList.size(); i++) {
				if (tourDao.selectTour(tourList.get(i)) == null) {
					System.out.println(tourList.get(i).getTitle()+"이 없어 추가함");
					tourDao.insertTour(tourList.get(i));
				}
				planOrder.setTourSeq(((TourDto)tourDao.selectTour(tourList.get(i))).getSeq());
				planOrder.setOrder(i);
				planOrder.setDistance(tourList.get(i).getDistance());
				planOrder.setTime(tourList.get(i).getTime());
				System.out.println(planOrder);
				planDao.insertPlanOrder(planOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}