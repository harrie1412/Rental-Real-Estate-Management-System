package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.service.BuildingService;


@Service
public class BuildingServiceImpl implements BuildingService {
	//buildingDTO có thể hiểu là lấy các yêu cầu cần thiết cần hiển thị
	//buildingEntity có thể hiểu là lấy tổng các dữ liệu liên quan mà DTO cần
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingDTOConverter buildingDTOConverter;
	
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	
	@Override
	public List<BuildingDTO> findAll(Map<String,Object> params,List<String> typeCode) {
		// TODO Auto-generated method stub
		//bắt đầu filter, chắt lọc dữ liệu client
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		List<BuildingEntity> buildingEntities= buildingRepository.findAll(buildingSearchBuilder);// lấy tổng dữ liệu
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for(BuildingEntity item : buildingEntities) {//chắt lọc dữ liệu
			BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);//set các dữ liệu tự động
			result.add(building);
		}
		return result;
	}//hứng dũ liệu trả ra --> hàm trả
	
}